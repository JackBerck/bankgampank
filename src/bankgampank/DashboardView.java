package bankgampank;

import javax.swing.*;

public class DashboardView extends JFrame {
    private JButton depositButton, withdrawButton, transferButton;
    private JLabel balanceLabel;
    private User user;

    public DashboardView(User user) {
        this.user = user;
        setTitle("Bank App - Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getUsername());
        welcomeLabel.setBounds(30, 20, 300, 25);
        add(welcomeLabel);

        balanceLabel = new JLabel("Balance: " + user.getBalance());
        balanceLabel.setBounds(30, 60, 300, 25);
        add(balanceLabel);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(30, 100, 100, 25);
        add(depositButton);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(150, 100, 100, 25);
        add(withdrawButton);

        transferButton = new JButton("Transfer");
        transferButton.setBounds(270, 100, 100, 25);
        add(transferButton);

        depositButton.addActionListener(e -> new DepositView(user, this).setVisible(true));
        withdrawButton.addActionListener(e -> new WithdrawView(user, this).setVisible(true));
        transferButton.addActionListener(e -> new TransferView(user, this).setVisible(true));
    }

    public void updateBalance(double newBalance) {
        balanceLabel.setText("Balance: " + newBalance);
    }
}
