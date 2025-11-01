package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.dto.RentalBook;
import service.Impl.RenataBookServiceImpl;
import service.RentalBookService;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class RentalPageFormController implements Initializable {

    ObservableList<RentalBook> rentalBooks = FXCollections.observableArrayList();
    RentalBookService rentalBookService = new RenataBookServiceImpl();

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReturn;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colBookID;

    @FXML
    private TableColumn<?, ?> colCustID;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colRentalDate;

    @FXML
    private TableColumn<?, ?> colRentalID;

    @FXML
    private ImageView imgIcon;

    @FXML
    private TableView<RentalBook> tblRental;

    @FXML
    private TextField txtBookID;

    @FXML
    private TextField txtCustID;

    @FXML
    private TextField txtDueDate;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtRentalDate;

    @FXML
    private TextField txtRentalID;

    @FXML
    private TextField txtReturnDate;

    @FXML
    private TextField txtSearchbar;

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {
        String rentalId = txtRentalID.getText();
        String bookId   = txtBookID.getText();
        String custId   = txtCustID.getText();
        String rentalDate = txtRentalDate.getText();
        String dueDate    = txtDueDate.getText();
        int qty = Integer.parseInt(txtQty.getText());

        rentalBookService.addRentalBook(rentalId, bookId, custId, rentalDate, dueDate, qty);
        rentalBooks.clear();
        clearRentalBook();
        getAllRentalBooks();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
         String deleteID = txtRentalID.getText();
         rentalBookService.deleteRental(deleteID);
         clearRentalBook();
         getAllRentalBooks();
    }

    @FXML
    void btnReturnOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String rentalId = txtRentalID.getText();
        String bookId   = txtBookID.getText();
        String custId   = txtCustID.getText();
        String rentalDate = txtRentalDate.getText();
        String dueDate    = txtDueDate.getText();
        int qty = Integer.parseInt(txtQty.getText());

        rentalBookService.updateRentalBook(rentalId, bookId, custId, rentalDate, dueDate, qty);
        clearRentalBook();
        getAllRentalBooks();
    }

    @FXML
    void iconSearchOnAction(MouseEvent event) throws SQLException {
        String rentalId = txtSearchbar.getText();
        rentalBooks.clear();
        rentalBooks = rentalBookService.searchRental(rentalId);
        tblRental.setItems(rentalBooks);
    }

    @FXML
    void txtSearchbarOnAction(ActionEvent event) throws SQLException {
        String rentalId = txtSearchbar.getText();
        rentalBooks.clear();
        rentalBooks = rentalBookService.searchRental(rentalId);
        tblRental.setItems(rentalBooks);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colRentalID.setCellValueFactory(new PropertyValueFactory<>("Rental_ID"));
        colBookID.setCellValueFactory(new PropertyValueFactory<>("Book_ID"));
        colCustID.setCellValueFactory(new PropertyValueFactory<>("Cust_ID"));
        colRentalDate.setCellValueFactory(new PropertyValueFactory<>("Rental_Date"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("Due_Date"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));

        try {
            getAllRentalBooks();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tblRental.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue != null){
                System.out.println(newValue);

                setSelectedValue(newValue);

            }
        });


    }

    private void getAllRentalBooks() throws SQLException {
        rentalBooks.clear();
        rentalBooks = rentalBookService.getAllRentalBooks();
        tblRental.setItems(rentalBooks);
    }

    private void setSelectedValue(RentalBook selectedValue){
        txtRentalID.setText(selectedValue.getRental_ID());
        txtBookID.setText(selectedValue.getBook_ID());
        txtCustID.setText(selectedValue.getCust_ID());
        txtRentalDate.setText(String.valueOf(selectedValue.getRental_Date()));
        txtDueDate.setText(String.valueOf(selectedValue.getDue_Date()));
        txtQty.setText(String.valueOf(selectedValue.getQty()));
    }

    private void clearRentalBook(){
        txtRentalID.setText(null);
        txtBookID.setText(null);
        txtCustID.setText(null);
        txtRentalDate.setText(null);
        txtDueDate.setText(null);
        txtQty.setText(null);
    }


}
