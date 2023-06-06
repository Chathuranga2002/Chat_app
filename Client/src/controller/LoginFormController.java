package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginFormController {
    public static String name;

    @FXML
    private AnchorPane loginContext;

    @FXML
    private TextField txtName;
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    void joineOnAction(ActionEvent event) throws IOException {
//        name = txtName.getText();
//        loginContext.getChildren().clear();
//        Stage stage = (Stage) loginContext.getScene().getWindow();
//        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ClentForm.fxml"))));
        name = txtName.getText();

        Parent root = FXMLLoader.load(getClass().getResource("/view/ClentForm.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
        scene.setOnMousePressed(this::handleMousePressed);
        scene.setOnMouseDragged(this::handleMouseDragged);


        txtName.clear();
    }
    private void handleMousePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }
    private void handleMouseDragged(MouseEvent event) {
        Stage primaryStage = (Stage) ((Scene) event.getSource()).getWindow();
        primaryStage.setX(event.getScreenX() - xOffset);
        primaryStage.setY(event.getScreenY() - yOffset);
    }

}
