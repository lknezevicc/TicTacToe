package hr.application.alerts;

import javafx.scene.control.Alert;

public class InvalidInputAlert {

    public Alert make(String contextText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid input");
        alert.setHeaderText(null);
        alert.setContentText(contextText);

        return alert;
    }
}
