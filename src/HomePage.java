import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JDialog {
    public JPanel homepanel;
    private JButton newBookButton;
    private JButton issueBookButton;
    private JButton bookReturnButton;
    private JButton bookStatusButton;
    private JButton logOutButton;



public HomePage() {
    newBookButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            NewBook newBook= new NewBook();

            newBook.setContentPane(newBook.NewBookPanel);
            newBook.setBounds(100,200,400,300);
            newBook.show();
        }
    });
    issueBookButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            issueBookPage issueBookpage=new issueBookPage();
            issueBookpage.setContentPane(issueBookpage.IssueBookPanel);
            issueBookpage.setBounds(100,200,400,300);
            issueBookpage.show();
        }
    });
    bookReturnButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            BookReturnPage bookReturnPage=new BookReturnPage();
            bookReturnPage.setContentPane(bookReturnPage.BookReturnpanel);
            bookReturnPage.setBounds(100,200,400,300);
            bookReturnPage.show();

        }
    });
    bookStatusButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            BookStatusPage bookStatusPage=new BookStatusPage();
            bookStatusPage.setContentPane(bookStatusPage.BookStatuspanel);
            bookStatusPage.setBounds(100,200,400,300);
            bookStatusPage.show();
        }
    });
    logOutButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
              JOptionPane.showMessageDialog(homepanel,"SYSTEM IS CLOSED!");
              dispose();
        }
    });
}
}
