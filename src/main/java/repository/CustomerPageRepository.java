package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerPageRepository {

    ResultSet getAllCustomers() throws SQLException;

    void addCustomer(String custId, String name, String phone, String email, String address);

    void deleteCustomer(String custId);

    void updateCustomer(String custId, String name, String phone, String email, String address);
}
