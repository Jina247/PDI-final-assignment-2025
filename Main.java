public class Main {
    public static void main(String[] args) {
        MissionController missionController = new MissionController(50);
        missionController.readFile("data.csv");

        // System.out.println("All mission(s)");
        // for (int i = 0; i < missionController.getMissionCount(); i++) {
        //     Mission m = missionController.getMissions()[i];
        //     missionController.displayAllMissions(m);
        // }
        // System.out.println("-----------------------------------");
        // System.out.println("All manned mission(s)");
        // Mission[] mannedMissions = missionController.getMannedMission();
        // for (Mission mMission : mannedMissions) {
        //     missionController.displayAllMissions(mMission);
        // }
        // System.out.println("-----------------------------------");
        // System.out.println("All unmanned mission(s)");
        // Mission[] unMannedMissions = missionController.getUnannedMission();
        // for (Mission unMMission : unMannedMissions) {
        //     missionController.displayAllMissions(unMMission);
        // }

        System.out.println("Average: " + missionController.getAverageSuccessRate());
        System.out.println("Max: " + missionController.getMaxSuccessRate());
        System.out.println("Min: " + missionController.getMinSuccessRate());

    }
}
