package repository.Impl;

import db.DBConnection;
import repository.StaffLoginRepository;
import service.StaffLoginService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffLoginRepositoryImpl implements StaffLoginRepository {

    @Override
    public ResultSet getUser(String username) throws SQLException {
        String SQL = "SELECT * FROM User WHERE User_ID = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(SQL);
        pstm.setObject(1,username);
        ResultSet resultSet = pstm.executeQuery();
        return resultSet;
    }
}
