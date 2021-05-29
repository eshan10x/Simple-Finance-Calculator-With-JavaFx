package sample;

public class Calculation {


    //*****************finance1 calculations ********************//
    //to get finance1 future value
    public static double finance_fv_calculation(double startP, double interestR, double numberP) {
        double fv = (Math.pow(1 + interestR,numberP)*startP);
        return Math.round((fv) * 100.0 / 100.0);
    }


    //to get finance1 start principle
    public static double finance_sp_calculation(double futureV, double interestR, double numberP) {
        double sp = futureV / (Math.pow(1+interestR,numberP));
        return Math.round((sp)*100.0/100.0);
    }

    //to get finance1 interest rate
    public static double interest_rate_cal(double futureV,double startP,double numberP) {
        //double pow = 1/(12*np);
        //to calculate the interest value for finance1
        double ir = Math.pow((futureV/startP),1/(12*numberP));
        double anIr = (ir-1)*12*100;
        return Math.round((anIr)*100.0)/100.0;
    }

    //to get finance1 number of payments
    public static double number_Of_Payments(double futureV,double startP,double interestR) {
        double nP = Math.log((interestR*futureV)/(interestR*startP))/Math.log(1+interestR);
        return Math.round((nP)*100.0)/100.0;
    }


    //**********************finance2 calculations****************//

    //to get finance2(compound savings)Future Value(beginning)
    public static double future_value_cal_beg(double startp,double interestR,double numberP,double annuityP) {
        //get total annuity payment
        double apFV = (annuityP / interestR) * (Math.pow(1+interestR,numberP)-1);
        //get future value for starting principle
        double irSp = (Math.pow(1+interestR,numberP)*startp);
        double fV = apFV + irSp;
        return Math.round((fV)*100.0) / 100.0 ;
    }

    //to get finance2(compound savings)present value(beginning)
    public static double present_value_cal_beg(double futureV,double interestR,double numberP,double annuityP) {
        //get total annuity payment
        double apFV = (annuityP / interestR) * (Math.pow(1+interestR,numberP)-1);
        double totSp = futureV-apFV;
        double sp = totSp/(Math.pow(1+interestR,numberP));
        return Math.round((sp)*100.0) / 100.0;
    }

    //to get number of periods(beginning)
    public static double number_periods_beg(double futureV,double interestR,double startP,double annuityP) {
        double np = Math.log((interestR*futureV+annuityP)/(interestR*startP+annuityP)/Math.log(1+interestR));
        return Math.round((np)*100.0) / 100.0;
    }

    //to get monthly payment for finance2(beginning)
    public static double mp_beg(double futureV,double interestR,double startP,double numberP) {
        double pow = Math.pow(1+interestR,numberP);
        double pmt = interestR* (futureV-(startP* pow)) / (pow - 1);
        return Math.round((pmt)*100.0) / 100.0;
    }

    //to get future value in finance2(compound saving)(end)
    public static double future_val_end(double startP,double interestR,double numberP,double annuityP) {
        double apFv = (annuityP / interestR) * (Math.pow(1+interestR,numberP)-1);
        double irSp = (Math.pow(1+interestR,numberP)*startP);
        double fv = irSp + apFv;
        return Math.round((fv)*100.0) / 100.0;
    }

    //to get start principle in finance2(compound)(end)
    public static double present_val_cal_end(double futureV,double interestR,double numberP,double annuityP) {
        double apFv = (annuityP / interestR) * (Math.pow(1+interestR,numberP)-1);
        double totSp = futureV - apFv;
        double sp = totSp/(Math.pow(1+interestR,numberP));
        return Math.round((sp)*100.0) /100.0;
    }

    //to get number of periods for finance2(compound) cal(end)
    public static double number_of_payments_end(double futureV,double startP,double interestR,double annuityP) {
        double np = Math.log((interestR*futureV+annuityP)/(interestR*startP+annuityP))/Math.log(1+interestR);
        return Math.round((np)*100.0) / 100.0;
    }

