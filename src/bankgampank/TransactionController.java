package bankgampank;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionController {
    public boolean deposit(int userId, double amount) {
        String updateBalance = "UPDATE users SET balance = balance + ? WHERE id = ?";
        String insertTransaction = "INSERT INTO transactions (user_id, transaction_type, amount) VALUES (?, 'deposit', ?)";
        
        try (Connection conn = DatabaseConnection.connect()) {
            conn.setAutoCommit(false);  // Memulai transaksi

            try (PreparedStatement stmt1 = conn.prepareStatement(updateBalance);
                 PreparedStatement stmt2 = conn.prepareStatement(insertTransaction)) {
                stmt1.setDouble(1, amount);
                stmt1.setInt(2, userId);
                stmt1.executeUpdate();

                stmt2.setInt(1, userId);
                stmt2.setDouble(2, amount);
                stmt2.executeUpdate();

                conn.commit();  // Menyelesaikan transaksi
                return true;
            } catch (SQLException e) {
                conn.rollback();  // Batalkan jika ada kesalahan
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean withdraw(int userId, double amount) {
        String updateBalance = "UPDATE users SET balance = balance - ? WHERE id = ? AND balance >= ?";
        String insertTransaction = "INSERT INTO transactions (user_id, transaction_type, amount) VALUES (?, 'withdraw', ?)";

        try (Connection conn = DatabaseConnection.connect()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt1 = conn.prepareStatement(updateBalance);
                 PreparedStatement stmt2 = conn.prepareStatement(insertTransaction)) {
                stmt1.setDouble(1, amount);
                stmt1.setInt(2, userId);
                stmt1.setDouble(3, amount);
                int rowsUpdated = stmt1.executeUpdate();

                if (rowsUpdated > 0) {
                    stmt2.setInt(1, userId);
                    stmt2.setDouble(2, amount);
                    stmt2.executeUpdate();
                    conn.commit();
                    return true;
                }
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean transfer(int userId, int targetUserId, double amount) {
        String deductBalance = "UPDATE users SET balance = balance - ? WHERE id = ? AND balance >= ?";
        String addBalance = "UPDATE users SET balance = balance + ? WHERE id = ?";
        String insertTransaction = "INSERT INTO transactions (user_id, transaction_type, amount, target_user_id) VALUES (?, 'transfer', ?, ?)";

        try (Connection conn = DatabaseConnection.connect()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt1 = conn.prepareStatement(deductBalance);
                 PreparedStatement stmt2 = conn.prepareStatement(addBalance);
                 PreparedStatement stmt3 = conn.prepareStatement(insertTransaction)) {
                stmt1.setDouble(1, amount);
                stmt1.setInt(2, userId);
                stmt1.setDouble(3, amount);
                int rowsUpdated = stmt1.executeUpdate();

                if (rowsUpdated > 0) {
                    stmt2.setDouble(1, amount);
                    stmt2.setInt(2, targetUserId);
                    stmt2.executeUpdate();

                    stmt3.setInt(1, userId);
                    stmt3.setDouble(2, amount);
                    stmt3.setInt(3, targetUserId);
                    stmt3.executeUpdate();

                    conn.commit();
                    return true;
                }
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
