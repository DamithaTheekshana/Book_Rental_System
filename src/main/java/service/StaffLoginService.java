package service;

import java.sql.SQLException;

public interface StaffLoginService {

    boolean chekeUser(String username, String password, String role) throws SQLException;
}
