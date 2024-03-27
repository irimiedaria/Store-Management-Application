package presentation;

import BLL.ClientBll;
import BLL.OrdersBll;
import BLL.ProductBll;
import model.Client;
import model.Orders;
import model.Product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class orderInterface extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldFindByIDOrder;
    private JTextField textFieldDeleteByIDOrder;
    private JTextField textFieldClientIDForOrder;
    private JTextField textFieldProductIDForOrder;
    private JTextField textFieldIDForUpdateOrder;
    private JTextField textFieldQuantityForUpdateOrder;
    private JTable tableForOrders;
    private JTextField textFieldQuantityForOrder;
    private JTable tableForFoundOrder;

    public orderInterface() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 863, 565);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblOrderTitle = new JLabel("ORDER");
        lblOrderTitle.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        lblOrderTitle.setBounds(376, 10, 188, 65);
        contentPane.add(lblOrderTitle);

        JButton btnFindByIDOrder = new JButton("FIND");
        btnFindByIDOrder.setBackground(new Color(255, 165, 0));
        btnFindByIDOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrdersBll obll = new OrdersBll();
                int orderID = Integer.parseInt(textFieldFindByIDOrder.getText());
                Orders orders = obll.findOrderById(orderID);
                List<Orders> result = new ArrayList<>();
                result.add(orders);
                DefaultTableModel tableModel = Reflection.getTableModel(result);
                tableForFoundOrder.setModel(tableModel);
            }
        });
        btnFindByIDOrder.setBounds(228, 78, 96, 33);
        btnFindByIDOrder.setBorderPainted(false);
        contentPane.add(btnFindByIDOrder);

        JLabel lblFindByIdOrder = new JLabel("Find by ID");
        lblFindByIdOrder.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFindByIdOrder.setHorizontalAlignment(SwingConstants.TRAILING);
        lblFindByIdOrder.setBounds(10, 86, 77, 13);
        contentPane.add(lblFindByIdOrder);

        textFieldFindByIDOrder = new JTextField();
        textFieldFindByIDOrder.setBounds(108, 83, 96, 21);
        contentPane.add(textFieldFindByIDOrder);
        textFieldFindByIDOrder.setColumns(10);

        JLabel lblDeleteByIDOrder = new JLabel("Delete by ID");
        lblDeleteByIDOrder.setHorizontalAlignment(SwingConstants.TRAILING);
        lblDeleteByIDOrder.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDeleteByIDOrder.setBounds(2, 129, 96, 13);
        contentPane.add(lblDeleteByIDOrder);

        textFieldDeleteByIDOrder = new JTextField();
        textFieldDeleteByIDOrder.setColumns(10);
        textFieldDeleteByIDOrder.setBounds(108, 127, 96, 21);
        contentPane.add(textFieldDeleteByIDOrder);

        JButton btnDeleteByIDOrder = new JButton("DELETE");
        btnDeleteByIDOrder.setBackground(new Color(255, 165, 0));
        btnDeleteByIDOrder.setBorderPainted(false);
        btnDeleteByIDOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrdersBll obll = new OrdersBll();
                int orderID = Integer.parseInt(textFieldDeleteByIDOrder.getText());
                obll.deleteByID(orderID);
            }
        });
        btnDeleteByIDOrder.setBounds(228, 121, 96, 33);
        contentPane.add(btnDeleteByIDOrder);

        JLabel lblInsertNewOrder = new JLabel("Insert new order :");
        lblInsertNewOrder.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblInsertNewOrder.setBounds(20, 174, 145, 13);
        contentPane.add(lblInsertNewOrder);

        JLabel lblClientIDForOrder = new JLabel("CLIENT ID");
        lblClientIDForOrder.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblClientIDForOrder.setBounds(151, 174, 77, 13);
        contentPane.add(lblClientIDForOrder);

        textFieldClientIDForOrder = new JTextField();
        textFieldClientIDForOrder.setBounds(238, 173, 71, 19);
        contentPane.add(textFieldClientIDForOrder);
        textFieldClientIDForOrder.setColumns(10);

        JLabel lblProductIDForOrder = new JLabel("PRODUCT ID");
        lblProductIDForOrder.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProductIDForOrder.setBounds(326, 174, 96, 13);
        contentPane.add(lblProductIDForOrder);

        textFieldProductIDForOrder = new JTextField();
        textFieldProductIDForOrder.setColumns(10);
        textFieldProductIDForOrder.setBounds(427, 173, 71, 19);
        contentPane.add(textFieldProductIDForOrder);

        JButton btnInsertOrder = new JButton("INSERT");
        btnInsertOrder.setBackground(new Color(189, 183, 107));
        btnInsertOrder.setBorderPainted(false);
        btnInsertOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrdersBll obll = new OrdersBll();
                Integer clientID = Integer.parseInt(textFieldClientIDForOrder.getText());
                Integer productID = Integer.parseInt(textFieldProductIDForOrder.getText());
                Integer orderQuantity = Integer.parseInt(textFieldQuantityForOrder.getText());

                if (isValidQuantity(orderQuantity)) {
                    boolean checkStock = obll.insertOrder(clientID, productID, orderQuantity);

                    if (!checkStock) {
                        JOptionPane.showMessageDialog(null, "Insufficient stock");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Quantity should be positive");
                }
            }
        });
        btnInsertOrder.setBounds(683, 166, 85, 33);
        contentPane.add(btnInsertOrder);

        JLabel lblIDForUpdateOrder = new JLabel("Update order with ID :");
        lblIDForUpdateOrder.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblIDForUpdateOrder.setBounds(31, 221, 173, 13);
        contentPane.add(lblIDForUpdateOrder);

        textFieldIDForUpdateOrder = new JTextField();
        textFieldIDForUpdateOrder.setColumns(10);
        textFieldIDForUpdateOrder.setBounds(184, 220, 71, 19);
        contentPane.add(textFieldIDForUpdateOrder);

        JLabel lblQuantityForUpdateOrder = new JLabel("QUANTITY");
        lblQuantityForUpdateOrder.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblQuantityForUpdateOrder.setBounds(282, 221, 85, 13);
        contentPane.add(lblQuantityForUpdateOrder);

        textFieldQuantityForUpdateOrder = new JTextField();
        textFieldQuantityForUpdateOrder.setColumns(10);
        textFieldQuantityForUpdateOrder.setBounds(376, 220, 85, 19);
        contentPane.add(textFieldQuantityForUpdateOrder);

        tableForOrders = new JTable();
        tableForOrders.setBounds(20, 281, 805, 145);
        contentPane.add(tableForOrders);

        JButton btnBackHomeFromOrder = new JButton("GO BACK HOME");
        btnBackHomeFromOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                homeInterface homeInterface = new homeInterface();
                homeInterface.setVisible(true);
            }
        });
        btnBackHomeFromOrder.setBackground(new Color(255, 228, 181));
        btnBackHomeFromOrder.setBorderPainted(false);
        btnBackHomeFromOrder.setBounds(20, 10, 157, 21);
        contentPane.add(btnBackHomeFromOrder);

        JLabel lblOrderQuantity = new JLabel("QUANTITY");
        lblOrderQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblOrderQuantity.setBounds(508, 176, 96, 13);
        contentPane.add(lblOrderQuantity);

        textFieldQuantityForOrder = new JTextField();
        textFieldQuantityForOrder.setColumns(10);
        textFieldQuantityForOrder.setBounds(583, 173, 71, 19);
        contentPane.add(textFieldQuantityForOrder);

        JButton btnUpdateQuantityForOrder = new JButton("UPDATE QUANTITY");
        btnUpdateQuantityForOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                OrdersBll obll = new OrdersBll();
                Integer orderID = Integer.parseInt(textFieldIDForUpdateOrder.getText());
                Orders orders = obll.findOrderById(orderID);
                Integer newOrderQuantity = Integer.parseInt(textFieldQuantityForUpdateOrder.getText());

                if (isValidQuantity(newOrderQuantity)) {
                    boolean checkStock = obll.updateOrder(orderID, orders.getIdClient(), orders.getIdProdus(), newOrderQuantity);
                     if (!checkStock) {
                         JOptionPane.showMessageDialog(null, "Insufficient stock");
                     }  else {
                         // Update the table
                         DefaultTableModel tableModel = (DefaultTableModel) tableForOrders.getModel();
                         int rowCount = tableModel.getRowCount();
                         for (int i = 0; i < rowCount; i++) {
                             String tableOrderIDStr = tableModel.getValueAt(i, 0).toString();
                             int tableOrderID = Integer.parseInt(tableOrderIDStr);
                             if (tableOrderID == orderID) {
                                 tableModel.setValueAt(newOrderQuantity, i, 3); // Update the quantity in the table
                                 break;
                             }
                         }
                     }
                 } else {
                     JOptionPane.showMessageDialog(null, "Quantity should be positive");
                 }
            }
        });
        btnUpdateQuantityForOrder.setBorderPainted(false);
        btnUpdateQuantityForOrder.setBackground(new Color(189, 183, 107));
        btnUpdateQuantityForOrder.setBounds(497, 213, 157, 33);
        contentPane.add(btnUpdateQuantityForOrder);

        JLabel lblFoundOrder = new JLabel("FOUND ORDER :");
        lblFoundOrder.setBounds(20, 434, 173, 13);
        contentPane.add(lblFoundOrder);

        tableForFoundOrder = new JTable();
        tableForFoundOrder.setBounds(20, 458, 805, 60);
        contentPane.add(tableForFoundOrder);

        JButton btnSeeAllOrders = new JButton("SEE ALL ORDERS");
        btnSeeAllOrders.setBackground(new Color(255, 204, 102));
        btnSeeAllOrders.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrdersBll obll = new OrdersBll();
                List<Orders> result = obll.findAll();
                DefaultTableModel tableModel = Reflection.getTableModel(result);
                tableForOrders.setModel(tableModel);
            }
        });
        btnSeeAllOrders.setBorderPainted(false);
        btnSeeAllOrders.setBounds(20, 250, 157, 21);
        contentPane.add(btnSeeAllOrders);
    }

    private boolean isValidQuantity(int quantity) {
        return quantity > 0;
    }
}