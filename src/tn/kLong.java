package tn;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//
/**
 * 恐龙类
 */
public class kLong {

    BufferedImage image;//图片
    BufferedImage image1, image2, image3,image_over;
    public int x, y;//坐标

    int stepTime = 0;//计时器
    int fresh = GamePanl.FRESH;

    boolean jumpState = false;//跳跃的状态
    final int LOWEST_Y = 200;//最低坐标
    int jumpValue = 5;//跳跃的增变量

    //无参构造器
    public kLong() {
        //读取图片
        try {
            image1 = ImageIO.read(new File("image/long1.png"));
            image2 = ImageIO.read(new File("image/long2.png"));
            image3 = ImageIO.read(new File("image/long3.png"));
            image_over = ImageIO.read(new File("image/over.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置坐标
        x = 50;//起始坐标
        y = LOWEST_Y;//起始
    }

    //踏步方法
    void step() {
        int tmp = stepTime / 100 % 2;
        if (tmp == 1) {
            image = image1;
        } else {
            image = image2;
        }
        stepTime += fresh;
    }

    //移动方法
    public void move() {
        step();//踏步
        if (jumpState) {
            if (y >= LOWEST_Y) {
                jumpValue = -4;
            }
            if (y <= LOWEST_Y - 100) { //起跳高度-跳跃高度
                jumpValue = 4;
            }
            y += jumpValue;
            image = image3;
            if (y >= LOWEST_Y) {
                jumpState = false;
            }
        }
    }

    //跳跃方法
    public void jump() {
        jumpState = true;
    }

    //接触死亡区域 头y
    public Rectangle bounds1(){
        return new Rectangle(x+20,y,20,12);
    } //接触死亡区域 脚y+35
    public Rectangle bounds2(){
        return new Rectangle(x+5,y+35,20,10);
    }

}
