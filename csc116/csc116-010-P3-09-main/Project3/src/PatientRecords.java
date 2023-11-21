// import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

// import javax.sound.sampled.Line;

// import java.nio.file.SimpleFileVisitor;
import java.nio.file.*;
import java.io.*;



/**
 * This program takes input from files as patient information and
 * allows users to list patient info, add patient info, update patient
 * info or Quit the program at any time.
 * 
 * @author Max Farthing
 */
public class PatientRecords {
    
    /** constant for first id number */
    public static final int FIRSTID = 1001;
    
        /**
         * Main method takes user input for what files to use for input
         * and output
         * 
         * @param args command line arguments
         */
    public static void main(String args[]) {
        Scanner scnr = new Scanner(System.in);
        

        if (args.length != 3) {
            System.out.println("Usage: java -cp bin PatientRecords infile outfile maxPatients");
            System.exit(1);
        }

        String inputfilename = args[0];
        String outputfilename = args[1];
        int maxPatients = 0;

        Scanner in = null;
        try {
            FileInputStream inputStream = new FileInputStream(inputfilename);
            in = new Scanner(inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to access input file: " + inputfilename);
            System.exit(1);
        }
 
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(outputfilename);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot create output file");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(outputStream);

        try {
            maxPatients = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Usage: java -cp bin PatientRecords infile outfile maxPatients");
            System.exit(1);
        }
        if (maxPatients <= 0) { // || !Character.isDigit(maxPatients)
            System.out.println("Usage: java -cp bin PatientRecords infile outfile maxPatients");
            System.exit(1);
        }

        int[] ids = new int[maxPatients];
        String[] names = new String[maxPatients];
        String[] birthdates = new String[maxPatients];
        int[] heights = new int[maxPatients];
        int[] weights = new int[maxPatients];
        double[] temperatures = new double[maxPatients];
        String[] bloodPressures = new String[maxPatients];

        if (inputPatients(in, ids, names, birthdates, heights, weights, temperatures,
            bloodPressures) == false) {
            System.out.println("Invalid input file");
            System.exit(1);
        }

        Path path = Path.of(outputfilename);
        if (Files.exists(path)) { // check lecture 16
            System.out.print(outputfilename + " exists - OK to overwrite(y,n)?: ");
            String overwrite = scnr.next();
            char result = overwrite.charAt(0);
            if (Character.toUpperCase(result) != 'Y') {
                System.exit(1);
            } 
        }

                // Path path = Path.of(outputfilename);
                // if(Files.exists(path)){
                // System.out.print(outputfilename + " exists - OK to overwrite(y,n)?: ");
                // String overwrite = scnr.next();
                // char result = overwrite.charAt(0);
                // Character.toUpperCase(result);
                // if(result != "Y"){
                // System.out.println("OK - exiting program");
                // System.exit(1);
                // }
                // }

        String option = "";
        while (!option.equalsIgnoreCase("Q")) {
            System.out.println();
            displayMenu();

            option = scnr.next();
            System.out.println();

            if (option.equalsIgnoreCase("L")) {
                getPatientList(ids, names,
                    birthdates, heights, weights, temperatures, bloodPressures);
            } else if (option.equalsIgnoreCase("U")) {
                updatePatient(scnr, ids, names, birthdates, heights, weights, temperatures,
                                                bloodPressures);
            } else if (option.equalsIgnoreCase("A")) {
                addPatient(scnr, ids, names, birthdates, heights, weights, temperatures,
                    bloodPressures);
            } else if (option.equalsIgnoreCase("Q")) {
                outputPatients(out, ids, names, birthdates, heights, weights, temperatures,
                    bloodPressures);
                System.exit(1);
            } else {
                System.out.println("Invalid option");
            }
        }

        out.close();
        scnr.close();
        in.close();
    }

        /**
         * This method goes thru the input file and determines whether or not
         * the patient information is valid
         * 
         * @param in             Scanner to take input
         * @param ids            Patient id numbers
         * @param names          Patient Names
         * @param birthdates     The birthdates of patients
         * @param heights        The height of patients
         * @param weights        The patients' weight
         * @param temperatures   Patients' temperatures
         * @param bloodPressures Patients' blood pressures
         * @return returns a boolean that checks if patient inputs are valid
         * @throws IllegalArgumentException Null file, array or invalid array length
         */
    public static boolean inputPatients(Scanner in, int[] ids, String[] names, String[] birthdates,
        int[] heights, int[] weights, double[] temperatures,
        String[] bloodPressures) {
        if (in == null) {
            throw new IllegalArgumentException("Null file");
        }
        if (ids == null || names == null || birthdates == null || heights == null || weights == null
            || temperatures == null || bloodPressures == null) {
            throw new IllegalArgumentException("Null array");
        }
        if (ids.length < 1 || ids.length != names.length || ids.length != birthdates.length
            || ids.length != heights.length
            || ids.length != weights.length || ids.length != temperatures.length
            || ids.length != bloodPressures.length) {
            throw new IllegalArgumentException("Invalid array length");
        }

        int arrlength = ids.length;
        int linecount = 0;
        int maxpatients = 0;
        int idnum = FIRSTID;
        final int TOTALENTRIES = 7;
        while (in.hasNextLine()) {
            if (linecount != arrlength) {
                linecount++;
            } else {
                return false;
            }
            int index = 0;
            Scanner line = new Scanner(in.nextLine());
            line.useDelimiter(",");

            if (!line.hasNextInt()) {
                return false;
            } else {
                ids[maxpatients] = line.nextInt();
                index++;
            }
            if (ids[maxpatients] != idnum + maxpatients) {
                return false;
            }
            if (!line.hasNext()) {
                return false;
            } else {
                names[maxpatients] = line.next();
                index++;
            }
            if (!line.hasNext()) {
                return false;
            } else {
                birthdates[maxpatients] = line.next();
                index++;
            }
            if (!line.hasNextInt()) {
                return false;
            } else {
                heights[maxpatients] = line.nextInt();
                index++;
            }
            if (!line.hasNextInt()) {
                return false;
            } else {
                weights[maxpatients] = line.nextInt();
                index++;
            }
            if (!line.hasNextDouble()) {
                return false;
            } else {
                temperatures[maxpatients] = line.nextDouble();
                index++;
            }
            if (!line.hasNext()) {
                return false;
            } else {
                bloodPressures[maxpatients] = line.next();
                index++;
            }
            if (line.hasNext()) {
                index++;
            }
            if (index > TOTALENTRIES) {
                return false;
            }
            maxpatients++;

        }
        return true;
    }

        /**
         * This method returns a string for every patient with their correct information
         * 
         * @param ids            patient id's
         * @param names          patient name
         * @param birthdates     patient birthdate
         * @param heights        patient height
         * @param weights        patient weight
         * @param temperatures   patient temperature
         * @param bloodPressures patient blood pressure
         * @return a string containing with all patients information
         * @throws IllegalArgumentException for null array or invalid array length
         */
    public static String getPatientList(int[] ids, String[] names, String[] birthdates,
        int[] heights, int[] weights, double[] temperatures,
        String[] bloodPressures) {
        if (ids == null || names == null || birthdates == null || heights == null || weights == null
            || temperatures == null || bloodPressures == null) {
            throw new IllegalArgumentException("Null array");
        }
        if (ids.length != names.length || ids.length != birthdates.length
            || ids.length != heights.length || ids.length != weights.length
            || ids.length != temperatures.length || ids.length != bloodPressures.length) {
            throw new IllegalArgumentException("Invalid array length");
        }
        System.out.println(" ID         NAME          BIRTHDATE HT  WT    T      BP");
        String patients = "";
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] != 0 && ids[i] != ids.length - 1) {
                patients += toString(ids[i], names[i], birthdates[i], heights[i], weights[i],
                    temperatures[i], bloodPressures[i]) + "\n";
            } else if (ids[i] != 0 && ids[i] == ids.length - 1) {
                patients += toString(ids[i], names[i], birthdates[i], heights[i], weights[i],
                temperatures[i], bloodPressures[i]);
            }
        }

        System.out.println(patients);
        return patients;
    }

        /**
         * This method is used for formatting the strings containing user info
         * 
         * @param id            patient id
         * @param name          patient name
         * @param birthdate     patient birthdate
         * @param height        patient height
         * @param weight        patient weight
         * @param temperature   patient temperature
         * @param bloodPressure patient bloodpressure
         * @return a string that formats the string of patient info correctly
         */
    public static String toString(int id, String name, String birthdate, int height, int weight,
        double temperature, String bloodPressure) {
        return String.format("%4d  %-18s %10s %2d %3d %6.2f %7s",
            id, name, birthdate, height, weight,
            temperature, bloodPressure);
    }

        /**
         * This method allows the user to update patient information
         * if they so choose
         * 
         * @param scnr           scanner for user input
         * @param ids            patient id
         * @param names          patient name
         * @param birthdates     patient birthdate
         * @param heights        patient height
         * @param weights        patient weight
         * @param temperatures   patient temperature
         * @param bloodPressures patient bloodpressure
         * @return a string with the updated patient info
         * @throws IllegalArgumentException for null array or invalid array lengths
         */
    public static String updatePatient(Scanner scnr, int[] ids, String[] names,
        String[] birthdates, int[] heights, int[] weights,
        double[] temperatures, String[] bloodPressures) {
        if (ids == null || names == null || birthdates == null || heights == null || weights == null
            || temperatures == null || bloodPressures == null) {
            throw new IllegalArgumentException("Null array");
        }
        if (ids.length < 1 || ids.length != names.length || ids.length != birthdates.length
            || ids.length != heights.length
            || ids.length != weights.length || ids.length != temperatures.length
            || ids.length != bloodPressures.length) {
            throw new IllegalArgumentException("Invalid array length");
        }
        final int NEWENTRIES = 4;
        int id = 0;
        int newHeight = 0;
        int newWeight = 0;
        double newTemp = 0.0;
        String newBP = "";
        String invalid = "Invalid value";
        String valid = "Successful update";

        System.out.print("Patient ID: ");
        if (!scnr.hasNextInt()) {

            System.out.println();
            System.out.println("Invalid id");
            System.out.println();

            return "Invalid id";
        } else {
            id = scnr.nextInt();
        }
        if (id == 0) {
            System.out.println();
            System.out.println("Invalid id");
            System.out.println();

            return "Invalid id";
        }
        int index = 0;
        for (int i = 0; i < ids.length; i++) {
            if (Arrays.binarySearch(ids, id) < 0) {

                System.out.println();
                System.out.println("Invalid id");
                System.out.println();

                return "Invalid id";
            } else if (id == ids[i]) {

                System.out.print("Height: ");
                if (scnr.hasNextInt()) {
                    newHeight = scnr.nextInt();
                    index++;
                } else {

                    System.out.println();
                    System.out.println(invalid);
                    System.out.println();

                    return invalid;
                }

                System.out.print("Weight: ");
                if (scnr.hasNextInt()) {
                    newWeight = scnr.nextInt();
                    index++;
                } else {

                    System.out.println();
                    System.out.println(invalid);
                    System.out.println();

                    return invalid;
                }

                System.out.print("Temperature: ");
                if (scnr.hasNextDouble()) {
                    newTemp = scnr.nextDouble();
                    index++;
                } else {

                    System.out.println();
                    System.out.println(invalid);
                    System.out.println();

                    return invalid;
                }

                System.out.print("Blood Pressure: ");
                if (scnr.hasNext()) {
                    newBP = scnr.next();
                    index++;
                } else {

                    System.out.println();
                    System.out.println(invalid);
                    System.out.println();

                    return invalid;
                }
                if (index == NEWENTRIES) {
                    heights[i] = newHeight;
                    weights[i] = newWeight;
                    temperatures[i] = newTemp;
                    bloodPressures[i] = newBP;
                }
            }
        }

        System.out.println();
        System.out.println("Successful Update");
        System.out.println();
        return valid;
    }

        /**
         * This method allows users to add new patients into the existing patient pool
         * if they choose to do so
         * 
         * @param scnr           scanner for user input
         * @param ids            patient id
         * @param names          patient name
         * @param birthdates     patient birthdate
         * @param heights        patient height
         * @param weights        patient weight
         * @param temperatures   patient temperature
         * @param bloodPressures patient bloodpressure
         * @return a string that adds a new patient to output file
         * @throws IllegalArgumentException with null array or invalid array length
         */
    public static String addPatient(Scanner scnr, int[] ids, String[] names,
        String[] birthdates, int[] heights, int[] weights,
        double[] temperatures, String[] bloodPressures) {
        if (ids == null || names == null || birthdates == null || heights == null || weights == null
            || temperatures == null || bloodPressures == null) {
            throw new IllegalArgumentException("Null array");
        }
        if (ids.length < 1 || ids.length != names.length || ids.length != birthdates.length
            || ids.length != heights.length
            || ids.length != weights.length || ids.length != temperatures.length
            || ids.length != bloodPressures.length) {
            throw new IllegalArgumentException("Invalid array length");
        }
        String newname = "";
        String newBday = "";
        int newHeight = 0;
        int newWeight = 0;
        double newTemp = 0.0;
        String newBP = "";
        String invalid = "Invalid value";
        int index = 0;

        int[] tempids = new int[ids.length];
        tempids = Arrays.copyOf(ids, ids.length);
        Arrays.sort(tempids);

        if (Arrays.binarySearch(tempids, 0) < 0) {
            System.out.println("Cannot add patient");
            System.out.println();

            return "Cannot add patient";
        }

        for (int i = 0; i < ids.length; i++) {

            if (ids[i] == 0) {

                System.out.print("Name: ");
                scnr.nextLine();
                if (scnr.hasNext()) {
                    newname = scnr.nextLine();
                    index++;
                } else {
                    System.out.println();
                    System.out.println();

                    return invalid;
                }

                System.out.print("Birthdate: ");
                if (scnr.hasNext()) {
                    newBday = scnr.next();
                    index++;

                } else {
                    System.out.println();
                    System.out.println(invalid);
                    System.out.println();

                    return invalid;
                }

                System.out.print("Height: ");

                if (scnr.hasNextInt()) {
                    newHeight = scnr.nextInt();
                    index++;
                } else {
                    System.out.println();
                    System.out.println(invalid);
                    System.out.println();

                    return invalid;
                }

                System.out.print("Weight: ");
                if (scnr.hasNextInt()) {
                    newWeight = scnr.nextInt();
                    index++;
                } else {
                    System.out.println();
                    System.out.println(invalid);
                    System.out.println();

                    return invalid;
                }

                System.out.print("Temperature: ");
                if (scnr.hasNextDouble()) {
                    newTemp = scnr.nextDouble();
                    index++;
                } else {
                    System.out.println();
                    System.out.println(invalid);
                    System.out.println();

                    return invalid;
                }

                System.out.print("Blood Pressure: ");
                if (scnr.hasNext()) {
                    newBP = scnr.next();
                    index++;
                } else {
                    System.out.println();
                    System.out.println(invalid);
                    System.out.println();

                    return invalid;
                }
                final int UPDATEENTRIES = 6;
                if (index == UPDATEENTRIES) {
                    names[i] = newname;
                    birthdates[i] = newBday;
                    heights[i] = newHeight;
                    weights[i] = newWeight;
                    temperatures[i] = newTemp;
                    bloodPressures[i] = newBP;

                }
                if (ids.length > 1 && ids[0] != 0) {
                    ids[i] = ids[i - 1] + 1;
                } else {
                    ids[0] = FIRSTID;
                }
                break;
            }
                      
        }

        System.out.println();
        System.out.println("Successful addition");
        System.out.println();
        return "Successful addition";
    }

    /**
    * This method formats the output file into a csv format and
    * puts patient information into output file
    * 
    * @param out            printwriter outputs info to output file
    * @param ids            patient id
    * @param names          patient name
    * @param birthdates     patient birthdate
    * @param heights        patient height
    * @param weights        patient weight
    * @param temperatures   patient temperature
    * @param bloodPressures patient bloodpressure
    * @throws IllegalArgumentException when full is null, array is null or array lengths dont match
    */
    public static void outputPatients(PrintWriter out, int[] ids,
        String[] names, String[] birthdates,
        int[] heights, int[] weights, double[] temperatures,
        String[] bloodPressures) {
        if (out == null) {
            throw new IllegalArgumentException("Null file");
        }
        if (ids == null || names == null || birthdates == null || heights == null || weights == null
            || temperatures == null || bloodPressures == null) {
            throw new IllegalArgumentException("Null array");
        }
        if (ids.length < 1 || ids.length != names.length || ids.length != birthdates.length
            || ids.length != heights.length
            || ids.length != weights.length || ids.length != temperatures.length
            || ids.length != bloodPressures.length) {
            throw new IllegalArgumentException("Invalid array length");
        }
        String delim = ",";
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] != 0) {
                out.println(ids[i] + delim + names[i] + delim + birthdates[i] + delim + heights[i] +
                    delim + weights[i] + delim + temperatures[i] + delim
                    + bloodPressures[i]);
            }
        }
    }

    /**
     * This method is used for returning the display menu when called
     * 
     */
    public static void displayMenu() {

        System.out.println("Patient Records Program - Please choose an option.");
        System.out.println();
        System.out.println("L - List patients");
        System.out.println("U - Update patient");
        System.out.println("A - Add patient");
        System.out.println("Q - Quit");
        System.out.println();
        System.out.print("Option: ");

    }
}