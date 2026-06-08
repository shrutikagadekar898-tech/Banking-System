package dao;

import model.Loan;
import model.LoanOption;
import util.DBConnection;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {
    // -----------------------------
    // GET ALL LOAN OPTIONS
    // -----------------------------
    public List<LoanOption> getLoanOptions(){
        List<LoanOption> list = new ArrayList<>();

        String sql = "SELECT * FROM loan_options";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()){

                LoanOption option = new LoanOption();

                option.setLoanOptionId(rs.getInt("loan_option_id"));
                option.setLoanName(rs.getString("loan_name"));
                option.setLoanAmount(rs.getDouble("loan_amount"));
                option.setInterestRate(rs.getDouble("interest_rate"));
                option.setTenureMonths(rs.getInt("duration_months"));
                option.setEmi(rs.getDouble("emi"));

                list.add(option);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }


    // -----------------------------
    // APPLY LOAN
    // -----------------------------
    public boolean applyLoan(Loan loan){
boolean status = false;
        String sql = "INSERT INTO loan(user_id,loan_option_id,amount,interest_rate, tenure_months,emi,status) VALUES(?,?,?,?,?,?,?)";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1,loan.getUserId());
            ps.setInt(2,loan.getLoanOptionId());
            ps.setDouble(3,loan.getAmount());
            ps.setDouble(4,loan.getInterestRate());
            ps.setInt(5,loan.getTenureMonths());
            ps.setDouble(6,loan.getEmi());
            ps.setString(7,loan.getStatus());
          //  ps.setDate(8, new java.sql.Date(System.currentTimeMillis()));
            ps.executeUpdate();
            int rows = ps.executeUpdate();
            if(rows >0) {
                status = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return status;
    }


    // -----------------------------
    // GET USER LOANS
    // -----------------------------
    public List<Loan> getUserLoans(int userId){

        List<Loan> list = new ArrayList<>();

        String sql = "SELECT * FROM loan WHERE user_id=?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1,userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                Loan loan = new Loan();

                loan.setLoanId(rs.getInt("loan_id"));
                loan.setUserId(rs.getInt("user_id"));
                loan.setLoanOptionId(rs.getInt("loan_option_id"));
                loan.setAmount(rs.getDouble("amount"));
                loan.setInterestRate(rs.getDouble("interest_rate"));
                loan.setTenureMonths(rs.getInt("tenure_months"));
                loan.setEmi(rs.getDouble("emi"));
                loan.setStatus(rs.getString("status"));
               // loan.setApplyDate(rs.getDate("apply_date"));

                list.add(loan);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }


    // -----------------------------
    // ADMIN - GET ALL LOANS
    // -----------------------------
    public List<Loan> getAllLoans(){

        List<Loan> list = new ArrayList<>();

        String sql = "SELECT * FROM loan";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()){

                Loan loan = new Loan();

                loan.setLoanId(rs.getInt("loan_id"));
                loan.setUserId(rs.getInt("user_id"));
                loan.setLoanOptionId(rs.getInt("loan_option_id"));

                loan.setAmount(rs.getDouble("amount"));
                loan.setInterestRate(rs.getDouble("interest_rate"));
                loan.setTenureMonths(rs.getInt("tenure_months"));
                loan.setEmi(rs.getDouble("emi"));

                loan.setStatus(rs.getString("status"));

                list.add(loan);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
    // -----------------------------
    // ADMIN APPROVE LOAN
    // -----------------------------
    public boolean approveLoan(int loanId){

        String sql = "UPDATE loan SET status='APPROVED' WHERE loan_id=?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1,loanId);

            ps.executeUpdate();

            return true;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }


    // -----------------------------
    // ADMIN REJECT LOAN
    // -----------------------------
    public boolean rejectLoan(int loanId){

        String sql = "UPDATE loan SET status='REJECTED' WHERE loan_id=?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1,loanId);

            ps.executeUpdate();

            return true;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

}