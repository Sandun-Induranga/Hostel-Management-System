package lk.d24.hostel_system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.d24.hostel_system.bo.BOFactory;
import lk.d24.hostel_system.bo.custom.ReserveRoomBO;
import lk.d24.hostel_system.dto.CustomDTO;
import lk.d24.hostel_system.dto.ReservationDTO;
import lk.d24.hostel_system.dto.RoomDTO;
import lk.d24.hostel_system.dto.StudentDTO;
import lk.d24.hostel_system.view.tdm.ReservationTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReserveRoomsFormController {
    public JFXComboBox<String> cmbStudentId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtPhoneNumber;
    public JFXComboBox<String> cmbRoomId;
    public JFXTextField txtType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQty;
    public Label lblResId;
    public JFXComboBox<String> cmbPayment;
    public TableView<ReservationTM> tblReservation;
    private final ReserveRoomBO reserveRoomBO = (ReserveRoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RESERVATION);
    public JFXComboBox<String> cmbFilter;
    public AnchorPane context;
    public JFXButton btnReserve;


    public void initialize() {
        tblReservation.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("resId"));
        tblReservation.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("studentId"));
        tblReservation.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblReservation.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("roomId"));
        tblReservation.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblReservation.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblReservation.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("key_money"));
        tblReservation.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("keyMoneyStatus"));
        cmbFilter.getItems().addAll("All", "Paid", "Unpaid");
        cmbFilter.setValue("All");
        loadAllReservations();
        loadAllStudentIds();
        loadAllRoomIds();
        cmbFilter.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            loadAllReservations();
        });
        cmbStudentId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            getSelectedStudent();
        });
        cmbRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            getSelectedRoom();
        });
        tblReservation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                lblResId.setText(newValue.getResId());
                cmbStudentId.setValue(newValue.getStudentId());
                cmbRoomId.setValue(newValue.getRoomId());
                cmbPayment.setValue(newValue.getKeyMoneyStatus());
                btnReserve.setText("Update");
            }
        });
        try {
            lblResId.setText(reserveRoomBO.generateNewReservationId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        cmbPayment.getItems().addAll("Paid", "Pending");
    }

    private void loadAllReservations() {
        tblReservation.getItems().clear();
        switch (cmbFilter.getValue()) {
            case "All":
                try {
                    List<CustomDTO> allReservations = reserveRoomBO.getAllReservations();
                    setTableData(allReservations);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "Paid":
                try {
                    List<CustomDTO> allReservations = reserveRoomBO.getPaidReservations();
                    setTableData(allReservations);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                try {
                    List<CustomDTO> allReservations = reserveRoomBO.getPendingReservations();
                    setTableData(allReservations);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void setTableData(List<CustomDTO> allReservations) {
        List<ReservationTM> reservationTMS = new ArrayList<>();
        for (CustomDTO dto :
                allReservations) {
            reservationTMS.add(new ReservationTM(dto.getResId(), dto.getStudentId(), dto.getName(), dto.getRoomId(), dto.getType(), dto.getDate(), dto.getKey_money(), dto.getKeyMoneyStatus()));
        }
        tblReservation.getItems().addAll(reservationTMS);
    }

    private void getSelectedRoom() {
        try {
            RoomDTO room = reserveRoomBO.findRoomById(cmbRoomId.getValue());
            txtType.setText(room.getType());
            txtKeyMoney.setText(String.valueOf(room.getKeyMoney()));
            txtQty.setText(String.valueOf(room.getQty()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getSelectedStudent() {
        try {
            StudentDTO student = reserveRoomBO.findStudentById(cmbStudentId.getValue());
            txtName.setText(student.getName());
            txtAddress.setText(student.getAddress());
            txtPhoneNumber.setText(student.getPhoneNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllStudentIds() {
        try {
            List<StudentDTO> students = reserveRoomBO.loadAllStudents();
            for (StudentDTO student :
                    students) {
                cmbStudentId.getItems().add(student.getStudentId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllRoomIds() {
        try {
            List<RoomDTO> students = reserveRoomBO.loadAllRooms();
            for (RoomDTO room :
                    students) {
                cmbRoomId.getItems().add(room.getRoomId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnReserveOnAction(ActionEvent actionEvent) {
        if (cmbPayment.getValue()==null) {
            new Alert(Alert.AlertType.WARNING, "All Fields Required.!").show();
            return;
        }
        if (btnReserve.getText().equalsIgnoreCase("Reserve")){
            try {
                reserveRoomBO.reserveRoom(lblResId.getText(), cmbStudentId.getValue(), cmbRoomId.getValue(), cmbPayment.getValue());
                new Alert(Alert.AlertType.INFORMATION, "Saved.!").show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong.!").show();
                e.printStackTrace();
            }
        }else {
            try {
                reserveRoomBO.updateReservation(lblResId.getText(), cmbStudentId.getValue(), cmbRoomId.getValue(), cmbPayment.getValue());
                new Alert(Alert.AlertType.INFORMATION, "Updated.!").show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong.!").show();
                e.printStackTrace();
            }
        }
        loadAllReservations();
    }

    public void btnHomeOnAction(MouseEvent mouseEvent) throws IOException {
        context.getChildren().clear();
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/d24/hostel_system/view/main-form.fxml"))));
    }
}
