import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class NewBook extends JDialog {
    public JPanel NewBookPanel;
    private JTextField BookTitletxt;
    private JTextField Subjecttxt;
    private JTextField AuthorText;
    private JButton Savebtn;
    private JTextField ShelfNumberText;
    private JTextField Quantity;
    private JTextField IDtxt;
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
            try {



                Class.forName("com.mysql.cj.jdbc.Driver");
                connection= DriverManager.getConnection("jdbc:mysql://localhost/librarydb","root","root");
                statement=connection.createStatement();
               //String mysql="INSERT INTO  + "values("++','+"++','"++"','"++"','"++"','"++")"
                String mysql="INSERT INTO books(id,title,subject,author,shelf_no,Quantity)" + "VALUES(?, ? ,? ,?,?,?)";
                PreparedStatement ps=connection.prepareStatement(mysql);
                ps.setString(1,id);
                ps.setString(2,BookTitle);
                ps.setString(3,subject);
                ps.setString(4,Author);
                ps.setString(5,ShelfNumber);
                ps.setString(6,amount);
                int addrows = ps.executeUpdate();

                statement.close();
                ps.close();


                JOptionPane.showMessageDialog(NewBookPanel,"The book is added Successfully !");
                    dispose();
                connection.close();
            } catch (ClassNotFoundException | SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    });
}
}
