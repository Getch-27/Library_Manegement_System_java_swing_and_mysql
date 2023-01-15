import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class BookReturnPage extends JDialog{
    public JPanel BookReturnpanel;
    private JTextField StudentText;
    private JButton searchButton;
    private JButton returnButton;
    private JScrollPane StudentScrolp;
    private JTable StudentTable;
    private JButton homeButton;
    private String bookName;

    public BookReturnPage() {
    searchButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String SearchStudent=StudentText.getText();
            try {
                String mysql = "SELECT *FROM students WHERE fullname='"+SearchStudent+"'";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/librarydb", "root", "root");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(mysql);
                //String BookName=(String) resultSet.getObject(6);
                DefaultTableModel t=BuildTable(resultSet);

                if(t.getDataVector().isEmpty()){

                    JOptionPane.showMessageDialog(BookReturnpanel,"Student Information is not exist !");


                }else{
                    StudentTable.setModel(t);
                }
                statement.close();
                connection.close();
            } catch (ClassNotFoundException | SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String mysql = "DELETE FROM students WHERE fullname='"+StudentText.getText()+"'";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/librarydb", "root", "root");
                    PreparedStatement ps=connection.prepareStatement(mysql);
                    ps.executeUpdate();


                    JOptionPane.showMessageDialog(BookReturnpanel,"Book Returned Successfully!!");
                    ps.close();
                    connection.close();

                } catch (ClassNotFoundException | SQLException ex) {
                    throw new RuntimeException(ex);
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
            for(int columnNumber=1;columnNumber<= columnCount;columnNumber++) {
                vector.add(resultSet.getObject(columnNumber));
            }
            tableData.add(vector);

        }


        return new DefaultTableModel(tableData,columnNames);

    }
}
