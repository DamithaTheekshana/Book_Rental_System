package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.RentalBookHistory;
import service.HistoryPageService;
import service.Impl.HistoryPageServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HistoryPageFormController implements Initializable {

    ObservableList<RentalBookHistory> rentalBookHistories = FXCollections.observableArrayList();
    HistoryPageService historyPageService = new HistoryPageServiceImpl();

    @FXML
    private Button btnClear;

    @FXML
    private TableColumn<?, ?> colBookID;

    @FXML
    private TableColumn<?, ?> colCustID;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private TableColumn<?, ?> colFineAmount;

    @FXML
    private TableColumn<?, ?> colOverDays;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colRentalDate;

    @FXML
    private TableColumn<?, ?> colRentalID;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableView<RentalBookHistory> tblHistory;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        rentalBookHistories.clear();
        historyPageService.clearData();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colRentalID.setCellValueFactory(new PropertyValueFactory<>("Rental_ID"));
        colBookID.setCellValueFactory(new PropertyValueFactory<>("Book_ID"));
        colCustID.setCellValueFactory(new PropertyValueFactory<>("Cust_ID"));
        colRentalDate.setCellValueFactory(new PropertyValueFactory<>("Rental_Date"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("Due_Date"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("Return_Date"));
        colOverDays.setCellValueFactory(new PropertyValueFactory<>("Overdue_Days"));
        colFineAmount.setCellValueFactory(new PropertyValueFactory<>("Fine_Amount"));

        try {
            getAllHistory();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getAllHistory() throws SQLException {
        rentalBookHistories.clear();
        rentalBookHistories = historyPageService.getAllHistory();
        tblHistory.setItems(rentalBookHistories);
    }
}
