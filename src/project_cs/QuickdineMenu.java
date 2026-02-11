/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_cs;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class QuickdineMenu extends JFrame {
    private ArrayList<Meal> meals;
    private JPanel menuPanel;
    private JButton cartButton, backButton;

    public QuickdineMenu() {
        setTitle("Queen Dine Menu");
        setSize(390, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String imagePath = "C:\\Users\\wajdd\\Downloads\\fastpic.jpg"; // خلفية المطعم
        Image bgImage = new ImageIcon(imagePath).getImage();
        BackgroundPanel background = new BackgroundPanel(bgImage);
        background.setLayout(null);
        setContentPane(background);

        meals = new ArrayList<>();
        meals.add(new Meal("Grilled Chicken", 35.0, "Juicy grilled chicken with herbs", "C:\\Users\\wajdd\\Downloads\\chicken.jpg"));
        meals.add(new Meal("Pasta Alfredo", 30.0, "Creamy Alfredo pasta with mushrooms", "C:\\Users\\wajdd\\Downloads\\pasta.jpg"));
        meals.add(new Meal("Beef Burger", 28.0, "Classic beef burger with cheese", "C:\\Users\\wajdd\\Downloads\\burger.jpg"));

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 1, 10, 10));
        menuPanel.setBounds(10, 10, 360, 470);
        menuPanel.setOpaque(false);
//كل مره تسوي لكل وجبه كرييت ف اذا اضفنا وجبه جديده بتطبق عليها الفنكشن مباشره
        for (Meal meal : meals) {
            menuPanel.add(createMealPanel(meal));
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
            JDialog cartDialog = new JDialog(this, "Cart", true);
            cartDialog.setSize(350, 300);
            cartDialog.setLocationRelativeTo(this);
            cartDialog.setLayout(new BorderLayout());
//مساحة نصية لعرض تفاصيل المنتجات في السلة، غير قابلة للتعديل.
            JTextArea cartArea = new JTextArea();
            cartArea.setEditable(false);

            double total = 0;
            for (Meal m : meals) {
                if (m.getQuantity() > 0) {
                    double subtotal = m.getQuantity() * m.getPrice();
                    cartArea.append(m.getName() + " x " + m.getQuantity() + " = " + subtotal + " SAR\n");
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
                //يسكر السله
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

    private JPanel createMealPanel(Meal meal) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        //يكون فيها تناسق بين الكومبننت
        panel.setPreferredSize(new Dimension(340, 160)); 
        //للخط الفاصل
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        panel.setOpaque(false);
//صورة الوجبه
        ImageIcon icon = new ImageIcon(meal.getImagePath());
        //تصغير الصورة لتناسب التصميم، مع الحفاظ على جودة العرض (SCALE_SMOOTH = جودة عالية
        Image scaledImage = icon.getImage().getScaledInstance(100, 90, Image.SCALE_SMOOTH);
        //عشان تكون الصوره في ليبل
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        //للوحة المعلومات السعر وغيره
        JPanel infoPanel = new JPanel();
        //الكومبننت تصير تعرض بشكل عمودي من فوق الى تحت
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
//اسم الوجبه في ليبل
        JLabel nameLabel = new JLabel(meal.getName());
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));  
        nameLabel.setForeground(new Color(255, 165, 0));  
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT); 

        JLabel priceLabel = new JLabel("Price: " + meal.getPrice() + " SAR");
        priceLabel.setForeground(new Color(255, 165, 0));  
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel descLabel = new JLabel(meal.getDescription());
        descLabel.setForeground(new Color(255, 165, 0));  
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
// - +
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        quantityPanel.setOpaque(false);

        JButton minus = new JButton("-");
        JButton plus = new JButton("+");
        JLabel countLabel = new JLabel("0");
        countLabel.setForeground(new Color(255, 165, 0)); 
//ايفينت ال- + اذا ناقص يشرط ان القيمه تكون اكبر من 0 بعدين يحدثها
        minus.addActionListener(e -> {
            int count = meal.getQuantity();
            if (count > 0) {
                meal.setQuantity(count - 1);
                countLabel.setText(String.valueOf(meal.getQuantity()));
            }
        });

        plus.addActionListener(e -> {
            meal.setQuantity(meal.getQuantity() + 1);
            countLabel.setText(String.valueOf(meal.getQuantity()));
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

    private class Meal {
        private String name, description, imagePath;
        private double price;
        private int quantity = 0;

        public Meal(String name, double price, String description, String imagePath) {
            this.name = name;
            this.price = price;
            this.description = description;
            this.imagePath = imagePath;
        }

        public String getName() { return name; }
        public double getPrice() { return price; }
        public String getDescription() { return description; }
        public String getImagePath() { return imagePath; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity;}
}
}
