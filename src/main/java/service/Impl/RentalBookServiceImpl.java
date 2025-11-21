package service.Impl;

import controller.RentalPageFormController;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.RentalBook;
import repository.BookPageRepository;
import repository.HistoryPageRepository;
import repository.Impl.BookPageRepositoryImpl;
import repository.Impl.HistoryRepositoryImpl;
import repository.Impl.RentalBookRepositoryImpl;
import repository.RentalBookRepository;
import service.BookPageService;
import service.HistoryPageService;
import service.RentalBookService;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RentalBookServiceImpl implements RentalBookService {

    ObservableList <RentalBook> rentalBooks = FXCollections.observableArrayList();
    RentalBookRepository rentalBookRepository = new RentalBookRepositoryImpl();
    BookPageService bookPageService = new BookPageServiceImpl();
    HistoryPageService historyPageService = new HistoryPageServiceImpl();

    @Override
    public ObservableList<RentalBook> getAllRentalBooks() throws SQLException {
        ResultSet resultSet = rentalBookRepository.getAllRentalBooks();

        while (resultSet.next()){
            rentalBooks.add(new RentalBook(
                    resultSet.getString("Rental_ID"),
                    resultSet.getString("Book_ID"),
                    resultSet.getString("Cust_ID"),
                    resultSet.getDate("Rental_Date"),
                    resultSet.getDate("Due_Date"),
                    resultSet.getInt("Qty")
            ));
        }
        return rentalBooks;
    }

    @Override
    public void addRentalBook(String rentalId, String bookId, String custId, String rentalDate, String dueDate, int qty) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            boolean isAdded = rentalBookRepository.addRentakBook(rentalId, bookId, custId, rentalDate, dueDate, qty);

            if (isAdded){
                boolean isUpdated = bookPageService.updateBookQty(qty, bookId);

                if (isUpdated){
                    boolean AddedHistory = historyPageService.addHistory(bookId, custId, rentalDate, dueDate, qty);

                    if (AddedHistory) {
                        connection.commit();
                    }
                }
            }
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public void deleteRental(String deleteID, int qty, String bookId, String custId, String rentalDate, String dueDate) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            boolean isDeleteRental = rentalBookRepository.deleteRental(deleteID);

            if (isDeleteRental){
                boolean isAddedBookQty = bookPageService.addedBookQty(qty, bookId);

                if (isAddedBookQty) {
                    boolean isDeletedHistoryTbl = historyPageService.deleteHistoryTbl(bookId, custId, rentalDate, dueDate);
                    if (isDeletedHistoryTbl){
                        connection.commit();
                    }
                }
            }
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public void updateRentalBook(String rentalId, String bookId, String custId, String rentalDate, String dueDate, int qty) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            int oldQty = bookPageService.getOldRentalQty(rentalId);
            boolean isUpdate = rentalBookRepository.updateRentalBook(rentalId, bookId, custId, rentalDate, dueDate, qty);

            if (isUpdate) {
                int difference = qty - oldQty;

                if (difference < 0) {

                    boolean updateQty = bookPageService.updateRentalQty(bookId, qty, difference * -1);

                } else {
                    boolean isUpdated = bookPageService.updateBookQty(difference, bookId);

                }

                boolean addedHistory = historyPageService.addHistory(bookId, custId, rentalDate, dueDate, qty);

                if (addedHistory) {
                    connection.commit();

                } else {
                    connection.rollback();
                }
            } else {
                connection.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public ObservableList<RentalBook> searchRental(String rentalId) throws SQLException {
        ResultSet resultSet = rentalBookRepository.searchRental(rentalId);
        while (resultSet.next()){
            rentalBooks.add(new RentalBook(
                    resultSet.getString("Rental_ID"),
                    resultSet.getString("Book_ID"),
                    resultSet.getString("Cust_ID"),
                    resultSet.getDate("Rental_Date"),
                    resultSet.getDate("Due_Date"),
                    resultSet.getInt("Qty")
            ));
        }
        return rentalBooks;
    }

    @Override
    public void setReturn(String rentalId, String bookId, String custId, String rentalDate, String dueDate, int qty, String returnDate, int overdueDays, double fineAmount) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            if (fineAmount > 0) {
                JOptionPane.showMessageDialog(
                        null,
                        "⚠️ You have a pending fine of Rs. " + fineAmount + "\nPlease settle it at the counter.",
                        "Fine Alert",
                        JOptionPane.WARNING_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "✅ No fines! Thank you for returning the book on time.",
                        "Return Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

            boolean isDeleted = rentalBookRepository.returnRental(rentalId);

            if (isDeleted){
                boolean isAddedBookQty = bookPageService.addedBookQty(qty, bookId);

                if (isAddedBookQty){
                    boolean isupdateTblHistory = historyPageService.updateTblHistory(bookId, custId, returnDate, overdueDays, fineAmount);
                    if (isupdateTblHistory){
                        connection.commit();
                        JOptionPane.showMessageDialog(
                                null,
                                "✅ Book returned successfully!\nRecord updated in history.",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }
            }
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }finally {
            connection.setAutoCommit(true);
        }
    }
}
