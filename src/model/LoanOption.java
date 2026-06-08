package model;

public class LoanOption {

    private int loanOptionId;
    private String loanName;
    private double loanAmount;
    private double interestRate;
    private int tenureMonths;
    private double emi;

    public int getLoanOptionId() {
        return loanOptionId;
    }

    public void setLoanOptionId(int loanOptionId) {
        this.loanOptionId = loanOptionId;
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getTenureMonths() {
        return tenureMonths;
    }

    public void setTenureMonths(int tenureMonths) {
        this.tenureMonths = tenureMonths;
    }

    public double getEmi() {
        return emi;
    }

    public void setEmi(double emi) {
        this.emi = emi;
    }
}