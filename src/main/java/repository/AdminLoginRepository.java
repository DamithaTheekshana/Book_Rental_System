package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface AdminLoginRepository {

    ResultSet getUser(String username) throws SQLException;
}
