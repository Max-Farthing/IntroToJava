import java.util.Scanner; 
    
/**
* Program allows users to request a loan amount between
* $1000-$10000 and will either deny or accept and 
* return the monthly payment, date the loan will be
* received, loan amount, and interest rate
* 
* @author Max Farthing
*/
public class WolfpackFinance {
    
    /** constant for leap years. */
    public static final int LEAP_YEAR_FREQUENCY = 4;

    /** constant for current year. */
    public static final int YEAR = 2023;

    /** constant for days in week as well as month july. */
    public static final int WEEK = 7;

    /** constant for maximum days in month. */
    public static final int MAXMONTH = 31;
    
    /** constant for number 12. */
    public static final int DOZEN = 12;
   
    /** constant for number 14. */
    public static final int TWOWEEK = 14;
    
    /** constant for number 100. */
    public static final int HUNDRED = 100;
    
    /** constant for leap years */
    public static final int FOURHUNDRED = 400;
    
    /** constant for standard shipping days. */
    public static final int STANDARDSHIP = 21;
    
    /** constant for express shipping days. */
    public static final int EXPRESSSHIP = 3;

    /** constant for length of loan in months. */
    public static final int LOANMONTHS = 48;
   
    /** constant for length of June in days as well as express fee. */
    public static final int JUNEDAYS = 30;

    /** constant for min credit score. */
    public static final int MINCREDITSCORE = 300;

    /** constant for max credit score. */
    public static final int MAXCREDITSCORE = 850;

    /** constant for min loan amount. */
    public static final int MINLOAN = 1000;

    /** constant for max loan amount. */
    public static final int MAXLOAN = 10000;

    /** constant for Month of June */
    public static final int JUNEMONTH = 6;

    /** constant for Month of August */
    public static final int AUGUSTMONTH = 8;

    /** constant for highest credit tier */
    public static final int CREDITTIER1 = 720;
    
    /** constant for 2nd highest credit tier */
    public static final int CREDITTIER2 = 500;

    /** constant for 3rd highest credit tier */
    public static final int CREDITTIER3 = 350;

    /** constant for lowest interest tier */
    public static final double INTERESTTIER1 = 7.5;

    /** constant for 2nd lowest interest tier */
    public static final double INTERESTTIER2 = 8.0;

    /** constant for 3rd lowest interest tier */
    public static final double INTERESTTIER3 = 8.5;

    /** constant for highest interest tier */
    public static final double INTERESTTIER4 = 9.0;
   
    /** constant for min annual interest */
    public static final double MINANNUALINT = .5;

    /** constant for last possible order day in august */
    public static final int LASTORDERDAY = 15;

    /** constant for Friday */
    public static final int FRIDAY = 5;

    /** constant for Month of September */
    public static final int SEPTEMBER = 9;
    
    /**
     * This method is executed when program is run. 
     * Takes user input and returns a series of 
     * information about user's loan
     * 
     * @param args command line arguments
     * @throws IllegalArgumentException when invalid date, credit score, income, or loan amount
     * are entered
     */
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        
        System.out.println("        Welcome to Wolfpack Finance!");
        System.out.println("Applications for loans from $1000 to $10000 will be accepted from");
        System.out.println("June 1 to August 15, 2023. All loans will be paid back over a");
        System.out.println("4 year period. When prompted, please enter today's date, your credit");
        System.out.println("score, your 2022 income, and the loan amount. Loans are normally paid");
        System.out.println("out 21 days after the application date, but for a fee of $30, which");
        System.out.println("will be added to the loan amount, you may request Express Processing");
        System.out.println("in which case the loan will be paid out 3 days after the application");
        System.out.println("date. If your loan is approved, the loan amount, interest rate,");
        System.out.println("disbursement date, and monthly payment amount will be output.");

        System.out.print("Month Day (e.g., 6 9): ");
        int month = in.nextInt();
        int day = in.nextInt();
        if(isValidDate(month, day) != true){
            throw new IllegalArgumentException("Invalid Date");
        } 
        
