import java.util.*;
public class Main {
    public static void main(String[] args) {
        MissionController missionController = new MissionController(50);
        missionController.readFile("data.csv");
        try (Scanner s = new Scanner(System.in)) {
            while (true) {
                System.out.println("=============================================");
                System.out.println(" Welcome to Mission Command and Control");
                System.out.println("=============================================");
                System.out.println("Your options for this system are listed below");
                System.out.println("1> View all Missions");
                System.out.println("2> View all manned missions.");
                System.out.println("3> View all unmanned missions.");
                System.out.println("4> View a mission's astronauts.");
                System.out.println("5> Add a new mission.");
                System.out.println("6> Edit an existing mission.");
                System.out.println("7> Summary of missions' success rates (average, highest, lowest).");
                System.out.println("8> List astronauts for a given nationality.");
                System.out.println("9> Exit Mission Command and Control");
                System.out.println("Please enter your choice (1-9): ");
                int choice = s.nextInt();
                s.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1 -> {
                        System.out.println("All missions");
                        for (int i = 0; i < missionController.getMissionCount(); i++) {
                            Mission m = missionController.getMissions()[i];
                            missionController.displayAllMissions(m);
                        }
                    }
                    case 2 -> {
                        System.out.println("Manned Missons ");
                        Mission[] mannedMissions = missionController.getMannedMission();
                        for (Mission mMission : mannedMissions) {
                            missionController.displayAllMissions(mMission);
                        }
                    }
                    case 3 -> {
                        System.out.println("Unmanned Missons ");
                        Mission[] unMannedMissions = missionController.getUnMannedMission();
                        for (Mission uMMission : unMannedMissions) {
                            missionController.displayAllMissions(uMMission);
                        }
                    }
                    case 4 -> {
                        System.out.print("Enter mission code to view astronauts: ");
                        String mCode = s.nextLine();
                        missionController.displayAstronautByMission(mCode);
                    }
                    case 5 -> {
                        missionController.addMission();
                    }
                    case 6 -> {
                        System.out.print("Enter mission code to edit:");
                        String mCode = s.nextLine();
                        missionController.editMission(mCode);
                    }
                    case 7 -> {
                        System.out.printf("Average: %.2f%n", missionController.getAverageSuccessRate());
                        System.out.printf("Max: %.2f%n", missionController.getMaxSuccessRate());
                        System.out.printf("Min: %.2f%n", missionController.getMinSuccessRate());
                    }
                    case 8 -> {
                        System.out.print("Enter nationality: ");
                        String nationality = s.nextLine();
                        Mission[] m = missionController.getMissions();
                        boolean found = false;

                        System.out.println("======List of astronaut matches a given nationality=====");
                        for (int i = 0; i < missionController.getMissionCount(); i++) {
                            m[i].listAstronautByNationality(nationality);
                            found = true;
                        }
                        if (!found) {
                            System.out.println("No astronauts found with nationality: " + nationality);
                        }  
                    }
                    case 9 -> {
                        System.out.println("Exiting Mission Command and Control. Goodbye!");
                        System.exit(0);
                        break;
                    }
                    default -> System.out.println("Invalid choice. Please enter a number from 1 to 9.");
                }
            }
        }
        
}

}