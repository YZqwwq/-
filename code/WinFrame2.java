import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class WinFrame2 extends JFrame implements ActionListener{
    ImageIcon back = new ImageIcon("file/txz/back.jpg");
    JLabel background =new JLabel(back);

    JLabel passtime = new JLabel("通关时间:");
    JLabel username = new JLabel("用户   :");

    JLabel ptText = new JLabel(Time.getDur()+"s");
    JLabel usText = new JLabel(login.getUser());

    JButton wobutton = new JButton("制作者名单");
    JButton replay   = new JButton("重玩");

    public WinFrame2() throws Exception {
        background.setBounds(0, 0, 450, 300);

        passtime.setBounds(25, 120, 130, 40);//45,120,110,40
        username.setBounds(50, 40, 110, 40);//20,40,130,40

        passtime.setFont(new Font("茶末余香行书", Font.PLAIN, 30));
        passtime.setForeground(Color.orange);

        username.setFont(new Font("茶末余香行书", Font.PLAIN, 30));
        username.setForeground((Color.orange));

        ptText.setBounds(175, 120, 200, 40);//165,120,200,40
        usText.setBounds(175, 25, 200, 60);//165,40,200,40

        ptText.setFont(new Font("茶末余香行书", Font.PLAIN, 20));
        ptText.setForeground((Color.orange));

        usText.setFont(new Font("茶末余香行书", Font.PLAIN, 40));
        usText.setForeground((Color.orange));

        wobutton.setBounds(300,190,100,50);
        replay.setBounds(80,190,100,50);

        wobutton.addActionListener(this);
        replay.addActionListener(this);


        this.setSize(450, 300);
        this.setTitle("推箱子");
        this.setLocationRelativeTo(null);
        BufferedImage image = ImageIO.read(new File("file/txz/icon.jpg"));
        this.setResizable(false);
        this.setLayout(null);
        this.setIconImage(image);

        componont();

        this.setVisible(true);
    }

    private void componont(){
        add(passtime);
        add(username);
        add(ptText);
        add(usText);
        add(wobutton);
        add(replay);
        add(background);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source==wobutton){
            Worker.getWorker();
            this.dispose();
        }else if (source==replay){
            Gamepanel.getGamepanel().setEnabled(true);
            Gamepanel.getGamepanel().tempmap=Gamepanel.getGamepanel().getMap();
            Gamepanel.getGamepanel().readMap(Level.Level);
            this.dispose();
        }

    }
}
