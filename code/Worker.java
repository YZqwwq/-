import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Worker extends JFrame implements MouseListener {

    ImageIcon image = new ImageIcon("file/txz/hh.png");

    JLabel picture = new JLabel(image);

    JLabel thanks = new JLabel("感谢游玩");

    private static Worker worker;

    static {
        try {
            worker = new Worker();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Worker() throws Exception {
        thanks.setBounds(330, 430, 130, 30);
        thanks.setFont(new Font("微雅软黑", Font.BOLD, 25));
        thanks.setForeground(Color.getHSBColor(50, 54, 4));

        picture.setBounds(0, 0, 782, 505);

        BufferedImage image2 = ImageIO.read(new File("file/txz/icon.jpg"));

        thanks.addMouseListener(this);

        this.setIconImage(image2);
        this.setSize(782, 505);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);

        componont();

        this.setVisible(true);
    }

    public static Worker getWorker() {
        return worker;
    }

    private void componont() {
        add(thanks);
        add(picture);
    }

    public static void main(String[] args) throws Exception {
        new Worker();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Gamepanel.getGamepanel().setEnabled(true);
        this.dispose();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        thanks.setForeground(Color.cyan);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        thanks.setForeground(Color.getHSBColor(50, 54, 4));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        thanks.setForeground(Color.cyan);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        thanks.setForeground(Color.getHSBColor(50, 54, 4));
    }

}
