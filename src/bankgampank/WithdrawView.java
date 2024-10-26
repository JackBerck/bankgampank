package bankgampank;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WithdrawView extends JFrame {
    private JTextField amountField;
    private JButton withdrawButton;
    private User user;
    private DashboardView dashboard;

    public WithdrawView(User user, DashboardView dashboard) {
        this.user = user;
        this.dashboard = dashboard;
        setTitle("Bank App - Withdraw");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(30, 30, 80, 25);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(120, 30, 150, 25);
        add(amountField);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(90, 80, 100, 25);
        add(withdrawButton);

        withdrawButton.addActionListener(new WithdrawAction());
    }

    private class WithdrawAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                TransactionController transactionController = new TransactionController();
                if (transactionController.withdraw(user.getId(), amount)) {
                    user.setBalance(user.getBalance() - amount);
                    JOptionPane.showMessageDialog(null, "Withdraw successful!");

                    // Update balance on DashboardView
                    dashboard.updateBalance(user.getBalance());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Withdraw failed.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid amount.");
            }
        }
    }
}
