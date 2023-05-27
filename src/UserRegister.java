import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class UserRegister extends JFrame implements ActionListener{
    
    JTextField IDField = new JTextField();
    JTextField userNameField = new JTextField();

    JButton confirm = new JButton("Confirm");
    JButton loginReturn = new JButton("Return to login");
    
    JLabel userName = new JLabel("Enter username");
    JLabel ID = new JLabel("Enter ID");
    JLabel Issued = new JLabel("Issued");
    JLabel validUntil = new JLabel("Valid until");
    
    JTextField IssuedField = new JTextField();
    JTextField validUntilField= new JTextField();

    String id = IDField.getText();
    String name = userNameField.getText();
    String validUnt = validUntilField.getText();
    String issued = IssuedField.getText();

    String uname="root";
    String url="jdbc:mysql://localhost:3306/Library";
    String password="Ceramida";

    UserRegister(){
        
        CreateGUI();
       
        //ID label
        ID.setSize(200, 20);
        ID.setLocation(145, 20);

        //username label
        userName.setSize(200, 20);
        userName.setLocation(145, 120);

        //ID text field
        IDField.setSize(200, 30);
        IDField.setLocation(145, 50);

        //username field
        userNameField.setSize(200, 30);
        userNameField.setLocation(145, 150);

        //issued label
        Issued.setSize(200, 20);
        Issued.setLocation(145, 220);

        //validuntil label
        validUntil.setSize(200, 20);
        validUntil.setLocation(145, 320);
    
        //issued field
        IssuedField.setSize(200, 30);
        IssuedField.setLocation(145, 250);
   
        //validuntil field
        validUntilField.setSize(200, 30);
        validUntilField.setLocation(145, 350);
        
        //confirm button
        confirm.setSize(200, 40);
        confirm.setLocation(145, 420);
        confirm.addActionListener(this);
        confirm.setFocusable(false);

        //loginReturn button
        loginReturn.setSize(200, 40);
        loginReturn.setLocation(145, 470);
        loginReturn.addActionListener(this);
        loginReturn.setFocusable(false);


    }

    public void CreateGUI(){
        //setting up the frame
        this.setSize(500,600);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        
        //adding the components
        this.add(userName);
        this.add(ID);
        this.add(userNameField);
        this.add(IDField);
        this.add(validUntil);
        this.add(validUntilField);
        this.add(Issued);
        this.add(IssuedField);
        this.add(confirm);
        this.add(loginReturn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        //go back to the login screen
        if(e.getSource()==loginReturn){
            this.dispose();
            LoginScreen LS = new LoginScreen();
        }

        //error in case a field is left empty
        else if(IDField.getText().isEmpty() || userNameField.getText().isEmpty() || validUntilField.getText().isEmpty() || IssuedField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "You must fill out all the fields!");   
        }
        
        //a successful user registration if all the fields are filled out
        else if(!IDField.getText().isEmpty() && !userNameField.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "Successfully registered new user!"); 
            
            //the strings take the input values from the textfields and then pass them into the RegisterUserIntoDB function 
            String id = IDField.getText();
            String name = userNameField.getText();
            String validUnt = validUntilField.getText();
            String issued = IssuedField.getText();
            String query="Insert into Users (ID,Name,ValidUnt,Iss) values ('"+id+"','"+name+"','"+validUnt+"','"+issued+"')";
            
            RegisterUserIntoDB(id, name, validUnt, issued, query);
        }
    }

    //Register admin credentials into a database
    public void RegisterUserIntoDB(String id,String name,String validU,String Iss,String query){
        
        try {
            Connection con=DriverManager.getConnection(url,uname,password);
            Statement statement=con.createStatement();
            statement.executeUpdate(query);
        } 
        
        catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }            
    }

}
