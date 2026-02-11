/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_cs;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaymentPage extends JFrame {
    public PaymentPage() {
        setTitle("Payment Page");
        setSize(390, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String imagePath = "C:\\Users\\wajdd\\Downloads\\AdminPic.jpg";
        Image bgImage = new ImageIcon(imagePath).getImage();
        BackgroundPanel bgPanel = new BackgroundPanel(bgImage);
        bgPanel.setLayout(null);

        // عنوان البيانات
        JLabel cardLabel = new JLabel("Enter Card Details");
        cardLabel.setBounds(100, 30, 200, 30);
        cardLabel.setFont(new Font("Arial", Font.BOLD, 18));
        cardLabel.setForeground(Color.WHITE);
        bgPanel.add(cardLabel);

        // حقل رقم البطاقة
        JTextField cardNumberField = new JTextField();
        cardNumberField.setBounds(100, 80, 180, 40);  // زيادة الحجم
        cardNumberField.setBorder(BorderFactory.createTitledBorder("Card Number"));
        cardNumberField.setFont(new Font("Arial", Font.PLAIN, 14));
        bgPanel.add(cardNumberField);

        // حقل تاريخ الانتهاء
        JTextField expiryField = new JTextField();
        expiryField.setBounds(100, 130, 180, 40);  // زيادة الحجم
        expiryField.setBorder(BorderFactory.createTitledBorder("Expiry Date (MM/YY)"));
        expiryField.setFont(new Font("Arial", Font.PLAIN, 14));
        bgPanel.add(expiryField);

        // حقل CVV
        JTextField cvvField = new JTextField();
        cvvField.setBounds(100, 180, 180, 40);  // زيادة الحجم
        cvvField.setBorder(BorderFactory.createTitledBorder("CVV"));
        cvvField.setFont(new Font("Arial", Font.PLAIN, 14));
        bgPanel.add(cvvField);

        // زر الدفع
        JButton payButton = new JButton("Pay Now");
        payButton.setBounds(120, 240, 140, 40);
        payButton.setBackground(new Color(0, 70, 60));
        payButton.setForeground(Color.WHITE);
        payButton.setFont(new Font("Arial", Font.BOLD, 16));
        payButton.addActionListener(e -> {
            String cardNumber = cardNumberField.getText().trim();
            String expiry = expiryField.getText().trim();
            String cvv = cvvField.getText().trim();

            if (cardNumber.isEmpty() || expiry.isEmpty() || cvv.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Payment Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                new OrderTrackingPage().startTracking(this.getLocation());
                dispose();
            }
        });
        bgPanel.add(payButton);

        setContentPane(bgPanel);
    }
}
