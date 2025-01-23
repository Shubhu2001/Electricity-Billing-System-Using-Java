package electricity.billing.system;


import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton Login, signup, cancle; // Class-level declarations
    JTextField username,password;
    Choice logginin;
    
    
    Login() {
        super("Login Page");
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel lpmusername = new JLabel("Username");
        lpmusername.setBounds(300, 20, 100, 20);
        add(lpmusername);

        // Text box for username
        username  = new JTextField();
        username.setBounds(400, 20, 150, 20);
        add(username);

        JLabel lpmpassword = new JLabel("Password");
        lpmpassword.setBounds(300, 60, 100, 20);
        add(lpmpassword);

        password = new JTextField();
        password.setBounds(400, 60, 150, 20);
        add(password);

        JLabel logininas = new JLabel("Login in as");
        logininas.setBounds(300, 100, 100, 20);
        add(logininas);

        logginin = new Choice();
        logginin.add("Admin");
        logginin.add("Customer");
        logginin.setBounds(400, 100, 150, 20);
        add(logginin);

        // Login button with an icon
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i2 = i1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        Login = new JButton("Login", new ImageIcon(i2));
        Login.setBounds(330, 160, 100, 20);
        Login.addActionListener(this);
        add(Login);

        // Cancel button with an icon
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i4 = i3.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        cancle = new JButton("Cancle", new ImageIcon(i4));
        cancle.setBounds(450, 160, 100, 20);
        cancle.addActionListener(this);
        add(cancle);

        // Signup button with an icon
        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i6 = i5.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        signup = new JButton("Signup", new ImageIcon(i6));
        signup.setBounds(380, 200, 100, 20);
        signup.addActionListener(this);
        add(signup);

        // Background image
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i8 = i7.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i8));
        image.setBounds(0, 0, 250, 250);
        add(image);

        setSize(640, 300);
        setLocation(400, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == Login) {
            // Login action
            String susername= username.getText();
            String spassword =password.getText();
            
            String user =logginin.getSelectedItem();
            
            try{
                Conn c= new Conn();
                String query= "select * from login where username = '" + susername + "' and password = '" + spassword + "' and user = '"  + user + "'";
                 //String query = "select * from login where username = '" + susername + "' and password = '" + spassword + "' and user = '" + user + "'";
                
               ResultSet rs= c.s.executeQuery(query);
               
               if(rs.next()){
                   String meter = rs.getString("meter_no");
                   setVisible(false);
                  new Project(user,meter);
                   
               }else{
                   JOptionPane.showMessageDialog(null,"Invalid Login");
                   username.setText("");
                   password.setText("");
               }
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
        } else if (ae.getSource() == cancle) {
            setVisible(false);
        } else if (ae.getSource() == signup) {
            setVisible(false);
            new Signup();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}

    

