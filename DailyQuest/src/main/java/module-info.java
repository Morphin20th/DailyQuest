module com.example.dailyquest {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dailyquest to javafx.fxml;
    exports com.example.dailyquest;
    exports com.example.dailyquest.habits;
    opens com.example.dailyquest.habits to javafx.fxml;
    exports com.example.dailyquest.daily;
    opens com.example.dailyquest.daily to javafx.fxml;
    exports com.example.dailyquest.tasks;
    opens com.example.dailyquest.tasks to javafx.fxml;
}