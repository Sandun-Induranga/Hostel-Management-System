package lk.d24.hostel_system.controller;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class MainFormController {
    public Label lblMenu;
    public ImageView imgStudent;
    public ImageView imgRoom;
    public ImageView imgReservation;
    public AnchorPane root;
    public ImageView imgSettings;

    @FXML
    private void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            switch (icon.getId()) {
                case "imgStudent":
                    lblMenu.setText("Manage Students");
                    break;
                case "imgRoom":
                    lblMenu.setText("Manage Rooms");
                    break;
                case "imgReservation":
                    lblMenu.setText("Manage Reservations");
                    break;
                case "imgSettings":
                    lblMenu.setText("Settings");
                default:
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.valueOf("#00AD8A"));
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    @FXML
    private void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblMenu.setText("Welcome");
        }
    }

    @FXML
    private void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent root = null;

            switch (icon.getId()) {
                case "imgStudent":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/d24/hostel_system/view/manage-students-form.fxml"));
                    break;
                case "imgRoom":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/d24/hostel_system/view/manage-rooms-form.fxml"));
                    break;
                case "imgReservation":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/d24/hostel_system/view/reserve-rooms-form.fxml"));
                    break;
                case "imgSettings":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/d24/hostel_system/view/user-settings-form.fxml"));
            }

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }
}
