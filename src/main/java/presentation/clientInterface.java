package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import model.Client;
import BLL.*;

public class clientInterface extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldFindByID;
    private JTextField textFieldDeleteByID;
    private JTextField textFieldClientName;
    private JTextField textFieldClientAge;
    private JTextField textFieldIDForUpdate;
    private JTextField textFieldNameForUpdate;
    private JTextField textFieldAgeForUpdate;
    private JTable tableForClients;

    private JTable tableForFoundClient;

    @Override
    public JPanel getContentPane() {
        return contentPane;
    }

    public JTextField getTextFieldFindByID() {
        return textFieldFindByID;
    }

    public JTextField getTextFieldDeleteByID() {
        return textFieldDeleteByID;
    }

    public clientInterface() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 863, 565);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblClientTitle = new JLabel("CLIENT");
        lblClientTitle.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        lblClientTitle.setBounds(376, 10, 188, 65);
        contentPane.add(lblClientTitle);

        JButton btnFindByID = new JButton("FIND");
        btnFindByID.setBackground(new Color(216, 191, 216));
        btnFindByID.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    ClientBll cbll = new ClientBll();
                    int clientID = Integer.parseInt(getTextFieldFindByID().getText());
                    Client client = cbll.findClientById(clientID);
                    List<Client> result = new ArrayList<>();
                    result.add(client);
                    DefaultTableModel tableModel = Reflection.getTableModel(result);
                    tableForFoundClient.setModel(tableModel);
            }
        });
        btnFindByID.setBounds(228, 78, 96, 33);
        btnFindByID.setBorderPainted(false);
        contentPane.add(btnFindByID);

        JLabel lblFindById = new JLabel("Find by ID");
        lblFindById.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFindById.setHorizontalAlignment(SwingConstants.TRAILING);
        lblFindById.setBounds(10, 86, 77, 13);
        contentPane.add(lblFindById);

        textFieldFindByID = new JTextField();
        textFieldFindByID.setBounds(108, 83, 96, 21);
        contentPane.add(textFieldFindByID);
        textFieldFindByID.setColumns(10);

        JLabel lblDeleteByID = new JLabel("Delete by ID");
        lblDeleteByID.setHorizontalAlignment(SwingConstants.TRAILING);
        lblDeleteByID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDeleteByID.setBounds(2, 129, 96, 13);
        contentPane.add(lblDeleteByID);

        textFieldDeleteByID = new JTextField();
        textFieldDeleteByID.setColumns(10);
        textFieldDeleteByID.setBounds(108, 127, 96, 21);
        contentPane.add(textFieldDeleteByID);

        JButton btnDeleteByID = new JButton("DELETE");
        btnDeleteByID.setBackground(new Color(216, 191, 216));
        btnDeleteByID.setBorderPainted(false);
        btnDeleteByID.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientBll cbll = new ClientBll();
                int clientID = Integer.parseInt(getTextFieldDeleteByID().getText());
                cbll.deleteByID(clientID);
            }
        });
        btnDeleteByID.setBounds(228, 121, 96, 33);
        contentPane.add(btnDeleteByID);

        JLabel lblInsertNewClient = new JLabel("Insert new client :");
        lblInsertNewClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblInsertNewClient.setBounds(20, 174, 119, 13);
        contentPane.add(lblInsertNewClient);

        JLabel lblClientName = new JLabel("NAME");
        lblClientName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblClientName.setBounds(149, 174, 45, 13);
        contentPane.add(lblClientName);

        textFieldClientName = new JTextField();
        textFieldClientName.setBounds(204, 173, 71, 19);
        contentPane.add(textFieldClientName);
        textFieldClientName.setColumns(10);

        JLabel lblClientAge = new JLabel("AGE");
        lblClientAge.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblClientAge.setBounds(326, 174, 45, 13);
        contentPane.add(lblClientAge);

        textFieldClientAge = new JTextField();
        textFieldClientAge.setColumns(10);
        textFieldClientAge.setBounds(373, 173, 71, 19);
        contentPane.add(textFieldClientAge);

        JButton btnInsertClient = new JButton("INSERT");
        btnInsertClient.setBackground(new Color(188, 143, 143));
        btnInsertClient.setBorderPainted(false);
        btnInsertClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientBll cbll = new ClientBll();
                String clientName = textFieldClientName.getText();
                int age = Integer.parseInt(textFieldClientAge.getText());

                if (isValidAge(age)) {
                    cbll.insert(clientName, age);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid age");
                }
            }
        });
        btnInsertClient.setBounds(486, 166, 85, 33);
        contentPane.add(btnInsertClient);

        JLabel lblUpdateClient = new JLabel("Update client :");
        lblUpdateClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblUpdateClient.setBounds(20, 211, 119, 13);
        contentPane.add(lblUpdateClient);

        JLabel lblIDForUpdate = new JLabel("Update Client with ID :");
        lblIDForUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblIDForUpdate.setBounds(124, 211, 152, 13);
        contentPane.add(lblIDForUpdate);

        textFieldIDForUpdate = new JTextField();
        textFieldIDForUpdate.setColumns(10);
        textFieldIDForUpdate.setBounds(270, 210, 71, 19);
        contentPane.add(textFieldIDForUpdate);

        JLabel lblNameForUpdate = new JLabel("NAME ");
        lblNameForUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNameForUpdate.setBounds(383, 222, 45, 13);
        contentPane.add(lblNameForUpdate);

        textFieldNameForUpdate = new JTextField();
        textFieldNameForUpdate.setColumns(10);
        textFieldNameForUpdate.setBounds(438, 221, 85, 19);
        contentPane.add(textFieldNameForUpdate);

        JLabel lblAgeForUpdate = new JLabel("AGE");
        lblAgeForUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblAgeForUpdate.setBounds(383, 268, 45, 13);
        contentPane.add(lblAgeForUpdate);

        textFieldAgeForUpdate = new JTextField();
        textFieldAgeForUpdate.setColumns(10);
        textFieldAgeForUpdate.setBounds(438, 262, 85, 19);
        contentPane.add(textFieldAgeForUpdate);

        JButton btnUpdateName = new JButton("UPDATE NAME");
        btnUpdateName.setBorderPainted(false);
        btnUpdateName.setBackground(new Color(188, 143, 143));
        btnUpdateName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientBll cbll = new ClientBll();
                String clientIDText = textFieldIDForUpdate.getText();
                String newName = textFieldNameForUpdate.getText();

                try {
                    int clientID = Integer.parseInt(clientIDText);
                    cbll.updateName(clientID, newName);

                    // Update la tabel
                    DefaultTableModel tableModel = (DefaultTableModel) tableForClients.getModel();
                    int rowCount = tableModel.getRowCount();
                    for (int i = 0; i < rowCount; i++) {
                        String tableClientIDStr = tableModel.getValueAt(i, 0).toString();
                        int tableClientID = Integer.parseInt(tableClientIDStr);
                        if (tableClientID == clientID) {
                            tableModel.setValueAt(newName, i, 1); // Update la name in tabel
                            break;
                        }
                    }
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnUpdateName.setBounds(541, 214, 124, 33);
        contentPane.add(btnUpdateName);

        JButton btnUpdateAge = new JButton("UPDATE AGE");
        btnUpdateAge.setBorderPainted(false);
        btnUpdateAge.setBackground(new Color(188, 143, 143));
        btnUpdateAge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientBll cbll = new ClientBll();
                String clientIDText = textFieldIDForUpdate.getText();
                String newAgeText = textFieldAgeForUpdate.getText();

                try {
                    int clientID = Integer.parseInt(clientIDText);
                    int newAge = Integer.parseInt(newAgeText);
                    if (isValidAge(newAge)) {
                        cbll.updateAge(clientID, newAge);

                        // Update la tabel
                        DefaultTableModel tableModel = (DefaultTableModel) tableForClients.getModel();
                        int rowCount = tableModel.getRowCount();
                        for (int i = 0; i < rowCount; i++) {
                            String tableClientIDStr = tableModel.getValueAt(i, 0).toString();
                            int tableClientID = Integer.parseInt(tableClientIDStr);
                            if (tableClientID == clientID) {
                                tableModel.setValueAt(newAge, i, 2); // Update la age in tabel
                                break;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid age");
                    }
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnUpdateAge.setBounds(541, 257, 124, 33);
        contentPane.add(btnUpdateAge);

        tableForClients = new JTable();
        tableForClients.setBounds(22, 300, 805, 150);

        contentPane.add(tableForClients);

        JButton btnBackHomeFromClient = new JButton("GO BACK HOME");
        btnBackHomeFromClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                homeInterface homeInterface = new homeInterface();
                homeInterface.setVisible(true);
            }
        });
        btnBackHomeFromClient.setBackground(new Color(230, 230, 250));
        btnBackHomeFromClient.setBorderPainted(false);
        btnBackHomeFromClient.setBounds(20, 10, 157, 21);
        contentPane.add(btnBackHomeFromClient);

        JLabel lblFoundClient = new JLabel("FOUND CLIENT :");
        lblFoundClient.setBounds(20, 460, 119, 13);
        contentPane.add(lblFoundClient);

        tableForFoundClient = new JTable();
        tableForFoundClient.setBounds(22, 478, 805, 50);
        contentPane.add(tableForFoundClient);

        JButton btnSeeAllClients = new JButton("SEE ALL CLIENTS");
        btnSeeAllClients.setBackground(new Color(204, 204, 255));
        btnSeeAllClients.setBorderPainted(false);
        btnSeeAllClients.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ClientBll cbll = new ClientBll();
                List<Client> result = cbll.findAll();
                DefaultTableModel tableModel = Reflection.getTableModel(result);
                tableForClients.setModel(tableModel);
            }
        });
        btnSeeAllClients.setBounds(20, 266, 138, 21);
        contentPane.add(btnSeeAllClients);

    }
    private boolean isValidAge(int age) {
        return age > 0 && age <= 100;
    }
}

