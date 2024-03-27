package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class homeInterface extends JFrame {

    private JFrame homeInterf;

    public homeInterface() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setSize(355, 451);

        homeInterf = this;

        JLabel lblHome = new JLabel("HOME");
        lblHome.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
        lblHome.setBounds(147, 44, 106, 30);
        getContentPane().add(lblHome);

        JButton btnHomeClient = new JButton("CLIENT");
        btnHomeClient.setBackground(new Color(143, 188, 143));
        btnHomeClient.setBorderPainted(false);
        btnHomeClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                clientInterface clientInterf = new clientInterface();
                clientInterf.setVisible(true);
            }
        });
        btnHomeClient.setBounds(66, 121, 222, 35);
        getContentPane().add(btnHomeClient);

        JButton btnHomeProduct = new JButton("PRODUCT");
        btnHomeProduct.setBackground(new Color(143, 188, 143));
        btnHomeProduct.setBorderPainted(false);
        btnHomeProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                productInterface productInterf = new productInterface();
                productInterf.setVisible(true);
            }
        });
        btnHomeProduct.setBounds(66, 202, 222, 35);
        getContentPane().add(btnHomeProduct);

        JButton btnHomeOrder = new JButton("ORDER");
        btnHomeOrder.setBackground(new Color(143, 188, 143));
        btnHomeOrder.setBorderPainted(false);
        btnHomeOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                orderInterface orderInterf = new orderInterface();
                orderInterf.setVisible(true);
            }
        });
        btnHomeOrder.setBounds(66, 284, 222, 35);
        getContentPane().add(btnHomeOrder);
    }
}
