import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Program to test WolfpackFinance methods
 * @author Suzanne Balik
 * @author Michelle Glatz
 * @author Max Farthing
 */
public class WolfpackFinanceTest {
 
    // in test program
    
    /** Delta value used for interest rate tests */
    public static final double DELTA = .001;
    
    /**
     * Test IsValidDate in JUNE with non boundary (6,23)
     */
    @Test
    public void testIsValidDateValidJunNonBoundary() {

        assertTrue(WolfpackFinance.isValidDate(6, 23), "WolfpackFinance.isValidDate(6, 23)");

    }
    
    /**
     * Test IsValidDate in JUNE with invalid boundary (6,31)
     */
    @Test
    public void testIsValidDateInvalidJunBoundary() {

        assertFalse(WolfpackFinance.isValidDate(6, 31), "WolfpackFinance.isValidDate(6, 31)");

    }
    
    /**
     * Test IsValidDate in JULY with invalid boundary (7,32)
     */
    @Test
    public void testIsValidDateInvalidJulBoundary() {

        assertFalse(WolfpackFinance.isValidDate(7, 32), "WolfpackFinance.isValidDate(7, 32)");

    }    
    
    /**
     * Test IsValidDate with invalid month (9,30)
     */
    @Test
    public void testIsValidDateInvalidMonth() {

        assertFalse(WolfpackFinance.isValidDate(9, 30), "WolfpackFinance.isValidDate(9, 30)");

    } 
    
    /**
     * Test IsValidDate JULY with negative day (7,-1)
     */
    @Test
    public void testIsValidDateJulDayNegative() {

        assertFalse(WolfpackFinance.isValidDate(7, -1), "WolfpackFinance.isValidDate(7, -1)");

    }

    /**
     * Test IsValidDate with month 0 (0,1)
     */
    @Test
    public void testIsValidDateMonthZero() {

        assertFalse(WolfpackFinance.isValidDate(0, 1), "WolfpackFinance.isValidDate(0, 1)");

    }  

    /**
     * Test IsValidDate with negative month (-1,1)
     */
    @Test
    public void testIsValidDateMonthNegative() {

        assertFalse(WolfpackFinance.isValidDate(-1, 1), "WolfpackFinance.isValidDate(-1, 1)");

    }        
 
    /**
     * Test IsValidDate in JUNE with day (6,1)
     */
    @Test
    public void testIsValidDateJun01() {
        
        assertTrue(WolfpackFinance.isValidDate(6, 1), "WolfpackFinance.isValidDate(6, 1)"); 

    }   

    /**
     * Test IsValidDate in JUNE (6,30)
     */
    @Test
    public void testIsValidDateJune30() {
        
        assertTrue(WolfpackFinance.isValidDate(6, 30), "WolfpackFinance.isValidDate(6, 30)"); 

    } 
    
    /**
     * Test IsValidDate in JULY with valid boundary (7,1)
     */
    @Test
    public void testIsValidDateJul01() {
            
        assertTrue(WolfpackFinance.isValidDate(7, 1), "WolfpackFinance.isValidDate(7, 1)");

    }
    
    /**
     * Test IsValidDate in JULY with valid nonboundary (7,12)
     */
    @Test
    public void testIsValidDateValidJulNonBoundary() {
        
        assertTrue(WolfpackFinance.isValidDate(7, 12), "WolfpackFinance.isValidDate(7, 12)");

    }    
    
    /**
     * Test IsValidDate in JULY with valid boundary (7,31)
     */
    @Test
    public void testIsValidDateJuly31() {
        
        assertTrue(WolfpackFinance.isValidDate(7, 31), "WolfpackFinance.isValidDate(7, 31)"); 

    } 
    
 
    /**
     * Test IsValidDate in AUG with valid boundary (8,1)
     */
    @Test
    public void testIsValidDateAug01() {
        
        assertTrue(WolfpackFinance.isValidDate(8, 1), "WolfpackFinance.isValidDate(8, 1)");

    }   
        
    
    /**
     * Test IsValidDate in Aug with valid nonboundary (8,7)
     */
    @Test
    public void testIsValidDateValidAugNonBoundary() {
        
        assertTrue(WolfpackFinance.isValidDate(8, 7), "WolfpackFinance.isValidDate(8, 7)");
    }      

