package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface StaffLoginRepository {
    ResultSet getUser(String username) throws SQLException;
}
