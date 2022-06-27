package lk.d24.hostel_system.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.d24.hostel_system.bo.BOFactory;
import lk.d24.hostel_system.bo.custom.UserBO;
import lk.d24.hostel_system.dto.UserDTO;

import java.io.IOException;
import java.net.URL;

public class LoginFormController {
    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;
    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
    public AnchorPane loginContext;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        UserDTO user = null;
        try {
            user = userBO.findUser(txtUsername.getText());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please Enter Correct Login Details").show();
        }
        if (user.getPassword().equals(txtPassword.getText())) {
            URL resource = this.getClass().getResource("/lk/d24/hostel_system/view/main-form.fxml");
            Parent root = FXMLLoader.load(resource);
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) (this.loginContext.getScene().getWindow());
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            Platform.runLater(() -> primaryStage.sizeToScene());
        } else {
            new Alert(Alert.AlertType.ERROR, "Please Enter Correct Login Details").show();
        }
    }
}
