module proyecto.ud3.sergio.y.manuel {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires atlantafx.base;
    requires com.fasterxml.jackson.databind;

    exports com.vaultapp to javafx.graphics, javafx.fxml;
}