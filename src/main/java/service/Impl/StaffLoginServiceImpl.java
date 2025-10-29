package service.Impl;

import repository.Impl.StaffLoginRepositoryImpl;
import repository.StaffLoginRepository;
import service.StaffLoginService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffLoginServiceImpl implements StaffLoginService {

    StaffLoginRepository staffLoginRepository = new StaffLoginRepositoryImpl();

    @Override
    public boolean chekeUser(String username, String password, String role) throws SQLException {

        ResultSet resultSet = staffLoginRepository.getUser(username);

        while (resultSet.next()){
            String user = resultSet.getString("User_ID");
            String pass = resultSet.getString("Password");
            String Role = resultSet.getString("Role");

            return username.equals(user) && password.equals(pass) && role.equals(Role);
        }
        return false;
    }
}
