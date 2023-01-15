import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
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
            dispose();
            NewBook newBook= new NewBook();
            newBook.setContentPane(newBook.NewBookPanel);
            newBook.setBounds(400,200,600,400);
            newBook.show();
        }
    });
    issueBookButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            issueBookPage issueBookpage=new issueBookPage();
            issueBookpage.setContentPane(issueBookpage.IssueBookPanel);
            issueBookpage.setBounds(100,100,1200,500);
            issueBookpage.show();
        }
    });
    bookReturnButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            BookReturnPage bookReturnPage=new BookReturnPage();
            bookReturnPage.setContentPane(bookReturnPage.BookReturnpanel);
            bookReturnPage.setBounds(100,200,400,300);
            bookReturnPage.show();

        }
    });
    bookStatusButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            BookStatusPage bookStatusPage=new BookStatusPage();
            bookStatusPage.setContentPane(bookStatusPage.BookStatuspanel);
            bookStatusPage.setBounds(100,200,800,300);
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
