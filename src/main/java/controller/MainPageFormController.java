package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageFormController {

    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnStaff;

    @FXML
    private ImageView imgAdmin;

    @FXML
    private ImageView imgLogo;

    @FXML
    private ImageView imgStaff;


    Stage AdminLoginPageStage = new Stage();
    @FXML
    void btnAdminOnAction(ActionEvent event) {
        try {
            AdminLoginPageStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminLoginPage.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AdminLoginPageStage.show();

    }

    Stage StaffLoginPageStage = new Stage();
    @FXML
    void btnStaffOnAction(ActionEvent event) {
        try {
            StaffLoginPageStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/StaffLoginPage.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StaffLoginPageStage.show();
    }

}
