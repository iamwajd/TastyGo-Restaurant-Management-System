/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_cs;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class EmployeeDashboard extends JFrame {

    private JButton home = new JButton("Home");
    private JButton add = new JButton("Add meal");
    private JButton rem = new JButton("Remove meal");
    private JButton edit = new JButton("Edit Price ");

    public EmployeeDashboard() {
        setTitle("Employee Page");
        setSize(390, 500);
        getContentPane().setBackground(new Color(255, 102, 0));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String imagePath = "C:\\Users\\wajdd\\Downloads\\AdminPic.jpg";
        Image backgroundImage = new ImageIcon(imagePath).getImage();

        BackgroundPanel panel = new BackgroundPanel(backgroundImage);
        panel.setLayout(null);

        home.setBounds(140, 400, 100, 35);
        home.setBackground(new Color(0, 70, 60));
        home.setForeground(Color.WHITE);
        home.addActionListener(new HomeButton());
        panel.add(home);

        add.setBounds(130, 150, 130, 35); // 60, 300, 100, 35
        add.setBackground(new Color(0, 70, 60));
        add.setForeground(Color.WHITE);

        panel.add(add);

        rem.setBounds(130, 200, 130, 35);
        rem.setBackground(new Color(0, 70, 60));
        rem.setForeground(Color.WHITE);

        panel.add(rem);

        edit.setBounds(130, 250, 130, 35);
        edit.setBackground(new Color(0, 70, 60));
        edit.setForeground(Color.WHITE);

        panel.add(edit);

        setLayout(null);
        setContentPane(panel);
    }

    class HomeButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            LoginPage homeBack = new LoginPage();
            homeBack.setLocation(getLocation());
            homeBack.setSize(getSize());
            homeBack.setVisible(true);
            dispose();
 }
}
}