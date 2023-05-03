
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Map {
    private char[][] map;

    private int monkeyX;
    private int monkeyY;
    public Map(int level) {

        map = new char[20][20];//创建基础地图

        Scanner Scanner = null;
        try {
            Scanner = new Scanner(new File("file/map/" + level + ".map"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }//抓取可能发生的异常

        int tempY = 0;

        while (Scanner.hasNextLine()) {
            String line = Scanner.nextLine();
            //System.out.println(line);

            char[] charArray = line.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                if (charArray[i] == '5') {
                    monkeyX = tempY;//tempY为行
                    monkeyY = i;//i代表列
                }
            }
            map[tempY] = charArray;//将每一行存入map
            tempY++;
        }//存放地图，定位人物对象位置

    }

    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

    public int getMonkeyX() {
        return monkeyX;
    }

    public void setMonkeyX(int monkeyX) {
        this.monkeyX = monkeyX;
    }

    public int getMonkeyY() {
        return monkeyY;
    }

    public void setMonkeyY(int monkeyY) {
        this.monkeyY = monkeyY;
    }

}
