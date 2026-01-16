package birthdaymanager;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BirthdayManagerApp extends Application {
    
    private BirthdayDAO birthdayDAO = new BirthdayDAO();
    private TableView<Birthday> tableView = new TableView<>();
    private TextField searchField = new TextField();
    private ComboBox<String> monthComboBox = new ComboBox<>();
    
    @Override
    public void start(Stage primaryStage) {
        // বাংলা ফন্ট লোড করার চেষ্টা
        try {
            Font.loadFont(getClass().getResourceAsStream("/fonts/Bangla.ttf"), 12);
        } catch (Exception e) {
            System.out.println("বাংলা ফন্ট লোড করা যায়নি, ডিফল্ট ফন্ট ব্যবহার হবে।");
        }
        
        // UI কম্পোনেন্টস
        Label titleLabel = new Label("🎂 সহপাঠীদের জন্মদিন ব্যবস্থাপনা");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        
        // Form ফিল্ডস
        Label nameLabel = new Label("নাম:");
        TextField nameField = new TextField();
        nameField.setPromptText("সম্পূর্ণ নাম লিখুন");
        
        Label dateLabel = new Label("জন্ম তারিখ:");
        DatePicker datePicker = new DatePicker(LocalDate.now());
        
        Label phoneLabel = new Label("ফোন:");
        TextField phoneField = new TextField();
        phoneField.setPromptText("০১XXXXXXXXX");
        
        Label emailLabel = new Label("ইমেইল:");
        TextField emailField = new TextField();
        emailField.setPromptText("example@domain.com");
        
        Label notesLabel = new Label("মন্তব্য:");
        TextArea notesArea = new TextArea();
        notesArea.setPrefRowCount(3);
        
        // বাটনস
        Button addButton = new Button("যোগ করুন");
        addButton.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold;");
        
        Button updateButton = new Button("আপডেট করুন");
        updateButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-weight: bold;");
        
        Button deleteButton = new Button("ডিলিট করুন");
        deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold;");
        
        Button clearButton = new Button("ক্লিয়ার করুন");
        clearButton.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white; -fx-font-weight: bold;");
        
        // সার্চ সেকশন
        Label searchLabel = new Label("সার্চ:");
        searchField.setPromptText("নাম দিয়ে সার্চ করুন");
        
        monthComboBox.getItems().addAll(
            "সকল মাস", "জানুয়ারি", "ফেব্রুয়ারি", "মার্চ", "এপ্রিল", "মে", "জুন",
            "জুলাই", "আগস্ট", "সেপ্টেম্বর", "অক্টোবর", "নভেম্বর", "ডিসেম্বর"
        );
        monthComboBox.setValue("সকল মাস");
        
        Button searchButton = new Button("সার্চ");
        searchButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
        
        Button showAllButton = new Button("সব দেখাও");
        showAllButton.setStyle("-fx-background-color: #9b59b6; -fx-text-fill: white;");
        
        Button todayButton = new Button("আজকের জন্মদিন");
        todayButton.setStyle("-fx-background-color: #e67e22; -fx-text-fill: white;");
        
        Button upcomingButton = new Button("আসন্ন জন্মদিন");
        upcomingButton.setStyle("-fx-background-color: #1abc9c; -fx-text-fill: white;");
        
        // টেবিল কনফিগারেশন
        TableColumn<Birthday, String> nameCol = new TableColumn<>("নাম");
        nameCol.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));
        
        TableColumn<Birthday, String> dateCol = new TableColumn<>("জন্ম তারিখ");
        dateCol.setCellValueFactory(cellData -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            return new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getBirthDate().format(formatter));
        });
        
        TableColumn<Birthday, String> ageCol = new TableColumn<>("বয়স");
        ageCol.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(
                String.valueOf(cellData.getValue().getAge())));
        
        TableColumn<Birthday, String> phoneCol = new TableColumn<>("ফোন");
        phoneCol.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPhone()));
        
        tableView.getColumns().addAll(nameCol, dateCol, ageCol, phoneCol);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        // লেআউট সেটআপ
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(10));
        
        formGrid.add(nameLabel, 0, 0);
        formGrid.add(nameField, 1, 0);
        formGrid.add(dateLabel, 0, 1);
        formGrid.add(datePicker, 1, 1);
        formGrid.add(phoneLabel, 0, 2);
        formGrid.add(phoneField, 1, 2);
        formGrid.add(emailLabel, 0, 3);
        formGrid.add(emailField, 1, 3);
        formGrid.add(notesLabel, 0, 4);
        formGrid.add(notesArea, 1, 4);
        
        HBox buttonBox = new HBox(10, addButton, updateButton, deleteButton, clearButton);
        buttonBox.setAlignment(Pos.CENTER);
        
        HBox searchBox = new HBox(10, searchLabel, searchField, 
            new Label("মাস:"), monthComboBox, searchButton, showAllButton);
        searchBox.setAlignment(Pos.CENTER_LEFT);
        
        HBox specialButtons = new HBox(10, todayButton, upcomingButton);
        specialButtons.setAlignment(Pos.CENTER);
        
        VBox mainLayout = new VBox(15);
        mainLayout.setPadding(new Insets(20));
        mainLayout.getChildren().addAll(
            titleLabel,
            new Separator(),
            formGrid,
            buttonBox,
            new Separator(),
            searchBox,
            specialButtons,
            new Label("সহপাঠীদের তালিকা:"),
            tableView
        );
        
        // ইভেন্ট হ্যান্ডলারস
        addButton.setOnAction(e -> {
            if (validateFields(nameField, datePicker)) {
                Birthday birthday = new Birthday(
                    nameField.getText(),
                    datePicker.getValue(),
                    phoneField.getText(),
                    emailField.getText(),
                    notesArea.getText()
                );
                
                if (birthdayDAO.addBirthday(birthday)) {
                    showAlert("সফল", "জন্মদিন সফলভাবে যোগ করা হয়েছে!", Alert.AlertType.INFORMATION);
                    refreshTable();
                    clearForm(nameField, datePicker, phoneField, emailField, notesArea);
                } else {
                    showAlert("ত্রুটি", "যোগ করতে ব্যর্থ!", Alert.AlertType.ERROR);
                }
            }
        });
        
        updateButton.setOnAction(e -> {
            Birthday selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null && validateFields(nameField, datePicker)) {
                selected.setName(nameField.getText());
                selected.setBirthDate(datePicker.getValue());
                selected.setPhone(phoneField.getText());
                selected.setEmail(emailField.getText());
                selected.setNotes(notesArea.getText());
                
                if (birthdayDAO.updateBirthday(selected)) {
                    showAlert("সফল", "জন্মদিন আপডেট করা হয়েছে!", Alert.Alert
