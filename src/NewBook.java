import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class NewBook extends JFrame {
    public JPanel NewBookPanel;
    private JTextField BookTitletxt;
    private JTextField Subjecttxt;
    private JTextField AuthorText;
    private JButton Savebtn;
    private JTextField ShelfNumberText;
    private JTextField Quantity;
    private JTextField IDtxt;
    private JButton homeButton;
    Connection connection;
    Statement statement;


    public NewBook() {

    Savebtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id=IDtxt.getText();
            String BookTitle=BookTitletxt.getText();
            String subject=Subjecttxt.getText();
            String Author=AuthorText.getText();
            String ShelfNumber=ShelfNumberText.getText();
            String amount=Quantity.getText();
            if(id.isEmpty() || BookTitle.isEmpty() || subject.isEmpty() || Author.isEmpty() || ShelfNumber.isEmpty() || amount.isEmpty()){
                JOptionPane.showMessageDialog(NewBookPanel,"Please fill all fields");
            }
            else {
                try {


                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost/librarydb", "root", "root");
                    statement = connection.createStatement();
                    //String mysql="INSERT INTO  + "values("++','+"++','"++"','"++"','"++"','"++")"
                    String mysql = "INSERT INTO books(id,title,subject,author,shelf_no,Quantity)" + "VALUES(?, ? ,? ,?,?,?)";
                    PreparedStatement ps = connection.prepareStatement(mysql);
                    ps.setString(1, id);
                    ps.setString(2, BookTitle);
                    ps.setString(3, subject);
                    ps.setString(4, Author);
                    ps.setString(5, ShelfNumber);
                    ps.setString(6, amount);
                    ps.executeUpdate();

                    statement.close();
                    ps.close();


                    JOptionPane.showMessageDialog(NewBookPanel, "The book is added Successfully !");
                    connection.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomePage homePage=new HomePage();
                homePage.setContentPane(homePage.homepanel);
                homePage.setBounds(200,100,900,600);
                homePage.show();
            }
        });
    }
}
