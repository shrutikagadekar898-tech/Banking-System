package service;
import util.DBConnection;
import dao.TransactionDAO;
import java.sql.*;

public class BankingService {

    public void transfer(long from,long to,double amt) throws Exception {
        Connection conn=DBConnection.getConnection();
        conn.setAutoCommit(false);
        try {
            PreparedStatement ps1=conn.prepareStatement(
                    "UPDATE accounts SET balance=balance-? WHERE account_number=? AND balance>=?");
            ps1.setDouble(1,amt);
            ps1.setLong(2,from);
            ps1.setDouble(3,amt);
            if(ps1.executeUpdate()==0) throw new Exception("Insufficient Balance");

            PreparedStatement ps2=conn.prepareStatement(
                    "UPDATE accounts SET balance=balance+? WHERE account_number=?");
            ps2.setDouble(1,amt);
            ps2.setLong(2,to);
            ps2.executeUpdate();

            new TransactionDAO().save(conn,from,to,amt,"TRANSFER");
            conn.commit();
        } catch(Exception e){
            conn.rollback();
            throw e;
        }
        conn.close();
    }
}