package bankgampank;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginView() {
        setTitle("Bank App - Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 30, 80, 25);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 30, 150, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 70, 80, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 150, 25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(30, 110, 100, 25);
        add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(170, 110, 100, 25);
        add(registerButton);

        loginButton.addActionListener(new LoginAction());
        registerButton.addActionListener(e -> new RegisterView().setVisible(true));
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            UserController userController = new UserController();
            User user = userController.login(username, password);
            if (user != null) {
                JOptionPane.showMessageDialog(null, "Login successful!");
                new DashboardView(user).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}
