package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.model.ITableLayout;
import org.example.model.TableFactory;

import java.io.IOException;

public class InputViewController {

    private final TableFactory tableFactory = new TableFactory();
    private ITableLayout tableLayout;

    @FXML
    private ListView<String> listView;
    @FXML
    private TextField inputField;
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
    public void onEnter(ActionEvent ae){
        addButton();
    }

    public void createLayout(String layoutType){
        System.out.println(layoutType);
        tableLayout = tableFactory.createTable(layoutType);
    }

    public void test(){
        System.out.println("startViewController bound to inputViewController ");
    }
}
