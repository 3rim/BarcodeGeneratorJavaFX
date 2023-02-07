package org.example.controller;

import javafx.fxml.FXML;
import org.example.App;

import java.io.IOException;

public class StartViewController {

    @FXML
    private void switchToInputView() throws IOException {
        App.setRoot("inputView");
    }
}
