package service;

import java.sql.SQLException;

public interface AdminLoginService {
    boolean chekUser(String username, String password, String JobRole) throws SQLException;
}
