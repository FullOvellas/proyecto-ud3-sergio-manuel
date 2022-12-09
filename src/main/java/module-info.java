module proyecto.ud3.sergio.y.manuel {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires atlantafx.base;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.bootstrapicons;

    exports com.vaultapp to javafx.graphics, javafx.fxml;
    exports com.vaultapp.controller to javafx.fxml;
    exports com.vaultapp.model.entities to javafx.fxml;

    opens com.vaultapp.controller to javafx.fxml;
}