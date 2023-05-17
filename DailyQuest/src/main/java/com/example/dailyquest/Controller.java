package com.example.dailyquest;

import com.example.dailyquest.daily.AddDailyController;
import com.example.dailyquest.daily.DailyCell;
import com.example.dailyquest.daily.Daily;
import com.example.dailyquest.habits.Habit;
import com.example.dailyquest.habits.HabitCell;
import com.example.dailyquest.habits.AddHabitController;
import com.example.dailyquest.tasks.AddTaskController;
import com.example.dailyquest.tasks.TaskCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.dailyquest.daily.DailyCell.completedDaily;
import static com.example.dailyquest.habits.HabitCell.completedHabit;
import static com.example.dailyquest.tasks.TaskCell.completedTask;

public class Controller {
    private Stage stage;
    @FXML private BorderPane scenePane1;
    @FXML private Label tabName;
    @FXML private ImageView imageView;
    @FXML private TextField profileNameField;
    @FXML private Label profileNameLabel;
    @FXML private ProgressBar progressBar;
    @FXML private Label levelLabel;
    @FXML private Label achievementsLabel;
    @FXML private AnchorPane profilePane;
    @FXML private AnchorPane achievementsPane;
    @FXML private AnchorPane taskPane;
    @FXML private AnchorPane habitsPane;
    @FXML private AnchorPane dailyPane;
    @FXML private ListView<Task> taskListView;
    @FXML private ListView<Habit> habitListView;
    @FXML private ListView<Daily> dailyListView;
    @FXML private HBox achievement1;
    @FXML private HBox achievement2;
    @FXML private HBox achievement3;
    @FXML private HBox achievement4;
    @FXML private HBox achievement5;
    @FXML private HBox achievement6;
    @FXML private HBox achievement7;
    @FXML private HBox achievement8;
    @FXML private HBox achievement9;
    @FXML private Label LabelTask;
    @FXML private Label LabelDaily;
    @FXML private Label LabelHabit;
    @FXML private Label LabelAchiev;
    private int level = 1;
    String pathToImage = "";
    String profileName = "Ім'я профілю";
    Double progress;
    Integer achievementsCount = 0;
    private List<Task> taskList = new ArrayList<Task>();
    private List<Habit> habitList = new ArrayList<Habit>();
    private List<Daily> dailyList = new ArrayList<Daily>();
    @FXML private void editProfileName(){
        profileName = profileNameField.getText();
        if (profileName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Помилка");
            alert.setContentText("Строка Ім'я профіля не може бути порожньою");
            alert.showAndWait();
        } else {
            profileNameLabel.setText(profileName);
        }
    }
    @FXML private void ChooseImage() {
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
            pathToImage= selectedFile.toString();
            imageView.setImage(image);
            imageView.setPreserveRatio(true);
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
            Task newTask = addTaskController.addTaskButton();
            taskList.add(newTask);

            // Обновляем интерфейс
            ObservableList<Task> items = FXCollections.observableArrayList(taskList);
            taskListView.setItems(items);
            taskListView.refresh();
            taskListView.setCellFactory(param -> new TaskCell(items,this,level,LabelTask));
        }
    }
    public void newHabit(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewHabit.fxml"));
        Parent root = loader.load();

        //открываем новое окно
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Додати звичку");
        stage.setScene(scene);

        AddHabitController addHabitController = loader.getController();
        addHabitController.setStage(stage);
        stage.showAndWait();

        if (addHabitController.addHabitButton() != null) {  //проверка пустое ли имя
            // Создаем новое задание и добавляем его в список заданий
            Habit newHabit = addHabitController.addHabitButton();
            habitList.add(newHabit);

            // Обновляем интерфейс
            ObservableList<Habit> items = FXCollections.observableArrayList(habitList);
            habitListView.setItems(items);
            habitListView.refresh();
            habitListView.setCellFactory(param -> new HabitCell(items,this,LabelHabit));
        }
    }
    public void newDaily(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewDaily.fxml"));
        Parent root = loader.load();

        //открываем новое окно
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Додати звичку");
        stage.setScene(scene);

        AddDailyController addDailyController = loader.getController();
        addDailyController.setStage(stage);
        stage.showAndWait();

        if (addDailyController.addDailyButton() != null) {  //проверка пустое ли имя
            // Создаем новое задание и добавляем его в список заданий
            Daily newDaily = addDailyController.addDailyButton();
            dailyList.add(newDaily);

            // Обновляем интерфейс
            ObservableList<Daily> items = FXCollections.observableArrayList(dailyList);
            dailyListView.setItems(items);
            dailyListView.refresh();
            dailyListView.setCellFactory(param -> new DailyCell(items,this, LabelDaily));
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
        habitsPane.toFront();
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
        achievementsPane.toFront();
    }
    public void switchToDaily(ActionEvent event) throws IOException {

        tabName.setText("Щоденні справи");
        dailyPane.toFront();
    }

    public void deleteTask(Task task) {
        // Удалить задачу с указанным именем из списка задач
        taskList.remove(task);


        // Обновить интерфейс
        ObservableList<Task> items = FXCollections.observableArrayList(taskList);
        taskListView.setItems(items);
        taskListView.refresh();
    }
    public void deleteHabit(Habit task) {
        // Удалить задачу с указанным именем из списка задач
        habitList.remove(task);


        // Обновить интерфейс
        ObservableList<Habit> items = FXCollections.observableArrayList(habitList);
        habitListView.setItems(items);
        habitListView.refresh();
    }
    public void deleteDaily(Daily task) {
        // Удалить задачу с указанным именем из списка задач
        dailyList.remove(task);


        // Обновить интерфейс
        ObservableList<Daily> items = FXCollections.observableArrayList(dailyList);
        dailyListView.setItems(items);
        dailyListView.refresh();
    }
    public void addProgress(String difficulty){

    double increment = 0.0;

        switch (difficulty) {
            case "Легко":
                increment = 0.05;
                break;
            case "Нормально":
                increment = 0.1;
                break;
            case "Складно":
                increment = 0.15;
                break;
            case "Позитивна":
                increment = 0.1;
                break;
            case"Негативна":
                increment = -0.1;
                break;
            default:
                return;
        }

        // Увеличиваем значение прогрессбара на increment
        progressBar.setProgress(progressBar.getProgress() + increment);
        // если опыт меньше 0
        if(progressBar.getProgress()<0)  {progressBar.setProgress(0);}


        if (progressBar.getProgress() >= 1.0) {
            // Сбрасываем значение прогрессбара
            progressBar.setProgress(0.0);

            // Увеличиваем уровень
            level++;
            levelLabel.setText("Рівень " + level);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Новий рівень!");
            alert.setHeaderText("Вітаю з досягненнням " + level +"-го рівня");

            if(alert.showAndWait().get() == ButtonType.OK) {
                stage =(Stage) scenePane1.getScene().getWindow();
            }

        }
    }
    public void addProgressByPercent(double percent) {
        double currentProgress = progressBar.getProgress();
        double newProgress = currentProgress + (percent / 100.0);

        if (newProgress >= 1.0) {
            // Если новый прогресс больше или равен 1.0, то сбрасываем значение прогрессбара
            progressBar.setProgress(0.0);
            // Увеличиваем уровень
            level++;
            levelLabel.setText("Рівень " + level);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Новий рівень!");
            alert.setHeaderText("Вітаю з досягненнням " + level +"-го рівня");

            if(alert.showAndWait().get() == ButtonType.OK) {
                stage =(Stage) scenePane1.getScene().getWindow();
            }
        } else {
            // Иначе устанавливаем новое значение прогрессбара
            progressBar.setProgress(newProgress);
        }
    }


    //раздел сохранений и загрузок
    String userHome = System.getProperty("user.home");
    String saveDirectory = userHome + File.separator + "Desktop";
    public void saveTask() {
        String filePath = saveDirectory + File.separator + "tasks.dat";
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            //сохранение списков заданий
            outputStream.writeObject(taskList);
            outputStream.writeObject(habitList);
            outputStream.writeObject(dailyList);

            //сохранения данных профиля
            outputStream.writeInt(level);
            outputStream.writeUTF(pathToImage);

            //сохраняем имя и прогресбар
            outputStream.writeUTF(profileName);
            progress=progressBar.getProgress();
            outputStream.writeDouble(progress);




        } catch (IOException e) {
            System.err.println("Ошибка при сохранении данных в файл: " + e.getMessage());
        }
    }

    public void loadTasksFromFile() {
        String filePath = saveDirectory + File.separator + "tasks.dat";
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            taskList = (List<Task>) inputStream.readObject();
            habitList = (List<Habit>) inputStream.readObject();
            dailyList = (List<Daily>) inputStream.readObject();

            level = inputStream.readInt();
            pathToImage = inputStream.readUTF();
            profileName = inputStream.readUTF();
            progress = inputStream.readDouble();

            // Обновляем интерфейс
            ObservableList<Task> loadtasks = FXCollections.observableArrayList(taskList);
            taskListView.setItems(loadtasks);
            taskListView.refresh();
            taskListView.setCellFactory(param -> new TaskCell(loadtasks, this,level,LabelTask));

            ObservableList<Habit> loadhabits = FXCollections.observableArrayList(habitList);
            habitListView.setItems(loadhabits);
            habitListView.refresh();
            habitListView.setCellFactory(param -> new HabitCell(loadhabits, this,LabelHabit));

            ObservableList<Daily> loaddaily = FXCollections.observableArrayList(dailyList);
            dailyListView.setItems(loaddaily);
            dailyListView.refresh();
            dailyListView.setCellFactory(param -> new DailyCell(loaddaily, this,LabelDaily));

            //обновляем данные профиля
            levelLabel.setText("Рівень: "+ level);
            if(!Objects.equals(pathToImage, "")) {
                Image profileImage = new Image(pathToImage);
                imageView.setImage(profileImage);
                imageView.setPreserveRatio(true);
            }

            //обновляем имя и прогресбар
            profileNameLabel.setText(profileName);
            progressBar.setProgress(progress);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка при загрузке данных из файла: " + e.getMessage());
        }
        if (LabelTask != null) {
            LabelTask.setText(""+completedTask);
        }
        if (LabelAchiev != null) {
            LabelAchiev.setText(""+achievementsCount);
        }
        if (LabelHabit != null) {
            LabelHabit.setText(""+completedHabit);
        }
        if (LabelDaily != null) {
            LabelDaily.setText(""+completedDaily);
        }
    }



    public void changeAchievLablel(){ //метод для увеличения значения выполненых достижений
        achievementsCount++;
        achievementsLabel.setText("Досягнень виконано: "+ achievementsCount +"/9");
        if (LabelAchiev != null) {
            LabelAchiev.setText(""+achievementsCount);
        }
    }

    /*public void Labels(){
        LabelTask.setText(completedTask);
        LabelDaily.setText(completedTask);
        LabelHabit.setText(completedTask);
        LabelAchiev.setText(achievementsCount);
    }*/
    public void AchievementTask(int completedTask) {
        if (completedTask == 5) {
            achievement1.setStyle("-fx-background-color: rgb(222, 245, 191);");
            changeAchievLablel();
        } else {
            achievement1.setStyle("");
        }

        if (completedTask == 10) {
            achievement1.setStyle("-fx-background-color: rgb(222, 245, 191);");
            achievement2.setStyle("-fx-background-color: rgb(222, 245, 191);");
            changeAchievLablel();
        } else {
            achievement2.setStyle("");
        }

        if (completedTask == 15) {
            achievement1.setStyle("-fx-background-color: rgb(222, 245, 191);");
            achievement2.setStyle("-fx-background-color: rgb(222, 245, 191);");
            achievement3.setStyle("-fx-background-color: rgb(222, 245, 191);");
            changeAchievLablel();
        } else {
            achievement3.setStyle("");
        }
    }
    public void AchievementHabit(int completedHabit) {
        if (completedHabit == 10) {
            achievement4.setStyle("-fx-background-color: rgb(222, 245, 191);");
            changeAchievLablel();
        } else {
            achievement4.setStyle("");
        }

        if (completedHabit == 20) {
            achievement4.setStyle("-fx-background-color: rgb(222, 245, 191);");
            achievement5.setStyle("-fx-background-color: rgb(222, 245, 191);");
            changeAchievLablel();
        } else {
            achievement5.setStyle("");
        }

        if (completedHabit == 30) {
            achievement4.setStyle("-fx-background-color: rgb(222, 245, 191);");
            achievement5.setStyle("-fx-background-color: rgb(222, 245, 191);");
            achievement6.setStyle("-fx-background-color: rgb(222, 245, 191);");
            changeAchievLablel();
        } else {
            achievement6.setStyle("");
        }
    }
    public void AchievementDaily(int completedDaily) {
        if (completedDaily == 5) {
            achievement7.setStyle("-fx-background-color: rgb(222, 245, 191);");
            changeAchievLablel();
        } else {
            achievement7.setStyle("");
        }

        if (completedDaily == 10) {
            achievement7.setStyle("-fx-background-color: rgb(222, 245, 191);");
            achievement8.setStyle("-fx-background-color: rgb(222, 245, 191);");
            changeAchievLablel();
        } else {
            achievement8.setStyle("");
        }

        if (completedDaily == 15) {
            achievement7.setStyle("-fx-background-color: rgb(222, 245, 191);");
            achievement8.setStyle("-fx-background-color: rgb(222, 245, 191);");
            achievement9.setStyle("-fx-background-color: rgb(222, 245, 191);");
            changeAchievLablel();
        } else {
            achievement9.setStyle("");
        }
    }
    public void achievementProgress() {
        level++;
        levelLabel.setText("Рівень " + level);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Новий рівень!");
        alert.setHeaderText("Вітаю з досягненнням " + level + "-го рівня");

        if (alert.showAndWait().orElse(ButtonType.OK) == ButtonType.OK) {
            // Действия, которые нужно выполнить при нажатии кнопки OK
        }
    }

}