    /**
     * Test IsValidDate in Aug with valid boundary (8,15)
     */
    @Test
    public void testIsValidDateAug15() {
            
        assertTrue(WolfpackFinance.isValidDate(8, 15), "WolfpackFinance.isValidDate(8, 15)");
    }
    
     /**
     * Test IsValidDate in Aug with invalid boundary (8,16)
     */
    @Test
    public void testIsValidDateAug16() {
        
        assertFalse(WolfpackFinance.isValidDate(8, 16), "WolfpackFinance.isValidDate(8, 16)"); 

    }      
    
     /**
     * Test IsValidDate in July with invalid day (7,0)
     */
    @Test
    public void testIsValidDateJulDayZero() {
        
        assertFalse(WolfpackFinance.isValidDate(7, 0), "WolfpackFinance.isValidDate(7, 0)");

    } 


     /**
     * Test GetInterestRate with int rate 7.5 (2000,720,2000)
     */
    @Test
    public void testGetInterestRate75() {
        
        assertEquals(7.5, WolfpackFinance.getInterestRate(2000, 720, 2000), DELTA, 
                     "WolfpackFinance.getInterestRate(2000, 720, 2000)" );
        
    }

    /**
     * Test GetInterestRate with invalid credit (1000,300,2000)
     */
    @Test
    public void testGetInterestRateNotApprovedCredit() {
        
        assertEquals(-1.0, WolfpackFinance.getInterestRate(1000, 300, 2000), DELTA,
                     "WolfpackFinance.getInterestRate(1000, 300, 2000)");
        
    }
    
    /**
     * Test GetInterestRate with invalid income (1000,350,1900)
     */
    @Test
    public void testGetInterestRateNotApprovedIncome() {
        
        assertEquals(-1.0, WolfpackFinance.getInterestRate(1000, 350, 1900), DELTA, 
                     "WolfpackFinance.getInterestRate(1000, 350, 1900)");
        
    } 

    /**
     * Test GetInterestRate with int rate 9.0 and low income(1000,600,2000)
     */
    @Test
    public void testGetInterestRate90LowIncome() {
        
        assertEquals(9.0, WolfpackFinance.getInterestRate(1000, 600, 2000), DELTA,
                     "WolfpackFinance.getInterestRate(1000, 600, 2000)");
        
    } 

    /**
     * Test GetInterestRate with int rate 9.0 and low credit (1000,400,4000)
     */
    @Test
    public void testGetInterestRate90LowCredit() {
        
        assertEquals(9.0, WolfpackFinance.getInterestRate(1000, 400, 4000), DELTA,
                     "WolfpackFinance.getInterestRate(1000, 400, 4000)");
        
    } 

    /**
     * Test GetInterestRate with loan more than 10000 (11000,800,2000)
     */
    @Test
    public void testGetInterestRateLoanMore10000() {
        
        assertEquals(7.5, WolfpackFinance.getInterestRate(11000, 800, 2000), DELTA, 
                     "WolfpackFinance.getInterestRate(11000, 800, 2000)" );
        
    }     
    
    /**
     * Test GetInterestRate with int rate 8.0 with credit 300 (11000,300,2000)
     */
    @Test
    public void testGetInterestRate80Credit300() {
        
        assertEquals(-1, WolfpackFinance.getInterestRate(11000, 300, 2000), DELTA, 
                     "WolfpackFinance.getInterestRate(11000, 300, 2000)" ); 
        
        
    }    

    /**
     * Test GetInterestRate with int rate 8.5 (6500,500,2000)
     */
    @Test
    public void testGetInterestRate85Credit500() {
        
        assertEquals(8.5, WolfpackFinance.getInterestRate(6500, 500, 20000), DELTA, 
                     "WolfpackFinance.getInterestRate(6500, 500, 2000)" );                   
        
    } 

    /**
     * Test GetInterestRate with loan less than 1000(950,350,1900)
     */
    @Test
    public void testGetInterestRateLoanLess1000() {
                
        assertEquals(9.0, WolfpackFinance.getInterestRate(950, 350, 1900), DELTA, 
                     "WolfpackFinance.getInterestRate(950, 350, 1900)");
        
    }  


