package service.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.Customer;
import repository.CustomerPageRepository;
import repository.Impl.CustomerPageRepositoryImpl;
import service.CustomerPageService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerPageServiceImpl implements CustomerPageService {

    ObservableList<Customer> customers = FXCollections.observableArrayList();
    CustomerPageRepository customerPageRepository = new CustomerPageRepositoryImpl();

    @Override
    public ObservableList<Customer> getAllCustomers() throws SQLException {
        ResultSet resultSet = customerPageRepository.getAllCustomers();
        while (resultSet.next()){
            customers.add(new Customer(
                    resultSet.getString("Cust_ID"),
                    resultSet.getString("Name"),
                    resultSet.getString("Phone"),
                    resultSet.getString("Email"),
                    resultSet.getString("Address")
            ));
        }
        return customers;
    }

    @Override
    public void addCustomer(String custId, String name, String phone, String email, String address) {
        customerPageRepository.addCustomer(custId, name, phone, email, address);
    }

    @Override
    public void deleteCustomer(String custId) {
        customerPageRepository.deleteCustomer(custId);
    }

    @Override
    public void updateCustomer(String custId, String name, String phone, String email, String address) {
        customerPageRepository.updateCustomer(custId, name, phone, email, address);
    }
}
