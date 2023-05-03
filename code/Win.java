
import javax.swing.*;

public class Win extends JFrame {

    public Boolean judgePanel(char[][] arr) {

            Boolean Judge = true;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    if (arr[i][j] == '3') {
                        Judge = false;
                    }
                }
            }
        long time =System.currentTimeMillis();
            Time.getend(time);
            return Judge;
    }

}
