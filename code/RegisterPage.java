import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterPage extends JFrame implements ActionListener, KeyListener {

    ImageIcon box = new ImageIcon("file/txz/box.jpg");
    JTextField userName = new JTextField();
    JPasswordField passWord = new JPasswordField();
    JPasswordField repassWord = new JPasswordField();

    JLabel useLabel = new JLabel("Username");
    JLabel passLabel = new JLabel("Password");

    JLabel rewrite = new JLabel("rewrite");
    JLabel picture = new JLabel(box);

    JButton signUP = new JButton("Sign Up");

    public RegisterPage() throws Exception {
        userName.setBounds(270, 85, 250, 40);
        passWord.setBounds(270, 165, 250, 40);
        repassWord.setBounds(270, 245, 250, 40);

        repassWord.addKeyListener(this);

        useLabel.setBounds(50, 85, 200, 40);
        passLabel.setBounds(50, 165, 200, 40);
        rewrite.setBounds(50, 245, 200, 40);


        userName.setFont(new Font("微雅软黑", Font.PLAIN, 25));
        passWord.setFont(new Font("微软雅黑", Font.PLAIN, 25));
        repassWord.setFont(new Font("微软雅黑", Font.PLAIN, 25));

        useLabel.setFont(new Font("04b30", Font.PLAIN, 25));
        useLabel.setForeground(Color.cyan);
        passLabel.setFont(new Font("04b30", Font.PLAIN, 25));
        passLabel.setForeground(Color.cyan);
        rewrite.setFont(new Font("04b30", Font.PLAIN, 25));
        rewrite.setForeground(Color.cyan);

        picture.setBounds(0, 0, 600, 500);

        signUP.setBounds(120, 330, 350, 50);
        signUP.setFont(new Font("Goudy Stout", Font.PLAIN, 25));
        signUP.addActionListener(this);

        componont();

        Image image = ImageIO.read(new File("file/txz/icon.jpg"));
        this.setIconImage(image);

        this.setTitle("Register");
        this.setSize(620, 490);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void componont() {
        this.add(userName);
        this.add(passWord);
        this.add(repassWord);
        this.add(useLabel);
        this.add(passLabel);
        this.add(rewrite);
        this.add(signUP);
        this.add(picture);
    }

    public boolean zhengZhe(String mima) throws Exception {
        Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");//java 中正则起手式

        Matcher m = pattern.matcher(mima);

        return m.matches();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String usernameinText = userName.getText();
        String passwordInField = new String(passWord.getPassword());
        String repasswordInField = new String(repassWord.getPassword());

        try {
            if (!zhengZhe(passwordInField)) {
                JOptionPane.showMessageDialog(this, "密码需包含英文数字和至少一个特殊字符且不少于8位","格式错误", JOptionPane.ERROR_MESSAGE);
                System.out.println("密码错误");
                passWord.setText("");
                repassWord.setText("");
                System.out.println("欢迎！");
                return;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        if (passwordInField.equals(repasswordInField)) {
            try {
                setUser(usernameinText, passwordInField);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "密码与重复输入密码不一致。", "推箱子注册", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setUser(String user1, String password1) throws ClassNotFoundException, SQLException {
        Connection connection=null;
        Statement statement = null;
        ResultSet rs=null;
        try {
            String all = "\'" + user1 + "\'" + "," + "\'" + password1 + "\'";

            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/user?useSSL=FALSE&serverTimezone=UTC";
            String user = "root";
            String password = "mysql$yingzhe";

            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();

            rs=statement.executeQuery("select * from user where name='"+user1+"'");

            if(rs.next())//next移动，查询到记录返回true
            {  JOptionPane.showMessageDialog(this, "用户名存在", "注册", JOptionPane.ERROR_MESSAGE);}
            else {
                String sql = "insert into user (name ,password) value " + "(" + all + ")";

                System.out.println(sql);

                statement.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, "注册成功，请返回登录界面。", "推箱子注册", JOptionPane.INFORMATION_MESSAGE);
            }

            if (connection == null) {
                System.out.println("连接失败");
            } else {
                System.out.println("连接成功");
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (statement!=null){
                statement.close();
            }if (rs!=null){
                rs.close();
            }if (connection != null){
                connection.close();
            }//释放内存
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            String usernameinText = userName.getText();
            String passwordInField = new String(passWord.getPassword());
            String repasswordInField = new String(repassWord.getPassword());

            try {
                if (!zhengZhe(passwordInField)) {
                    JOptionPane.showMessageDialog(this, "密码需包含英文数字和至少一个特殊字符且不少于8位","格式错误", JOptionPane.ERROR_MESSAGE);
                    System.out.println("密码错误");
                    passWord.setText("");
                    repassWord.setText("");
                    System.out.println("欢迎！");
                    return;
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            if (passwordInField.equals(repasswordInField)) {
                try {
                    setUser(usernameinText, passwordInField);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "密码与重复输入密码不一致。", "推箱子注册", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
