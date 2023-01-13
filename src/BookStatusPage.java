import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

public class BookStatusPage extends JDialog{
    public JPanel BookStatuspanel;
    private JTable BookTable;


    Connection connection;
    Statement statement;
    ResultSet resultSet;
    public void showBook() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/librarydb", "root", "root");
            statement = connection.createStatement();
            String mysql = "SELECT id,title,subject,author,shelf_no,Quantity FROM books";
            resultSet = statement.executeQuery(mysql);
            DefaultTableModel table= BuildTable(resultSet);
             BookTable.setModel(table);
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        }


    }
    public static DefaultTableModel BuildTable(ResultSet resultSet) throws SQLException{
        // to get meta data of the column (int,varchar.....)
         ResultSetMetaData metaData=resultSet.getMetaData();
         //vector declaration to store column name of the table from result set.
         Vector<String> columnNames= new Vector<String>();
         int columnCount=metaData.getColumnCount();
         for(int column=1;column <= columnCount;column++){
             columnNames.add(metaData.getColumnName(column));

         }
         Vector<Vector<Object>> tableData =new Vector<Vector<Object>>();
         while (resultSet.next()){
             Vector<Object> vector=new Vector<Object>();
             for(int columnNumber=1;columnNumber<= columnCount;columnNumber++){
                 vector.add(resultSet.getObject(columnNumber));
             }
             tableData.add(vector);
         }
         return new DefaultTableModel(tableData,columnNames);
    }
}
