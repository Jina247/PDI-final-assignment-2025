import java.io.*;

public class MissionController {
    // instances of the class
    private Mission[] missions;
    private int missionCount;

    public MissionController(int capacity) {
        missions = new Mission[capacity];
        this.missionCount = 0;
    }
    public void readFile(String fileName) {
        FileInputStream fileStream;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line;
        
        try {
            fileStream = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);
            line = bufRdr.readLine();
            bufRdr.readLine(); // skip the first line

            while (line != null) {
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
                        String [] astronautDetails = astronautData.split(":");
                        String name = astronautDetails[0].trim();
                        String role = astronautDetails[1].trim();
                        int age = Integer.parseInt(astronautDetails[2].trim());
                        String natiionality = astronautDetails[3].trim();
                        Astronauts astronauts = new Astronauts(name, role, age, natiionality);
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
        catch (IOException e) {
            System.out.println("Error reading file:" + fileName);
        }

    }
}