    //to get monthly payment for finance2(compound savings) (end)
    public static double mp_end(double futureV,double startP,double interestR,double numberP) {
        double pow = Math.pow(1+interestR,numberP);
        double pmt = interestR * (futureV-(startP*pow)) / (pow - 1);
        return Math.round((pmt)*100.0)/100.0;
    }



//********************* mortgage calcutions ******************//
    //to get starting mortgage value
    public static double starting_mortgage_calculation(double propertyP, double downP) {
        //house price - down payment
        double startingMortgageValue = propertyP - downP;
        return Math.round((startingMortgageValue)*100.0) / 100.0;
    }

    //for mortgage monthly payment calculation
    public static double monthly_pay_calculation(double propertyP, double downP, double interestR, double lornT) {
        double startingMortgage = propertyP - downP;
        double irPow = Math.pow(1 + interestR / 12, lornT * 12);
        double monthlyPay = (startingMortgage * interestR / 12 * irPow) / (irPow - 1);
        return Math.round((monthlyPay) * 100.0) / 100.0;
    }

    //to get mortgage total interest rate
    public static double tot_interest(double propertyP, double downP, double interestR,double lornT) {
        double strMortgage = propertyP - downP;
        double irPow = Math.pow(1 + interestR / 12, lornT * 12);
        double monthlyPay = (strMortgage * interestR / 12 * irPow) / (irPow - 1);
        double irValue = monthlyPay*lornT;
        return Math.round((irValue)* 100.0) / 100.0;
    }

    //to get total mortgage payments
    public static double tot_mortgage_payments(double propertyP,double downP,double interestR,double lornT) {
        double strMortgage = propertyP - downP;
        double irPow = Math.pow(1 + interestR / 12, lornT * 12);
        double monthlyPay = (strMortgage * interestR / 12 * irPow) / (irPow - 1);
        double irValue = monthlyPay*lornT;
        double tot_mortgage_pay = irValue+propertyP;
        return Math.round((tot_mortgage_pay)*100.0) / 100.0;
    }



    //**************** Loan calculation *************//
    //to get loan monthly payment
    public static double loanMP(double autoP, double lornT, double interestR, double downP) {
        //to get starting loan balance
        double strLB = autoP - downP;
        double pow = Math.pow(1 + interestR / 12, lornT * 12);
        double laonmp = (strLB * interestR / 12 * pow) / (pow - 1);
        return Math.round((laonmp) * 100.0) / 100.0;
    }

    //to get total payble amount in loan calculation
    public static double totPayble(double autoP, double lornT, double interestR, double downP) {
        double strLB = autoP - downP;
        double pow = Math.pow(1 + interestR / 12, lornT * 12);
        double laonmp = (strLB * interestR / 12 * pow) / (pow - 1);
        double totPayble = laonmp * (lornT * 12);
        return Math.round((totPayble) * 100.0) / 100.0;
    }

    //calculate total interest rate
    public static double totIrCal(double autoP,double lornT,double interestR,double downP) {
        double strLB = autoP - downP;
        double pow = Math.pow(1 + interestR / 12, lornT * 12);
        double laonmp = (strLB * interestR / 12 * pow) / (pow - 1);
        double totPayble = laonmp * (lornT * 12);
        double totIrr = totPayble-autoP;
        return Math.round((totIrr) * 100.0) / 100.0;
    }

    //to calculate the auto price
    public static double autoPrice(double monthlyP,double lornT,double interestR) {
        double ap = (monthlyP*(1-1/(Math.pow(1+interestR,lornT))))/interestR;
        return Math.round((ap)*100.0) / 100.0;
    }

    //to get ful Loan amount
    public static double fullLoan(double monthlyP,double lornT,double interestR,double downP) {
        double fLoan = autoPrice(monthlyP,lornT,interestR) + downP;
        return Math.round((fLoan)*100.0)/100.0;
    }


}




