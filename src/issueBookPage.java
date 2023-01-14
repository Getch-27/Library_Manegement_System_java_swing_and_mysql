import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class issueBookPage extends JDialog{
    public JPanel IssueBookPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox DepartmentCmb;
    private JTextField textField3;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JButton issueButton;
    private JTextField SubjectText;
    private JTextField AuthorTxet;
    private JTextField ShelfNoText;
    private JTextField QuantityText;
    private JTextField SearchText;
    private JButton SearchButton;
    private JTextField IDtext;

    public issueBookPage() {
    DepartmentCmb.addItem("Computer");
    DepartmentCmb.addItem("Accounting");
    DepartmentCmb.addItem("Managment");

        SearchButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {

                try {
                    String Searched=SearchText.getText();
                    String mysql = "SELECT *FROM books WHERE title='"+Searched+"'";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/librarydb", "root", "root");
                  Statement statement = connection.createStatement();
                   ResultSet resultSet = statement.executeQuery(mysql);
                   if(resultSet.next()){
                       ResultSetMetaData metaData=resultSet.getMetaData();
                       Vector<Object> Elements= new Vector<>();
                       int columnCount=metaData.getColumnCount();
                       for(int columnNumber=1;columnNumber<= columnCount;columnNumber++){
                           Elements.add(resultSet.getObject(columnNumber));
                       }
                       IDtext.setText(String.valueOf((int) Elements.get(0)));
                       SubjectText.setText((String) Elements.get(2));
                       AuthorTxet.setText((String) Elements.get(3));
                       ShelfNoText.setText(String.valueOf((int) Elements.get(4)));
                       QuantityText.setText(String.valueOf((int) Elements.get(5)));

                   }else {
                       JOptionPane.showMessageDialog(IssueBookPanel,"no such book");
                   }


                    statement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
