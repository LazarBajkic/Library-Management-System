import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AdminRegister extends JFrame implements ActionListener{
    
    JTextField IDField = new JTextField();
    JTextField userNameField = new JTextField();
    JTextField startedWorkField = new JTextField();

    JButton confirm = new JButton("Confirm");
    JButton loginReturn = new JButton("Return to login");
    
    JLabel ID = new JLabel("Enter ID");
    JLabel userName = new JLabel("Enter username");
    JLabel started = new JLabel("Started working");

    String uname="root";
    String url="jdbc:mysql://localhost:3306/Library";
    String password="Ceramida";

    AdminRegister(){
        
        CreateGUI();
       
        //ID label
        ID.setSize(200, 20);
        ID.setLocation(145, 20);

        //username label
        userName.setSize(200, 20);
        userName.setLocation(145, 120);

        //started working label
        started.setSize(200, 20);
        started.setLocation(145, 220);
 
        //ID text field
        IDField.setSize(200, 30);
        IDField.setLocation(145, 50);

        //username field
        userNameField.setSize(200, 30);
        userNameField.setLocation(145, 150);
        
        //started working field
        startedWorkField.setSize(200, 30);
        startedWorkField.setLocation(145, 250);

        //confirm button
        confirm.setSize(200, 40);
        confirm.setLocation(145, 350);
        confirm.addActionListener(this);
        confirm.setFocusable(false);
        
        //loginReturn button
        loginReturn.setSize(200, 40);
        loginReturn.setLocation(145, 400);
        loginReturn.addActionListener(this);
        loginReturn.setFocusable(false);

    }

    public void CreateGUI(){
        
        //the frame is setup
        this.setSize(500,500);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        
        //The frame adds all the components
        this.add(userName);
        this.add(ID);
        this.add(started);
        this.add(startedWorkField);
        this.add(userNameField);
        this.add(IDField);
        this.add(confirm);
        this.add(loginReturn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
        //return to login screen
        if(e.getSource()==loginReturn){
            this.dispose();
            LoginScreen LS = new LoginScreen();
        }

        //error in case a field is left empty
        else if(IDField.getText().isEmpty() || userNameField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "You must fill out all the fields!");            
        }
        
        //a successful admin registration if all fields where filled out
        else if(!IDField.getText().isEmpty() && !userNameField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Successfully registered new admin!");

            //The strings tkae the input from the textfields which are then passsed on into the function where it is then stored in a database
            String id = IDField.getText();
            String name = userNameField.getText();
            String startedWorking = startedWorkField.getText();
            String query="Insert into Admins (ID,Name,StartedWorking) values ('"+id+"','"+name+"','"+startedWorking+"')";
            
            RegisterAdminIntoDB(id, name,startedWorking,query);
        }
    }

    
    //Register admin credentials into a database
    public void RegisterAdminIntoDB(String id,String name,String startedWorking,String query){
        try {
            Connection con=DriverManager.getConnection(url,uname,password);
            Statement statement=con.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }            
}
}
