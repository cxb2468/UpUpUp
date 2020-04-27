package tn;

import javax.swing.*;
import java.awt.*;
//
public class MainFrame extends JFrame {
    GamePanl p;
    public   static  int topScore=0;//最高分

    //无参构造
    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口关闭
        p = new GamePanl();//获取窗体的主容器
        Container c = getContentPane();
        c.add(p);//把面板添加到主容器中
        addKeyListener(p);//添加键盘监听

    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        //窗体大小
        frame.setBounds(340, 220, 734, 320);
        //窗体可见
        frame.setVisible(true);
        //屏幕居中
        frame.setLocationRelativeTo(null);
        frame.setTitle("跳一跳");
    }


    //复活restart()，把死掉的东西删掉，重新来过
    public void restart(){
        Container c=getContentPane();
        c.removeAll(); //删除所有死的组件

        GamePanl gp=new GamePanl();
        c.add(gp);
        addKeyListener(gp);
        c.validate();//重新验证容器组件
    }
}
