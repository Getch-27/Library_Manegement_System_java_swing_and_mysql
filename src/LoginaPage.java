import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginaPage extends JDialog{
    private JTextField usernametxt;
    private JPasswordField passwordTxt;
    private JButton logInButton;
    private JButton resetButton;
    public  JPanel loginPanne;
    Connection connection;
    Statement statement;
    ResultSet resultSet;

public LoginaPage() {
    resetButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
              usernametxt.setText("");
              passwordTxt.setText("");
        }
    });
    logInButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                 String username=usernametxt.getText();
                 String password=passwordTxt.getText();


                Class.forName("com.mysql.cj.jdbc.Driver");
                connection= DriverManager.getConnection("jdbc:mysql://localhost/librarydb","root","root");
                statement=connection.createStatement();
                resultSet= statement.executeQuery("SELECT *FROM librarian WHERE username='"+username+"' and password='"+password+"'");
                if(resultSet.next()){

                  dispose();
                    HomePage homePage=new HomePage();
                    homePage.setContentPane(homePage.homepanel);
                    homePage.setBounds(300,100,700,600);
                    homePage.show();
                }else{
                    JOptionPane.showMessageDialog(loginPanne,"dsf");
                    usernametxt.setText("");
                    passwordTxt.setText("");
                }
                connection.close();
            } catch (ClassNotFoundException | SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    });
}

    public static void main(String[] args) {
        LoginaPage loginaPage=new LoginaPage();
        loginaPage.setContentPane(loginaPage.loginPanne);
        loginaPage.setBounds(100,200,400,300);
        loginaPage.show();
    }
}
