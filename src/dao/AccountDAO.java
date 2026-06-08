package dao;

import model.Account;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    // 🔹 Create Account
    public boolean createAccount(int userId, int bankId, double balance) {

        String sql = "INSERT INTO accounts(user_id, bank_id, balance) VALUES (?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, bankId);
            ps.setDouble(3, balance);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // 🔹 Get All Accounts (JOIN)
    public List<Account> getAllAccountsWithDetails() {

        List<Account> list = new ArrayList<>();

        String sql = "SELECT a.account_id, u.username, b.bank_name, a.balance " +
                "FROM accounts a " +
                "JOIN users u ON a.user_id = u.user_id " +
                "JOIN banks b ON a.bank_id = b.bank_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Account a = new Account();
                a.setAccountId(rs.getInt("account_id"));
                a.setUsername(rs.getString("username"));
                a.setBankName(rs.getString("bank_name"));
                a.setBalance(rs.getDouble("balance"));

                list.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    // 🔹 Deposit
    public boolean deposit(int accountId, double amount) {

        String sql = "UPDATE accounts SET balance = balance + ? WHERE account_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, amount);
            ps.setInt(2, accountId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // 🔹 Withdraw
    public boolean withdraw(int accountId, double amount) {

        String sql = "UPDATE accounts SET balance = balance - ?" +
                "WHERE account_id=? AND balance >= ? ";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, amount);
            ps.setInt(2, accountId);
            ps.setDouble(3, amount);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public List<Account> getAllAccounts(){

        List<Account> list = new ArrayList<>();

        String sql = "SELECT * FROM accounts";

        try(Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){

            while(rs.next()){

                Account a = new Account();

                a.setAccountId(rs.getInt("account_id"));
                a.setUserId(rs.getInt("user_id"));
                a.setBankId(rs.getInt("bank_id"));
                a.setBalance(rs.getDouble("balance"));

                list.add(a);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
   /* public List<Object[]> getAllAccounts(){

        List<Object[]> list = new ArrayList<>();

        String sql = "SELECT * FROM accounts";

        try(Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){

            while(rs.next()){

                list.add(new Object[]{
                        rs.getInt("account_id"),
                        rs.getInt("user_id"),
                        rs.getInt("bank_id"),
                        rs.getDouble("balance")
                });

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }*/
    // 🔹 Transfer (Transaction Safe)
   public boolean transfer(int from,int to,double amount){

       try(Connection con = DBConnection.getConnection()){

           con.setAutoCommit(false);

           PreparedStatement ps1 = con.prepareStatement(
                   "UPDATE accounts SET balance=balance-? WHERE account_id=?");

           ps1.setDouble(1,amount);
           ps1.setInt(2,from);
           ps1.executeUpdate();


           PreparedStatement ps2 = con.prepareStatement(
                   "UPDATE accounts SET balance=balance+? WHERE account_id=?");

           ps2.setDouble(1,amount);
           ps2.setInt(2,to);
           ps2.executeUpdate();

           con.commit();

           return true;

       }catch(Exception e){
           e.printStackTrace();
       }

       return false;
   }
}