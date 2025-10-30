package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardFormController {

    @FXML
    private Button btnBooks;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnRentals;

    @FXML
    private Button btnReports;

    @FXML
    private Label lblTotalBooks;

    @FXML
    private Label lblTotalCustomers;

    Stage BookPageStage = new Stage();
    @FXML
    void btnBooksOnAction(ActionEvent event) {
        try {
            BookPageStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/BookPage.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BookPageStage.show();

    }

    Stage customerPageStage = new Stage();
    @FXML
    void btnCustomersOnAction(ActionEvent event) throws IOException {
         customerPageStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CustomerPage.fxml"))));
         customerPageStage.show();
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {

    }

    Stage rentalPageStage = new Stage();
    @FXML
    void btnRentalsOnActon(ActionEvent event) {
        try {
            rentalPageStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/RentalPage.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        rentalPageStage.show();
    }

    @FXML
    void btnRepotsOnAction(ActionEvent event) {

    }

}
