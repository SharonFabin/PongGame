package Pong;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Paddle {

    private double speedY;
    private Rectangle paddle;
    private double width,height;
    private int sHeight;

    public Paddle(double speedY, double width, double height, Color color, int xdis, int sHeight){
        this.speedY=speedY;
        this.width=width;
        this.height=height;
        paddle = new Rectangle();
        paddle.setWidth(width);
        paddle.setHeight(height);
        paddle.setFill(color);
        this.sHeight=sHeight;
        paddle.setX(xdis);
        paddle.setY(sHeight/2-height/2);

    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public Rectangle getPaddle() {
        return paddle;
    }

    public void setPaddle(Rectangle paddle) {
        this.paddle = paddle;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
        this.paddle.setWidth(width);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        this.paddle.setHeight(height);
    }

    public void moveUp(){
        if(paddle.getY()>=0)
            paddle.setY(paddle.getY()-speedY);
    }

    public void moveDown(){
        if(paddle.getY()<=sHeight-height)
            paddle.setY(paddle.getY()+speedY);
    }



}
