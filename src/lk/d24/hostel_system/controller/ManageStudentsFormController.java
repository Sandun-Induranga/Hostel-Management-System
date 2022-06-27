package lk.d24.hostel_system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.d24.hostel_system.bo.BOFactory;
import lk.d24.hostel_system.bo.custom.StudentBO;
import lk.d24.hostel_system.dto.StudentDTO;
import lk.d24.hostel_system.util.ValidationUtil;
import lk.d24.hostel_system.view.tdm.StudentTM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class ManageStudentsFormController {
    public TableView<StudentTM> tblStudent;
    public JFXTextField txtStudentId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtTel;
    public JFXButton btnAddNew;
    public JFXButton btnSave;
    public JFXTextField txtDob;
    public JFXTextField txtGender;
    public JFXButton btnDelete;
    private final StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);
    private final LinkedHashMap<JFXTextField, Pattern> map = ValidationUtil.getPatternMap();

    public void initialize() {
        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("studentId"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));
        initUI();
        loadAllStudents();
        setValidationPatterns();
        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtStudentId.setText(newValue.getStudentId());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtTel.setText(newValue.getPhoneNumber());
                txtDob.setText(String.valueOf(newValue.getDob()));
                txtGender.setText(newValue.getGender());

                txtStudentId.setDisable(false);
                txtName.setDisable(false);
                txtAddress.setDisable(false);
                txtTel.setDisable(false);
                txtDob.setDisable(false);
                txtGender.setDisable(false);
            }
        });
    }

    private void setValidationPatterns() {
        Pattern idPattern = Pattern.compile("^(S)[0-9]{3}$");
        Pattern namePattern = Pattern.compile("^[A-z ]{3,15}$");
        Pattern addressPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern telNumberPattern = Pattern.compile("^(070|072|074|075|076|077|078)[0-9]{7}$");
        Pattern dobPattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        Pattern genderPattern = Pattern.compile("^[A-z ]{3,15}$");

        map.put(txtStudentId, idPattern);
        map.put(txtName, namePattern);
        map.put(txtAddress, addressPattern);
        map.put(txtTel, telNumberPattern);
        map.put(txtDob, dobPattern);
        map.put(txtGender, genderPattern);
    }

    public void textFieldsKeyReleasedOnAction(KeyEvent keyEvent) {
        ValidationUtil.validate(map, btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response = ValidationUtil.validate(map, btnSave);

            if (response instanceof JFXTextField) {
                JFXTextField textField = (JFXTextField) response;
                textField.requestFocus();
            } else if (response instanceof Boolean) {
                saveStudent();
            }
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        saveStudent();
    }

    private void saveStudent() {
        String id = txtStudentId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();
        LocalDate date = LocalDate.parse(txtDob.getText());
        String gender = txtGender.getText();

        if (btnSave.getText().equalsIgnoreCase("save")) {
            try {
                if (studentBO.saveStudent(new StudentDTO(id, name, address, tel, date, gender))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
                return;
            }
        } else {
            try {
                if (studentBO.updateStudent(new StudentDTO(id, name, address, tel, date, gender))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated.!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
                return;
            }
        }
        loadAllStudents();
        initUI();
    }

    private void loadAllStudents() {
        tblStudent.getItems().clear();
        ArrayList<StudentTM> studentTMS = new ArrayList<>();
        try {
            List<StudentDTO> allStudents = studentBO.getAllStudents();
            for (StudentDTO dto :
                    allStudents) {
                studentTMS.add(new StudentTM(dto.getStudentId(), dto.getName(), dto.getAddress(), dto.getPhoneNumber(), dto.getDob(), dto.getGender()));
            }
            tblStudent.getItems().addAll(studentTMS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        txtStudentId.clear();
        txtName.clear();
        txtAddress.clear();
        txtTel.clear();
        txtDob.clear();
        txtGender.clear();
        txtStudentId.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtTel.setDisable(true);
        txtDob.setDisable(true);
        txtGender.setDisable(true);
//        btnSave.setDisable(true);
//        btnDelete.setDisable(true);
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            if (studentBO.delete(txtStudentId.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted.!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }
        loadAllStudents();
        initUI();
    }

    public void btnAddNewOnAction(ActionEvent actionEvent) {
        txtStudentId.clear();
        txtName.clear();
        txtAddress.clear();
        txtTel.clear();
        txtDob.clear();
        txtGender.clear();
        txtStudentId.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtTel.setDisable(false);
        txtDob.setDisable(false);
        txtGender.setDisable(false);
        txtStudentId.requestFocus();
        btnSave.setDisable(true);
        btnSave.setText("Save");
        btnDelete.setDisable(true);
    }
}
