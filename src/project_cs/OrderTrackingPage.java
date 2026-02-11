/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_cs;


import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class OrderTrackingPage extends JFrame {

    private JLabel statusLabel;
    private JProgressBar progressBar;
    private JLabel stage1, stage2, stage3;

    public OrderTrackingPage() {
        setTitle("Order Tracking");
        setSize(390, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String imagePath = "C:\\Users\\wajdd\\Downloads\\AdminPic.jpg";
        Image bgImage = new ImageIcon(imagePath).getImage();
        BackgroundPanel bgPanel = new BackgroundPanel(bgImage);
        bgPanel.setLayout(null);

        statusLabel = new JLabel("Tracking your order...", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setBounds(30, 30, 320, 40);
        bgPanel.add(statusLabel);

        stage1 = new JLabel("✓ Order Confirmed");
        stage2 = new JLabel("✓ Preparing Order");
        stage3 = new JLabel("✓ On the Way");

        Font stageFont = new Font("Arial", Font.PLAIN, 16);
        stage1.setFont(stageFont);
        stage2.setFont(stageFont);
        stage3.setFont(stageFont);

        stage1.setForeground(Color.LIGHT_GRAY);
        stage2.setForeground(Color.LIGHT_GRAY);
        stage3.setForeground(Color.LIGHT_GRAY);

        stage1.setBounds(40, 100, 300, 30);
        stage2.setBounds(40, 160, 300, 30);
        stage3.setBounds(40, 220, 300, 30);

        bgPanel.add(stage1);
        bgPanel.add(stage2);
        bgPanel.add(stage3);

        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(45, 300, 280, 30);
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(new Color(0, 70, 60));
        progressBar.setBorderPainted(false);

        bgPanel.add(progressBar);
        setContentPane(bgPanel);
    }

    public void startTracking(Point location) {
        setLocation(location);
        setVisible(true);

        Thread trackingThread = new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    final int progress = i;
                    SwingUtilities.invokeLater(() -> {
                        progressBar.setValue(progress);

                        if (progress <= 30) {
                            statusLabel.setText("Order is being confirmed...");
                            stage1.setForeground(new Color(0, 128, 0));
                        } else if (progress <= 60) {
                            statusLabel.setText("Restaurant is preparing your food...");
                            stage2.setForeground(new Color(0, 128, 0));
                        } else if (progress < 100) {
                            statusLabel.setText("Order is on the way...");
                            stage3.setForeground(new Color(0, 128, 0));
                        } else {
                            statusLabel.setText("Order Delivered!");
                        }
                    });

                    TimeUnit.MILLISECONDS.sleep(300); 
                }

                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this, "Your order has been successfully delivered!", "Delivery Complete", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        trackingThread.start();
    }
}
