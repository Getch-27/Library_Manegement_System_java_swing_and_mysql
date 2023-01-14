import javax.swing.*;

public class issueBookPage extends JDialog{
    public JPanel IssueBookPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox DepartmentCmb;
    private JTextField textField3;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JButton issueButton;

    public issueBookPage() {
    DepartmentCmb.addItem("Computer");
    DepartmentCmb.addItem("Accounting");
    DepartmentCmb.addItem("Managment");

}
}
