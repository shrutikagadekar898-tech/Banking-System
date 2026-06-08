package dao;

import model.Bank;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankDAO {

    public boolean addBank(String name,String branch,String ifsc){

        String sql="INSERT INTO banks(bank_name,branch,ifsc) VALUES(?,?,?)";

        try(Connection con= DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){

            ps.setString(1,name);
            ps.setString(2,branch);
            ps.setString(3,ifsc);

            return ps.executeUpdate()>0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateBank(int id,String name,String branch,String ifsc){

        String sql="UPDATE banks SET bank_name=?,branch=?,ifsc=? WHERE bank_id=?";

        try(Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){

            ps.setString(1,name);
            ps.setString(2,branch);
            ps.setString(3,ifsc);
            ps.setInt(4,id);

            return ps.executeUpdate()>0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteBank(int id){

        String sql="DELETE FROM banks WHERE bank_id=?";

        try(Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){

            ps.setInt(1,id);

            return ps.executeUpdate()>0;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public List<Bank> getAllBanks(){

        List<Bank> list=new ArrayList<>();

        String sql="SELECT * FROM banks";

        try(Connection con=DBConnection.getConnection();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sql)){

            while(rs.next()){

                Bank b=new Bank();

                b.setBankId(rs.getInt("bank_id"));
                b.setBankName(rs.getString("bank_name"));
                b.setBranch(rs.getString("branch"));
                b.setIfsc(rs.getString("ifsc"));

                list.add(b);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
}