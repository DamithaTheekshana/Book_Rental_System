package service;

import javafx.collections.ObservableList;
import model.dto.Customer;

import java.sql.SQLException;

public interface CustomerPageService {
    ObservableList<Customer> getAllCustomers() throws SQLException;


    void addCustomer(String custId, String name, String phone, String email, String address);

    void deleteCustomer(String custId);

    void updateCustomer(String custId, String name, String phone, String email, String address);
}
