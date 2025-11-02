package repository.Impl;

import db.DBConnection;
import repository.BookPageRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookPageRepositoryImpl implements BookPageRepository {
    @Override
    public ResultSet getAllCustomers() throws SQLException {

        String SQL = "SELECT * FROM book";

            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(SQL);
            ResultSet resultSet = pstm.executeQuery();

        return resultSet;
    }

    @Override
    public ResultSet getSearchedBook(String bookName) throws SQLException {
        String SQL = "SELECT * FROM book WHERE Title = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(SQL);
        pstm.setObject(1,bookName);
        ResultSet resultSet = pstm.executeQuery();
        return resultSet;
    }

    @Override
    public void addBook(String bookId, String title, String author, int qty, String language) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO book (Book_ID, Title, Author, Qty, Language) VALUES (?, ?, ?, ?, ?)");
            pstm.setObject(1,bookId);
            pstm.setObject(2,title);
            pstm.setObject(3,author);
            pstm.setObject(4,qty);
            pstm.setObject(5,language);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBook(String bookId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM book WHERE Book_ID = ?");
            pstm.setObject(1,bookId);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBook(String bookId, String title, String author, int qty, String language) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE book SET Book_ID=?, Title=?, Author=?, Qty=?, Language=? WHERE Book_ID=?");
            pstm.setObject(1,bookId);
            pstm.setObject(2,title);
            pstm.setObject(3,author);
            pstm.setObject(4,qty);
            pstm.setObject(5,language);
            pstm.setObject(6,bookId);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean updateBookQty(int qty, String bookId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE book SET Qty = Qty - ? WHERE Book_ID = ?");
            pstm.setObject(1,qty);
            pstm.setObject(2,bookId);
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean addedBookQty(int qty, String bookId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE book SET Qty = Qty + ? WHERE Book_ID = ?");
            pstm.setObject(1,qty);
            pstm.setObject(2,bookId);
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
