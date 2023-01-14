import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class issueBookPage extends JDialog{
    public JPanel IssueBookPanel;
    private JTextField IDtext;
    private JTextField FullnameText;
    private JComboBox DepartmentCmb;
    private JTextField DateText;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JButton issueButton;
    private JTextField SearchText;
    private JButton SearchButton;
    private JTable SearchTable;
    private JTextField StBookName;

    public issueBookPage() {
    DepartmentCmb.addItem("Computer");
    DepartmentCmb.addItem("Accounting");
    DepartmentCmb.addItem("Managment");
       ButtonGroup group=new ButtonGroup();
       group.add(maleRadioButton);
       group.add(femaleRadioButton);
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

                    DefaultTableModel t=BuildTable(resultSet);
                    StBookName.setText(String.valueOf(Searched));
                    SearchTable.setModel(t);
                     long millis=System.currentTimeMillis();
                     java.sql.Date date=new java.sql.Date(millis);
                    DateText.setText(String.valueOf(date));
                   if(resultSet.next()){

                       JOptionPane.showMessageDialog(IssueBookPanel,"no such book");


                   }else{

                   }
                    statement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        issueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String  ID=IDtext.getText();
                String FullName=FullnameText.getText();


                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                   Connection  connection= DriverManager.getConnection("jdbc:mysql://localhost/librarydb","root","root");
                   Statement statement=connection.createStatement();
                   //ResultSet resultSet= statement.executeQuery("SELECT *FROM librarian WHERE username='"+username+"' and password='"+password+"'");

                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }
    public static DefaultTableModel BuildTable(ResultSet resultSet) throws SQLException{
        // to get meta data of the column (int,varchar.....)
        ResultSetMetaData metaData=resultSet.getMetaData();
        //vector declaration to store column name of the table from result set.
        Vector<String> columnNames= new Vector<>();
        int columnCount=metaData.getColumnCount();
        for(int column=1;column <= columnCount;column++){
            columnNames.add(metaData.getColumnName(column));
        }
        Vector<Vector<Object>> tableData =new Vector<>();
        while (resultSet.next()){
            Vector<Object> vector=new Vector<>();
            for(int columnNumber=1;columnNumber<= columnCount;columnNumber++){
                vector.add(resultSet.getObject(columnNumber));

            }
            tableData.add(vector);

        }

        return new DefaultTableModel(tableData,columnNames);

    }
}
