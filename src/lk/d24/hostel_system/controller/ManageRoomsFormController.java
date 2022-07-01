package lk.d24.hostel_system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.d24.hostel_system.bo.BOFactory;
import lk.d24.hostel_system.bo.custom.RoomBO;
import lk.d24.hostel_system.dto.RoomDTO;
import lk.d24.hostel_system.util.ValidationUtil;
import lk.d24.hostel_system.view.tdm.RoomTM;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class ManageRoomsFormController {
    public TableView<RoomTM> tblRoom;
    public JFXTextField txtRoomId;
    public JFXTextField txtType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQty;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    private final RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);
    private final LinkedHashMap<JFXTextField, Pattern> map = ValidationUtil.getPatternMap();
    public AnchorPane context;

    public void initialize() {
        tblRoom.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("roomId"));
        tblRoom.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblRoom.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        tblRoom.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        initUI();
        loadAllRooms();
        setValidationPatterns();
        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtRoomId.setText(newValue.getRoomId());
                txtType.setText(newValue.getType());
                txtKeyMoney.setText(String.valueOf(newValue.getKeyMoney()));
                txtQty.setText(String.valueOf(newValue.getQty()));

                txtRoomId.setDisable(false);
                txtType.setDisable(false);
                txtKeyMoney.setDisable(false);
                txtQty.setDisable(false);
            }
        });
    }

    private void setValidationPatterns() {
        Pattern idPattern = Pattern.compile("^(RM-)[0-9]{4}$");
        Pattern typePattern = Pattern.compile("^[A-z0-9 ,-/]{3,20}$");
        Pattern keyMoneyPattern = Pattern.compile("^[1-9][0-9]*(.[0-9]{2})?$");
        Pattern qtyPattern = Pattern.compile("^[0-9]{1,}$");

        map.put(txtRoomId, idPattern);
        map.put(txtType, typePattern);
        map.put(txtKeyMoney, keyMoneyPattern);
        map.put(txtQty, qtyPattern);
    }

    private void loadAllRooms() {
        tblRoom.getItems().clear();
        ArrayList<RoomTM> roomTMS = new ArrayList<>();
        try {
            List<RoomDTO> allStudents = roomBO.getAllRooms();
            for (RoomDTO dto :
                    allStudents) {
                roomTMS.add(new RoomTM(dto.getRoomId(), dto.getType(), dto.getKeyMoney(), dto.getQty()));
            }
            tblRoom.getItems().addAll(roomTMS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void textFieldsKeyReleasedOnAction(KeyEvent keyEvent) {
        ValidationUtil.validate(map, btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response = ValidationUtil.validate(map, btnSave);

            if (response instanceof JFXTextField) {
                JFXTextField textField = (JFXTextField) response;
                textField.requestFocus();
            } else if (response instanceof Boolean) {
                saveRoom();
            }
        }
    }

    private void saveRoom() {
        String roomId = txtRoomId.getText();
        String type = txtType.getText();
        BigDecimal keyMoney = new BigDecimal(txtKeyMoney.getText());
        int qty = Integer.parseInt(txtQty.getText());
        if (btnSave.getText().equalsIgnoreCase("save")) {
            try {
                if (roomBO.saveRoom(new RoomDTO(roomId, type, keyMoney, qty))) {
                    new Alert(Alert.AlertType.INFORMATION, "Saved.!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong.!").show();
            }
        } else {
            try {
                if (roomBO.updateRoom(new RoomDTO(roomId, type, keyMoney, qty))) {
                    new Alert(Alert.AlertType.INFORMATION, "Updated.!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong.!").show();
            }
        }
        initUI();
        loadAllRooms();
    }

    private void initUI() {
        txtRoomId.clear();
        txtType.clear();
        txtKeyMoney.clear();
        txtQty.clear();
        txtRoomId.setDisable(true);
        txtType.setDisable(true);
        txtKeyMoney.setDisable(true);
        txtQty.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        saveRoom();
    }

    public void btnAddNewOnAction(ActionEvent actionEvent) {
        txtRoomId.clear();
        txtType.clear();
        txtKeyMoney.clear();
        txtQty.clear();
        txtRoomId.setDisable(false);
        txtType.setDisable(false);
        txtQty.setDisable(false);
        txtKeyMoney.setDisable(false);
        txtRoomId.requestFocus();
        btnSave.setDisable(true);
        btnSave.setText("Save");
        btnDelete.setDisable(true);
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            if (roomBO.deleteRoom(txtRoomId.getText())) {
                new Alert(Alert.AlertType.INFORMATION, "Deleted.!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong.!").show();
        }
        loadAllRooms();
    }
    public void btnHomeOnAction(MouseEvent mouseEvent) throws IOException {
        context.getChildren().clear();
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/d24/hostel_system/view/main-form.fxml"))));
    }
}
