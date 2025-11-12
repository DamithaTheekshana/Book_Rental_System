package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repository.Impl.StaffLoginRepositoryImpl;
import service.Impl.StaffLoginServiceImpl;
import service.StaffLoginService;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class StaffLoginPageFormController {

    StaffLoginService staffLoginService = new StaffLoginServiceImpl();

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws SQLException {

        String username = txtUsername.getText();
        String Password = txtPassword.getText();
        String role     = "Staff";

        boolean isOk = staffLoginService.chekeUser(username , Password , role);

        if (isOk){
            System.out.println(true);
            Stage StaffDashboardStage = new Stage();
            try {
                StaffDashboardStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/StaffDashboard.fxml"))));
                StaffDashboardStage.setResizable(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            StaffDashboardStage.show();
        }else {
            System.out.println(false);
            JOptionPane.showMessageDialog(null, "Invalid login! please try again", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
