import java.io.*;
import java.util.*;

public class MissionController {
    private final Mission[] missions;
    private int missionCount;

    /* Constructors */
    public MissionController(int capacity) {
        missions = new Mission[capacity];
        this.missionCount = 0;
    }
    /* Accessors */
    public Mission[] getMissions() {
        return missions;
    }
    public int getMissionCount() {
        return missionCount;
    }
    /* Filter manned mission */
    public Mission[] getMannedMission(){
        int count = 0;
        for (int i = 0; i < missionCount; i++) {
            if (missions[i].isManned()) {
                count++;
            }
        }
        Mission[] mannedMission = new Mission[count];
        int idx = 0;
        for (int j = 0; j < missionCount; j++) {
            if (missions[j].isManned()) {
                mannedMission[idx++] = missions[j];
            }
        }
        return mannedMission;
    }
    /* Filter unmanned mission */
    public Mission[] getUnMannedMission(){
        int count = 0;
        for (int i = 0; i < missionCount; i++) {
            if (!(missions[i].isManned())) {
                count++;
            }
        }
        Mission[] unMannedMission = new Mission[count];
        int idx = 0;
        for (int j = 0; j < missionCount; j++) {
            if (!(missions[j].isManned())) {
                unMannedMission[idx++] = missions[j];
            }
        }
        return unMannedMission;
    }

    /* Funtion: readFile()
     * * Import: fileName (String)
     * * Export: None
     * * Assertion: Extract and parse data from fileName, and store data to mission object. Throw multiple exceptions during file reading and closing.
     */
    public void readFile(String fileName) {
        FileInputStream fileStream = null;
        InputStreamReader rdr = null;
        BufferedReader bufRdr = null;
        String line;

        try {
            fileStream = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);
            bufRdr.readLine(); /* skip the first line */

            while ((line = bufRdr.readLine()) != null) {
                String[] part = line.split(",", 7);
                String missionName = part[0].trim();
                String missionCode = part[1].trim();
                String destinationPlanet = part[2].trim();
                int launchYear = Integer.parseInt(part[3].trim());
                double successRate = Double.parseDouble(part[4].trim());
                boolean isManned = Boolean.parseBoolean(part[5].trim());

                Mission mission = new Mission(missionName, missionCode, destinationPlanet, launchYear, successRate, isManned);
                if (mission.isManned()) {
                    String[] astronautsData = part[6].trim().split("\\|");
                    for (String astronautData : astronautsData) {
                        String[] astronautDetails = astronautData.split(":");
                        String name = astronautDetails[0].trim();
                        String role = astronautDetails[1].trim();
                        int age = Integer.parseInt(astronautDetails[2].trim());
                        String nationality = astronautDetails[3].trim();
                        Astronaut astronaut = new Astronaut(name, role, age, nationality);
                        mission.addAstronaut(astronaut);
                    }
                }
                missions[missionCount++] = mission;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading file:" + fileName);
        } finally {
            try {
                if (bufRdr != null) bufRdr.close();
                if (rdr != null) rdr.close();
                if (fileStream != null) fileStream.close();
            } catch (IOException e) {
                System.out.println("Error closing file: " + fileName);
            }
        }
    }

    /* Funtion: displayAllMission()
     * * Import: mission (Mission class)
     * * Export: None
     * * Assertion: Display all mission details and astronauts information
     */
    public void displayAllMissions(Mission mission) {
        System.out.println("===================================");
        System.out.println("\tMission Details");
        System.out.println(mission);
        
        if (mission.isManned()) {
            for (int i = 0; i < mission.getAstronautCount(); i++) {
            Astronaut astronaut = mission.getAstronaut()[i];
            System.out.println("=========================");
            System.out.println("\tAstronauts Information:");
            System.out.println(astronaut);
            }
        }
        System.out.println();
    }

    /* Funtion: getSuccessRatesArray()
     * * Import: None
     * * Export: successRates (double-type array)
     * * Assertion: Return array containing success rate
     */
    public double[] getSuccessRatesArray() {
        double[] successRatesArr = new double[missionCount];
        for (int i = 0; i < missionCount; i++) {
            successRatesArr[i] = missions[i].getSuccessRate();
        }
        return successRatesArr;
    }

    /* Funtion: getAverageSuccessRate()
     * * Import: None
     * * Export: averageSuccessRate (double)
     * * Assertion: Returns the average success rate of all missions by summing all success rates and dividing by the number of missions.
     */
    public double getAverageSuccessRate() {
        double[] successRatesArr = getSuccessRatesArray();
        double sum = 0;
        for (int i = 0; i < successRatesArr.length; i++) {
            sum += successRatesArr[i];
        }
        return sum / successRatesArr.length;
    }
    
