package dao;
import util.DBConnection;
import java.sql.*;
import model.Transaction;
import java.util.List;
import java.util.ArrayList;

public class TransactionDAO {

    public void save(Connection conn,long from,long to,double amt,String type) throws Exception {
        String sql="INSERT INTO transactions(from_account,to_account,amount,type) VALUES(?,?,?,?)";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setLong(1,from);
        ps.setLong(2,to);
        ps.setDouble(3,amt);
        ps.setString(4,type);
        ps.executeUpdate();
    }
    public List<Transaction> getTransactions(){

        List<Transaction> list = new ArrayList<>();

        String sql = "SELECT * FROM transactions";

        try(Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){

            while(rs.next()){

                Transaction t = new Transaction();

                t.setId(rs.getInt("txn_id"));
                t.setAccountId(rs.getInt("account_id"));
                t.setType(rs.getString("type"));
                t.setAmount(rs.getDouble("amount"));
                t.setDate(rs.getDate("txn_date"));

                list.add(t);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
}