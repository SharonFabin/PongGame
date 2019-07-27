package Pong;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {

    private double speedX, speedY;
    private Circle ball;
    private double radius;
    private int sWidth, sHeight;

    public Ball(double speedX, double speedY, double radius, Color color, int sWidth, int sHeight){
        this.speedX=speedX;
        this.speedY=speedY;
        ball = new Circle();
        this.radius=radius;
        ball.setRadius(radius);
        ball.setFill(color);
        this.sWidth=sWidth;
        this.sHeight=sHeight;
        ball.setCenterX(sWidth/2);
        ball.setCenterY(sHeight/2);

    }

    public Circle getBall(){
        return this.ball;
    }

    public double getSpeedX(){
        return this.speedX;
    }

    public double getSpeedY(){
        return this.speedY;
    }

    public void setSpeedX(double speedX){
        this.speedX=speedX;
    }

    public void setSpeedY(double speedY){
        this.speedY=speedY;
    }

    public void move(double x, double y){
        ball.setCenterX(x);
        ball.setCenterY(y);
    }

    public void update(){
        ball.setCenterX(ball.getCenterX()+speedX);
        ball.setCenterY(ball.getCenterY()+speedY);
//        if(ball.getCenterX()+radius >= sWidth){
//            ball.setCenterX(sWidth-radius);
//            speedX*=-1;
//        }
//        if(ball.getCenterX()-radius < 0){
//            ball.setCenterX(radius);
//            speedX*=-1;
//        }
        if(ball.getCenterY()+radius >= sHeight){
            ball.setCenterY(sHeight-radius);
            speedY*=-1;
        }
        if(ball.getCenterY()-radius < 0){
            ball.setCenterY(radius);
            speedY*=-1;
        }


        //Collision

    }



}
