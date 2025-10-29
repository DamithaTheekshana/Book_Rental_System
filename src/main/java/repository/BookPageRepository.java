package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BookPageRepository {
    ResultSet getAllCustomers() throws SQLException;

    ResultSet getSearchedBook(String bookName) throws SQLException;
}
