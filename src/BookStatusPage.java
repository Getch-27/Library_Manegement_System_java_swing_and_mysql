import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class BookStatusPage extends JDialog{
    public JPanel BookStatuspanel;
    private JTable BookTable;
    private JButton Issued;
    private JButton issuedBooksButton;


    Connection connection;
    Statement statement;
    ResultSet resultSet;
    BookStatusPage() {
        String mysql = "SELECT id,title,subject,author,shelf_no,Quantity FROM books WHERE is_issued = 0 OR Quantity >0";
        String mysql2="SELECT id,title,subject,author,shelf_no,Quantity FROM books WHERE is_issued  =1  ";




        Issued.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost/librarydb", "root", "root");
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(mysql);
                    DefaultTableModel t=BuildTable(resultSet);
                    BookTable.setModel(t);

                    statement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    throw new RuntimeException(ex);
                }



            }
        });
        issuedBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost/librarydb", "root", "root");
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(mysql2);
                    DefaultTableModel t=BuildTable(resultSet);
                    BookTable.setModel(t);

                    statement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException ex) {
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
