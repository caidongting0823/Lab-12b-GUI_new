package com.example.lab12bgui_new;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class GameController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView game_Image;

    @FXML
    private Text getInput_Des;

    @FXML
    private TextField getInput_TextBox;

    @FXML
    private AnchorPane guessWord;

    @FXML
    private Text lifeCount_Text;

    @FXML
    private Text mainArea_Text;

    @FXML
    private Text gameStatus_Text;

    private String word;

    StringBuilder hiddenWord = new StringBuilder();

    private int lifeCount = 0;

    public void initialize(URL url, ResourceBundle resources) {
        Image image0 = new Image(Objects.requireNonNull(GameController.class.getResourceAsStream("S1.png")));
        game_Image.setImage(image0);
    }

    // ArrayList<String> livesIdPic = new ArrayList<>(Arrays.asList("S1.png", "S2.png", "S3.png", "S4.png", "S5.png", "S6.png", "S7.png"));

    @FXML
    void getInput(ActionEvent event) {
        if (word == null) {
            word = getInput_TextBox.getText();
            wordSetUp();
            getInput_TextBox.clear();
        } else {
            playTurn();
        }
    }

    @FXML
    void setRestartGame(ActionEvent event) {

        gameStatus_Text.setText("Restart Complete...");
        lifeCount_Text.setText("Waiting to Start...");
        mainArea_Text.setText("...");
        getInput_Des.setText("Please enter the word You will guess: ");
        word = null;
        hiddenWord.setLength(0);
        lifeCount = 0;
        hangManPicControl(0);

    }

    @FXML
    void initialize() {
        assert game_Image != null : "fx:id=\"game_Image\" was not injected: check your FXML file 'gameView.fxml'.";
        assert getInput_Des != null : "fx:id=\"getInput_Des\" was not injected: check your FXML file 'gameView.fxml'.";
        assert getInput_TextBox != null : "fx:id=\"getInput_TextBox\" was not injected: check your FXML file 'gameView.fxml'.";
        assert guessWord != null : "fx:id=\"guessWord\" was not injected: check your FXML file 'gameView.fxml'.";
        assert lifeCount_Text != null : "fx:id=\"lifeCount_Text\" was not injected: check your FXML file 'gameView.fxml'.";
        assert mainArea_Text != null : "fx:id=\"mainArea_Text\" was not injected: check your FXML file 'gameView.fxml'.";
    }



    public void wordSetUp() {
        int wordLength = word.length();

        for (int i = 0; i < wordLength; i++) {
            hiddenWord.append("*");
        }
        mainArea_Text.setText(String.valueOf(hiddenWord));

        getInput_Des.setText("Please enter the letter wou would like to guess: ");
    }

    public Image hangManPicControl(int wrongGuessesCount) {
        switch (wrongGuessesCount) {
            case 0:
                Image image0 = new Image(this.getClass().getClassLoader().getResourceAsStream("S1.png"));
                game_Image.setImage(image0);

            case 1:
                Image image1 = new Image(this.getClass().getClassLoader().getResourceAsStream("S2.png"));
                game_Image.setImage(image1);

            case 2:
                Image image2 = new Image(this.getClass().getClassLoader().getResourceAsStream("S3.png"));
                game_Image.setImage(image2);

            case 3:
                Image image3 = new Image(this.getClass().getClassLoader().getResourceAsStream("S4.png"));
                game_Image.setImage(image3);

            case 4:
                Image image4 = new Image(this.getClass().getClassLoader().getResourceAsStream("S5.png"));
                game_Image.setImage(image4);

            case 5:
                Image image5 = new Image(this.getClass().getClassLoader().getResourceAsStream("S6.png"));
                game_Image.setImage(image5);

            case 6:
                Image image6 = new Image(this.getClass().getClassLoader().getResourceAsStream("S7.png"));
                game_Image.setImage(image6);
        }
        return null;
    }


    public void playTurn() {
        String getInput_TextBox = this.getInput_TextBox.getText();
        ArrayList<Integer> pos = new ArrayList<>();
        char[] wordChars = word.toCharArray();
        char guessLetter = getInput_TextBox.charAt(0);

        int lifeRemain = 5 - lifeCount;

        if(word.equals(getInput_TextBox)) {
            System.out.println("Congregations! You Won the Game!");
            lifeCount_Text.setText("You Passed the Game!");
            gameStatus_Text.setText("Yeah!");
            return;
        }

        if(word.contains(getInput_TextBox)) {
            for (int i = 0; i < word.length(); i++) {
                if(wordChars[i] == guessLetter) {
                    pos.add(i);
                }
            }

            pos.forEach(pos2 -> {
                hiddenWord.setCharAt(pos2, guessLetter);
            });

            mainArea_Text.setText(String.valueOf(hiddenWord));

            lifeCount_Text.setText("You have " + lifeRemain + " life(s) left.");

        } else {

            lifeCount = lifeCount + 1;
            hangManPicControl(lifeCount);
            lifeCount_Text.setText("You have " + lifeRemain + " life(s) left.");

            if (lifeCount == 6) {
                gameStatus_Text.setText("You lose... Press Restart to New Game.");
                System.out.println("");
            }

            System.out.println(lifeCount);

        }
    }
}
