package org.example.controller;

import javafx.fxml.FXML;
import org.example.App;

import java.io.IOException;

public class InputViewController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
