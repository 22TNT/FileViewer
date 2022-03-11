module com.example.fileviewer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fileviewer to javafx.fxml;
    exports com.example.fileviewer;
}