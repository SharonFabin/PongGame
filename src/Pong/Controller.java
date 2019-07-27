package Pong;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    Main main;
    Stage stage;
    Scene playScene;
    int mode;

    @FXML Button player1;
    @FXML Button player2;
    @FXML Button exitbut;

    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    public void mode1(){
        this.mode=1;
        stage.setScene(playScene);
        main.play();
    }

    public void mode2(){
        this.mode=2;
        stage.setScene(playScene);
        main.play();
    }

    public void exit(){
        stage.close();
    }

    public void setMain(Main main){
        this.main=main;
    }

    public void setStage(Stage stage){
        this.stage=stage;
    }

    public void setPlayScene(Scene playScene){
        this.playScene=playScene;
    }

    public int getMode(){
        return this.mode;
    }



}
