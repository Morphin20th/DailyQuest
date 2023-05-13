package com.example.dailyquest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Stage stage;
    private Scene scene;
    @FXML
    private SplitPane scenePane;
    @FXML
    private StackPane stackPane;
    @FXML
    private BorderPane scenePane1;
    @FXML
    private ListView<String> taskListView;
    @FXML
    private Label tabName;
    @FXML
    private AnchorPane taskPane;
    @FXML
    private AnchorPane habitsPane;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField profileNameField;
    @FXML
    private Label profileNameLabel;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label levelLabel;
    @FXML
    private AnchorPane profilePane;


    private int level = 1;
    private List<String> taskList = new ArrayList<>();



    private void updateProgress() {
        // Вычислить процент выполненных задач
        double completedTasksPercentage = (double) completedTasksCount() / (double) taskList.size();

        // Обновить индикатор прогресса
        progressBar.setProgress(completedTasksPercentage);
    }

    private int completedTasksCount() {
        int count = 0;
        // Подсчитать количество выполненных задач в списке задач
        for (String task : taskList) {
            // TODO: Проверить, выполнена ли задача
            // Если выполнена, увеличить счетчик
            if (isTaskCompleted(task)) {
                count++;
            }
        }
        return count;
    }

    private boolean isTaskCompleted(String task) {
        // TODO: Реализовать этот метод
        // Проверить, выполнена ли задача
        // Если выполнена, вернуть true, в противном случае - false
        return false;
    }
    public void progresslistener() {
        progressBar.setProgress(0);
        progressBar.progressProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() == 1.0) {
                level++;
                levelLabel.setText("Рівень " + level);
                progressBar.setProgress(0);
            }
        });
    }


    //ім'я профілю
    @FXML
    private void editProfileName(){
        String newProfileName = profileNameField.getText();
        if (newProfileName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Помилка");
            alert.setContentText("Строка Ім'я профіля не може бути порожньою");
            alert.showAndWait();
        } else {
            profileNameLabel.setText(newProfileName);
        }
    }


    //фото профілю
    @FXML
    private void ChooseImage() {
        // Создание диалогового окна выбора файла
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Оберіть зображення");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Зображення", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Всі файли", "*.*")
        );
        // Отображение диалогового окна и получение выбранного файла
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            // Создание объекта Image на основе выбранного файла и установка его в ImageView
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
        }
    }


    public void newTask(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewTask.fxml"));
        Parent root = loader.load();

        //открываем новое окно
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Додати завдання");
        stage.setScene(scene);

        AddTaskController addTaskController = loader.getController();
        addTaskController.setStage(stage);
        stage.showAndWait();

        if (addTaskController.addTaskButton() != null) {  //проверка пустое ли имя
            // Создаем новое задание и добавляем его в список заданий
            taskList.add(addTaskController.addTaskButton());
            String difficulty=addTaskController.diff();
            // Обновляем интерфейс
            ObservableList<String> items = FXCollections.observableArrayList(taskList);
            taskListView.setItems(items);
            taskListView.refresh();
            taskListView.setCellFactory(param -> new TaskCell(difficulty,items));
        }
    }

    public void Exit(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Вихід");
        alert.setHeaderText("Ви збираєтесь закрити програму");
        alert.setContentText("Ви впевнені?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            stage =(Stage) scenePane1.getScene().getWindow();
            stage.close();
        }
    }

    public void switchToHabits(ActionEvent event) throws IOException {

        tabName.setText("Звички");
    }

    public void switchToProfile(ActionEvent event) throws IOException {

        tabName.setText("Профіль");
        profilePane.toFront();
    }

    public void switchToQuest(ActionEvent event) throws IOException {

        tabName.setText("Завдання");
        taskPane.toFront();
    }

    public void switchToAchievements(ActionEvent event) throws IOException {

        tabName.setText("Досягнення");
    }

    public void switchToDaily(ActionEvent event) throws IOException {

        tabName.setText("Щоденні справи");

    }
    }
