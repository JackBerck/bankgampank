package bankgampank;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepositView extends JFrame {
    private JTextField amountField;
    private JButton depositButton;
    private User user;
    private DashboardView dashboard;

    public DepositView(User user, DashboardView dashboard) {
        this.user = user;
        this.dashboard = dashboard;
        setTitle("Bank App - Deposit");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(30, 30, 80, 25);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(100, 30, 150, 25);
        add(amountField);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(100, 70, 100, 25);
        add(depositButton);

        depositButton.addActionListener(new DepositAction());
    }

    private class DepositAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                TransactionController transactionController = new TransactionController();
                if (transactionController.deposit(user.getId(), amount)) {
                    user.setBalance(user.getBalance() + amount);
                    JOptionPane.showMessageDialog(null, "Deposit successful!");

                    // Update balance on DashboardView
                    dashboard.updateBalance(user.getBalance());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Deposit failed.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
            }
        }
    }
}
