/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_cs;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, removeButton;
    private JComboBox roleComboBox;
    private Image backgroundImage;//

    public LoginPage() {
        String imagePath = "C:\\Users\\wajdd\\Downloads\\OrderFoodPic.jpg"; 
        backgroundImage = new ImageIcon(imagePath).getImage();

        BackgroundPanel panel = new BackgroundPanel(backgroundImage);
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Welcome To TastyGo");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 23));
        titleLabel.setForeground(new Color(0, 70, 60));
        titleLabel.setBounds(100, 20, 250, 30);
        panel.add(titleLabel);

        JLabel roleLabel = new JLabel("Select Role:");
        roleLabel.setBounds(50, 60, 100, 25);
        roleLabel.setForeground(new Color(0, 70, 60));
        panel.add(roleLabel);

        String roles[] = {"Customer", "Employee", "Admin"};
        roleComboBox = new JComboBox(roles);
        roleComboBox.setBounds(150, 60, 180, 25);
        panel.add(roleComboBox);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 100, 100, 25);
        userLabel.setForeground(new Color(0, 70, 60));
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 100, 180, 25);
        panel.add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 140, 100, 25);
        passLabel.setForeground(new Color(0, 70, 60));
        panel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 140, 180, 25);
        panel.add(passwordField);

        final JLabel forgetPasswordLabel = new JLabel("Forgot Password?");
        forgetPasswordLabel.setBounds(150, 170, 180, 25);
        forgetPasswordLabel.setForeground(Color.BLACK);
        forgetPasswordLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(forgetPasswordLabel);

        final JLabel guestLabel = new JLabel("Continue as Guest");
        guestLabel.setBounds(150, 190, 180, 25);
        guestLabel.setForeground(Color.BLACK);
        guestLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(guestLabel);

        forgetPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Password reset link will be sent to your email.", "Forgot Password", JOptionPane.INFORMATION_MESSAGE);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                forgetPasswordLabel.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                forgetPasswordLabel.setForeground(Color.BLACK);
            }
        });

        guestLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Welcome Guest!", "Guest Login", JOptionPane.INFORMATION_MESSAGE);
                Point location = ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).getLocation();

                CustomerDashboard dashboard = new CustomerDashboard(); 
                dashboard.setLocation(location);
                dashboard.setVisible(true);

                ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                guestLabel.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                guestLabel.setForeground(Color.BLACK);
            }
        });

        loginButton = new JButton("Login");
        loginButton.setBounds(140, 220, 100, 35);
        loginButton.setBackground(new Color(0, 70, 60));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);//?
        loginButton.addActionListener(new LoginButton());
        panel.add(loginButton);

        removeButton = new JButton("Remove");
        removeButton.setBounds(250, 220, 100, 35);
        removeButton.setBackground(new Color(0, 70, 60));
        removeButton.setForeground(Color.WHITE);
        removeButton.setFocusPainted(false);//?
        removeButton.addActionListener(new RemoveButton());
        panel.add(removeButton);

        add(panel);
        setTitle("Login Page");
        setSize(420, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    class LoginButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String user = usernameField.getText().trim();
            String pass = new String(passwordField.getPassword()).trim();
            String role = roleComboBox.getSelectedItem().toString();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter username and password", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (pass.length() < 8) {
                JOptionPane.showMessageDialog(null, "Password must be at least 8 characters", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!Character.isLetter(pass.charAt(0))) {
                JOptionPane.showMessageDialog(null, "Password must start with a letter", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Welcome " + user + " (" + role + ")", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                Point location = ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).getLocation();

                if (role.equals("Customer")) {
                    CustomerDashboard dashboard = new CustomerDashboard();
                    dashboard.setLocation(location);
                    dashboard.setVisible(true);
                } else if (role.equals("Employee")) {
                    EmployeeDashboard dashboard = new EmployeeDashboard();
                    dashboard.setLocation(location);
                    dashboard.setVisible(true);
                } else if (role.equals("Admin")) {
                    AdminDashboard dashboard = new AdminDashboard();
                    dashboard.setLocation(location);
                    dashboard.setVisible(true);
                }

                ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
            }
        }
    }

    class RemoveButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            usernameField.setText("");
            passwordField.setText("");
        }
    }

    
}
