package controllers;

import models.Question;
import models.PlayerScore;
import java.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class QuizController {
    private Database db;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private String playerName = "";
    private Timeline timer;
    private int timeLeft = 30; // 30 seconds per question
    
    // UI Components
    private Stage stage;
    private VBox mainLayout;
    private Label questionLabel;
    private Label timerLabel;
    private Label scoreLabel;
    private ToggleGroup optionsGroup;
    private RadioButton optionA, optionB, optionC, optionD;
    private Button nextButton, restartButton, exitButton;
    private ProgressBar timerBar;
    
    public QuizController(Stage primaryStage) {
        this.stage = primaryStage;
        this.db = new Database();
        initializeUI();
        showStartScreen();
    }
    
    private void initializeUI() {
        mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: #f0f8ff;");
        
        // Timer Label
        timerLabel = new Label("Time: 30s");
        timerLabel.setFont(Font.font("Arial", 16));
        timerLabel.setTextFill(Color.RED);
        
        // Score Label
        scoreLabel = new Label("Score: 0");
        scoreLabel.setFont(Font.font("Arial", 16));
        scoreLabel.setTextFill(Color.BLUE);
        
        // Question Label
        questionLabel = new Label();
        questionLabel.setFont(Font.font("Arial", 18));
        questionLabel.setWrapText(true);
        questionLabel.setMaxWidth(700);
        
        // Options
        optionsGroup = new ToggleGroup();
        optionA = new RadioButton();
        optionB = new RadioButton();
        optionC = new RadioButton();
        optionD = new RadioButton();
        
        optionA.setToggleGroup(optionsGroup);
        optionB.setToggleGroup(optionsGroup);
        optionC.setToggleGroup(optionsGroup);
        optionD.setToggleGroup(optionsGroup);
        
        VBox optionsBox = new VBox(10, optionA, optionB, optionC, optionD);
        optionsBox.setPadding(new Insets(20));
        
        // Timer Progress Bar
        timerBar = new ProgressBar(1.0);
        timerBar.setPrefWidth(700);
        timerBar.setStyle("-fx-accent: green;");
        
        // Buttons
        nextButton = new Button("Next Question");
        nextButton.setFont(Font.font("Arial", 14));
        nextButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        nextButton.setOnAction(e -> handleNextQuestion());
        
        restartButton = new Button("Restart Game");
        restartButton.setFont(Font.font("Arial", 14));
        restartButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");
        restartButton.setOnAction(e -> restartGame());
        
        exitButton = new Button("Exit");
        exitButton.setFont(Font.font("Arial", 14));
        exitButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
        exitButton.setOnAction(e -> Platform.exit());
        
        HBox buttonBox = new HBox(20, nextButton, restartButton, exitButton);
        buttonBox.setAlignment(Pos.CENTER);
        
        // Add all to main layout
        mainLayout.getChildren().addAll(timerLabel, scoreLabel, timerBar, 
                                       questionLabel, optionsBox, buttonBox);
    }
    
    private void showStartScreen() {
        VBox startScreen = new VBox(30);
        startScreen.setAlignment(Pos.CENTER);
        startScreen.setPadding(new Insets(50));
        startScreen.setStyle("-fx-background-color: #e3f2fd;");
        
        Label title = new Label("🎯 JavaFX Quiz Game");
        title.setFont(Font.font("Arial", 32));
        title.setTextFill(Color.DARKBLUE);
        
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");
        nameField.setMaxWidth(300);
        nameField.setFont(Font.font("Arial", 14));
        
        Button startButton = new Button("Start Quiz");
        startButton.setFont(Font.font("Arial", 16));
        startButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        startButton.setOnAction(e -> {
            playerName = nameField.getText().trim();
            if (playerName.isEmpty()) {
                playerName = "Anonymous";
            }
            startQuiz();
        });
        
        // Show top scores
        List<PlayerScore> topScores = db.getTopScores(5);
        Label scoresTitle = new Label("🏆 Top Scores");
        scoresTitle.setFont(Font.font("Arial", 18));
        
        VBox scoresBox = new VBox(5);
        scoresBox.setAlignment(Pos.CENTER_LEFT);
        
        for (PlayerScore ps : topScores) {
            Label scoreLabel = new Label(String.format("%s: %d points (%s)", 
                ps.getPlayerName(), ps.getScore(), 
                ps.getDatePlayed().toString().substring(0, 10)));
            scoreLabel.setFont(Font.font("Arial", 12));
            scoresBox.getChildren().add(scoreLabel);
        }
        
        startScreen.getChildren().addAll(title, nameField, startButton, scoresTitle, scoresBox);
        
        Scene scene = new Scene(startScreen, 800, 600);
        stage.setScene(scene);
    }
    
    private void startQuiz() {
        // Get 5 random questions from DB
        questions = db.getRandomQuestions(5);
        if (questions.isEmpty()) {
            showAlert("No questions found in database!");
            return;
        }
        
        currentQuestionIndex = 0;
        score = 0;
        updateScore();
        
        // Show first question
        showQuestion();
        
        Scene scene = new Scene(mainLayout, 800, 600);
        stage.setScene(scene);
    }
    
    private void showQuestion() {
        if (currentQuestionIndex >= questions.size()) {
            endGame();
            return;
        }
        
        Question q = questions.get(currentQuestionIndex);
        questionLabel.setText((currentQuestionIndex + 1) + ". " + q.getQuestionText());
        optionA.setText("A. " + q.getOptionA());
        optionB.setText("B. " + q.getOptionB());
        optionC.setText("C. " + q.getOptionC());
        optionD.setText("D. " + q.getOptionD());
        
        optionsGroup.selectToggle(null);
        
        // Start timer
        startTimer();
    }
    
    private void startTimer() {
        timeLeft = 30;
        updateTimer();
        
        if (timer != null) {
            timer.stop();
        }
        
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeLeft--;
            updateTimer();
            
            if (timeLeft <= 0) {
                timer.stop();
                handleTimeout();
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }
    
    private void updateTimer() {
        timerLabel.setText("Time: " + timeLeft + "s");
        timerBar.setProgress(timeLeft / 30.0);
        
        if (timeLeft <= 10) {
            timerLabel.setTextFill(Color.RED);
        } else if (timeLeft <= 20) {
            timerLabel.setTextFill(Color.ORANGE);
        } else {
            timerLabel.setTextFill(Color.GREEN);
        }
    }
    
    private void handleNextQuestion() {
        if (timer != null) {
            timer.stop();
        }
        
        // Check answer
        RadioButton selected = (RadioButton) optionsGroup.getSelectedToggle();
        if (selected != null) {
            char selectedAnswer = selected.getText().charAt(0);
            Question currentQ = questions.get(currentQuestionIndex);
            
            if (currentQ.isCorrect(selectedAnswer)) {
                score += 10; // 10 points per correct answer
                updateScore();
                showAlert("✅ Correct! +10 points");
            } else {
                showAlert("❌ Wrong! Correct answer: " + currentQ.getCorrectAnswer());
            }
        } else {
            showAlert("⚠️ No answer selected!");
        }
        
        currentQuestionIndex++;
        showQuestion();
    }
    
    private void handleTimeout() {
        showAlert("⏰ Time's up! Moving to next question.");
        currentQuestionIndex++;
        showQuestion();
    }
    
    private void updateScore() {
        scoreLabel.setText("Score: " + score);
    }
    
    private void endGame() {
        // Save score to database
        db.saveScore(playerName, score);
        
        VBox endScreen = new VBox(30);
        endScreen.setAlignment(Pos.CENTER);
        endScreen.setPadding(new Insets(50));
        endScreen.setStyle("-fx-background-color: #e8f5e9;");
        
        Label resultLabel = new Label("🎉 Quiz Completed!");
        resultLabel.setFont(Font.font("Arial", 28));
        resultLabel.setTextFill(Color.DARKGREEN);
        
        Label scoreLabel = new Label(playerName + ", your final score: " + score + "/50");
        scoreLabel.setFont(Font.font("Arial", 20));
        
        Button playAgainButton = new Button("Play Again");
        playAgainButton.setFont(Font.font("Arial", 16));
        playAgainButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        playAgainButton.setOnAction(e -> showStartScreen());
        
        Button exitButton = new Button("Exit");
        exitButton.setFont(Font.font("Arial", 16));
        exitButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
        exitButton.setOnAction(e -> Platform.exit());
        
        HBox buttonBox = new HBox(20, playAgainButton, exitButton);
        buttonBox.setAlignment(Pos.CENTER);
        
        // Show top scores
        List<PlayerScore> topScores = db.getTopScores(3);
        Label scoresTitle = new Label("🏆 Top 3 Scores");
        scoresTitle.setFont(Font.font("Arial", 18));
        
        VBox scoresBox = new VBox(10);
        for (PlayerScore ps : topScores) {
            Label sLabel = new Label(String.format("%s: %d points", 
                ps.getPlayerName(), ps.getScore()));
            sLabel.setFont(Font.font("Arial", 14));
            scoresBox.getChildren().add(sLabel);
        }
        
        endScreen.getChildren().addAll(resultLabel, scoreLabel, buttonBox, scoresTitle, scoresBox);
        
        Scene scene = new Scene(endScreen, 800, 600);
        stage.setScene(scene);
    }
    
    private void restartGame() {
        if (timer != null) {
            timer.stop();
        }
        showStartScreen();
    }
    
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Quiz Game");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
