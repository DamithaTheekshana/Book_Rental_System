package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RentalBookRepository {
    ResultSet getAllRentalBooks() throws SQLException;
}
