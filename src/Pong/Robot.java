package Pong;

public class Robot {

    private Paddle paddle;

    public Robot(Paddle paddle) {
        this.paddle = paddle;
    }

    public void move(double target){
        if(paddle.getPaddle().getY()+paddle.getHeight()/2>target) paddle.moveUp();
        if(paddle.getPaddle().getY()+paddle.getHeight()/2<target) paddle.moveDown();
    }
}