    /* Function: getMaxSuccessRate()
     * Import: None
     * Export: max (double)
     * Assertion: Returns the maximum success rate among all missions by scanning through the success rate array.
     */
    public double getMaxSuccessRate() {
        double[] successRatesArr = getSuccessRatesArray();
        double max = successRatesArr[0];
        for (int i = 1; i < successRatesArr.length; i++) {
            if (successRatesArr[i] > max) {
                max = successRatesArr[i];
            }
        }
        return max;
    }

    /* Function: getMinSuccessRate()
     * Import: None
     * Export: min (double)
     * Assertion: Returns the minimum success rate among all missions by scanning through the success rate array.
     */
    public double getMinSuccessRate() {
        double[] successRatesArr = getSuccessRatesArray();
        double min = successRatesArr[0];
        for (int i = 1; i < successRatesArr.length; i++) {
            if (successRatesArr[i] < min) {
                min = successRatesArr[i];
            }
        }
        return min;
    }

    /* Function: writeFile()
     * Import: fileName (String)
     * Export: None
     * Assertion: Writes all mission details including astronauts to a CSV file with the given file name.
     */
    public void writeFile(String fileName) {
        FileOutputStream fOutStrm = null;
        PrintWriter prWrt = null;
        try {
            fOutStrm = new FileOutputStream(fileName);
            prWrt = new PrintWriter(fOutStrm);
            /* Add header line */
            prWrt.println("Mission Name,Mission Code,Destination Planet,Launch Year,Success Rate,Manned Mission,Astronauts");
            for (int i = 0; i < missionCount; i++) {
                Mission m = missions[i];
                String line = m.getMissionName()+ "," + m.getMissionCode() + "," + m.getDestinationPlanet()+ ","  + m.getLaunchYear()+ ","  + m.getSuccessRate()+ ","  + m.isManned();
                if (m.isManned()) {
                    Astronaut[] astronaut = m.getAstronaut();
                    String astronautLine = "";
                    for (int j = 0; j < m.getAstronautCount(); j++) {
                        Astronaut a = astronaut[j];
                        String aStr = a.getName() + ":" + a.getRole() + ":" + a.getAge() + ":" + a.getNationality();
                        /* If the mission only has one astronaut */
                        if (j == 0) {
                            astronautLine = aStr;
                        } 
                        else {
                            astronautLine += "|" + aStr;
                        }
                    }
                    prWrt.println(line + "," + astronautLine);
                } else {
                    prWrt.println(line + ",");
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error reading file:" + fileName);
        }
        finally {
            try {
                if (prWrt != null) prWrt.close();
                if (fOutStrm != null) fOutStrm.close();  
            }
            catch (IOException e) {
                System.out.println("Error closing file:" + fileName);
            }
        }
    }

    /* Function: addMission()
     * Import: None
     * Export: None
     * Assertion: Adds a new mission by prompting the user for mission details, including astronaut data if it's manned, and updates the mission list and data file.
     */
    public void addMission() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\t==========Add a new mission==========");

        /* Validate each field */
        String name;
        do {
            System.out.print("Enter mission name: ");
            name = sc.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Error: Mission name cannot be empty. Please try again.");
            }
        } while (name.isEmpty());
        
        String code;
        do {
            System.out.print("Enter mission code: ");
            code = sc.nextLine().trim();
            if (code.isEmpty()) {
                System.out.println("Error: Mission code cannot be empty. Please try again.");
            }
        } while (code.isEmpty());
        
        String desPlanet;
        do {
            System.out.print("Enter destination planet: ");
            desPlanet = sc.nextLine().trim();
            if (desPlanet.isEmpty()) {
                System.out.println("Error: Destination planet cannot be empty. Please try again.");
            }
        } while (desPlanet.isEmpty());

        int year;
        while (true) {
            try {
                System.out.print("Enter launch year (1990-2100): ");
                year = sc.nextInt();
                sc.nextLine(); // consume newline
                if (year < 1990 || year > 2100) {
                    System.out.println("Error: Launch year must be between 1990 and 2100. Please try again.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid year (numbers only).");
                sc.nextLine(); // consume invalid input
            }
        }

        double rate;
        while (true) {
            try {
                System.out.print("Enter success rate (0.0-100.0): ");
                rate = sc.nextDouble();
                sc.nextLine(); // consume newline
                if (rate < 0.0 || rate > 100.0) {
                    System.out.println("Error: Success rate must be between 0.0 and 100.0. Please try again.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid success rate (decimal number).");
                sc.nextLine(); // consume invalid input
            }
        }

        boolean isManned;
        while (true) {
            System.out.print("Is the mission manned? (true/false): ");
            String mannedInput = sc.nextLine().trim().toLowerCase();
            if (mannedInput.equals("true") || mannedInput.equals("false")) {
                isManned = Boolean.parseBoolean(mannedInput);
                break;
            } else {
                System.out.println("Error: Please enter 'true' or 'false' only.");
            }
        }

        Mission newMission = new Mission(name, code, desPlanet, year, rate, isManned);
        
        if (newMission.isManned()) {
            newMission.addAstronautsToMission();
        }
        missions[missionCount++] = newMission;
        System.out.println("Mission added!");
        writeFile("data.csv");
    }

    /* Function: editMission()
     * Import: missionCode (String)
     * Export: None
     * Assertion: Searches for a mission by code and allows the user to update any mission details. Handles astronaut updates based on manned status and rewrites data to file.
     */
    public void editMission(String missionCode) {
        if (missionCode.isEmpty()) {
            System.out.println("Mission code cannot be empty!");
            return;
        }
        boolean missionFound = false; /* Flag to track if mission is found */ 
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < missionCount; i++) {
            if (missions[i].getMissionCode().equals(missionCode)) {
                Mission eMission = missions[i];
                System.out.println("\t===========Edit mission==========");
                System.out.println("Mission Name: " + eMission.getMissionName());

                System.out.print("Enter a new mission name (or Enter to keep): ");
                String newName = sc.nextLine();
                if (!newName.isEmpty()) {
                    eMission.setMissionName(newName);
                }

                System.out.print("Enter a new mission code (or Enter to keep): ");
                String newCode = sc.nextLine();
                if (!newCode.isEmpty()) {
                    eMission.setMissionCode(newCode);
                }

                System.out.print("Enter a new destination planet (or Enter to keep): ");
                String newPlanet = sc.nextLine();
                if (!newPlanet.isEmpty()) {
                    eMission.setDestinationPlanet(newPlanet);
                }

                System.out.print("Enter a new launch year (or Enter to keep): ");
                String yearInput = sc.nextLine();
                if (!yearInput.isEmpty()) {
                    try {
                        int newYear = Integer.parseInt(yearInput);
                        eMission.setLaunchYear(newYear);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid year. Keeping previous value.");
                    }
                }

                System.out.print("Enter a new success rate (or Enter to keep): ");
                String rateInput = sc.nextLine();
                if (!rateInput.isEmpty()) {
                    try {
                        double newRate = Double.parseDouble(rateInput);
                        eMission.setSuccessRate(newRate);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid rate. Keeping previous value.");
                    }
                }
                System.out.print("Is the mission manned? (true/false or Enter to keep): ");
                String mannedInput = sc.nextLine();
                /* mannedInput is not empty */
                if (!mannedInput.isEmpty()) {
                    eMission.setManned(Boolean.parseBoolean(mannedInput));
                     
                    if (eMission.isManned()) {
                        /* If current mission is false and then changed to true, perform adding Astronaut to the mission */
                        System.out.println("Mission status is changed to true");
                        eMission.addAstronautsToMission();
                    }
                    else {
                        /* If current mission is false and then changed to true, perform removing Astronaut from the mission */
                        System.out.println("Mission status is changed to false");
                        eMission.removeAstronaut();
                    }

                /* mannedInput is empty, check current mission's manned status */ 
                } else {
                    if (eMission.isManned()) {
                        System.out.println("Enter the astronaut's name to EDIT (or Enter to skip): ");
                        String astronautName = sc.nextLine();
                        if (!astronautName.isEmpty()) {
                            eMission.editAstronaut(astronautName);
                        } else {
                            System.out.println("No astronaut update!");
                        }
                    }
                }
                System.out.println("Mission updated!");
                /* After editing, write to file */
                writeFile("data.csv");
                break;
            }
        }
        /* Check if mission was not found */
        if (!missionFound) {
            System.out.println("Mission not found!");
        }
    }

    /* Function: displayAstronautByMission()
     * Import: missionCode (String)
     * Export: None
     * Assertion: Finds the mission by code and displays all astronaut information associated with it, or shows a message if there are none.
     */
    public void displayAstronautByMission(String missionCode) {
        for (int i = 0; i < missionCount; i++) {
            Mission m = missions[i];
            if (m.getMissionCode().equals(missionCode)) {
                Astronaut[] astronauts = m.getAstronaut();
                if (m.getAstronautCount() > 0) {
                    for (int j = 0; j < m.getAstronautCount(); j++) {
                        m.displayAstronaut(astronauts[j]);
                    }
                } else {
                    System.out.println("No astronauts found for this mission.");
                }
                break;
            }
        }
    }
}