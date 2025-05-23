import java.util.*;

public class Mission {
    private String missionName;
    private String missionCode;
    private String destinationPlanet;
    private int lauchYear;
    private double successRate;
    private boolean isManned;
    private static final int MAX_ASTRONAUTS = 5;
    Astronaut[] astronaut = new Astronaut[MAX_ASTRONAUTS];
    private int astronautCount = 0;

    public Mission(String missionName, String missionCode, 
                   String destinationPlanet, int lauchYear, 
                   double successRate, boolean isManned) {
        this.missionName = missionName;
        this.missionCode = missionCode;
        this.destinationPlanet = destinationPlanet;
        this.lauchYear = lauchYear;
        this.successRate = successRate;
        this.isManned = isManned;
    }
    public String getMissionName() {
        return missionName;
    }
    public String getMissionCode() {
        return missionCode;
    }
    public String getDestinationPlanet() {
        return destinationPlanet;
    }
    public int getLauchYear() {
        return lauchYear;
    }
    public double getSuccessRate() {
        return successRate;
    }
    public boolean isManned() {
        return isManned;
    }
    public Astronaut[] getAstronaut() {
        return astronaut;
    }
    public int getAstronautCount() {
        return astronautCount;
    }

    public void setMissionName(String missionName) {
        if (missionName == null || missionName.isEmpty()) {
            throw new IllegalArgumentException("Mission name cannot be null or empty");
        }
        this.missionName = missionName;
    }
    public void setMissionCode(String missionCode) {
        if (missionCode == null || missionCode.isEmpty()) {
            throw new IllegalArgumentException("Mission code cannot be null or empty");
        }
        this.missionCode = missionCode;
    }
    public void setDestinationPlanet(String destinationPlanet) {
        if (destinationPlanet == null || destinationPlanet.isEmpty()) {
            throw new IllegalArgumentException("Destination planet cannot be null or empty");
        }
        this.destinationPlanet = destinationPlanet;
    }
    public void setLauchYear(int lauchYear) {
        if (lauchYear < 1990 && lauchYear > 2100) {
            throw new IllegalArgumentException("Launch year must be a valid integer between 1990 and 2100");
        }
        this.lauchYear = lauchYear;
    }
    public void setSuccessRate(double successRate) {
        if (successRate < 0 && successRate > 100) {
            throw new IllegalArgumentException("Success rate must be between 0 and 100");
        }
        this.successRate = successRate;
    }
    public void setManned(boolean manned) {
        isManned = manned;
    }
    public void setAstronaut(Astronaut[] astronaut) {
        if (astronaut.length > MAX_ASTRONAUTS) {
            throw new IllegalArgumentException("Cannot have more than " + MAX_ASTRONAUTS + " astronauts!");
        }
        this.astronaut = astronaut;
        this.astronautCount = astronaut.length;
    }
    public void addAstronaut(Astronaut pAstronaut) {
        if (astronautCount >= MAX_ASTRONAUTS) {
            throw new IllegalArgumentException("Cannot add more astronauts!");
        }
        astronaut[astronautCount++] = pAstronaut;
    }
    // Scanner in main for asking
    public void editAstronaut(String astronaut_name) {
        try (Scanner sc = new Scanner(System.in)) {
            for (int i = 0; i < astronautCount; i++) {
                Astronaut a = astronaut[i];
                if (a.getName().equals(astronaut_name)) {
                    System.out.println("Enter a new name (or Enter to keep): " + a.getName());
                    String name = sc.nextLine(); 
                    a.setName(name);

                    System.out.println("Enter a new role (or Enter to keep): " + a.getRole());
                    String role = sc.nextLine();
                    a.setRole(role);

                    System.out.println("Enter a new age (or Enter to keep): " + a.getAge());
                    int age = sc.nextInt();
                    a.setAge(age);

                    System.out.println("Enter a new nationality (or Enter to keep): " + a.getNationality());
                    String nationality = sc.nextLine();
                    a.setNationality(nationality);
                }
            }
            System.out.println("Astronaut updated!");
        }
    }

    public void removeAstronaut() {
        for (int i = 0; i < astronautCount; i++) {
            astronaut[i] = null;
        }
        astronautCount = 0;
    }

    public void listAstronaut(String asNationality) {
        System.out.println("======List of astronaut matches a given nationality=====");
        boolean found = false;
        for (int i = 0; i < astronautCount; i++) {
            Astronaut a = astronaut[i];
            if (a.getNationality().equals(asNationality)) {
                System.out.println("Name: " + a.getName());
                System.out.println("Role: " + a.getRole());
                System.out.println("Age: " + a.getAge());
                System.out.println("Nationality: " + a.getNationality());
                System.out.println("-----------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No astronauts found with nationality: " + asNationality);
        }   
    }
}