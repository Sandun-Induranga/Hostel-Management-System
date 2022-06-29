package lk.d24.hostel_system.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.d24.hostel_system.bo.BOFactory;
import lk.d24.hostel_system.bo.custom.UserBO;
import lk.d24.hostel_system.dto.UserDTO;

import java.io.IOException;

public class UserSettingsFormController {
    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;
    public JFXPasswordField txtConfirm;
    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
    public AnchorPane context;

    public void initialize() {
        setUserData();
    }

    private void setUserData() {
        try {
            UserDTO user = userBO.getUser();
            txtUsername.setText(user.getUsername());
            txtPassword.setText(user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if (!txtPassword.getText().equals(txtConfirm.getText())) {
            new Alert(Alert.AlertType.WARNING, "Password not matched").show();
            return;
        }
        try {
            userBO.updateUser(new UserDTO(txtUsername.getText(), txtPassword.getText()));
            new Alert(Alert.AlertType.CONFIRMATION, "Updated.!").show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnHomeOnAction(MouseEvent mouseEvent) throws IOException {
        context.getChildren().clear();
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/d24/hostel_system/view/main-form.fxml"))));
    }
}
