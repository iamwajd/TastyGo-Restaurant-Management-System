package project_cs;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AdminDashboard extends JFrame {

    private JComboBox<String> categoryComboBox, locationComboBox;
    private JTextField nameField;

    private JButton home = new JButton("Home");

    private JButton addButton = new JButton("Add");
    private JButton deleteRestaurant = new JButton("Delete Restaurant");
    private JButton deleteEmployee = new JButton("Delete Employee or customer");
    private JButton edit = new JButton("Edit");

    //   private ArrayList<Restaurant> restaurants = new ArrayList<>();
    public AdminDashboard() {

        setTitle("Admin Page");
        setSize(390, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String imagePath = "C:\\Users\\96655\\Downloads\\FoodPic.jpg";
        Image backgroundImage = new ImageIcon(imagePath).getImage();

        BackgroundPanel panel = new BackgroundPanel(backgroundImage);
        panel.setLayout(null);

        home.setBounds(140, 400, 100, 35);
        home.setBackground(new Color(0, 70, 60));
        home.setForeground(Color.WHITE);
        home.addActionListener(new HomeButton());
        panel.add(home);

        JLabel nameSentence = new JLabel("Choose one of the following:");
        nameSentence.setBounds(20, 60, 200, 30);
        panel.add(nameSentence);

        addButton.setBounds(50, 140, 100, 30);
        addButton.setBackground(new Color(0, 70, 60));
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(new AddButton());
        panel.add(addButton);

        deleteRestaurant.setBounds(180, 140, 150, 30);
        deleteRestaurant.setBackground(new Color(0, 70, 60));
        deleteRestaurant.setForeground(Color.WHITE);
        deleteRestaurant.addActionListener(new DeleteRestaurantButton());
        panel.add(deleteRestaurant);

        deleteEmployee.setBounds(50, 200, 220, 30);
        deleteEmployee.setBackground(new Color(0, 70, 60));
        deleteEmployee.setForeground(Color.WHITE);
        //  addButton.addActionListener(new AddButton());
        panel.add(deleteEmployee);

        edit.setBounds(50, 260, 100, 30);
        edit.setBackground(new Color(0, 70, 60));
        edit.setForeground(Color.WHITE);
        //  addButton.addActionListener(new AddButton());
        panel.add(edit);

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

    class AddButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            final JFrame newframe = new JFrame("Add new Restaurant");
            newframe.setSize(390, 500);
            newframe.setLayout(null);
            newframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            newframe.setLocation(getLocation());
            newframe.setSize(getSize());

            String imagePath = "C:\\Users\\96655\\Downloads\\FoodPic.jpg";
            Image backgroundImage = new ImageIcon(imagePath).getImage();
            BackgroundPanel panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            //Type of food
            JLabel foodTypeLabel = new JLabel("Type of Food:");
            foodTypeLabel.setBounds(20, 100, 100, 30);
            foodTypeLabel.setForeground(Color.BLACK);
            panel.add(foodTypeLabel);

            String[] foodTypes = {"All", "Fast Food", "Healthy", "Dessert"};
            categoryComboBox = new JComboBox<>(foodTypes);
            categoryComboBox.setBounds(120, 100, 100, 30);
            panel.add(categoryComboBox);

            //location 
            JLabel locationLabel = new JLabel("Location:");
            locationLabel.setBounds(20, 140, 100, 30);
            locationLabel.setForeground(Color.BLACK);
            panel.add(locationLabel);

            String[] locations = {"All", "Qassim", "Riyadh", "Jeddah"};
            locationComboBox = new JComboBox<>(locations);
            locationComboBox.setBounds(120, 140, 100, 30);
            panel.add(locationComboBox);

            JLabel nameLabel = new JLabel("RestaurantName");
            nameField = new JTextField(10);
            nameLabel.setBounds(20, 60, 100, 30);
            nameField.setBounds(120, 60, 100, 30);
            panel.add(nameLabel);
            panel.add(nameField);

            JButton save = new JButton("Save");
            save.setBounds(100, 260, 100, 30);
            save.setBackground(new Color(0, 70, 60));
            save.setForeground(Color.WHITE);
            panel.add(save);

            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    String type = categoryComboBox.getSelectedItem().toString();
                    String location = locationComboBox.getSelectedItem().toString();

                    JOptionPane.showMessageDialog(null, "The addition was successful", "Add Message", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            JButton backButton = new JButton("Back");
            backButton.setBounds(100, 300, 100, 30);
            backButton.setBackground(new Color(0, 70, 60));
            backButton.setForeground(Color.WHITE);

            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    newframe.dispose();

                    AdminDashboard adminDashboard = new AdminDashboard();
                    adminDashboard.setLocation(getLocation());
                    adminDashboard.setSize(getSize());
                    adminDashboard.setVisible(true);
                }
            });
            panel.add(backButton);

            newframe.setVisible(true);
            newframe.setContentPane(panel);
        }
    }

    class DeleteRestaurantButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            final JFrame delframe = new JFrame("Delete a Restaurant");
            delframe.setSize(390, 500);
            delframe.setLayout(null);
            delframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            delframe.setLocation(getLocation());
            delframe.setSize(getSize());

            String imagePath = "C:\\Users\\96655\\Downloads\\FoodPic.jpg";
            Image backgroundImage = new ImageIcon(imagePath).getImage();
            BackgroundPanel panel = new BackgroundPanel(backgroundImage);
            panel.setLayout(null);

            JLabel nameLabel = new JLabel("Restaurant Name:");
            nameLabel.setBounds(100, 140, 200, 30);
            nameLabel.setForeground(Color.BLACK);
            panel.add(nameLabel);

            final JTextField nameField = new JTextField();
            nameField.setBounds(100, 180, 100, 30);
            panel.add(nameField);

            JButton DeleteButton = new JButton("Delete");
            DeleteButton.setBounds(100, 260, 100, 30);
            DeleteButton.setBackground(new Color(0, 70, 60));
            DeleteButton.setForeground(Color.WHITE);
            panel.add(DeleteButton);

            DeleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Plese Enter a Name", "Message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        int confirm = JOptionPane.showConfirmDialog(null, "Are you Sure?", "Message", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            JOptionPane.showMessageDialog(null, "delete successful", "Message", JOptionPane.INFORMATION_MESSAGE);

                        }
                    }
                }

            });
            JButton backButton = new JButton("Back");
            backButton.setBounds(100, 300, 100, 30);
            backButton.setBackground(new Color(0, 70, 60));
            backButton.setForeground(Color.WHITE);

            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    delframe.dispose();

                    AdminDashboard adminDashboard = new AdminDashboard();
                    adminDashboard.setLocation(getLocation());
                    adminDashboard.setSize(getSize());
                    adminDashboard.setVisible(true);
                }
            });
            panel.add(backButton);

            delframe.setVisible(true);
            delframe.setContentPane(panel);
        }
    }
}
