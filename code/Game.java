

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;


public class Game extends JFrame implements ActionListener {
    JMenuBar menuBar = new JMenuBar();

    JMenu opration = new JMenu("选项");
    JMenu game = new JMenu("游戏");
    JMenu help = new JMenu("帮助");

    //定义初始菜单列表

    JMenu menuInMenu = new JMenu("菜单中菜单");
    JMenuItem menuItem = new JMenuItem("菜单选项");

    ButtonPanel buttonPanel = new ButtonPanel();

    public Game() throws Exception {
        this.setSize(730, 655);
        this.setTitle("推箱子");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        BufferedImage image = ImageIO.read(new File("file/txz/icon.jpg"));
        this.setResizable(false);//设置窗口不可动
        this.setLayout(null);//居中布局
        this.setIconImage(image);//设置基本标致
        this.add(menuBar);//添加顶部布局

        addPanel();
        addMenu();//设置顶部布局
        addListener();

        this.setVisible(true);
    }

    private void addMenu() {
        menuBar.setBounds(0, 0, 730, 20);
        menuBar.add(opration);
        menuBar.add(game);
        menuBar.add(help);

        opration.add(menuInMenu);
        menuInMenu.add(menuItem);
    }

    private void addPanel() {
        this.add(Gamepanel.getGamepanel());
        this.add(buttonPanel);
    }

    public static void main(String[] args) throws Exception {
        new Game();
    }

    private void addListener() {
        buttonPanel.firstBt.addActionListener(this);
        buttonPanel.nextBt.addActionListener(this);
        buttonPanel.lastBt.addActionListener(this);
        buttonPanel.choiceBt.addActionListener(this);
        buttonPanel.preBt.addActionListener(this);
        buttonPanel.replay.addActionListener(this);
        buttonPanel.back.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        //System.out.println(source);//source打印

        if (source == buttonPanel.firstBt) {//首关
           Level.Level=0;
           Gamepanel.getGamepanel().readMap(Level.Level);
           //重新加载地图
           Chehuistack.getChehuistack().mapstack.clear();
           //清空数组撤回列表，重新建立
            long start =System.currentTimeMillis();
            Time.getstart(start);
            Gamepanel.getGamepanel().setEnabled(true);

        } else if (source == buttonPanel.preBt) {//上一关
            Gamepanel.getGamepanel().setEnabled(true);
            if (Level.Level != 0) {
                Chehuistack.getChehuistack().mapstack.clear();

                long start =System.currentTimeMillis();
                Time.getstart(start);
                Level.Level--;
                Gamepanel.getGamepanel().readMap(Level.Level);
            }

        } else if (source == buttonPanel.nextBt) {//下一关
            Gamepanel.getGamepanel().setEnabled(true);
            if (Level.Level != 4) {
                Chehuistack.getChehuistack().mapstack.clear();

                long start =System.currentTimeMillis();
                Time.getstart(start);
                Level.Level++;
                Gamepanel.getGamepanel().readMap(Level.Level);
            }
        } else if (source == buttonPanel.lastBt) {//最后一关
            Gamepanel.getGamepanel().setEnabled(true);
            Chehuistack.getChehuistack().mapstack.clear();

            long start =System.currentTimeMillis();
            Time.getstart(start);
            Level.Level=4;
            Gamepanel.getGamepanel().readMap(Level.Level);
        } else if (source == buttonPanel.choiceBt) {//选择关
            Object choice[] = {0,1,2,3,4};
            Object checked = JOptionPane.showInputDialog(this,"请选择关卡：","选择关",JOptionPane.INFORMATION_MESSAGE,null,choice,choice[0]);

            Level.Level=(int)checked;
            Gamepanel.getGamepanel().setEnabled(true);
            Chehuistack.getChehuistack().mapstack.clear();

            Gamepanel.getGamepanel().readMap(Level.Level);
            long start =System.currentTimeMillis();
            Time.getstart(start);

        }else if (source==buttonPanel.replay){//重玩
            Gamepanel.getGamepanel().setEnabled(true);
            Chehuistack.getChehuistack().mapstack.clear();

            long start =System.currentTimeMillis();
            Time.getstart(start);
            Gamepanel.getGamepanel().readMap(Level.Level);

        }else if (source == buttonPanel.back){//撤回

            if (! Chehuistack.getChehuistack().mapstack.isEmpty()){
                char[][] guoji =   Chehuistack.getChehuistack().mapstack.pop();

                for (int i = 0; i < guoji.length; i++) {
                    for (int j = 0; j < guoji[i].length; j++) {
                        Gamepanel.getGamepanel().tempmap[i][j] = guoji[i][j];
                    }
                }//将拿到的的地图重新赋给temper暂存图
                System.out.println("123");
            }
            Gamepanel.getGamepanel().houChe();//拿到判断外，一定要重获焦点
        }
    }
}

