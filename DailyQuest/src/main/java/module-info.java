module com.example.dailyquest {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dailyquest to javafx.fxml;
    exports com.example.dailyquest;
}