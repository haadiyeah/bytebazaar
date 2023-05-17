module bytebazaar {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.microsoft.sqlserver.jdbc;
    requires java.sql;

    opens bytebazaarUI to javafx.fxml;
    opens bytebazaar to javafx.fxml;

    exports bytebazaar;
    exports bytebazaarUI;

}
