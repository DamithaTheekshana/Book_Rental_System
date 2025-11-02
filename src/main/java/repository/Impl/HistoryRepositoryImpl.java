package repository.Impl;

import db.DBConnection;
import repository.HistoryPageRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryRepositoryImpl implements HistoryPageRepository {
    @Override
    public ResultSet getAllHistories() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM rentalbookhistory");
        ResultSet resultSet = pstm.executeQuery();
        return resultSet;
    }

    @Override
    public boolean addHistory(String rentalId, String bookId, String custId, String rentalDate, String dueDate, int qty) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO rentalbookhistory(Rental_ID, Book_ID, Cust_ID, Rental_Date, Due_Date, Qty) Values (?, ?, ?, ?, ?, ?)");
            pstm.setObject(1,rentalId);
            pstm.setObject(2,bookId);
            pstm.setObject(3,custId);
            pstm.setObject(4,rentalDate);
            pstm.setObject(5,dueDate);
            pstm.setObject(6,qty);

            return  pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean updateTblHistory(String rentalId, String returnDate, int overdueDays, double fineAmount) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE rentalbookhistory SET Return_Date = ?, Overdue_Days = ?, Fine_Amount = ? WHERE Rental_ID = ?");
            pstm.setObject(1,returnDate);
            pstm.setObject(2,overdueDays);
            pstm.setObject(3,fineAmount);
            pstm.setObject(4,rentalId);

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void clearData() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM rentalbookhistory");
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
