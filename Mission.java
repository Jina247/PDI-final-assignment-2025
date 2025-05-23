import java.util.*;

public class Mission {
    /* Class attributes */
    private String missionName;
    private String missionCode;
    private String destinationPlanet;
    private int launchYear;
    private double successRate;
    private boolean isManned;
    private static final int MAX_ASTRONAUTS = 5;
    Astronaut[] astronaut = new Astronaut[MAX_ASTRONAUTS];
    private int astronautCount = 0;

    /* Constructors with given parameters */
    public Mission(String missionName, String missionCode, String destinationPlanet, int launchYear, double successRate, boolean isManned) {
        this.missionName = missionName;
        this.missionCode = missionCode;
        this.destinationPlanet = destinationPlanet;
        this.launchYear = launchYear;
        this.successRate = successRate;
        this.isManned = isManned;
    }

    /* Accessors */
    public String getMissionName() {
        return missionName;
    }
    public String getMissionCode() {
        return missionCode;
    }
    public String getDestinationPlanet() {
        return destinationPlanet;
    }
    public int getLaunchYear() {
        return launchYear;
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

    /* Mutators with validation */
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
    public void setLaunchYear(int launchYear) {
        if (launchYear < 1990 || launchYear > 2100) {
            throw new IllegalArgumentException("Launch year must be a valid integer between 1990 and 2100");
        }
        this.launchYear = launchYear;
    }
    public void setSuccessRate(double successRate) {
        if (successRate < 0.0 || successRate > 100.0) {
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

    /* Function: addAstronaut()
     * * Import: None
     * * Export: None
     * * Assertion: Add single astronaut to the mission, keep track astronaut's number.
     */
    public void addAstronaut(Astronaut pAstronaut) {
        if (astronautCount >= MAX_ASTRONAUTS) {
            throw new IllegalArgumentException("Cannot add more astronauts!");
        }
        astronaut[astronautCount++] = pAstronaut;
    }

    /* Function: addAstronautsToMission()
     * * Import: None
     * * Export: None
     * * Assertion: Prompts the user to enter astronaut details via the console and adds them to the mission.
     */
    public void addAstronautsToMission() {
        Scanner sc = new Scanner(System.in);
        int numAstronauts;

        while (true) {
            try {
                System.out.print("Enter the number of astronauts (1-5): ");
                numAstronauts = Integer.parseInt(sc.nextLine().trim());
                if (numAstronauts < 1 || numAstronauts > 5) {
                    System.out.println("Error: Number of astronauts must be between 1 and 5. Please try again.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number (1-5).");
            }
        }

        for (int j = 0; j < numAstronauts; j++) {
            System.out.println("--- Astronaut " + (j + 1) + " ---");

            String asName;
            do {
                System.out.print("Enter astronaut's name: ");
                asName = sc.nextLine().trim();
                if (asName.isEmpty()) {
                    System.out.println("Error: Astronaut name cannot be empty. Please try again.");
                }
            } while (asName.isEmpty());

            String asRole;
            do {
                System.out.print("Enter astronaut's role: ");
                asRole = sc.nextLine().trim();
                if (asRole.isEmpty()) {
                    System.out.println("Error: Astronaut role cannot be empty. Please try again.");
                }
            } while (asRole.isEmpty());

            int asAge;
            while (true) {
                try {
                    System.out.print("Enter astronaut's age (0-100): ");
                    asAge = Integer.parseInt(sc.nextLine().trim());
                    if (asAge < 0 || asAge > 100) {
                        System.out.println("Error: Astronaut age must be between 0 and 100. Please try again.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Please enter a valid age (numbers only).");
                }
            }

            String asNationality;
            do {
                System.out.print("Enter astronaut's nationality: ");
                asNationality = sc.nextLine().trim();
                if (asNationality.isEmpty()) {
                    System.out.println("Error: Astronaut nationality cannot be empty. Please try again.");
                }
            } while (asNationality.isEmpty());

            Astronaut newAstronaut = new Astronaut(asName, asRole, asAge, asNationality);
            addAstronaut(newAstronaut);
        }
    }

    /* Function: editAstronaut()
     * * Import: None
     * * Export: None
     * * Assertion: Prompts the user to enter astronaut details via the console and edit.
     */
    public void editAstronaut(String astronaut_name) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < astronautCount; i++) {
            Astronaut a = astronaut[i];
            if (a.getName().equals(astronaut_name)) {
                System.out.println("Enter a new name (or Enter to keep): ");
                String name = sc.nextLine().trim();
                if (!name.isEmpty()) {
                    a.setName(name);
                }

                System.out.println("Enter a new role (or Enter to keep): ");
                String role = sc.nextLine().trim();
                if (!role.isEmpty()) {
                    a.setRole(role);
                }

                System.out.println("Enter a new age (or Enter to keep): ");
                String ageInput = sc.nextLine().trim();
                if (!ageInput.isEmpty()) {
                    try {
                        int age = Integer.parseInt(ageInput);
                        a.setAge(age);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid age input. Age not changed.");
                    }
                }

                System.out.println("Enter a new nationality (or Enter to keep): ");
                String nationality = sc.nextLine().trim();
                if (!nationality.isEmpty()) {
                    a.setNationality(nationality);
                }
                System.out.println("Astronaut updated!");
                return;
            }
        }
        System.out.println("Astronaut not found!");
    }

    /* Function: removeAstronaut()
     * * Import: None
     * * Export: None
     * * Assertion: Remove astronaut from mission.
     */
    public void removeAstronaut() {
        for (int i = 0; i < astronautCount; i++) {
            astronaut[i] = null;
        }
        astronautCount = 0;
    }

    /* Function: listAstronautByNationality()
     * * Import: asNationality (String)
     * * Export: None
     * * Assertion: Display astronaut by given nationality.
     */
    public void listAstronautByNationality(String asNationality) {
        for (int i = 0; i < astronautCount; i++) {
            Astronaut a = astronaut[i];
            if (a.getNationality().equals(asNationality)) {
                System.out.println("Name: " + a.getName());
                System.out.println("Role: " + a.getRole());
                System.out.println("Age: " + a.getAge());
                System.out.println("Nationality: " + a.getNationality());
                System.out.println("-----------------------------");
            }
        } 
    }
    
    /* Function: displayAstronaut()
     * * Import: a (Astronaut class)
     * * Export: None
     * * Assertion: Display astronaut.
     */
    public void displayAstronaut(Astronaut a) {
        System.out.println(a);
        System.out.println();
    }
    
    /* Returns a string representation of the mission. */
    @Override
    public String toString() {
        return "Mission Name: " + missionName + "\n" +
            "Mission Code: " + missionCode + "\n" +
            "Destination Planet: " + destinationPlanet + "\n" +
            "Launch Year: " + launchYear + "\n" +
            "Success Rate: " + successRate + "\n" +
            "Is Manned: " + isManned;
}

}