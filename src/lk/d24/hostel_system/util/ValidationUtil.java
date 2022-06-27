package lk.d24.hostel_system.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ValidationUtil {
    public static LinkedHashMap<JFXTextField, Pattern> getPatternMap() {
        return new LinkedHashMap<>();
    }

    public static Object validate(LinkedHashMap<JFXTextField, Pattern> map, JFXButton btn) {
        for (JFXTextField key : map.keySet()) {
            Pattern pattern = map.get(key);
            if (!pattern.matcher(key.getText()).matches()) {
                addError(key, btn);
                return key;
            }
            removeError(key, btn);
        }
        return true;
    }

    private static void removeError(JFXTextField txtField, JFXButton btn) {
        txtField.setStyle("-fx-text-fill: #001219");
        btn.setDisable(false);
    }

    private static void addError(JFXTextField txtField, JFXButton btn) {
        if (txtField.getText().length() > 0) {
            txtField.setStyle("-fx-text-fill: red");
        }
        btn.setDisable(true);
    }
}
