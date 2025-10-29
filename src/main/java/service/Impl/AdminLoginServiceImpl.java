package service.Impl;

import repository.AdminLoginRepository;
import repository.Impl.AdminLoginRepositortImpl;
import service.AdminLoginService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginServiceImpl implements AdminLoginService {

    AdminLoginRepository adminLoginRepository = new AdminLoginRepositortImpl();

    @Override
    public boolean chekUser(String username, String password, String JobRole) throws SQLException {

          ResultSet resultSet = adminLoginRepository.getUser(username);

          if (resultSet.next()){
              String user = resultSet.getString("User_ID");
              String Pass = resultSet.getString("Password");
              String role = resultSet.getString("Role");

              return username.equals(user) && password.equals(Pass) && JobRole.equals(role);
          }

          return false;
    }
}
