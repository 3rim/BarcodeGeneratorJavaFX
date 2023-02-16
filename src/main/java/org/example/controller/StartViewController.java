package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.example.App;
import org.example.utility.TableLayoutEnum;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartViewController  implements Initializable {

    private InputViewController inputViewController;


    private void switchToInputView()  throws IOException {
        App.setRoot("inputView");
    }

    @Deprecated
    @FXML
    private void handleButtonClick(ActionEvent actionEvent) throws IOException {
        String text = ((Button)actionEvent.getSource()).getText();
        inputViewController.createLayout(text);
        switchToInputView();
    }

    @FXML
    private void averyA4Button() throws IOException {
        inputViewController.createLayout(TableLayoutEnum.AVERY_A4);
        switchToInputView();
    }

    @FXML
    private void lyrecoA4Button() throws IOException {
        inputViewController.createLayout(TableLayoutEnum.LYRECO_A4);
        switchToInputView();
    }

    /**
     * TODO: Is called everytime startView is switched to! Why so?
     * @param url -
     * @param resourceBundle -
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("inputView.fxml"));
            loader.load();
            inputViewController = loader.getController();
            inputViewController.test();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
