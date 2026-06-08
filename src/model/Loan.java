package model;
import java.util.*;
public class Loan {

    int loanId;
    int userId;
    int loanOptionId;
    Date applyDate;
    double amount;
    double interestRate;
    int tenureMonths;
    double emi;
    String status;

    public int getLoanId(){ return loanId; }
    public void setLoanId(int loanId){ this.loanId=loanId; }

    public int getUserId(){ return userId; }
    public void setUserId(int userId){ this.userId=userId; }
public int getLoanOptionId() { return loanOptionId; }
    public void setLoanOptionId(int loanOptionId) { this.loanOptionId= loanOptionId;}

    public Date getApplyDate() {
        return applyDate;
    }
    public void setApplyDate(Date applyDate){ this.applyDate= applyDate; }

    public double getAmount(){ return amount; }
    public void setAmount(double amount){ this.amount=amount; }

    public double getInterestRate(){ return interestRate; }
    public void setInterestRate(double interestRate){ this.interestRate=interestRate; }

    public int getTenureMonths(){ return tenureMonths; }
    public void setTenureMonths(int tenureMonths){ this.tenureMonths=tenureMonths; }

    public double getEmi(){ return emi; }
    public void setEmi(double emi){ this.emi=emi; }

    public String getStatus(){ return status; }
    public void setStatus(String status){ this.status=status; }
}