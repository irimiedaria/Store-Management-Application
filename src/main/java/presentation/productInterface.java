package presentation;

import model.Product;
import BLL.ProductBll;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class productInterface extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldFindByIDProduct;
    private JTextField textFieldDeleteByIDProduct;
    private JTextField textFieldProductName;
    private JTextField textFieldProductQuantity;
    private JTextField textFieldIDForUpdateProduct;
    private JTextField textFieldNameForUpdate;
    private JTextField textFieldQuantityForUpdate;
    private JTable tableForProducts;

    private JTable tableForFoundProduct;
    private JTextField textFieldProductPrice;
    private JTextField textFieldPriceForUpdate;

    @Override
    public JPanel getContentPane() {
        return contentPane;
    }

    public JTextField getTextFieldFindByIDProduct() {
        return textFieldFindByIDProduct;
    }

    public JTextField getTextFieldDeleteByIDProduct() {
        return textFieldDeleteByIDProduct;
    }
    public productInterface() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 863, 565);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblProductTitle = new JLabel("PRODUCT");
        lblProductTitle.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        lblProductTitle.setBounds(376, 10, 188, 65);
        contentPane.add(lblProductTitle);

        JButton btnFindByIDProduct = new JButton("FIND");
        btnFindByIDProduct.setBackground(new Color(51, 153, 153));
        btnFindByIDProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductBll cbll = new ProductBll();
                int productID = Integer.parseInt(getTextFieldFindByIDProduct().getText());
                Product product = cbll.findProdusById(productID);
                List<Product> result = new ArrayList<>();
                result.add(product);
                DefaultTableModel tableModel = Reflection.getTableModel(result);
                tableForFoundProduct.setModel(tableModel);
            }
        });
        btnFindByIDProduct.setBounds(228, 78, 96, 33);
        btnFindByIDProduct.setBorderPainted(false);
        contentPane.add(btnFindByIDProduct);

        JLabel lblFindByIdProduct = new JLabel("Find by ID");
        lblFindByIdProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFindByIdProduct.setHorizontalAlignment(SwingConstants.TRAILING);
        lblFindByIdProduct.setBounds(10, 86, 77, 13);
        contentPane.add(lblFindByIdProduct);

        textFieldFindByIDProduct = new JTextField();
        textFieldFindByIDProduct.setBounds(108, 83, 96, 21);
        contentPane.add(textFieldFindByIDProduct);
        textFieldFindByIDProduct.setColumns(10);

        JLabel lblDeleteByIDProduct = new JLabel("Delete by ID");
        lblDeleteByIDProduct.setHorizontalAlignment(SwingConstants.TRAILING);
        lblDeleteByIDProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDeleteByIDProduct.setBounds(2, 129, 96, 13);
        contentPane.add(lblDeleteByIDProduct);

        textFieldDeleteByIDProduct = new JTextField();
        textFieldDeleteByIDProduct.setColumns(10);
        textFieldDeleteByIDProduct.setBounds(108, 127, 96, 21);
        contentPane.add(textFieldDeleteByIDProduct);

        JButton btnDeleteByIDProduct = new JButton("DELETE");
        btnDeleteByIDProduct.setBackground(new Color(51, 153, 153));
        btnDeleteByIDProduct.setBorderPainted(false);
        btnDeleteByIDProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductBll pbll = new ProductBll();
                int productID = Integer.parseInt(getTextFieldDeleteByIDProduct().getText());
                pbll.deleteByID(productID);
            }
        });
        btnDeleteByIDProduct.setBounds(228, 121, 96, 33);
        contentPane.add(btnDeleteByIDProduct);

        JLabel lblInsertNewProduct = new JLabel("Insert new product :");
        lblInsertNewProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblInsertNewProduct.setBounds(20, 174, 145, 13);
        contentPane.add(lblInsertNewProduct);

        JLabel lblProductName = new JLabel("NAME");
        lblProductName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProductName.setBounds(164, 174, 45, 13);
        contentPane.add(lblProductName);

        textFieldProductName = new JTextField();
        textFieldProductName.setBounds(238, 173, 71, 19);
        contentPane.add(textFieldProductName);
        textFieldProductName.setColumns(10);

        JLabel lblProductQuantity = new JLabel("QUANTITY");
        lblProductQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProductQuantity.setBounds(326, 174, 96, 13);
        contentPane.add(lblProductQuantity);

        textFieldProductQuantity = new JTextField();
        textFieldProductQuantity.setColumns(10);
        textFieldProductQuantity.setBounds(407, 173, 71, 19);
        contentPane.add(textFieldProductQuantity);

        JButton btnInsertProduct = new JButton("INSERT");
        btnInsertProduct.setBackground(new Color(176, 224, 230));
        btnInsertProduct.setBorderPainted(false);
        btnInsertProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductBll pbll = new ProductBll();
                String productName = textFieldProductName.getText();
                int productQuantity = Integer.parseInt(textFieldProductQuantity.getText());
                double productPrice = Double.parseDouble(textFieldProductPrice.getText());
                pbll.insert(productName,productQuantity, productPrice);
            }
        });
        btnInsertProduct.setBounds(683, 166, 85, 33);
        contentPane.add(btnInsertProduct);

        JLabel lblIDForUpdateProduct = new JLabel("Update Product with ID :");
        lblIDForUpdateProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblIDForUpdateProduct.setBounds(20, 211, 173, 13);
        contentPane.add(lblIDForUpdateProduct);

        textFieldIDForUpdateProduct = new JTextField();
        textFieldIDForUpdateProduct.setColumns(10);
        textFieldIDForUpdateProduct.setBounds(184, 210, 71, 19);
        contentPane.add(textFieldIDForUpdateProduct);

        JLabel lblNameForUpdateProduct = new JLabel("NAME ");
        lblNameForUpdateProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNameForUpdateProduct.setBounds(284, 216, 45, 13);
        contentPane.add(lblNameForUpdateProduct);

        textFieldNameForUpdate = new JTextField();
        textFieldNameForUpdate.setColumns(10);
        textFieldNameForUpdate.setBounds(370, 215, 85, 19);
        contentPane.add(textFieldNameForUpdate);

        JLabel lblQuantityForUpdateProduct = new JLabel("QUANTITY");
        lblQuantityForUpdateProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblQuantityForUpdateProduct.setBounds(284, 248, 85, 13);
        contentPane.add(lblQuantityForUpdateProduct);

        textFieldQuantityForUpdate = new JTextField();
        textFieldQuantityForUpdate.setColumns(10);
        textFieldQuantityForUpdate.setBounds(370, 247, 85, 19);
        contentPane.add(textFieldQuantityForUpdate);

        JButton btnUpdateName = new JButton("UPDATE NAME");
        btnUpdateName.setBorderPainted(false);
        btnUpdateName.setBackground(new Color(176, 224, 230));
        btnUpdateName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductBll pbll = new ProductBll();
                String productIDText = textFieldIDForUpdateProduct.getText();
                String newName = textFieldNameForUpdate.getText();

                try {
                    int productID = Integer.parseInt(productIDText);
                    pbll.updateName(productID, newName);

                    // Update the table
                    DefaultTableModel tableModel = (DefaultTableModel) tableForProducts.getModel();
                    int rowCount = tableModel.getRowCount();
                    for (int i = 0; i < rowCount; i++) {
                        String tableProductIDStr = tableModel.getValueAt(i, 0).toString();
                        int tableProductID = Integer.parseInt(tableProductIDStr);
                        if (tableProductID == productID) {
                            tableModel.setValueAt(newName, i, 1); // Update the name in the table
                            break;
                        }
                    }
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnUpdateName.setBounds(495, 214, 157, 21);
        contentPane.add(btnUpdateName);

        tableForProducts = new JTable();
        tableForProducts.setBounds(20, 305, 805, 135);
        contentPane.add(tableForProducts);

        JButton btnBackHomeFromProduct = new JButton("GO BACK HOME");
        btnBackHomeFromProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                homeInterface homeInterface = new homeInterface();
                homeInterface.setVisible(true);
            }
        });
        btnBackHomeFromProduct.setBackground(new Color(176, 196, 222));
        btnBackHomeFromProduct.setBorderPainted(false);
        btnBackHomeFromProduct.setBounds(20, 10, 157, 21);
        contentPane.add(btnBackHomeFromProduct);

        JLabel lblProductPrice = new JLabel("PRICE");
        lblProductPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProductPrice.setBounds(508, 176, 96, 13);
        contentPane.add(lblProductPrice);

        textFieldProductPrice = new JTextField();
        textFieldProductPrice.setColumns(10);
        textFieldProductPrice.setBounds(570, 173, 71, 19);
        contentPane.add(textFieldProductPrice);

        JLabel lblUpdatePriceForProduct = new JLabel("PRICE");
        lblUpdatePriceForProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblUpdatePriceForProduct.setBounds(284, 277, 85, 13);
        contentPane.add(lblUpdatePriceForProduct);

        textFieldPriceForUpdate = new JTextField();
        textFieldPriceForUpdate.setColumns(10);
        textFieldPriceForUpdate.setBounds(370, 276, 85, 19);
        contentPane.add(textFieldPriceForUpdate);

        JButton btnUpdateQuantity = new JButton("UPDATE QUANTITY");
        btnUpdateQuantity.setBorderPainted(false);
        btnUpdateQuantity.setBackground(new Color(176, 224, 230));
        btnUpdateQuantity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductBll pbll = new ProductBll();
                String productIDText = textFieldIDForUpdateProduct.getText();
                String newQuantityText = textFieldQuantityForUpdate.getText();

                try {
                    int productID = Integer.parseInt(productIDText);
                    int newQuantity = Integer.parseInt(newQuantityText);
                    if (isValidQantity(newQuantity)) {
                        pbll.updateQuantity(productID, newQuantity);

                        // Update the table
                        DefaultTableModel tableModel = (DefaultTableModel) tableForProducts.getModel();
                        int rowCount = tableModel.getRowCount();
                        for (int i = 0; i < rowCount; i++) {
                            String tableProductIDStr = tableModel.getValueAt(i, 0).toString();
                            int tableProductID = Integer.parseInt(tableProductIDStr);
                            if (tableProductID == productID) {
                                tableModel.setValueAt(newQuantity, i, 2); // Update the quantity in the table
                                break;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient quantity");
                    }
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnUpdateQuantity.setBounds(495, 246, 157, 21);
        contentPane.add(btnUpdateQuantity);

        JButton btnUpdatePrice = new JButton("UPDATE PRICE");
        btnUpdatePrice.setBorderPainted(false);
        btnUpdatePrice.setBackground(new Color(176, 224, 230));
        btnUpdatePrice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductBll pbll = new ProductBll();
                String productIDText = textFieldIDForUpdateProduct.getText();
                String newPriceText = textFieldPriceForUpdate.getText();

                try {
                    int productID = Integer.parseInt(productIDText);
                    double newPrice = Double.parseDouble(newPriceText);
                    if (isValidPrice(newPrice)) {
                        pbll.updatePrice(productID, newPrice);

                        // Update the table
                        DefaultTableModel tableModel = (DefaultTableModel) tableForProducts.getModel();
                        int rowCount = tableModel.getRowCount();
                        for (int i = 0; i < rowCount; i++) {
                            String tableProductIDStr = tableModel.getValueAt(i, 0).toString();
                            int tableProductID = Integer.parseInt(tableProductIDStr);
                            if (tableProductID == productID) {
                                tableModel.setValueAt(newPrice, i, 3); // Update the price in the table
                                break;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Price should be positive");
                    }
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnUpdatePrice.setBounds(495, 275, 157, 21);
        contentPane.add(btnUpdatePrice);

        JLabel lblProductFound = new JLabel("FOUND PRODUCT :");
        lblProductFound.setBounds(30, 450, 115, 13);
        contentPane.add(lblProductFound);

        tableForFoundProduct = new JTable();
        tableForFoundProduct.setBounds(20, 467, 805, 61);
        contentPane.add(tableForFoundProduct);

        JButton btnSeeAllProducts = new JButton("SEE ALL PRODUCTS");
        btnSeeAllProducts.setBackground(new Color(153, 204, 204));
        btnSeeAllProducts.setBorderPainted(false);
        btnSeeAllProducts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductBll pbll = new ProductBll();
                List<Product> result = pbll.findAll();
                DefaultTableModel tableModel = Reflection.getTableModel(result);
                tableForProducts.setModel(tableModel);
            }
        });
        btnSeeAllProducts.setBounds(20, 275, 157, 21);
        contentPane.add(btnSeeAllProducts);

    }
    private boolean isValidQantity(int quantity) {
        return quantity > 0;
    }

    private boolean isValidPrice(double price) {
        return price > 0;
    }
}

