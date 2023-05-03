

import javax.swing.*;
import java.awt.*;
public class ButtonPanel extends JPanel {
    JButton firstBt = new JButton("第一关");
    JButton back = new JButton("撤回");

    JButton lastBt = new JButton("最终关");

    JButton nextBt = new JButton("下一关");

    JButton preBt = new JButton("上一关");

    JButton choiceBt = new JButton("选择关");

    JButton replay = new JButton("重玩");



    public ButtonPanel() {
        this.setBounds(600, 20, 120, 600);
        this.setBackground(new Color(0xD3718B));

        addButton();

    }


    private void addButton() {
        Box verticalBox = Box.createVerticalBox();
        //创建盒模型，为项目加支撑
        verticalBox.add(Box.createVerticalStrut(30));
        verticalBox.add(replay);
        verticalBox.add(Box.createVerticalStrut(50));
        verticalBox.add(back);
        verticalBox.add(Box.createVerticalStrut(50));
        verticalBox.add(firstBt);
        verticalBox.add(Box.createVerticalStrut(50));
        verticalBox.add(lastBt);
        verticalBox.add(Box.createVerticalStrut(50));
        verticalBox.add(nextBt);
        verticalBox.add(Box.createVerticalStrut(50));
        verticalBox.add(preBt);
        verticalBox.add(Box.createVerticalStrut(50));
        verticalBox.add(choiceBt);

        this.add(verticalBox);
    }

}
