package project_cs;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CustomerDashboard extends JFrame {
    private JButton home;
    private JComboBox<String> categoryComboBox, categoryComboBox2, locationComboBox;
    private JPanel restaurantPanel;//
    private ArrayList<Restaurant> restaurants;//

    public CustomerDashboard() {
        setTitle("Welcome to our APP");
        setSize(390, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String imagePath = "C:\\Users\\wajdd\\Downloads\\cusPic.jpg";
        Image backgroundImage = new ImageIcon(imagePath).getImage();
        BackgroundPanel panel = new BackgroundPanel(backgroundImage);
        panel.setLayout(null);
        setContentPane(panel);//

        home = new JButton("Home");
        home.setBounds(140, 420, 100, 35);
        home.setBackground(new Color(0, 70, 60));
        home.setForeground(Color.WHITE);
        home.addActionListener(new HomeButton());
        panel.add(home);

        // Food Type
        JLabel foodTypeLabel = new JLabel("Type:");
        foodTypeLabel.setBounds(250, 0, 90, 20);
        foodTypeLabel.setForeground(Color.BLACK);
        panel.add(foodTypeLabel);

        String[] foodTypes = {"All", "Fast Food", "Healthy", "Dessert"};
        categoryComboBox = new JComboBox<>(foodTypes);
        categoryComboBox.setBounds(250, 20, 90, 30);
        panel.add(categoryComboBox);

        // Rating
        JLabel ratingLabel = new JLabel("Rating:");
        ratingLabel.setBounds(150, 0, 90, 20);
        ratingLabel.setForeground(Color.BLACK);
        panel.add(ratingLabel);

        String[] ratings = {"All", "2", "3", "4", "5"};
        categoryComboBox2 = new JComboBox<>(ratings);
        categoryComboBox2.setBounds(150, 20, 90, 30);
        panel.add(categoryComboBox2);

        // Location
        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setBounds(50, 0, 90, 20);
        locationLabel.setForeground(Color.BLACK);
        panel.add(locationLabel);

        String[] locations = {"All", "Qassim", "Riyadh", "Jeddah"};
        locationComboBox = new JComboBox<>(locations);
        locationComboBox.setBounds(50, 20, 90, 30);
        panel.add(locationComboBox);

        // Listeners
        categoryComboBox.addActionListener(e -> filterRestaurants());
        categoryComboBox2.addActionListener(e -> filterRestaurants());
        locationComboBox.addActionListener(e -> filterRestaurants());

        // Panel for Restaurants
        restaurantPanel = new JPanel();
        restaurantPanel.setLayout(new GridLayout(3, 2, 10, 10));
        restaurantPanel.setOpaque(false);
        restaurantPanel.setBounds(20, 60, 340, 340);
        panel.add(restaurantPanel);

        restaurants = new ArrayList<>();
        loadRestaurants();
        displayRestaurants(restaurants);
    }

    private void loadRestaurants() {
       restaurants.add(new Restaurant("Burgito", "C:\\Users\\wajdd\\Downloads\\Burgito.jpg", "Fast Food", 4, "Qassim"));
        restaurants.add(new Restaurant("Quickdine", "C:\\Users\\wajdd\\Downloads\\Quickdine.jpg", "Fast Food", 5, "Riyadh"));
        restaurants.add(new Restaurant("Vitalicious", "C:\\Users\\wajdd\\Downloads\\Vitalicious.jpg", "Healthy", 3, "Jeddah"));
        restaurants.add(new Restaurant("Nutricraft", "C:\\Users\\wajdd\\Downloads\\Nutricraft.jpg", "Healthy", 4, "Qassim"));
        restaurants.add(new Restaurant("Purely Desserts", "C:\\Users\\wajdd\\Downloads\\PurelyDesserts.jpg", "Dessert", 5, "Riyadh"));
        restaurants.add(new Restaurant("Dona", "C:\\Users\\wajdd\\Downloads\\Dona.jpg", "Dessert", 2, "Jeddah"));
    }

    private void displayRestaurants(ArrayList<Restaurant> filteredRestaurants) {
        restaurantPanel.removeAll();

        for (Restaurant r : filteredRestaurants) {
            JPanel rPanel = new JPanel(new BorderLayout());
            rPanel.setOpaque(false);

            ImageIcon icon = new ImageIcon(r.getImagePath());
            Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(scaledImage));

            JLabel nameLabel = new JLabel(r.getName(), SwingConstants.CENTER);
            nameLabel.setForeground(Color.BLACK);

            if (r.getName().equals("Quickdine")) {
                nameLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                nameLabel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new QuickdineMenu().setVisible(true);
                        dispose();
                    }
                });
            } else if (r.getName().equals("Purely Desserts")) {
                nameLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                nameLabel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        new PurelyDessertsMenu().setVisible(true);
                        dispose();
                    }
                });
            }

            rPanel.add(imgLabel, BorderLayout.CENTER);
            rPanel.add(nameLabel, BorderLayout.SOUTH);
            restaurantPanel.add(rPanel);
        }

       restaurantPanel.revalidate();
       restaurantPanel.repaint();
    }

    private void filterRestaurants() {
        String selectedCategory = categoryComboBox.getSelectedItem().toString();
        String selectedRating = categoryComboBox2.getSelectedItem().toString();
        String selectedLocation = locationComboBox.getSelectedItem().toString();

        ArrayList<Restaurant> filtered = new ArrayList<>();

        for (Restaurant r : restaurants) {
            boolean matchCategory = selectedCategory.equals("All") || r.getCategory().equalsIgnoreCase(selectedCategory);
            boolean matchRating = selectedRating.equals("All") || String.valueOf(r.getRating()).equals(selectedRating);
            boolean matchLocation = selectedLocation.equals("All") || r.getLocation().equalsIgnoreCase(selectedLocation);

            if (matchCategory && matchRating && matchLocation) {
                filtered.add(r);
            }
        }

        displayRestaurants(filtered);
    }

    private class Restaurant {
        private final String name, imagePath, category, location;
        private final int rating;

        public Restaurant(String name, String imagePath, String category, int rating, String location) {
            this.name = name;
            this.imagePath = imagePath;
            this.category = category;
            this.rating = rating;
            this.location = location;
        }

        public String getName() { return name; }
        public String getImagePath() { return imagePath; }
        public String getCategory() { return category; }
        public int getRating() { return rating; }
        public String getLocation() { return location; }
    
    }
    
    private class HomeButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoginPage homeBack = new LoginPage();
            homeBack.setLocation(getLocation());
            homeBack.setSize(getSize());
            homeBack.setVisible(true);
            dispose();
}}}