        System.out.print("Credit Score (300-850): ");
        int creditScore = in.nextInt();
        if (creditScore < MINCREDITSCORE || creditScore > MAXCREDITSCORE){
            throw new IllegalArgumentException("Invalid credit score");
        } 

        System.out.print("2022 Income: ");
        int income = in.nextInt();
        if (income < 0){
            throw new IllegalArgumentException("Invalid income");
        }

        System.out.print("Loan Amount (1000-10000): ");
        double loanAmount = in.nextDouble();
        if (loanAmount < MINLOAN || loanAmount > MAXLOAN){
            throw new IllegalArgumentException("Invalid Loan Amount");
        }

        System.out.print("Express Processing(y,n) ");
        String express = in.next();
        if (express.contains("y") || express.contains("Y")){
            loanAmount += JUNEDAYS;
        }
        
        boolean expressProcessing = false;

        if(express.contains("y") || express.contains("Y")){
            expressProcessing = true;
        } else {
            expressProcessing = false;
        }

        double annualInterestRate = getInterestRate(loanAmount, creditScore, income);

        System.out.printf("Loan Amount: %.2f \n", loanAmount);
        System.out.println("Interest Rate: " + 
            getInterestRate(loanAmount, creditScore, income) + "%");
        System.out.printf("Monthly Payment: $%.2f \n" ,
            getMonthlyPayment(loanAmount, annualInterestRate, month));
        System.out.println("Disbursement Date: " 
            + getDisbursementDate(month, day, expressProcessing));
        in.close();
    }
    
    /**
     * Method checks if the date input by the user is 
     * valid in terms of what the program allows. Between 
     * june 1st and august 15.
     * @param month the month in which the user input
     * @param day the day in which the user input
     * @return boolean based upon whether the date input by the user is valid
     */
    public static boolean isValidDate(int month, int day) {
        if (month < JUNEMONTH || month > AUGUSTMONTH || day < 1 || day > MAXMONTH){
            return false;
        } else if (month == JUNEMONTH && day > JUNEDAYS){
            return false;
        } else if (month == WEEK && day > MAXMONTH){
            return false;
        } else if (month == AUGUSTMONTH && day > LASTORDERDAY){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method checks the users credit score and income compared 
     * to their requested loan amount. The user will
     * receive an interest rate based upon these factors.
     * @param loanAmount the loan amount requested by user
     * @param creditScore the credit score of user
     * @param income the income of user
     * @return the interest rate in double form
     * @throws IllegalArgumentException if user has invalid loan, credit score, or income
     */
    public static double getInterestRate(double loanAmount, int creditScore, int income) {
        double interestRate = 0;
        if(creditScore >= CREDITTIER1 && income > 0 && loanAmount > 0){
            interestRate = INTERESTTIER1;
            return interestRate;
        } else if(creditScore < CREDITTIER3 && creditScore >= MINCREDITSCORE
            || income < loanAmount * 2 && loanAmount > 0 && income > 0){
            interestRate = -1.0; 
            return interestRate;
        } else if (loanAmount <= 0){
            throw new IllegalArgumentException("Invalid loan amount");
        } else if(creditScore < MINCREDITSCORE || creditScore > MAXCREDITSCORE){
            throw new IllegalArgumentException("Invalid credit score");
        } else if(income < 0){
            throw new IllegalArgumentException("Invalid income");
        } else if(creditScore < CREDITTIER1 && income >= FRIDAY * loanAmount){
            interestRate = INTERESTTIER2;
            return interestRate;
        } else if(creditScore >= CREDITTIER2 && income >= loanAmount * 3){
            interestRate = INTERESTTIER3;
            return interestRate;
        } else if(creditScore >= CREDITTIER3 && income >= loanAmount * 2){
            interestRate = INTERESTTIER4;
            return interestRate;
        }  else {
            return 0.0;
        }
    }

    /**
     * This method returns a user their monthly loan 
     * payment based upon their loan amount, interest rate 
     * and the length of the loan
     * @param loanAmount the loan amount user requested
     * @param annualInterestRate the yearly interest rate of user
     * @param numberOfMonths number of months the loan will be paid out
     * @return the monthly payment users will be paying monthly in double format
     * @throws IllegalArgumentException if user inputs an invalid loan, 
     * interest rate, number of months
     */
    public static double getMonthlyPayment(double loanAmount, double annualInterestRate,
        int numberOfMonths) {
        double monthlyPayment = 0;
        double monthlyInterestRate = (annualInterestRate / DOZEN) / HUNDRED;
        if (loanAmount <= 0){
            throw new IllegalArgumentException("Invalid loan amount");
        } else if(annualInterestRate < MINANNUALINT){
            throw new IllegalArgumentException("Invalid interest rate");
        } else if(numberOfMonths <= 0){
            throw new IllegalArgumentException("Invalid number of months");
        } else if(loanAmount >= MINLOAN && loanAmount <= MAXLOAN){
            numberOfMonths = LOANMONTHS;
            monthlyPayment = loanAmount * (monthlyInterestRate * 
                (( Math.pow(1 + monthlyInterestRate, numberOfMonths))
                / (Math.pow(1 + monthlyInterestRate, numberOfMonths) - 1)));
            return monthlyPayment;
        } 
        return 0.0;
    }

    /**
     * This method returns a user the date they will receive 
     * the loan based upon whether they chose express shipping or standard 
     * @param month the user input month of loan
     * @param day the user input day of loan
     * @param expressProcessing whether or not user chose express processing
     * @return string giving the user when they will receive their loan money
     * @throws IllegalArgumentException if user inputs an invalid date
     */
    public static String getDisbursementDate(int month, int day, 
        boolean expressProcessing) {
        if(isValidDate(month, day) != true){
            throw new IllegalArgumentException("Invalid date");
        }
        
        if(expressProcessing == true){
            day += EXPRESSSHIP;
        } else if (expressProcessing == false){
            day += STANDARDSHIP;
        }

        int dayofWeek = 0;
        int w = 0; 
        int x = 0; 
        int z = 0;
        w = YEAR - (TWOWEEK - month) / DOZEN;
        x = w + w / LEAP_YEAR_FREQUENCY - w / HUNDRED + w / FOURHUNDRED;
        z = month + DOZEN * ((TWOWEEK - month) / DOZEN) - 2;
        dayofWeek = (day + x + (MAXMONTH * z) / DOZEN) % WEEK;
        
        
        if(month == JUNEMONTH && day > JUNEDAYS){
            month = WEEK; 
            day -= JUNEDAYS;
        } else if (month == WEEK && day > MAXMONTH){
            month = AUGUSTMONTH; 
            day -= MAXMONTH;
        } else if (month == AUGUSTMONTH && day > MAXMONTH){
            month = SEPTEMBER; 
            day -= MAXMONTH;
        } 
        
        if (dayofWeek == 0){
            return "Sun, " + month + " " + day +  " " + YEAR;
        } else if (dayofWeek == 1){
            return "Mon, " + month + " " + day + " " + YEAR;
        } else if (dayofWeek == 2){
            return "Tue, " + month + " " + day + " " + YEAR;
        } else if (dayofWeek == 3){
            return "Wed, " + month + " " + day + " " + YEAR;
        } else if (dayofWeek == LEAP_YEAR_FREQUENCY){
            return "Thu, " + month + " " + day + " " + YEAR;
        } else if (dayofWeek == FRIDAY){
            return "Fri, " + month + " " + day + " " + YEAR;
        } else if (dayofWeek == JUNEMONTH){
            return "Sat, " + month + " " + day + " " + YEAR;
        } else {
            return "Error";
        }
    }
}    