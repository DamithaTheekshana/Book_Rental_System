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
    public boolean addHistory(String bookId, String custId, String rentalDate, String dueDate, int qty) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO rentalbookhistory(Book_ID, Cust_ID, Rental_Date, Due_Date, Qty) Values (?, ?, ?, ?, ?)");

            pstm.setObject(1,bookId);
            pstm.setObject(2,custId);
            pstm.setObject(3,rentalDate);
            pstm.setObject(4,dueDate);
            pstm.setObject(5,qty);

            return  pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean updateTblHistory(String bookId, String custId, String returnDate, int overdueDays, double fineAmount) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE rentalbookhistory SET Return_Date = ?, Overdue_Days = ?, Fine_Amount = ? WHERE Book_ID = ? && Cust_ID = ?");
            pstm.setObject(1,returnDate);
            pstm.setObject(2,overdueDays);
            pstm.setObject(3,fineAmount);
            pstm.setObject(4,bookId);
            pstm.setObject(5,custId);

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
