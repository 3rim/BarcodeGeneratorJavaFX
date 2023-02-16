package org.example.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.erim.components.ToggleSwitch;
import org.example.App;
import org.example.model.ITableLayout;
import org.example.model.TableFactory;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.opencsv.CSVReader;
import org.example.utility.TableLayoutEnum;

public class InputViewController implements Initializable {
    private static String FILE = "src/main/resources/FirstPdf.pdf";

    @FXML
    public Button clearBtn;
    @FXML
    public Label footer;
    @FXML
    private ListView<String> listView;
    @FXML
    private TextField inputField;
    @FXML
    private CheckBox checkBox;
    @FXML
    private ToggleSwitch toggleSwitch;

    private final TableFactory tableFactory = new TableFactory();
    //TODO a: If tableLayout is not static tableLayout becomes null, why?
    private static ITableLayout tableLayout;
    private boolean toBarcode = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setEditable(true);
        listView.setCellFactory(TextFieldListCell.forListView());

    }

    @FXML
    private void switchToStartView() throws IOException {
        App.setRoot("startView");
    }

    @FXML
    private void addButton() {
        if (!inputField.getText().isEmpty()) {
            listView.getItems().add(inputField.getText());
            inputField.clear();
        }
    }

    @FXML
    private void onEnter(ActionEvent event) {
        addButton();
    }

    @FXML
    private void removeData(ActionEvent event) {
        ObservableList<Integer> indices = listView.getSelectionModel().getSelectedIndices().sorted();

        for (int k = indices.size() - 1; k >= 0; k--) {
            listView.getItems().remove((int) indices.get(k));
        }
        listView.getSelectionModel().clearSelection();
    }

    @FXML
    private void clearList() {
        listView.getItems().clear();
    }

    /*@FXML
    private void setCheckBox(ActionEvent event) {
        toBarcode = checkBox.isSelected();
    }*/

    @FXML
    private void generatePDF() {
        try {
            //TODO a: If tableLayout is not static tableLayout becomes null, why?

            //FileChooser settings
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialFileName("newFile.pdf");
            File file = fileChooser.showSaveDialog(new Stage());
            //No File/destination chosen
            if (file == null)
                return;

            //IText Pdf settings
            Document document = tableLayout.getNewDocument();
            PdfPTable table = tableLayout.getNewTable();

            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            PdfContentByte pdfContentByte = pdfWriter.getDirectContent();

            tableLayout.add(listView.getItems(),pdfContentByte,toggleSwitch.switchedOnProperty().get());

            /*  Fill the row if there are fewer cells than column otherwise no pages exception is thrown
                Furthermore; If a table has e.g 4 Columns but in the last row are less than 4 Cells filled with Data
                it would not fill that row with data -->therefore "completeRow()" fills empty Cells to it!
             */
            //TODO refactor into Models?
            table.completeRow();

            document.add(table);
            document.close();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void readCSV() {
        try {
            //FileChooser settings
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(new Stage());
            //No File/destination chosen
            if (file == null)
                return;

            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(';')
                    .build();
            Reader reader = new FileReader(file);
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withCSVParser(parser)
                    .build();

            List<String[]> allData = csvReader.readAll();

            StringBuilder stringBuilder = new StringBuilder();
            for (String[] row : allData) {
                for (String cell : row) {
                    stringBuilder.append(cell).append(" ");
                }
                listView.getItems().add(stringBuilder.toString());
                //reset stringBuilder
                stringBuilder.setLength(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public void createLayout(String layoutType) {
        tableLayout = tableFactory.createTable("LAYOUT1");
    }
    public void createLayout(TableLayoutEnum layoutType) {
        tableLayout = tableFactory.createTable(layoutType);
    }

    public void test() {
        System.out.println("startViewController bound to inputViewController ");
    }


}
