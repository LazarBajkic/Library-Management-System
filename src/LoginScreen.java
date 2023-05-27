import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;

public class LoginScreen extends JFrame implements ActionListener{
    
    JButton user = new JButton("User");
    JButton admin = new JButton("Admin");
    
    JTextField IDField = new JTextField();
    JTextField userNameField = new JTextField();

    JLabel userName = new JLabel("Enter username");
    JLabel ID = new JLabel("Enter ID");

    boolean isUser = false;
    boolean isAdmin = false;
    
    JButton registerUser = new JButton("Register new user");
    JButton registerAdmin = new JButton("Register new admin");
    JButton confirm = new JButton("Confirm");

    String uname="root";
    String url="jdbc:mysql://localhost:3306/Library";
    String password="Ceramida";

    String checkUser="Select Name,ID from users";
    String checkAdmin="Select Name,ID from admins";

    LoginScreen(){
        
        CreateGUI();

        //user login button
        user.setSize(200, 40);
        user.setLocation(145, 50);
        user.addActionListener(this);
        user.setFocusable(false);

        //admin login button
        admin.setSize(200, 40);
        admin.setLocation(145, 150);
        admin.addActionListener(this);
        admin.setFocusable(false);

        //register new user
        registerUser.setSize(200, 40);
        registerUser.setLocation(145, 250);
        registerUser.addActionListener(this);
        registerUser.setFocusable(false);
         
        //register new admin
        registerAdmin.setSize(200, 40);
        registerAdmin.setLocation(145, 350);
        registerAdmin.addActionListener(this);
        registerAdmin.setFocusable(false);

        //confirm login or registration
        confirm.setSize(200, 40);
        confirm.setLocation(145, 420);
        confirm.addActionListener(this);
        confirm.setVisible(false);
        confirm.setFocusable(false);

        //ID label
        ID.setSize(200, 20);
        ID.setLocation(145, 20);
        ID.setVisible(false);

        //username label
        userName.setSize(200, 20);
        userName.setLocation(145, 120);
        userName.setVisible(false);

        //ID text field
        IDField.setSize(200, 30);
        IDField.setLocation(145, 50);
        IDField.setVisible(false);

        //username field
        userNameField.setSize(200, 30);
        userNameField.setLocation(145, 150);
        userNameField.setVisible(false);
    }
    
    public void CreateGUI(){
        //setting up the frame
        this.setSize(500,500);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        //Adding the components
        this.add(confirm);
        this.add(registerAdmin);
        this.add(registerUser);
        this.add(userName);
        this.add(userNameField);
        this.add(IDField);
        this.add(ID);
        this.add(user);
        this.add(admin);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        //logging in as user
        if(e.getSource()==user){
            user.setVisible(false);
            admin.setVisible(false); 
            confirm.setVisible(true);
            userName.setVisible(true);
            userNameField.setVisible(true);
            ID.setVisible(true);
            IDField.setVisible(true);
            isUser=true;
        }
        
        //Logging in as admin
        else if(e.getSource()==admin){
            user.setVisible(false);
            admin.setVisible(false); 
            confirm.setVisible(true);
            userName.setVisible(true);
            userNameField.setVisible(true);
            ID.setVisible(true);
            IDField.setVisible(true);
            isAdmin = true;
        }

        //user validation
        if(e.getSource()==confirm && isUser==true){  
            
             //the function for log in is called passing in the boolean 'correct' as an arguemnt
            boolean correct=true;
            
            try {
                CheckUserCredentials(correct);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            //if a field was left empty,an error message will be shown
            if(IDField.getText().isEmpty() || userNameField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "You must fill out all the fields!");            
            }
        }
        
        //admin validation
        if(e.getSource()==confirm && isAdmin==true){
            
            //the function for log in is called passing in the boolean 'correct' as an arguemnt 
            boolean correct=true;  
            
            try {
                CheckAdminCredentials(correct);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            //if a field was left empty,an error message will be shown
            if(IDField.getText().isEmpty() || userNameField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "You must fill out all the fields!");            
            }
          
        }
        
        //register new admin
        if(e.getSource()==registerAdmin){
            this.dispose();
            AdminRegister AR = new AdminRegister();
        }
        
        //register new user
        if(e.getSource()==registerUser){    
            this.dispose();
            UserRegister UR = new UserRegister();
        }
        
    }

    
    //Check the credentials when logging in as a user
    public boolean CheckUserCredentials(boolean correct) throws SQLException{
        
        Connection con=DriverManager.getConnection(url,uname,password);
        Statement statement=con.createStatement();
        ResultSet set = statement.executeQuery(checkUser);
        
        while(set.next()){
            
            //if all the fields where filled out,and the credentials of the person trying to log in are in the respective database,they will be redirected to the correct panel  
            if(IDField.getText().equals(set.getString("ID"))&& userNameField.getText().equals(set.getString("Name"))){
                UserDisplay UD = new UserDisplay();
                this.dispose();
                correct=true;
            }
            
            //if the fields are not empty,but the credentials do not match the ones in the database,an error message will be shown 
            else if(!IDField.getText().equals(set.getString("ID")) || !userNameField.getText().equals(set.getString("Name"))){
                JOptionPane.showMessageDialog(null, "Incorrect credentials,try again!");
                correct=false;
            }
            
        }
        return correct;
    }


    //Check the credentials when logging in as an admin
    public boolean CheckAdminCredentials(boolean correct) throws SQLException{
        
        Connection con=DriverManager.getConnection(url,uname,password);
        Statement statement=con.createStatement();
        ResultSet set = statement.executeQuery(checkAdmin);
        
        while(set.next()){
            
            //if all the fields where filled out,and the credentials of the person trying to log in are in the respective database,they will be redirected to the correct panel 
            if(IDField.getText().equals(set.getString("ID"))&& userNameField.getText().equals(set.getString("Name"))){
                AdminDisplay AD = new AdminDisplay();
                this.dispose();
                correct=true;
            }
            
            //if the fields are not empty,but the credentials do not match the ones in the database,an error message will be shown 
            else if(!IDField.getText().equals(set.getString("ID")) || !userNameField.getText().equals(set.getString("Name"))){
                JOptionPane.showMessageDialog(null, "Incorrect credentials,try again!");
                correct=false;
            }
            
        }        
        return correct;    
    }

}
