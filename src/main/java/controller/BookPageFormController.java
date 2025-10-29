package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class BookPageFormController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAsuthor;

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
    private TableView<?> tblBook;

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

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void iconSearchOnAction(MouseEvent event) {

    }

    @FXML
    void txtSearchbarOnAction(ActionEvent event) {

    }

}
