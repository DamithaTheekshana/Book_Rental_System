package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.dto.Book;
import model.dto.Customer;
import service.BookPageService;
import service.Impl.BookPageServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BookPageFormController implements Initializable {

    ObservableList <Book> books = FXCollections.observableArrayList();
    BookPageService bookPageService = new BookPageServiceImpl();

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colBookID;

    @FXML
    private TableColumn<?, ?> colLanguage;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private ImageView imgIcon;

    @FXML
    private TableView<Book> tblBook;

    @FXML
    private TextField tctQty;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtBookID;

    @FXML
    private TextField txtLanguage;

    @FXML
    private TextField txtSearchbar;

    @FXML
    private TextField txtTitle;

    @FXML
    void btnAddOnAction(ActionEvent event) {
         String bookId   = txtBookID.getText();
         String title    = txtTitle.getText();
         String author   = txtAuthor.getText();
         int    qty      = Integer.parseInt(tctQty.getText());
         String language = txtLanguage.getText();

         bookPageService.addBook(bookId, title, author, qty, language);
         getAllBooks();
         clearBooks();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String bookId   = txtBookID.getText();

        bookPageService.deleteBook(bookId);
        getAllBooks();
        clearBooks();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String bookId   = txtBookID.getText();
        String title    = txtTitle.getText();
        String author   = txtAuthor.getText();
        int    qty      = Integer.parseInt(tctQty.getText());
        String language = txtLanguage.getText();

        bookPageService.updateBook(bookId, title, author, qty, language);
        getAllBooks();
        clearBooks();
    }

    @FXML
    void iconSearchOnAction(MouseEvent event) throws SQLException {
        String bookName = txtSearchbar.getText();
        books.clear();
        books = bookPageService.getSearchedBook(bookName);
        tblBook.setItems(books);

    }

    @FXML
    void txtSearchbarOnAction(ActionEvent event) throws SQLException {
         String bookName = txtSearchbar.getText();
         books.clear();
         books = bookPageService.getSearchedBook(bookName);
         tblBook.setItems(books);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colBookID.setCellValueFactory(new PropertyValueFactory<>("Book_ID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("Author"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colLanguage.setCellValueFactory(new PropertyValueFactory<>("Language"));

        getAllBooks();

        tblBook.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue != null){
                System.out.println(newValue);

                setSelectedValue(newValue);

            }
        });
    }

    private void getAllBooks(){
          books.clear();
          books = bookPageService.getAllBooks();
          tblBook.setItems(books);
    }

    private void setSelectedValue(Book selectedValue){
        txtBookID.setText(selectedValue.getBook_ID());
        txtTitle.setText(selectedValue.getTitle());
        txtAuthor.setText(selectedValue.getAuthor());
        tctQty.setText(String.valueOf(selectedValue.getQty()));
        txtLanguage.setText(selectedValue.getLanguage());

    }

    private void clearBooks(){
         txtBookID.setText(null);
         txtTitle.setText(null);
         txtAuthor.setText(null);
         tctQty.setText(null);
         txtLanguage.setText(null);
    }
}
