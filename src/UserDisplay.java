
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserDisplay extends JFrame implements ActionListener{
    
    JPanel panel;

    JButton del;
    JButton add = new JButton("add book");
    
    JTextField userInputAddName = new JTextField();
    
    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
    Calendar calendar = Calendar.getInstance();

    JLabel inputBookName=new JLabel("Book name:");

    String bookNameStr;
    String dateBorrowedStr;
    String returnDateStr;
    
    int startPointY = 80;

    UserDisplay(){
        
        CreateGui();

        //add button setup
        add.setPreferredSize(new Dimension(100, 30));
        add.addActionListener(this);
        add.setLocation(20, 20);
        add.setFocusable(false);

        //input book name label 
        inputBookName.setPreferredSize(new Dimension(75, 30));

        //add name textfield
        userInputAddName.setPreferredSize(new Dimension(100, 30));

    }

    //JFrame layout method
    private void CreateGui(){
        
        this.setSize(500,500);
        this.setVisible(true);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        this.add(inputBookName);
        this.add(userInputAddName);
        this.add(add);
    }

    //New panel method
    public void setNewPanel(JPanel panel){
        
        panel.setPreferredSize(new Dimension(475, 70));
        panel.setLocation(0, startPointY);
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        del = new JButton("Delete");
        JLabel dateBorrowed = new JLabel();
        JLabel bookName = new JLabel();
        JLabel returnDate = new JLabel();

        del.setPreferredSize(new Dimension(80, 20));
        del.addActionListener(this);
        del.setFocusable(false);

        sdf.setTimeZone(calendar.getTimeZone());
        int month = calendar.get(Calendar.MONTH)+3;

        returnDateStr = calendar.get(Calendar.YEAR)+"-"+month+"-"+calendar.get(Calendar.DAY_OF_MONTH);
        bookNameStr = userInputAddName.getText();
        dateBorrowedStr = sdf.format(calendar.getTime());;
        
        dateBorrowed.setText("|Date Borrowed: " + dateBorrowedStr);
        dateBorrowed.setFont(new Font("Tahoma", Font.BOLD, 14));
        
        returnDate.setText("|Return date: " + returnDateStr);
        returnDate.setFont(new Font("Tahoma", Font.BOLD, 14));

        bookName.setText("Book name: \n\n" + bookNameStr);
        bookName.setFont(new Font("Tahoma", Font.BOLD, 14));

        panel.add(del);
        panel.add(bookName);
        panel.add(dateBorrowed);
        panel.add(returnDate);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
       
        //add new panel to frame
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
