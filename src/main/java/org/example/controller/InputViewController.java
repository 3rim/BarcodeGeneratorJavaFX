package org.example.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.App;
import org.example.model.ITableLayout;
import org.example.model.TableFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class  InputViewController implements Initializable {
    private static String FILE ="src/main/resources/FirstPdf.pdf";

    @FXML
    private  ListView<String> listView;
    @FXML
    private TextField inputField;
    @FXML
    private CheckBox checkBox;

    private final TableFactory tableFactory = new TableFactory();
    //TODO a: If tableLayout is not static tableLayout becomes null, why?
    private static ITableLayout tableLayout;
    private boolean toBarcode = false;
    private ObservableList<String> items;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setEditable(true);
        listView.setCellFactory(TextFieldListCell.forListView());

        /*items = FXCollections.observableArrayList();
        listView.setItems(items);*/
    }

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
            /*items.add(inputField.getText());
            inputField.clear();*/

        }
    }

    @FXML
    private void onEnter(ActionEvent event){
        addButton();
    }

    @FXML
    private void removeData(ActionEvent event) {
        ObservableList<Integer> indices = listView.getSelectionModel().getSelectedIndices().sorted();

        for (int k = indices.size() - 1; k >= 0; k--)
        {
            listView.getItems().remove((int) indices.get(k));
        }
        listView.getSelectionModel().clearSelection();
    }

    @FXML
    private void setCheckBox(ActionEvent event) {
        toBarcode = checkBox.isSelected();
    }

    @FXML
    private void generatePDF()  {
        try {
            //TODO a: If tableLayout is not static tableLayout becomes null, why?

            //FileChooser settings
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialFileName("newFile.pdf");
            File file = fileChooser.showSaveDialog(new Stage());
            //No File/destination chosen
            if(file ==null)
                return;

            //IText Pdf settings
            Document document = tableLayout.getNewDocument();
            PdfPTable table = tableLayout.getNewTable();

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
            /*  Fill the row if there are less cells than columns. Otherwise no pages exception is thrown
                Furthermore; If a table has e.g 4 Columns but in the last row are less than 4 Cells filled with Data
                it would not fill that row with data -->therefore "completeRow()" fills empty Cells to it!
             */
            table.completeRow();

            //listView.getItems().clear();

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
