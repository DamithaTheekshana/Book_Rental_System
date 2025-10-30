package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.Customer;
import service.CustomerPageService;
import service.Impl.CustomerPageServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerPageFormController implements Initializable {

    CustomerPageService customerPageService = new CustomerPageServiceImpl();
    ObservableList<Customer> customers = FXCollections.observableArrayList();

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCust_ID;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCust_ID;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {
        String Cust_ID = txtCust_ID.getText();
        String Name    = txtName.getText();
        String Phone   = txtPhone.getText();
        String Email   = txtEmail.getText();
        String Address = txtAddress.getText();
        customerPageService.addCustomer(Cust_ID, Name, Phone, Email, Address);

        getAllCustomers();
        clearCustomer();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String Cust_ID = txtCust_ID.getText();
        customerPageService.deleteCustomer(Cust_ID);
        getAllCustomers();
        clearCustomer();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String Cust_ID = txtCust_ID.getText();
        String Name    = txtName.getText();
        String Phone   = txtPhone.getText();
        String Email   = txtEmail.getText();
        String Address = txtAddress.getText();
        customerPageService.updateCustomer(Cust_ID, Name, Phone, Email, Address);

        getAllCustomers();
        clearCustomer();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colCust_ID.setCellValueFactory(new PropertyValueFactory<>("Cust_ID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));

        try {
            getAllCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue != null){
                System.out.println(newValue);

                setSelectedValue(newValue);

            }
        });
    }

    private void setSelectedValue(Customer selectedValue) {
        txtCust_ID.setText(selectedValue.getCust_ID());
        txtName.setText(selectedValue.getName());
        txtPhone.setText(selectedValue.getPhone());
        txtEmail.setText(selectedValue.getEmail());
        txtAddress.setText(selectedValue.getAddress());
    }

    private void getAllCustomers() throws SQLException {
        customers.clear();
        customers = customerPageService.getAllCustomers();
        tblCustomer.setItems(customers);
    }

    private void clearCustomer(){
        txtCust_ID.setText(null);
        txtName.setText(null);
        txtPhone.setText(null);
        txtEmail.setText(null);
        txtAddress.setText(null);
    }

}
