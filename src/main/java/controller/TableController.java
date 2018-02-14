package controller;

import dao.JoinDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.JoinTable;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Andrey Volinskiy on 12.02.2018.
 */
public class TableController implements Initializable {
    @FXML
    public TableColumn<Object, Object> columnId;
    @FXML
    public TableColumn<Object, Object> columnOrder;
    @FXML
    public TableColumn<Object, Object> columnClientName;
    @FXML
    public TableColumn<Object, Object> columnClientPhone;
    @FXML
    public TableView<JoinTable> table;
    @FXML
    public TableColumn<Object, Object> columnCompleted;
    @FXML
    public Button btnAdmin;

    private ObservableList<JoinTable> observableList = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    private void init() {
        btnAdmin.setOnAction(event -> goToAdminWindow(event));
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnOrder.setCellValueFactory(new PropertyValueFactory<>("orderName"));
        columnCompleted.setCellValueFactory(new PropertyValueFactory<>("completed"));
        columnClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        columnClientPhone.setCellValueFactory(new PropertyValueFactory<>("clientPhone"));
        load();
    }

    private void goToAdminWindow(ActionEvent event) {
        final FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/password.fxml"));
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Stage stage = new Stage();
        Scene value = new Scene(parent);
        stage.setScene(value);
        stage.initModality(Modality.WINDOW_MODAL);
        Window window = ((Node) event.getSource()).getScene().getWindow();
        stage.initOwner(window);
        stage.show();
    }

    protected void load() {
        JoinDao joinDao = new JoinDao();
        List<JoinTable> list = joinDao.getAll();
        observableList.clear();
        observableList.addAll(list);
        table.setItems(observableList);
    }
}

