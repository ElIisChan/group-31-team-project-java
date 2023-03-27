module src {
    requires javafx.controls;
    requires javafx.fxml;

    // requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.inventory to javafx.fxml;
    exports com.example.inventory;
}
