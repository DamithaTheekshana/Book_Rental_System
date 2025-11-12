package repository.Impl;

import db.DBConnection;
import repository.RentalBookRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RentalBookRepositoryImpl implements RentalBookRepository {
    @Override
    public ResultSet getAllRentalBooks() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM rentalbook");
        ResultSet resultSet = pstm.executeQuery();
        return resultSet;
    }

    @Override
    public boolean addRentakBook(String rentalId, String bookId, String custId, String rentalDate, String dueDate, int qty) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO rentalbook (Rental_ID, Book_ID, Cust_ID, Rental_Date, Due_Date, Qty) Values (?, ?, ?, ?, ?, ?)");
            pstm.setObject(1,rentalId);
            pstm.setObject(2,bookId);
            pstm.setObject(3,custId);
            pstm.setObject(4,rentalDate);
            pstm.setObject(5,dueDate);
            pstm.setObject(6,qty);

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean deleteRental(String deleteID) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM rentalbook WHERE Rental_ID = ?");
            pstm.setObject(1,deleteID);
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateRentalBook(String rentalId, String bookId, String custId, String rentalDate, String dueDate, int qty) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE rentalbook SET Rental_ID=?, Book_ID=?, Cust_ID=?, Rental_Date=?, Due_Date=?, Qty=? WHERE Rental_ID=?");
            pstm.setObject(1,rentalId);
            pstm.setObject(2,bookId);
            pstm.setObject(3,custId);
            pstm.setObject(4,rentalDate);
            pstm.setObject(5,dueDate);
            pstm.setObject(6,qty);
            pstm.setObject(7,rentalId);

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet searchRental(String rentalId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM rentalbook WHERE Rental_ID = ?");
        pstm.setObject(1,rentalId);
        ResultSet resultSet = pstm.executeQuery();
        return resultSet;
    }

    @Override
    public boolean returnRental(String rentalId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM rentalbook WHERE Rental_ID = ?");
            pstm.setObject(1,rentalId);

            return  pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