    /**
     * Test GetMonthlyPayment with nonstandardinterest (26890,8.3,48)
     */
    @Test
    public void testGetMonthlyPaymentNonStandardInterest() {
        
        assertEquals(0, WolfpackFinance.getMonthlyPayment(26890, 8.3, 48), DELTA,
                     "WolfpackFinance.getMonthlyPayment(26890, 8.3, 48)");
        
    }
    
    /**
     * Test GetMonthlyPayment with high loan (10058,8.0,48)
     */
    @Test
    public void testGetMonthlyPaymentHighLoanAmount() {
        
        assertEquals(0, WolfpackFinance.getMonthlyPayment(10058, 8.0, 48), DELTA,
                     "WolfpackFinance.getMonthlyPayment(10058, 8.0, 48)");
        
    }

    /**
     * Test GetMonthlyPayment with low interest rate (3400,.5,48)
     */
    @Test
    public void testGetMonthlyPaymentLowInterestRate() {
        
        assertEquals(71.559, WolfpackFinance.getMonthlyPayment(3400, 0.5, 48), DELTA,
                     "WolfpackFinance.getMonthlyPayment(3400, 0.5, 48)");
        
    }  

    /**
     * Test GetMonthlyPayment with low loan (100,10.0,5)
     */
    @Test
    public void testGetMonthlyPaymentLowLoanAmount() {
        
        assertEquals(0, WolfpackFinance.getMonthlyPayment(100, 10.0, 5), DELTA,
                     "WolfpackFinance.getMonthlyPayment(100, 10.0, 5)");
        
    } 
    
    /**
     * Test GetMonthlyPayment with 60 months (7550,7.0,60)
     */
    @Test
    public void testGetMonthlyPayment60Months() {
        
        assertEquals(180.794, WolfpackFinance.getMonthlyPayment(7550, 7.0, 60), DELTA,
                     "WolfpackFinance.getMonthlyPayment(7550, 7.0, 60)");
        
    }    

    /**
     * Test GetMonthlyPayment with 7.5 interest (2502,7.5,48)
     */
    @Test
    public void testGetMonthlyPayment75InterestRate() {
        
        assertEquals(60.496, WolfpackFinance.getMonthlyPayment(2502, 7.5, 48), DELTA,
                     "WolfpackFinance.getMonthlyPayment(2502, 7.5, 48)");
        
    }       

    /**
     * Test GetMonthlyPayment with 8.5 interest (2502,8.5,48)
     */
    @Test
    public void testGetMonthlyPayment85InterestRate() {
        
        assertEquals(61.67, WolfpackFinance.getMonthlyPayment(2502, 8.5, 48), DELTA,
                     "WolfpackFinance.getMonthlyPayment(2502, 8.5, 48)");
        
    } 

    /**
     * Test GetMonthlyPayment with 9.0 interest (3021,9,48)
     */
    @Test
    public void testGetMonthlyPayment90InterestRate() {
        
        assertEquals(75.177, WolfpackFinance.getMonthlyPayment(3021, 9, 48), DELTA,
                     "WolfpackFinance.getMonthlyPayment(2502, 9, 48)");
        
    }   

    /**
     * Test GetMonthlyPayment with 30 months (2502,7.5,30)
     */
    @Test
    public void testGetMonthlyPayment30Months() {
        
        assertEquals(60.495, WolfpackFinance.getMonthlyPayment(2502, 7.5, 30), DELTA,
                     "WolfpackFinance.getMonthlyPayment(2502, 7.5, 30)");
        
    }
       

    /**
     * Test GetDisbursementDate with June 1 Express (6,1,true)
     */
    @Test
    public void testGetDisbursementDateJune1Express() {
        
        assertEquals("Sun, 6 4 2023", WolfpackFinance.getDisbursementDate(6, 1, true), 
                     "WolfpackFinance.getDisbursementDate(6, 1, true)");
        
    }

    /**
     * Test GetDisbursementDate with aug15 no Express (8,15,false)
     */
    @Test
    public void testGetDisbursementDateAugust15NotExpress() {
        
        assertEquals("Tue, 9 5 2023", WolfpackFinance.getDisbursementDate(8, 15, false), 
                     "WolfpackFinance.getDisbursementDate(8, 15, false)");
        
    }
  

