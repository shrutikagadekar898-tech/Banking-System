package util;

public class EMICalculator {

    public static double calculateEMI(double principal,double rate,int months){

        double r = rate / (12 * 100);

        double emi =
                (principal * r * Math.pow(1+r,months)) /
                        (Math.pow(1+r,months)-1);

        return Math.round(emi);
    }
}