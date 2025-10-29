package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.dto.Book;

import java.io.IOException;

public class StaffDashboardFormController {

    @FXML
    private Button btnBooks;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnRentals;

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

    @FXML
    void btnCustomersOnAction(ActionEvent event) {

    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {

    }

    @FXML
    void btnRentalsOnActon(ActionEvent event) {

    }

}
