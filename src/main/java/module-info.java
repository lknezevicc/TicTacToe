module hr.application.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.fontawesome;
    requires org.kordamp.ikonli.javafx;

    opens hr.application.tictactoe to javafx.fxml;
    exports hr.application.tictactoe;
}