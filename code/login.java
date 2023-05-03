
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.io.File;
import java.sql.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login extends JFrame implements ActionListener, MouseListener, KeyListener {
    ImageIcon box = new ImageIcon("file/txz/box.jpg");

    static JTextField usernameField = new JTextField();

    JPasswordField passwordField = new JPasswordField();

    JLabel usernameLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");

    JLabel register = new JLabel("Not Registed Yet?  Creat AN Accout");

    JLabel picture = new JLabel(box);

    JButton firstButton = new JButton("Sign IN");

    HashMap<TextAttribute, Object> hm = new HashMap<TextAttribute, Object>();


    public login() throws Exception {

        usernameField.setBounds(270, 50, 250, 50);
        passwordField.setBounds(270, 175, 250, 50);
        usernameLabel.setBounds(50, 50, 200, 50);
        passwordLabel.setBounds(50, 175, 200, 50);

        usernameField.setFont(new Font("微雅软黑", Font.PLAIN, 25));
        passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 25));
        passwordField.addKeyListener(this);

        usernameLabel.setFont(new Font("04b30", Font.PLAIN, 25));
        usernameLabel.setForeground(Color.cyan);

        passwordLabel.setFont(new Font("04b30", Font.PLAIN, 25));
        passwordLabel.setForeground(Color.cyan);

        firstButton.setBounds(120, 280, 350, 80);
        firstButton.setFont(new Font("Goudy Stout", Font.PLAIN, 25));
        firstButton.addActionListener(this);

        hm.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON); // 定义是否有下划线
        hm.put(TextAttribute.SIZE, 12); // 定义字号
        hm.put(TextAttribute.FAMILY, "Simsun"); // 定义字体名
        Font font = new Font(hm); // 生成字号为12，字体为宋体，字形带有下划线的字体

        register.setBounds(140, 380, 210, 20);
        register.setForeground(Color.RED);
        register.setFont(font);
        register.addMouseListener(this);

        picture.setBounds(0, 0, 600, 500);

        componont();
        //设置窗口属性
        Image image = ImageIO.read(new File("file/txz/icon.jpg"));

        this.setIconImage(image);
        this.setTitle("firstwindow");
        this.setSize(620, 490);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);

    }

    public static String getUser() {
        return usernameField.getText();
    }


    private void componont() {

        this.add(usernameLabel);
        this.add(usernameField);
        this.add(passwordField);
        this.add(passwordLabel);
        this.add(register);
        this.add(firstButton);
        this.add(picture);
    }



    private boolean getUser(String usernameField, String passwordField) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/user?useSSL=FALSE&serverTimezone=UTC";
            String user = "root";
            String password = "mysql$yingzhe";

            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();

            String sql = ("select * from user where name='" + usernameField + "' ");

            rs = statement.executeQuery(sql);

            if (rs.next()) {
                String ps = rs.getString("password");
                if (ps.equals(passwordField)) {
                    return true;
                }
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }//释放内存

        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        new login();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String passwordInField = new String(passwordField.getPassword());
            String usernameFieldText = usernameField.getText();

            try {
                if (getUser(usernameFieldText, passwordInField)) {
                    this.dispose();
                    try {
                        Game game = new Game();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    long start = System.currentTimeMillis();
                    Time.getstart(start);

                    Music.music();

                    System.out.println("欢迎！");
                } else {
                    JOptionPane.showMessageDialog(this, "密码错误", "账号错误", JOptionPane.ERROR_MESSAGE);
                    System.out.println("密码错误");
                    passwordField.setText("");
                    usernameField.setText("");
                    System.out.println("欢迎！");
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String passwordInField = new String(passwordField.getPassword());//作为输入的值，password并不是String
        String usernameFieldText = usernameField.getText();


        try {
            if (getUser(usernameFieldText, passwordInField)) {
                this.dispose();
                try {
                    Game game = new Game();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                long start = System.currentTimeMillis();
                Time.getstart(start);

                Music.music();

                System.out.println("欢迎！");
            } else {
                JOptionPane.showMessageDialog(this, "密码错误", "账号错误", JOptionPane.ERROR_MESSAGE);
                System.out.println("密码错误");
                passwordField.setText("");
                usernameField.setText("");
                System.out.println("欢迎！");
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        register.setForeground(Color.cyan);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        register.setForeground(Color.black);
        try {
            RegisterPage registerPage = new RegisterPage();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        register.setForeground(Color.black);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        register.setForeground(Color.red);
    }
}
