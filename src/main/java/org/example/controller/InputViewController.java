package org.example.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.App;
import org.example.model.ITableLayout;
import org.example.model.TableFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class InputViewController {
    private static String FILE ="src/main/resources/FirstPdf.pdf";

    @FXML
    private ListView<String> listView;
    @FXML
    private TextField inputField;
    @FXML
    private CheckBox checkBox;

    private final TableFactory tableFactory = new TableFactory();
    //TODO a: If tableLayout is not static tableLayout becomes null, why?
    private static ITableLayout tableLayout;
    private boolean toBarcode = false;


    @FXML
    private void switchToStartView() throws IOException {
        App.setRoot("startView");
    }

    @FXML
    private void addButton(){
        if(!inputField.getText().isEmpty())
        {
            listView.getItems().add(inputField.getText());
            inputField.clear();
        }
    }

    @FXML
    private void onEnter(ActionEvent event){
        addButton();
        System.out.println(this);
    }

    @FXML
    private void setCheckBox(ActionEvent event) {
        toBarcode = checkBox.isSelected();
    }

    @FXML
    private void generatePDF()  {
        try {
            //TODO a: If tableLayout is not static tableLayout becomes null, why?

            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName("newFile.pdf");
            fileChooser.setInitialDirectory(new File("C:\\Users\\erim\\Desktop")); //TODO:
            File file = fileChooser.showSaveDialog(new Stage());

            Document document = tableLayout.getDocument();
            PdfPTable table = tableLayout.getTable();
            PdfWriter pdfWriter = PdfWriter.getInstance(document,new FileOutputStream(file));
            document.open();
            PdfContentByte pdfContentByte = pdfWriter.getDirectContent();

            if(toBarcode){
                Barcode128 barcode128 = new Barcode128();
                barcode128.setCodeType(Barcode128.CODE128);
                for (String data:listView.getItems())
                {
                    barcode128.setCode(data);
                    Image code128Img =barcode128.createImageWithBarcode(pdfContentByte, null, null);
                    table.addCell(code128Img);
                }
            }
            else {
                listView.getItems().forEach(table::addCell);
            }

            //Fill the row if there are less cells than columns. Otherwise no pages exception is thrown
            if(listView.getItems().size() <4)
                table.completeRow();

            document.add(table);
            document.close();
        }
        catch (FileNotFoundException | DocumentException e){
            e.printStackTrace();
        }
    }

    public void createLayout(String layoutType){
        tableLayout = tableFactory.createTable(layoutType);
    }

    public void test(){
        System.out.println("startViewController bound to inputViewController ");
    }
}
