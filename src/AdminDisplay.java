import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminDisplay extends JFrame implements ActionListener{
    
    JPanel panel;
    
    JButton removeBorrowed;
    JButton add = new JButton("Add information");
    
    int startPointY = 120;
    
    Calendar calendar = Calendar.getInstance();
    
    String bookNameStr;
    String dateBorrowedStr;
    String userNameStr;
    String returnDateStr;
    
    JLabel inputBookName=new JLabel("Book name:");
    JLabel inputDateBorrowed=new JLabel("Date borrowed:");
    JLabel inputReturnDate=new JLabel("Return date:");
    JLabel inputUserName=new JLabel("User name:");
   
    JTextField adminInputAddBookName = new JTextField();
    JTextField adminInputAddBorrowedDate = new JTextField();
    JTextField adminInputAddReturnDate = new JTextField();
    JTextField adminInputAddUserName = new JTextField();

    AdminDisplay(){
        
        CreateGui();

        add.setPreferredSize(new Dimension(150, 30));
        add.addActionListener(this);
        add.setLocation(20, 20);
        add.setFocusable(false);

        inputBookName.setLocation(200, 20);

        adminInputAddBookName.setPreferredSize(new Dimension(100, 30));

        inputDateBorrowed.setPreferredSize(new Dimension(100, 30));

        adminInputAddBorrowedDate.setPreferredSize(new Dimension(100, 30));

        adminInputAddReturnDate.setPreferredSize(new Dimension(100, 30));

        adminInputAddUserName.setPreferredSize(new Dimension(100, 30));

        inputUserName.setPreferredSize(new Dimension(75, 30));

    }

    private void CreateGui(){
        
        this.setSize(930,500);
        this.setVisible(true);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        this.add(add);
        
        this.add(inputBookName);
        this.add(adminInputAddBookName);
        
        this.add(inputDateBorrowed);
        this.add(adminInputAddBorrowedDate);
        
        this.add(inputUserName);
        this.add(adminInputAddUserName);

        this.add(inputReturnDate);
        this.add(adminInputAddReturnDate);
    }

    public void setNewPanel(JPanel panel){
        
        panel.setPreferredSize(new Dimension(905, 70));
        panel.setLocation(0, startPointY);
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.GRAY);
        
        removeBorrowed = new JButton("Delete");
        JLabel dateBorrowed = new JLabel();
        JLabel bookName = new JLabel();
        JLabel userName = new JLabel();
        JLabel returnDate = new JLabel();

        removeBorrowed.setPreferredSize(new Dimension(80, 20));
        removeBorrowed.addActionListener(this);
        removeBorrowed.setLocation(200, 300);
        removeBorrowed.setFocusable(false);

        bookNameStr = adminInputAddBookName.getText();
        dateBorrowedStr = adminInputAddBorrowedDate.getText();
        userNameStr = adminInputAddUserName.getText();
        returnDateStr = adminInputAddReturnDate.getText();
        
        dateBorrowed.setText("|Date Borrowed: " + dateBorrowedStr);
        dateBorrowed.setFont(new Font("Tahoma", Font.BOLD, 14));
        
        bookName.setText("|Book name: " + bookNameStr);
        bookName.setFont(new Font("Tahoma", Font.BOLD, 14));
        
        userName.setText("|User: " + userNameStr);
        userName.setFont(new Font("Tahoma", Font.BOLD, 14));
        
        returnDate.setText("|Return date: " + returnDateStr);
        returnDate.setFont(new Font("Tahoma", Font.BOLD, 14));

        panel.add(removeBorrowed);
        panel.add(userName);
        panel.add(dateBorrowed);
        panel.add(returnDate);
        panel.add(bookName);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
        if(e.getSource()==add){
            panel = new JPanel();
            setNewPanel(panel);
            this.add(panel);
            this.repaint();
            this.revalidate();
            startPointY=startPointY + 65;
        }    
        
        //delete panel from frame
        Object source = e.getSource();
        if (source instanceof Component) {
        Component comp = (Component)source;
        this.remove(comp.getParent());
        revalidate();
        repaint();
    }
    }


}
