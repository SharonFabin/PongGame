package Pong;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.net.URL;

public class Main extends Application
        implements EventHandler <KeyEvent>
{
    final int WIDTH = 1200;
    final int HEIGHT = 800;

    Ball ball;
    Paddle p1,p2;
    Robot robot;
    MediaPlayer player;
    Text score1,score2;
    final ContextMenu contextMenu = new ContextMenu();
    int s1=0,s2=0,waitTime=50;
    boolean win=false,cur=false;
    final BooleanProperty upPressed = new SimpleBooleanProperty(false);
    final BooleanProperty downPressed = new SimpleBooleanProperty(false);
    final BooleanProperty wPressed = new SimpleBooleanProperty(false);
    final BooleanProperty sPressed = new SimpleBooleanProperty(false);

    AnimationTimer animator;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Pong Game");


        //Declare Play Scene
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());

        //Build Menu Scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Pong/menu.fxml"));
        Parent menuRoot = (Parent)loader.load();
        Controller controller = (Controller) loader.getController();
        controller.setStage(stage);
        controller.setPlayScene(scene);
        controller.setMain(this);
        Scene menu = new Scene(menuRoot,WIDTH,HEIGHT);
        menu.getStylesheets().add(getClass().getResource("menu.css").toExternalForm());

        //Build Play Scene
        contextMenu.setId("menu");
        MenuItem resume = new MenuItem("Resume");
        MenuItem mute = new MenuItem("Stop music");
        MenuItem play = new MenuItem("Play music");
        MenuItem exit = new MenuItem("Exit");
        contextMenu.getItems().addAll(resume, mute, play, exit);
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.play();
            }
        });
        mute.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.stop();
            }
        });
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.stop();
                animator.stop();
                s1=0;
                s2=0;
                score1.setText(""+s1);
                score1.setText(""+s2);
                waitTime=50;
                stage.setScene(menu);
            }
        });


        Button playBut = new Button();


        playBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur=false;
                menu.setCursor(Cursor.DEFAULT);
                stage.setScene(scene);
                score1.setText(""+s1);
                score1.setText(""+s2);
                animator.start();
                player.play();
            }
        });

        Image img = new Image("Pong/fondo_pong.png");
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(HEIGHT);
        imgView.setFitWidth(WIDTH);


        ball = new Ball(15,4,10,Color.WHITE, WIDTH, HEIGHT);
        p1 = new Paddle(10,20,100,Color.WHITE,10,HEIGHT);
        p2 = new Paddle(10,20,100,Color.WHITE,WIDTH-30,HEIGHT);
        robot = new Robot(p2);

        score1 = new Text(""+s1);
        score2 = new Text(""+s2);
        score1.setId("fancytext");
        score1.setY(100);
        score1.setX(WIDTH/2-150);
        score2.setId("fancytext");
        score2.setY(100);
        score2.setX(WIDTH/2+100);

        root.getChildren().add(imgView);
        root.getChildren().add(score1);
        root.getChildren().add(score2);
        root.getChildren().add(ball.getBall());
        root.getChildren().add(p1.getPaddle());
        root.getChildren().add(p2.getPaddle());

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.UP){
                    upPressed.set(true);
                }else if(event.getCode() == KeyCode.W){
                    wPressed.set(true);
                }else if(event.getCode() == KeyCode.DOWN) {
                    downPressed.set(true);
                }else if(event.getCode() == KeyCode.S) {
                    sPressed.set(true);
                }if(event.getCode()==KeyCode.ESCAPE){
//                    animator.stop();
//                    stage.setScene(menu);
//                    cur=true;
                    contextMenu.show(root,WIDTH/1.4,HEIGHT/2);
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.UP){
                    upPressed.set(false);
                }else if(event.getCode() == KeyCode.W){
                    wPressed.set(false);
                }else if(event.getCode() == KeyCode.DOWN) {
                    downPressed.set(false);
                }else if(event.getCode() == KeyCode.S) {
                    sPressed.set(false);
                }
            }
        });


        final Box keyboardNode = new Box();
        keyboardNode.setFocusTraversable(true);
        keyboardNode.requestFocus();
        keyboardNode.setOnKeyPressed(this);

        root.getChildren().add(keyboardNode);

        URL resource = getClass().getResource("sound1.mp3");
        player =new MediaPlayer(new Media(resource.toString()));
        player.setCycleCount(MediaPlayer.INDEFINITE);
        //player.play();


        stage.setScene(menu);
        stage.show();

        animator = new AnimationTimer(){

            @Override
            public void handle(long arg0) {

                // UPDATE

                if(upPressed.get()){
                    p2.moveUp();
                }
                if(downPressed.get()){
                    p2.moveDown();
                }
                if(wPressed.get()){
                    p1.moveUp();
                }
                if(sPressed.get()){
                    p1.moveDown();
                }


                if(controller.getMode()==1){
                    p2.setSpeedY(3.75);
                    //p1.setSpeedY(4);
                    //if(p2.getPaddle().getY()+p2.getHeight()/2>ball.getBall().getCenterY()+ ball.getBall().getRadius()) {p2.moveUp();}
                    //else {p2.moveDown();}
                    robot.move(ball.getBall().getCenterY());

                }else{
                    p2.setSpeedY(p1.getSpeedY());
                }

                double x = ball.getBall().getCenterX();
                double y = ball.getBall().getCenterY();
                ball.update();

                if(!win) {

                    if (ball.getBall().getCenterX() - ball.getBall().getRadius() < p1.getWidth() + p1.getPaddle().getX()) {
                        if (ball.getBall().getCenterY() + ball.getBall().getRadius() > p1.getPaddle().getY() &&
                                ball.getBall().getCenterY() - ball.getBall().getRadius() < p1.getPaddle().getY() + p1.getHeight()) {
                            ball.setSpeedX(ball.getSpeedX() * -1);
                            //p1.setHeight(p1.getHeight()-10);
                            ball.setSpeedX(ball.getSpeedX()+0.5);
                        } else {
                            s2++;
                            win = true;
                        }
                    }
                    if (ball.getBall().getCenterX() + ball.getBall().getRadius() > p2.getPaddle().getX()) {
                        if (ball.getBall().getCenterY() + ball.getBall().getRadius() > p2.getPaddle().getY() &&
                                ball.getBall().getCenterY() - ball.getBall().getRadius() < p2.getPaddle().getY() + p2.getHeight()) {
                            ball.setSpeedX(ball.getSpeedX() * -1);
                            //p2.setHeight(p2.getHeight()-10);
                            ball.setSpeedX(ball.getSpeedX());
                        } else {
                            s1++;
                            win = true;
                        }

                    }

                }else{
                    waitTime--;
                    if(ball.getBall().getCenterX()<=0 || ball.getBall().getCenterX()>=WIDTH){
                        ball.move(WIDTH / 2, HEIGHT / 2);
                        ball.setSpeedX(0);
                        ball.setSpeedY(0);
                        score1.setText(""+s1);
                        score2.setText(""+s2);
                    }
                    if(waitTime<=0){
                        if(s1>s2)
                            ball.setSpeedX(15);
                        else ball.setSpeedX(-15);
                        ball.setSpeedY(4);
                        waitTime=50;
                        win=false;
                    }
                }

                // RENDER
                //circle.setCenterX(ballX);
            }
        };

        //animator.start();
    }

    @Override
    public void handle(KeyEvent arg0) {

        if (arg0.getCode() == KeyCode.SPACE )
        {
            ball.setSpeedX(ball.getSpeedX()*1);
            ball.setSpeedY(ball.getSpeedY()*-1);
        }

    }

    public void play(){
        score1.setText(""+s1);
        score1.setText(""+s2);
        animator.start();
        player.play();
    }
}