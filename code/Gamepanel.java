

import javax.crypto.spec.PSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class Gamepanel extends JPanel implements KeyListener {//继承后成为panel，在内部设定即可
    static int a = 1;
    private static char[][] map;
    protected char[][] tempmap;

    public void setMonkeyX(int monkeyX) {
        this.monkeyX = monkeyX;
    }

    public void setMonkeyY(int monkeyY) {
        this.monkeyY = monkeyY;
    }

    private int monkeyX;
    private int monkeyY;
    //创建地图
    Win win = new Win();

    Toolkit toolkit = Toolkit.getDefaultToolkit();//从磁盘中导出

    private char wall = '1';//不可移动

    private char tree = '2';//可覆盖

    private char box = '3';//可移动遇墙不可移动

    private char destination = '4';

    private char monkeyDown = '5';

    private char monkeyLeft = '6';

    private char monkeyRight = '7';

    private char monkeyUp = '8';

    private char finalBox = '9';

    Image[] images = {
            toolkit.getImage("file/txz/0.png"),
            toolkit.getImage("file/txz/1.png"),
            toolkit.getImage("file/txz/2.png"),
            toolkit.getImage("file/txz/3.png"),
            toolkit.getImage("file/txz/4.png"),
            toolkit.getImage("file/txz/5.png"),
            toolkit.getImage("file/txz/6.png"),
            toolkit.getImage("file/txz/7.png"),
            toolkit.getImage("file/txz/8.png"),
            toolkit.getImage("file/txz/9.png"),
    };

    @Override
    public void paint(Graphics g) {//无需使用会被自动调用一次
        super.paint(g);
        for (int i = 0; i < tempmap.length; i++) {
            for (int j = 0; j < tempmap[i].length; j++) {
                char point = tempmap[i][j];
                int index = Integer.parseInt(String.valueOf(point));
                g.drawImage(images[index], j * 30, i * 30, 30, 30, this);
            }
        }
        this.requestFocus();//不需要
    }

    private static Gamepanel gamepanel = new Gamepanel();

    public static Gamepanel getGamepanel() {
        return gamepanel;
    }//设置为单例模式

    private Gamepanel() {
        this.setSize(600, 600);
        this.setLocation(0, 20);
        this.setBackground(new Color(0xFF45F3E1, true));

        this.addKeyListener(this);//添加监听器

        readMap(0);
    }

    public void readMap(int level1) {
        Map map = new Map(Level.Level);//源地图

        tempmap = new Map(Level.Level).getMap();//临时地图
        this.map = map.getMap();

        this.monkeyX = map.getMonkeyX();//人物行位置
        this.monkeyY = map.getMonkeyY();//人物列位置

        this.requestFocus();//重绘后请求焦点
        repaint();
        //读取后重绘地图
    }

    public void houChe() {
        for (int i = 0; i < tempmap.length; i++) {
            for (int j = 0; j < tempmap[i].length; j++) {
                if (tempmap[i][j] == '5' || tempmap[i][j] == '6' || tempmap[i][j] == '7' || tempmap[i][j] == '8') {
                    this.monkeyX = i;
                    this.monkeyY = j;
                    System.out.println(i);
                    System.out.println(j);
                }
            }
        }
        System.out.println("123");
        this.requestFocus();
        repaint();
    }

    public char[][] getMap() {
        return map;
    }

    public void judgeWin() throws Exception {
        if (win.judgePanel(tempmap) == true && Level.Level != 4) {
            Chehuistack.getChehuistack().mapstack.clear();
            this.setEnabled(false);
            new WinFrame();
        } else if (win.judgePanel(tempmap) == true && Level.Level == 4) {
            Chehuistack.getChehuistack().mapstack.clear();
            this.setEnabled(false);
            new WinFrame2();
        }
    }

    private void goLeft() {
        cunMap();

        if (tempmap[monkeyX][monkeyY - 1] == '1') {
            tempmap[monkeyX][monkeyY] = '6';//turn left
        }//1单人物，左侧无箱子，不能走

        if (tempmap[monkeyX][monkeyY - 1] == '3' && tempmap[monkeyX][monkeyY - 2] == '1') {//2箱子到头
            tempmap[monkeyX][monkeyY] = '6';
        }
        if (tempmap[monkeyX][monkeyY - 1] == '9' && tempmap[monkeyX][monkeyY - 2] == '1') {//3箱子到头且终点
            tempmap[monkeyX][monkeyY] = '6';
        }

        //左边有两箱子
        if (tempmap[monkeyX][monkeyY - 1] == '3' && tempmap[monkeyX][monkeyY - 2] == '3') {
            tempmap[monkeyX][monkeyY] = '6';
        }

        if (tempmap[monkeyX][monkeyY - 1] == '9' && tempmap[monkeyX][monkeyY - 2] == '3') {
            tempmap[monkeyX][monkeyY] = '6';
        }

        if (tempmap[monkeyX][monkeyY - 1] == '9' && tempmap[monkeyX][monkeyY - 2] == '9') {
            tempmap[monkeyX][monkeyY] = '6';
        }

        if (tempmap[monkeyX][monkeyY - 1] == '3' && tempmap[monkeyX][monkeyY - 2] == '9') {
            tempmap[monkeyX][monkeyY] = '6';
        }

        if (tempmap[monkeyX][monkeyY - 1] != '3' && tempmap[monkeyX][monkeyY - 1] != '9') {//4单人物，左侧无箱子，能走
            if (tempmap[monkeyX][monkeyY - 1] != '1') {
                tempmap[monkeyX][monkeyY - 1] = '6';//左侧一格变为向左人物
                if (map[monkeyX][monkeyY] == '4') {
                    tempmap[monkeyX][monkeyY] = '4';//初始为人物时修正为森林
                } else tempmap[monkeyX][monkeyY] = '2';

                monkeyY--;//修订monkeyY位置

            }

        } else if (tempmap[monkeyX][monkeyY - 1] == '3' && tempmap[monkeyX][monkeyY - 2] != '1') {//5单人物左侧有箱子
            if (tempmap[monkeyX][monkeyY - 2] != '3' && tempmap[monkeyX][monkeyY - 2] != '9') {//能走
                tempmap[monkeyX][monkeyY - 1] = '6';
                if (map[monkeyX][monkeyY] == '4') {
                    tempmap[monkeyX][monkeyY] = '4';//初始为人物时修正为森林
                } else tempmap[monkeyX][monkeyY] = '2';

                //上方为人物码，下方为箱子码
                if (map[monkeyX][monkeyY - 2] == '4') {
                    tempmap[monkeyX][monkeyY - 2] = '9';
                } else tempmap[monkeyX][monkeyY - 2] = '3';
                monkeyY--;

            }
        } else if (tempmap[monkeyX][monkeyY - 1] == '9' && tempmap[monkeyX][monkeyY - 2] != '1') {//6箱子位置为终点，可动
            if (tempmap[monkeyX][monkeyY - 2] != '3' && tempmap[monkeyX][monkeyY - 2] != '9') {
                tempmap[monkeyX][monkeyY - 1] = '6';
                if (map[monkeyX][monkeyY] == '4') {
                    tempmap[monkeyX][monkeyY] = '4';//初始为人物时修正为森林
                } else tempmap[monkeyX][monkeyY] = '2';

                //上方为人物，下方为箱子
                if (map[monkeyX][monkeyY - 2] == '4') {
                    tempmap[monkeyX][monkeyY - 2] = '9';
                } else tempmap[monkeyX][monkeyY - 2] = '3';
                monkeyY--;

            }
        }

        repaint();// 此处走一步动了两次？？
    }

    /*想左走后改变tempmap，重绘地图（repain）
     * 人物monkeyX，monkeyY
     * this.monkeyX = map.getMonkeyX();
     * this.monkeyY = map.getMonkeyY();
     * 与原地图map比较得到位置*/

    private void goRight() {
        cunMap();

        if (tempmap[monkeyX][monkeyY + 1] == '1') {
            tempmap[monkeyX][monkeyY] = '7';//turn right
        }//1单人物，右侧无箱子，不能走

        if (tempmap[monkeyX][monkeyY + 1] == '3' && tempmap[monkeyX][monkeyY + 2] == '1') {//2箱子到头
            tempmap[monkeyX][monkeyY] = '7';
        }
        if (tempmap[monkeyX][monkeyY + 1] == '9' && tempmap[monkeyX][monkeyY + 2] == '1') {//3箱子到头且终点
            tempmap[monkeyX][monkeyY] = '7';
        }

        if (tempmap[monkeyX][monkeyY + 1] == '3' && tempmap[monkeyX][monkeyY + 2] == '3') {
            tempmap[monkeyX][monkeyY] = '7';
        }

        if (tempmap[monkeyX][monkeyY + 1] == '9' && tempmap[monkeyX][monkeyY + 2] == '3') {
            tempmap[monkeyX][monkeyY] = '7';
        }

        if (tempmap[monkeyX][monkeyY + 1] == '9' && tempmap[monkeyX][monkeyY + 2] == '9') {
            tempmap[monkeyX][monkeyY] = '7';
        }

        if (tempmap[monkeyX][monkeyY + 1] == '3' && tempmap[monkeyX][monkeyY + 2] == '9') {
            tempmap[monkeyX][monkeyY] = '7';
        }
        if (tempmap[monkeyX][monkeyY + 1] != '3' && tempmap[monkeyX][monkeyY + 1] != '9') {  //4单人物，右侧无箱子，能走
            if (tempmap[monkeyX][monkeyY + 1] != '1') {
                tempmap[monkeyX][monkeyY + 1] = '7';//左侧一格变为向左人物
                if (map[monkeyX][monkeyY] == '4') {
                    tempmap[monkeyX][monkeyY] = '4';//初始为人物时修正为森林
                } else tempmap[monkeyX][monkeyY] = '2';

                monkeyY++;//修订monkeyY位置
            }
        } else if (tempmap[monkeyX][monkeyY + 1] == '3' && tempmap[monkeyX][monkeyY + 2] != '1') {//5单人物右侧有箱子
            if (tempmap[monkeyX][monkeyY + 2] != '3' && tempmap[monkeyX][monkeyY + 2] != '9') {
                tempmap[monkeyX][monkeyY + 1] = '7';
                if (map[monkeyX][monkeyY] == '4') {
                    tempmap[monkeyX][monkeyY] = '4';//初始为人物时修正为森林
                } else tempmap[monkeyX][monkeyY] = '2';

                //上方为人物，下方为箱子
                if (map[monkeyX][monkeyY + 2] == '4') {
                    tempmap[monkeyX][monkeyY + 2] = '9';
                } else tempmap[monkeyX][monkeyY + 2] = '3';
                monkeyY++;

            }
        } else if (tempmap[monkeyX][monkeyY + 1] == '9' && tempmap[monkeyX][monkeyY + 2] != '1') {//6箱子位置为终点，可动
            if (tempmap[monkeyX][monkeyY + 2] != '3' && tempmap[monkeyX][monkeyY + 2] != '9') {
                tempmap[monkeyX][monkeyY + 1] = '7';
                if (map[monkeyX][monkeyY] == '4') {
                    tempmap[monkeyX][monkeyY] = '4';//初始为人物时修正为森林
                } else tempmap[monkeyX][monkeyY] = '2';

                //上方为人物，下方为箱子
                if (map[monkeyX][monkeyY + 2] == '4') {
                    tempmap[monkeyX][monkeyY + 2] = '9';
                } else tempmap[monkeyX][monkeyY + 2] = '3';
                monkeyY++;

            }
        }

        repaint();
    }

    private void goUp() {
        cunMap();

        if (tempmap[monkeyX - 1][monkeyY] == '1') {
            tempmap[monkeyX][monkeyY] = '8';//turn up
        }//1单人物，右侧无箱子，不能走

        if (tempmap[monkeyX - 1][monkeyY] == '8' && tempmap[monkeyX - 2][monkeyY] == '1') {//2箱子到头
            tempmap[monkeyX][monkeyY] = '8';
        }
        if (tempmap[monkeyX - 1][monkeyY] == '8' && tempmap[monkeyX - 2][monkeyY] == '1') {//3箱子到头且终点
            tempmap[monkeyX][monkeyY] = '8';
        }

        if (tempmap[monkeyX - 1][monkeyY] == '3' && tempmap[monkeyX - 2][monkeyY] == '3') {
            tempmap[monkeyX][monkeyY] = '8';
        }

        if (tempmap[monkeyX - 1][monkeyY] == '9' && tempmap[monkeyX - 2][monkeyY] == '3') {
            tempmap[monkeyX][monkeyY] = '8';
        }

        if (tempmap[monkeyX - 1][monkeyY] == '9' && tempmap[monkeyX - 2][monkeyY] == '9') {
            tempmap[monkeyX][monkeyY] = '8';
        }

        if (tempmap[monkeyX - 1][monkeyY] == '3' && tempmap[monkeyX - 2][monkeyY] == '9') {
            tempmap[monkeyX][monkeyY] = '8';
        }

        if (tempmap[monkeyX - 1][monkeyY] != '3' && tempmap[monkeyX - 1][monkeyY] != '9') {  //4单人物，右侧无箱子，能走
            if (tempmap[monkeyX - 1][monkeyY] != '1') {
                tempmap[monkeyX - 1][monkeyY] = '8';//左侧一格变为向左人物
                if (map[monkeyX][monkeyY] == '4') {
                    tempmap[monkeyX][monkeyY] = '4';//初始为人物时修正为森林
                } else tempmap[monkeyX][monkeyY] = '2';

                monkeyX--;//修订monkeyY位置
            }
        } else if (tempmap[monkeyX - 1][monkeyY] == '3' && tempmap[monkeyX - 2][monkeyY] != '1') {//5单人物右侧有箱子
            if (tempmap[monkeyX - 2][monkeyY] != '3' && tempmap[monkeyX - 2][monkeyY] != '9') {
                tempmap[monkeyX - 1][monkeyY] = '8';
                if (map[monkeyX][monkeyY] == '4') {
                    tempmap[monkeyX][monkeyY] = '4';//初始为人物时修正为森林
                } else tempmap[monkeyX][monkeyY] = '2';

                //上方为人物，下方为箱子
                if (map[monkeyX - 2][monkeyY] == '4') {
                    tempmap[monkeyX - 2][monkeyY] = '9';
                } else tempmap[monkeyX - 2][monkeyY] = '3';

                monkeyX--;
            }
        } else if (tempmap[monkeyX - 1][monkeyY] == '9' && tempmap[monkeyX - 2][monkeyY] != '1') {//6箱子位置为终点，可动
            if (tempmap[monkeyX - 2][monkeyY] != '3' && tempmap[monkeyX - 2][monkeyY] != '9') {
                tempmap[monkeyX - 1][monkeyY] = '8';
                if (map[monkeyX][monkeyY] == '4') {
                    tempmap[monkeyX][monkeyY] = '4';//初始为人物时修正为森林
                } else tempmap[monkeyX][monkeyY] = '2';

                //上方为人物，下方为箱子
                if (map[monkeyX - 2][monkeyY] == '4') {
                    tempmap[monkeyX - 2][monkeyY] = '9';
                } else tempmap[monkeyX - 2][monkeyY] = '3';
                monkeyX--;
            }
        }
        repaint();
    }

    private void goDown() {
        cunMap();

        if (tempmap[monkeyX + 1][monkeyY] == '1') {
            tempmap[monkeyX][monkeyY] = '5';//turn right
        }//1单人物，右侧无箱子，不能走

        if (tempmap[monkeyX + 1][monkeyY] == '3' && tempmap[monkeyX + 2][monkeyY] == '1') {//2箱子到头
            tempmap[monkeyX][monkeyY] = '5';
        }
        if (tempmap[monkeyX + 1][monkeyY] == '9' && tempmap[monkeyX + 2][monkeyY] == '1') {//3箱子到头且终点
            tempmap[monkeyX][monkeyY] = '5';
        }
        if (tempmap[monkeyX + 1][monkeyY] == '3' && tempmap[monkeyX + 2][monkeyY] == '3') {
            tempmap[monkeyX][monkeyY] = '5';
        }

        if (tempmap[monkeyX + 1][monkeyY] == '9' && tempmap[monkeyX - 2][monkeyY] == '3') {
            tempmap[monkeyX][monkeyY] = '5';
        }

        if (tempmap[monkeyX + 1][monkeyY] == '9' && tempmap[monkeyX - 2][monkeyY] == '9') {
            tempmap[monkeyX][monkeyY] = '5';
        }

        if (tempmap[monkeyX + 1][monkeyY] == '3' && tempmap[monkeyX + 2][monkeyY] == '9') {
            tempmap[monkeyX][monkeyY] = '5';
        }


        if (tempmap[monkeyX + 1][monkeyY] != '3' && tempmap[monkeyX + 1][monkeyY] != '9') {  //4单人物，右侧无箱子，能走
            if (tempmap[monkeyX + 1][monkeyY] != '1') {
                tempmap[monkeyX + 1][monkeyY] = '5';//左侧一格变为向左人物
                if (map[monkeyX][monkeyY] == '4') {
                    tempmap[monkeyX][monkeyY] = '4';//初始为人物时修正为森林
                } else tempmap[monkeyX][monkeyY] = '2';

                monkeyX++;//修订monkeyY位置
            }
        } else if (tempmap[monkeyX + 1][monkeyY] == '3' && tempmap[monkeyX + 2][monkeyY] != '1') {//5单人物右侧有箱子
            if (tempmap[monkeyX + 2][monkeyY] != '3' && tempmap[monkeyX + 2][monkeyY] != '9') {
                tempmap[monkeyX + 1][monkeyY] = '5';
                if (map[monkeyX][monkeyY] == '4') {
                    tempmap[monkeyX][monkeyY] = '4';//初始为人物时修正为森林
                } else tempmap[monkeyX][monkeyY] = '2';

                //上方为人物，下方为箱子
                if (map[monkeyX + 2][monkeyY] == '4') {
                    tempmap[monkeyX + 2][monkeyY] = '9';
                } else tempmap[monkeyX + 2][monkeyY] = '3';

                monkeyX++;
            }
        } else if (tempmap[monkeyX + 1][monkeyY] == '9' && tempmap[monkeyX + 2][monkeyY] != '1') {//6箱子位置为终点，可动
            if (tempmap[monkeyX + 2][monkeyY] != '3' && tempmap[monkeyX + 2][monkeyY] != '9') {
                tempmap[monkeyX + 1][monkeyY] = '5';
                if (map[monkeyX][monkeyY] == '4') {
                    tempmap[monkeyX][monkeyY] = '4';//初始为人物时修正为森林
                } else tempmap[monkeyX][monkeyY] = '2';

                //上方为人物，下方为箱子
                if (map[monkeyX + 2][monkeyY] == '4') {
                    tempmap[monkeyX + 2][monkeyY] = '9';
                } else tempmap[monkeyX + 2][monkeyY] = '3';

                monkeyX++;
            }
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            goLeft();
            try {
                judgeWin();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            goRight();
            try {
                judgeWin();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            goUp();
            try {
                judgeWin();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            goDown();
            try {
                judgeWin();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void cunMap() {
        char[][] chengjie = new char[20][20];
        for (int i = 0; i < tempmap.length; i++) {
            for (int j = 0; j < tempmap[i].length; j++) {
                chengjie[i][j] = tempmap[i][j];
            }
        }

        Chehuistack.getChehuistack().mapstack.push(chengjie);//每次移动将新地图加入stack队列

        Stack<char[][]> my = Chehuistack.getChehuistack().mapstack;

        /*for (int i = 0; i < my.size(); i++) {
            ToString(my.get(i));
        }*/
    }

    public void ToString(char[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


}
