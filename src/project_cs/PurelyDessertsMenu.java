/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_cs;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PurelyDessertsMenu extends JFrame {
    private ArrayList<Dessert> desserts;
    private JPanel menuPanel;
    private JButton cartButton, backButton;

    public PurelyDessertsMenu() {
        setTitle("Purely Desserts Menu");
        setSize(390, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String imagePath = "C:\\Users\\96655\\Downloads\\PurelyDesserts2.jpg";
        Image bgImage = new ImageIcon(imagePath).getImage();
        BackgroundPanel background = new BackgroundPanel(bgImage);
        background.setLayout(null);
        setContentPane(background);

        desserts = new ArrayList<>();
        desserts.add(new Dessert("Chocolate Cake", 25.0, "Rich chocolate sponge with ganache", "C:\\Users\\wajdd\\Downloads\\cake1.jpg"));
        desserts.add(new Dessert("Strawberry Tart", 22.0, "Crispy tart with fresh strawberries", "C:\\Users\\wajdd\\Downloads\\cake2.jpg"));
        desserts.add(new Dessert("Cheesecake", 20.0, "Classic creamy cheesecake", "C:\\Users\\wajdd\\Downloads\\cake3.jpg"));

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 1, 10, 10));
        menuPanel.setBounds(10, 10, 360, 470);
        menuPanel.setOpaque(false);

        for (Dessert dessert : desserts) {
            menuPanel.add(createDessertPanel(dessert));
        }

        background.add(menuPanel);

        backButton = new JButton("Back");
        backButton.setBounds(30, 510, 150, 35);
        backButton.setBackground(new Color(150, 0, 0));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            new CustomerDashboard().setVisible(true);
            dispose();
        });
        background.add(backButton);

        cartButton = new JButton("Cart");
        cartButton.setBounds(200, 510, 150, 35);
        cartButton.setBackground(new Color(0, 70, 60));
        cartButton.setForeground(Color.WHITE);
        cartButton.addActionListener(e -> {
            JDialog cartDialog = new JDialog(this, "Cart", true); // same show dialog
            cartDialog.setSize(350, 300);
            cartDialog.setLocationRelativeTo(this);
            cartDialog.setLayout(new BorderLayout());

            JTextArea cartArea = new JTextArea();
            cartArea.setEditable(false);

            double total = 0;
            for (Dessert d : desserts) {
                if (d.getQuantity() > 0) {
                    double subtotal = d.getQuantity() * d.getPrice();
                    cartArea.append(d.getName() + " x " + d.getQuantity() + " = " + subtotal + " SAR\n");
                    total += subtotal;
                }
            }

            if (total == 0) {
                cartArea.setText("Your cart is empty.");
            } else {
                cartArea.append("\nTotal: " + total + " SAR");
            }

            JButton payButton = new JButton("Proceed to Payment");
            payButton.setBackground(new Color(0, 70, 60));
            payButton.setForeground(Color.WHITE);
            payButton.addActionListener(ae -> {
                cartDialog.dispose();
                PaymentPage paymentPage = new PaymentPage();
                paymentPage.setVisible(true);
            });

            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(ae -> cartDialog.dispose());

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(cancelButton);
            buttonPanel.add(payButton);

            cartDialog.add(new JScrollPane(cartArea), BorderLayout.CENTER);
            cartDialog.add(buttonPanel, BorderLayout.SOUTH);
            cartDialog.setVisible(true);
        });
        background.add(cartButton);
    }

    private JPanel createDessertPanel(Dessert dessert) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setPreferredSize(new Dimension(340, 160)); 
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        panel.setOpaque(false);

        ImageIcon icon = new ImageIcon(dessert.getImagePath());
        Image scaledImage = icon.getImage().getScaledInstance(100, 90, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        JLabel nameLabel = new JLabel(dessert.getName());
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));  
        nameLabel.setForeground(new Color(255, 165, 0));  
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT); 

        JLabel priceLabel = new JLabel("Price: " + dessert.getPrice() + " SAR");
        priceLabel.setForeground(new Color(255, 165, 0));  
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel descLabel = new JLabel(dessert.getDescription());
        descLabel.setForeground(new Color(255, 165, 0));  
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        quantityPanel.setOpaque(false);

        JButton minus = new JButton("-");
        JButton plus = new JButton("+");
        JLabel countLabel = new JLabel("0");
        countLabel.setForeground(new Color(255, 165, 0)); 

        minus.addActionListener(e -> {
            int count = dessert.getQuantity();
            if (count > 0) {
                dessert.setQuantity(count - 1);
                countLabel.setText(String.valueOf(dessert.getQuantity()));
            }
        });

        plus.addActionListener(e -> {
            dessert.setQuantity(dessert.getQuantity() + 1);
            countLabel.setText(String.valueOf(dessert.getQuantity()));
        });

        minus.setForeground(new Color(255, 165, 0));  
        plus.setForeground(new Color(255, 165, 0));   

        quantityPanel.add(minus);
        quantityPanel.add(countLabel);
        quantityPanel.add(plus);

        infoPanel.add(nameLabel);
        infoPanel.add(priceLabel);
        infoPanel.add(descLabel);
        infoPanel.add(quantityPanel);

        panel.add(imageLabel, BorderLayout.WEST);
        panel.add(infoPanel, BorderLayout.CENTER);

        return panel;
    }

    private class Dessert {
        private String name, description, imagePath;
        private double price;
        private int quantity = 0;

        public Dessert(String name, double price, String description, String imagePath) {
            this.name = name;
            this.price = price;
            this.description = description;
            this.imagePath = imagePath;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getImagePath() {
            return imagePath;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
