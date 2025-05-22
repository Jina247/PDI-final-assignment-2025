import java.io.*;

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
                    for (int j = 0; j < m.getAstronautCount(); j++) {
                        Astronaut a = astronaut[j];
                        String aStr = a.getName() + ":" + a.getRole() + ":" + a.getAge() + ":" + a.getNationality();
                        if (m.getAstronautCount() > 1 && m.getAstronautCount() <= 5) {
                            aStr += "|";
                        }
                        prWrt.println(aStr);
                    }
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
}