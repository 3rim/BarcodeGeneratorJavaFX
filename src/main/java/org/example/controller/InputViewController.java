package org.example.controller;

import javafx.fxml.FXML;
import org.example.App;

import java.io.IOException;

public class InputViewController {

    @FXML
    private void switchToStartView() throws IOException {
        App.setRoot("startView");
    }

    public void createLayout(String layoutType){
        //TODO Factory-Pattern?
        System.out.println(layoutType);
    }

    public void test(){
        System.out.println("data received");
    }
}
