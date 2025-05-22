import java.io.*;
import java.util.*;

public class MissionController {
    private final Mission[] missions;
    private int missionCount;

    public MissionController(int capacity) {
        missions = new Mission[capacity];
        this.missionCount = 0;
    }
    public Mission[] getMissions() {
        return missions;
    }
    public int getMissionCount() {
        return missionCount;
    }
    public Mission[] getMannedMission(){
        int count = 0;
        for (int i = 0; i < missionCount; i++) {
            if (missions[i].isManned()) {
                count++;
            }
        }
        Mission[] mannedMission = new Mission[count];
        int idx = 0;
        for (int j = 0;j < missionCount; j++) {
            if (missions[j].isManned()) {
                mannedMission[idx++] = missions[j];
            }
        }
        return mannedMission;
    }

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

    public void readFile(String fileName) {
        FileInputStream fileStream = null;
        InputStreamReader rdr = null;
        BufferedReader bufRdr = null;
        String line;

        try {
            fileStream = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);
            bufRdr.readLine(); // skip the first line

            while ((line = bufRdr.readLine()) != null) {
                String[] part = line.split(",", 7);
                String missionName = part[0].trim();
                String missionCode = part[1].trim();
                String destinationPlaner = part[2].trim();
                int lauchYear = Integer.parseInt(part[3].trim());
                double successRate = Double.parseDouble(part[4].trim());
                boolean isManned = Boolean.parseBoolean(part[5].trim());

                Mission mission = new Mission(missionName, missionCode, destinationPlaner, lauchYear, successRate, isManned);
                if (mission.isManned()) {
                    String[] astronautsData = part[6].trim().split("\\|");
                    for (String astronautData : astronautsData) {
                        String[] astronautDetails = astronautData.split(":");
                        String name = astronautDetails[0].trim();
                        String role = astronautDetails[1].trim();
                        int age = Integer.parseInt(astronautDetails[2].trim());
                        String natiionality = astronautDetails[3].trim();
                        Astronaut astronaut = new Astronaut(name, role, age, natiionality);
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

    public void displayAllMissions(Mission mission) {
        System.out.println("===================================");
        System.out.println("Mission Details");
        System.out.println("Mission Name: " + mission.getMissionName());
        System.out.println("Mission Code: " + mission.getMissionCode());
        System.out.println("Destination Planet: " + mission.getDestinationPlanet());
        System.out.println("Launch Year: " + mission.getLauchYear());
        System.out.println("Success Rate: " + mission.getSuccessRate());
        System.out.println("Is Manned: " + mission.isManned());
        
        if (mission.isManned()) {
            for (int i = 0; i < mission.getAstronautCount(); i++) {
            Astronaut astronaut = mission.getAstronaut()[i];
            System.out.println("=========================");
            System.out.println("Astronauts Information:");
            System.out.println(astronaut);
            System.out.println("Name: " + astronaut.getName());
            System.out.println("Age: " + astronaut.getAge()); 
            System.out.println("Role: " + astronaut.getRole());
            System.out.println("Nationality: " + astronaut.getNationality());
            System.out.println("\n");
            }
        }
    }

    public double[] getSuccessRatesArray() {
        double[] successRatesArr = new double[missionCount];
        for (int i = 0; i < missionCount; i++) {
            successRatesArr[i] = missions[i].getSuccessRate();
        }
        return successRatesArr;
    }

    public double getAverageSuccessRate() {
        double[] successRatesArr = getSuccessRatesArray();
        double sum = 0;
        for (int i = 0; i < successRatesArr.length; i++) {
            sum += successRatesArr[i];
        }
        return sum / successRatesArr.length;
    }
    
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

    public void writeFile(String fileName) {
        FileOutputStream fOutStrm = null;
        PrintWriter prWrt = null;
        try {
            fOutStrm = new FileOutputStream(fileName);
            prWrt = new PrintWriter(fOutStrm);
            for (int i = 0; i < missionCount; i++) {
                Mission m = missions[i];
                String line = m.getMissionCode()+ "," + m.getMissionName() + "," + m.getDestinationPlanet()+ ","  + m.getLauchYear()+ ","  + m.getSuccessRate()+ ","  + m.isManned();
                prWrt.println(line);

                if (m.isManned()) {
                    Astronaut[] astronaut = m.getAstronaut();
                    String astronautLine = "";
                    for (int j = 0; j < m.getAstronautCount(); j++) {
                        Astronaut a = astronaut[j];
                        String aStr = a.getName() + ":" + a.getRole() + ":" + a.getAge() + ":" + a.getNationality();
                        if (j == 0) {
                            astronautLine = aStr;
                        } 
                        else {
                            astronautLine += "|" + aStr;
                        }
                    }
                    prWrt.println(astronautLine);
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
    // Add scanner in main asking mission code to edit
    public void editMission(String missionCode) {
        try (Scanner sc = new Scanner(System.in)) {
            for (int i = 0; i < missionCount; i++) {
                if (missions[i].getMissionCode().equals(missionCode)) {
                    Mission m = missions[i];
                    System.out.println("Edit mission");
                    System.out.println("Mission Name: " + m.getMissionName());
                    
                    System.out.println("Enter a new mission name (or Enter to keep): " + m.getMissionName());
                    String newName = sc.nextLine();
                    if (!newName.isEmpty()) {
                        m.setMissionName(newName);
                    }
                    
                    System.out.println("Enter a new mission code (or Enter to keep): " + m.getMissionCode());
                    String newCode = sc.nextLine();
                    if (!newCode.isEmpty()) {
                        m.setMissionCode(newCode);
                    }
                    System.out.println("Enter a new destination planet (or Enter to keep): " + m.getDestinationPlanet());
                    String newPlanet = sc.nextLine();
                    if (!newPlanet.isEmpty()) {
                        m.setDestinationPlanet(newPlanet);
                    }
                    
                    System.out.println("Enter a new lauch year (or Enter to keep): " + m.getLauchYear());
                    int newYear = sc.nextInt();
                    m.setLauchYear(newYear);
                    
                    System.out.println("Enter a new success rate (or Enter to keep): " + m.getSuccessRate());
                    double newRate = sc.nextDouble();
                    m.setSuccessRate(newRate);
                    
                    System.out.println("Is the mission manned? (true/false): " + m.isManned());
                    boolean isManned = sc.nextBoolean();
                    m.setManned(isManned);
                    
                    if (m.isManned()) {
                        
                    }
                    System.out.println("Mission updated!");
                }
            }
        }
    }

    public void editAstronaut(Mission mission) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter an astronaut's name to edit:");
            String astronaut_name = sc.nextLine();
            Astronaut[] astronauts = mission.getAstronaut();
            for (int i = 0; i < mission.getAstronautCount(); i++) {
                Astronaut a = astronauts[i];
                if (a.getName().equals(astronaut_name)) {
                    System.out.println("Enter a new name (or Enter to keep): " + a.getName());
                    String name = sc.nextLine(); 
                    a.setName(name);
                    
                    String role = sc.nextLine();
                    a.setRole(role);

                    int age = sc.nextInt();
                    a.setAge(age);

                    String nationality = sc.nextLine();
                    a.setNationality(nationality);
                }
                System.out.println("Astronaut updated!");
            }
            
        }

    }
    
}