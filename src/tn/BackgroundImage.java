package tn;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
//
public class BackgroundImage {

    BufferedImage image;//主图片
    BufferedImage image1, image2,image_over,image_yun;//轮播图片
    int x1, x2;//两个图片的坐标
    int x_yun=800,y_yun=50;
    int x_over=240,y_over=50;

    Graphics2D g2;//美工
    static final int SPEED = 3;//速度

    //背景类构造器（用来初始化类）
    public BackgroundImage() {

        try {
            //读取背景图片
            //getClass方法可以把图片打入jar包，而new File()不行； image1 = ImageIO.read((getClass().getClassLoader().getResource("image/image1.png")));
            image1 = ImageIO.read(new File("image/map.png"));
            image2 = ImageIO.read(new File("image/map1.png"));
            image_over = ImageIO.read(new File("image/game_over.png"));
            image_yun = ImageIO.read(new File("image/yun.png"));

} catch (Exception e) {
        e.printStackTrace();
        }
        //设置图1 图2 的X坐标
        x1 = 0;
        x2 = 734;
        //新建缓存图image;绘图到g2;g2画出图1、图2
        image = new BufferedImage(734, 286, BufferedImage.TYPE_INT_BGR);
        g2 = image.createGraphics();
        g2.drawImage(image1, x1, 0, null);
        g2.drawImage(image2, x2, 0, null);
        }

    //背景滚动方法
    public void roll() {
        //图1的滚动
        x1 -= SPEED;
        if (x1 <= -734) {
            x1 = 734;
        }
        g2.drawImage(image1, x1, 0, null);

        //图2的滚动
        x2 -= SPEED;
        if (x2 <= -734) {
            x2 = 734;
        }
        g2.drawImage(image2, x2, 0, null);

        //云的滚动
        x_yun-=1;
        if (x_yun<=0){
            g2.drawImage(image_yun, x_yun, y_yun, null);
            x_yun=750;
             if ((y_yun+=25)<80)
                y_yun=25;
        }
    }


}
