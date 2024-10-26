package bankgampank;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferView extends JFrame {
    private JTextField targetUserField, amountField;
    private JButton transferButton;
    private User user;
    private DashboardView dashboard;

    public TransferView(User user, DashboardView dashboard) {
        this.user = user;
        this.dashboard = dashboard;
        setTitle("Bank App - Transfer");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel targetUserLabel = new JLabel("Target Username:");
        targetUserLabel.setBounds(30, 30, 120, 25);
        add(targetUserLabel);

        targetUserField = new JTextField();
        targetUserField.setBounds(150, 30, 100, 25);
        add(targetUserField);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(30, 70, 80, 25);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(150, 70, 100, 25);
        add(amountField);

        transferButton = new JButton("Transfer");
        transferButton.setBounds(90, 120, 100, 25);
        add(transferButton);

        transferButton.addActionListener(new TransferAction());
    }

    private class TransferAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String targetUsernameStr = targetUserField.getText();
                int targetUsername = Integer.parseInt(targetUsernameStr);
                double amount = Double.parseDouble(amountField.getText());
                
                TransactionController transactionController = new TransactionController();
                if (transactionController.transfer(user.getId(), targetUsername, amount)) {
                    user.setBalance(user.getBalance() - amount);
                    JOptionPane.showMessageDialog(null, "Transfer successful!");

                    // Update balance on DashboardView
                    dashboard.updateBalance(user.getBalance());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Transfer failed.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid username (numeric) and amount.");
            }
        }
    }
}
