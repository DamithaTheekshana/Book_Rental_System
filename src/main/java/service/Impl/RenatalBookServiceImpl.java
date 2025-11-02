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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RenatalBookServiceImpl implements RentalBookService {

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
            //        ---------- Add Rental Book ----------
            boolean isAdded = rentalBookRepository.addRentakBook(rentalId, bookId, custId, rentalDate, dueDate, qty);
            System.out.println("Add Rental Book : "+isAdded);

//        ---------- Update Book Qty ----------
            if (isAdded){
                boolean isUpdated = bookPageService.updateBookQty(qty, bookId);
                System.out.println("Update Book QTY : "+isUpdated);

//                ---------- Update History Table ----------
                if (isUpdated){
                    boolean AddedHistory = historyPageService.addHistory(bookId, custId, rentalDate, dueDate, qty);
                    System.out.println("Added History : "+AddedHistory );

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
    public void deleteRental(String deleteID) {
        rentalBookRepository.deleteRental(deleteID);
    }

    @Override
    public void updateRentalBook(String rentalId, String bookId, String custId, String rentalDate, String dueDate, int qty) {
        rentalBookRepository.updateRentalBook(rentalId, bookId, custId, rentalDate, dueDate, qty);
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
//        ---------- Rental Table ----------
            boolean isDeleted = rentalBookRepository.returnRental(rentalId);
            System.out.println("Return Ok : "+isDeleted);

//        ---------- Book Table ----------
            if (isDeleted){
                boolean isAddedBookQty = bookPageService.addedBookQty(qty, bookId);
                System.out.println("Added Book Qty : "+isAddedBookQty);

                if (isAddedBookQty){
                    boolean isupdateTblHistory = historyPageService.updateTblHistory(bookId, custId, returnDate, overdueDays, fineAmount);
                    System.out.println("Update Table History : "+isupdateTblHistory);

                    if (isupdateTblHistory){
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
}
