package com.fxcode.twitterjavafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowProfile implements Initializable {
    @FXML public AnchorPane anchorPane;
    @FXML public VBox tweetBox;
    @FXML public Label name;
    @FXML public javafx.scene.shape.Circle Circle;
    @FXML public Label bio;
    @FXML public Label username;
    @FXML public ScrollPane scrollPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Circle.setStroke(Color.SEAGREEN);
        Image image;
    }
}
