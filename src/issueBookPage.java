import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private JTextField SearchText;
    private JButton SearchButton;
    private JTable SearchTable;

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

                    DefaultTableModel t=BuildTable(resultSet);
                    SearchTable.setModel(t);
                   if(resultSet.next()){

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
