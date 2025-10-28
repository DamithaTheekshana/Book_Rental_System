package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.AdminLoginService;
import service.Impl.AdminLoginServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

public class AdminLoginPageFormController {

    AdminLoginService adminLoginService = new AdminLoginServiceImpl();

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws SQLException {

        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String JobRole  = "Admin";

        boolean isOk = adminLoginService.chekUser(username , password , JobRole);

        if (isOk){
            System.out.println(true);
            Stage AdminDashboardStage = new Stage();
            try {
                AdminDashboardStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminDashboard.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            AdminDashboardStage.show();
        }else {
            System.out.println(false);
        }
    }

}
