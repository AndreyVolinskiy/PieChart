package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Andrey Volinskiy on 13.02.2018.
 */
public class PasswordController implements Initializable {
    @FXML
    public Button btnOk;
    @FXML
    public PasswordField txtPassword;
    @FXML
    public Text txtField;
    @FXML
    public Button btnExit;
    private static final String PASSWORD = "password";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnOk.setOnAction(this::checkPassword);
        btnExit.setOnAction(this::exit);
    }

    private void exit(ActionEvent event) {
        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
        stage.setOnHiding(event1 -> new TableController().load());
    }

    private void checkPassword(ActionEvent event) {
        String password = new String();
        password = txtPassword.getText();
        while (true) {
            if (password.equals(PASSWORD)) {
                final FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/pieChart.fxml"));
                Parent parent = null;
                try {
                    parent = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage1 = (Stage) btnOk.getScene().getWindow();
                stage1.close();

                final Stage stage = new Stage();
                Scene value = new Scene(parent);
                stage.setScene(value);
                stage.initModality(Modality.WINDOW_MODAL);
                Window window = ((Node) event.getSource()).getScene().getWindow();
                stage.initOwner(window);
                stage.show();
                break;
            } else {
                txtField.setText("You are not admin, get out of here or try again");
            }
            break;
        }
    }
}


