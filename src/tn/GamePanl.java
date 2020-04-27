package tn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
//
/**
 * 继承窗体面板的图片
 */
@SuppressWarnings("all")
public class GamePanl extends JPanel implements KeyListener {
    BufferedImage image;//主图片
    Graphics2D g2;      //绘图工具，美工

    BackgroundImage background;
    kLong klong;//恐龙
    List<object> list = new ArrayList<object>();//障碍物集合
    object ob=new object();
    int addObjectTimer = 0;//新增障碍物计时器

    boolean finish = false;//游戏结束
    static final int FRESH = 10;//刷新时间，毫秒

    int score=0;//分数
    int addScoreTimer=0;

    //无参构造器
    public GamePanl() {
        image = new BufferedImage(734, 286, BufferedImage.TYPE_INT_BGR);
        g2 = image.createGraphics();
        background = new BackgroundImage();//实例化背景图片
        klong = new kLong();//实例化恐龙类
        list.add(new object());

        FreshThread t = new FreshThread(this);//刷新此线程
        t.start();
    }

    //绘制图片方法
    private void painImage() {
        //画背景及方法
        g2.drawImage(background.image, 0, 0, this);//绘制背景
        g2.drawImage(background.image_yun, background.x_yun,background.y_yun, this);//绘制白云
        background.roll();//调用滚动图片
        //画恐龙及方法
         g2.drawImage(klong.image, klong.x, klong.y, this);//绘制恐龙
          klong.move();//让小恐龙移动
        //从障碍物列表get i 并画出障碍物
        for (int i = 0; i < list.size(); i++) {
            object o = list.get(i);
            g2.drawImage(o.image, o.x, o.y, this);//绘制障碍
            o.move();
            o.bridMove();
            //判断障碍物是否和头、脚相撞
            if (o.bounds().intersects(klong.bounds1()) || o.bounds().intersects(klong.bounds2())){
                gameOver();//游戏结束
            }
        }

        if (addObjectTimer >= 1400) {//计算分数时间判断
            list.add(new object());
            addObjectTimer = 0;
        }
        addObjectTimer += FRESH;
        //分数++
        if (addObjectTimer>=50){
            score+=1;
            addScoreTimer=0;
        }
        //分数显示代码---"%05d"---指的是分数以五位数显示
        g2.drawString(String.format("%05d",score),600,35 );
        g2.drawString("Hi",536,35 );
        g2.drawString(String.format("%05d",+MainFrame.topScore),550,35 );

        addScoreTimer +=FRESH;
    }
    //重新paint方法 调上面的painImage(),并画出image
    @Override
    public void paint(Graphics g) {
        painImage();
        g.drawImage(image, 0, 0, this);
    }
    //游戏结束
    public void gameOver(){
        finish=true;
        g2.drawImage(background.image_over,background.x_over,background.y_over,null);
        if (score>MainFrame.topScore){//判断最高分
            MainFrame.topScore=score;
        }
    }

    public boolean isFinish() {
        return finish;
    }
    //键盘监听
    @Override
    public void keyTyped(KeyEvent e) {//按键类型

    }
    @Override
    public void keyPressed(KeyEvent e) {//按键按下
        int keyCode = e.getKeyCode();//获取按下的编码
        if (keyCode == KeyEvent.VK_SPACE) {
            klong.jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {//按键抬起

    }
}