     /**
     * Test GetDisbursementDate with june 9 no Express (6,9,false)
     */
    @Test
    public void testGetDisbursementDateJune9NotExpress() {
        
        assertEquals("Fri, 6 30 2023", WolfpackFinance.getDisbursementDate(6, 9, false), 
                     "WolfpackFinance.getDisbursementDate(6, 9, false)");
        
    }

     /**
     * Test GetDisbursementDate with aug 2 no Express (8,2,false)
     */
    @Test
    public void testGetDisbursementDateAugust2NotExpress() {
        
        assertEquals("Wed, 8 23 2023", WolfpackFinance.getDisbursementDate(8, 2, false),
                     "WolfpackFinance.getDisbursementDate(8, 2, false)");
        
    } 

     /**
     * Test GetDisbursementDate with aug 10 no Express (8,10,false)
     */
    @Test
    public void testGetDisbursementDateAugust10NotExpress() {
        
        assertEquals("Thu, 8 31 2023", WolfpackFinance.getDisbursementDate(8, 10, false),
                     "WolfpackFinance.getDisbursementDate(8, 10, false)");
        
    } 
    
    
    /**
     * Test GetDisbursementDate with june 28 Express (6,28,true)
     */
    @Test
    public void testGetDisbursementDateJune28Express() {
        
        assertEquals("Sat, 7 1 2023", WolfpackFinance.getDisbursementDate(6, 28, true),
                     "WolfpackFinance.getDisbursementDate(6, 28, true)");
        
    }    

    /**
     * Test GetDisbursementDate with june 28 no Express (6,30,true)
     */
    @Test
    public void testGetDisbursementDateJuly30NotExpress() {
        
        assertEquals("Sun, 8 20 2023", WolfpackFinance.getDisbursementDate(7, 30, false),
                     "WolfpackFinance.getDisbursementDate(7, 30, false)");
        
    }
    

    
    
    /**
     * Test the WolfpackFinance methods with invalid values
     */ 
    @Test
    public void testInvalidMethods() {
        // Invalid test cases are provided for you below - You do NOT
        // need to add additional invalid tests. Just make sure these
        // pass!

        Exception exception = assertThrows(
                IllegalArgumentException.class, () -> 
                WolfpackFinance.getInterestRate(0, 500, 5000),
                "Testing invalid loan amount");
        assertEquals("Invalid loan amount", exception.getMessage(),
                "Testing invalid loan amount - exception message");

        exception = assertThrows(
                IllegalArgumentException.class, () -> 
                WolfpackFinance.getInterestRate(1000, 200, 5000),
                "Testing invalid credit score");
        assertEquals("Invalid credit score", exception.getMessage(),
                "Testing invalid credit score - exception message");

        exception = assertThrows(
                IllegalArgumentException.class, () -> 
                WolfpackFinance.getInterestRate(1000, 500, -20),
                "Testing invalid income");
        assertEquals("Invalid income", exception.getMessage(),
                "Testing invalid income - exception message");

        exception = assertThrows(
                IllegalArgumentException.class, () -> 
                WolfpackFinance.getMonthlyPayment(-5, 5.5, 20),
                "Testing invalid loan amount");
        assertEquals("Invalid loan amount", exception.getMessage(),
                "Testing invalid cloan amount - exception message");

        exception = assertThrows(
                IllegalArgumentException.class, () -> 
                WolfpackFinance.getMonthlyPayment(3000, 0.49, 20),
                "Testing invalid interest rate");
        assertEquals("Invalid interest rate", exception.getMessage(),
                "Testing invalid interest rate - exception message");

        exception = assertThrows(
                IllegalArgumentException.class, () -> 
                WolfpackFinance.getMonthlyPayment(4500, 5.5, 0),
                "Testing invalid scoret");
        assertEquals("Invalid number of months", exception.getMessage(),
                "Testing invalid number of months - exception message");

        exception = assertThrows(
                IllegalArgumentException.class, () -> 
                WolfpackFinance.getDisbursementDate(13, 4, true),
                "Testing invalid date");
        assertEquals("Invalid date", exception.getMessage(),
                "Testing invalid date - exception message");

        exception = assertThrows(
                IllegalArgumentException.class, () -> 
                WolfpackFinance.getDisbursementDate(4, 31, false),
                "Testing invalid date");
        assertEquals("Invalid date", exception.getMessage(),
                "Testing invalid date - exception message");
    }
}