package FarmerSimulation;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import FarmerSimulation.Stores.ActivityStore;
import FarmerSimulation.Stores.FarmStore;
import FarmerSimulation.Stores.FarmerStore;

public class DataVisualization {

    public static void option() {
        System.out.println(
                "To display all activity logs with date A and date B - Please enter: 5");
        System.out.println(
                "To display summarized logs activity logs with date A and date B- Please enter: 6");
        System.out.println("To quit the farm data visualization - Please enter: -1");
        System.out.print("Enter the command number: ");
    }

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean dataVisualizationProcessing = true, farmerDVP = true,
                farmDVP = true, farmOnlyDVP = true;
        int commandChosen, farmerChosen, farmChosen, plantChosen, fertilizerChosen, pesticideChosen;
        while (dataVisualizationProcessing) {
            dataVisualizationProcessing = true;
            farmerDVP = true;
            farmDVP = true;
            farmOnlyDVP = true;
            commandChosen = -1;
            System.out.println();
            System.out.println("Enter the command's number to visualize the activities data:");
            System.out.println("To display all activity logs for a target farm - Please enter: 0");
            System.out.println("To display all activity logs for a target farmer - Please enter: 1");
            System.out.println("To quit the data visualization - Please enter: -1");
            System.out.print("Enter the command number: ");
            try {
                commandChosen = Integer.parseInt(scanner.next());
                if (commandChosen == 0) {
                    while (farmDVP) {
                        System.out.println();
                        System.out.println("Enter the command's number to visualize the activities data:");
                        System.out.println("To display all activity logs for a target farm only - Please enter: 0");
                        System.out
                                .println("To display all activity logs for a target farm with plant - Please enter: 2");
                        System.out.println(
                                "To display all activity logs for a target farm with fertilizer - Please enter: 3");
                        System.out.println(
                                "To display all activity logs for a target farm with pesticide - Please enter: 4");
                        System.out.println("To quit the farm data visualization - Please enter: -1");
                        System.out.print("Enter the command number: ");
                        try {
                            commandChosen = Integer.parseInt(scanner.next());
                            if (commandChosen == 0 || commandChosen == 2 || commandChosen == 3 || commandChosen == 4) {
                                farmChosen = -1;
                                FarmStore farmStore = new FarmStore();
                                HashMap<String, String> farms = farmStore.getFarms();
                                if (farms.size() > 0) {
                                    while (farmOnlyDVP) { // this may need change
                                        System.out.println();
                                        System.out.println(
                                                "Please Enter the respective farm's number to see farm's activity logs");
                                        for (Entry<String, String> entry : farms.entrySet()) {
                                            System.out.println(entry.getValue() + " - Please Enter: " + entry.getKey());
                                        }
                                        System.out.print("Enter the farm's number: ");
                                        try {
                                            farmChosen = Integer.parseInt(scanner.next());
                                            if (farmChosen >= 0 && farmChosen < farms.size()) {
                                                ActivityStore activityStore = new ActivityStore();
                                                List<Activity> activityLogs = activityStore
                                                        .findActivitiesByFarmId(farmChosen);
                                                System.out.println();
                                                System.out.println("The Activity Logs of Farm "
                                                        + farms.get(String.valueOf(farmChosen)) + " : ");
                                                for (Activity activity : activityLogs) {
                                                    farms.get(String.valueOf(farmChosen));
                                                    System.out.println(activity.getAction() + " " + activity.getType()
                                                            + " Field "
                                                            + activity.getField() + " Row " + activity.getRow() + " "
                                                            + activity.getQuantity() + " " + activity.getUnit() + " "
                                                            + activity.getDate() + " in Farm ID: "
                                                            + activity.getFarmId());
                                                    // Sowing Broccoli Field 1 Row 1 1 kg 2022-03-03
                                                }
                                                System.out.println();
                                                // call method to create a output text file here
                                                while (true) {
                                                    System.out.println();
                                                    System.out.println(
                                                            "Do you want to continue viewing farm's acitivity logs?");
                                                    System.out.println("Yes - Please enter: 1");
                                                    System.out.println("No - Please enter: -1");
                                                    System.out.print("Enter the option's number: ");
                                                    try {
                                                        commandChosen = Integer.parseInt(scanner.next());
                                                        if (commandChosen == 1) {
                                                            break;
                                                        } else if (commandChosen == -1) {
                                                            farmOnlyDVP = false;
                                                            break;
                                                        }
                                                    } catch (NumberFormatException ex) {
                                                        System.out.println();
                                                        System.out.println(
                                                                "Invalid input, Please enter the command number that be displayed only.");
                                                        System.out.println();
                                                        continue;
                                                    }
                                                }
                                            } else {
                                                System.out.println();
                                                System.out.println(
                                                        "Invalid input, Please enter the command number that be displayed only.");
                                                System.out.println();
                                                continue;
                                            }
                                        } catch (NumberFormatException ex) {
                                            System.out.println();
                                            System.out
                                                    .println(
                                                            "Invalid input, Please enter the command number that be displayed only.");
                                            System.out.println();
                                            continue;
                                        }
                                    }
                                    if (commandChosen == 0) {
                                        System.out.println();
                                        System.out.println(
                                                "Enter the command's number to visualize the activities data:");
                                        System.out.println("To display normal activity logs - Please enter: 2");
                                        option();
                                        try {
                                            commandChosen = Integer.parseInt(scanner.next());
                                            // code here display activity logs
                                        } catch (NumberFormatException ex) {
                                            System.out.println();
                                            System.out
                                                    .println(
                                                            "Invalid input, Please enter the command number that be displayed only.");
                                            System.out.println();
                                            continue;
                                        }
                                    } else if (commandChosen == 2) {
                                        System.out.println();
                                        System.out.println(
                                                "Enter the command's number to visualize the activities data:");
                                        System.out.println("To display normal activity logs - Please enter: 2");
                                        option();
                                        try {
                                            commandChosen = Integer.parseInt(scanner.next());
                                            // code here let user choose plant exist in the farm chosen
                                        } catch (NumberFormatException ex) {
                                            System.out.println();
                                            System.out
                                                    .println(
                                                            "Invalid input, Please enter the command number that be displayed only.");
                                            System.out.println();
                                            continue;
                                        }
                                    } else if (commandChosen == 3) {
                                        System.out.println();
                                        System.out.println(
                                                "Enter the command's number to visualize the activities data:");
                                        System.out.println("To display normal activity logs - Please enter: 3");
                                        option();
                                        try {
                                            commandChosen = Integer.parseInt(scanner.next());
                                            // code here let user choose fertilizer exist in the farm chosen
                                        } catch (NumberFormatException ex) {
                                            System.out.println();
                                            System.out
                                                    .println(
                                                            "Invalid input, Please enter the command number that be displayed only.");
                                            System.out.println();
                                            continue;
                                        }
                                    } else if (commandChosen == 4) {
                                        System.out.println();
                                        System.out.println(
                                                "Enter the command's number to visualize the activities data:");
                                        System.out.println("To display normal activity logs - Please enter: 4");
                                        option();
                                        try {
                                            commandChosen = Integer.parseInt(scanner.next());
                                            // code here let user choose pesticide exist in the farm chosen
                                        } catch (NumberFormatException ex) {
                                            System.out.println();
                                            System.out
                                                    .println(
                                                            "Invalid input, Please enter the command number that be displayed only.");
                                            System.out.println();
                                            continue;
                                        }
                                    }
                                } else {
                                    System.out.println();
                                    System.out.println("Sorry, that is no farms data");
                                    System.out.println();
                                }
                            } else if (commandChosen == -1) {
                                farmDVP = false;
                                break;
                            }
                        } catch (NumberFormatException ex) {
                            System.out.println();
                            System.out
                                    .println("Invalid input, Please enter the command number that be displayed only.");
                            System.out.println();
                            continue;
                        }
                    }
                } else if (commandChosen == 1) {
                    farmerChosen = -1;
                    FarmerStore farmerStore = new FarmerStore();
                    HashMap<String, String> farmers = farmerStore.getFarmers();
                    if (farmers.size() > 0) {
                        while (farmerDVP) {
                            System.out.println();
                            System.out.println(
                                    "Please Enter the respective farmer's number to see her/his activity logs");
                            for (Entry<String, String> entry : farmers.entrySet()) {
                                System.out.println(entry.getValue() + " - Please Enter: " + entry.getKey());
                            }
                            System.out.print("Enter the farmer's number: ");
                            try {
                                farmerChosen = Integer.parseInt(scanner.next());
                                if (farmerChosen >= 0 && farmerChosen < farmers.size()) {
                                    ActivityStore activityStore = new ActivityStore();
                                    List<Activity> activityLogs = activityStore.findActivitiesByUserId(farmerChosen);
                                    System.out.println();
                                    System.out.println("The Activity Logs of Farmer "
                                            + farmers.get(String.valueOf(farmerChosen)) + " : ");
                                    for (Activity activity : activityLogs) {
                                        farmers.get(String.valueOf(farmerChosen));
                                        System.out.println(activity.getAction() + " " + activity.getType() + " Field "
                                                + activity.getField() + " Row " + activity.getRow() + " "
                                                + activity.getQuantity() + " " + activity.getUnit() + " "
                                                + activity.getDate() + " in Farm ID: " + activity.getFarmId());
                                        // Sowing Broccoli Field 1 Row 1 1 kg 2022-03-03
                                    }
                                    System.out.println();
                                    // call method to create a output text file here
                                    while (true) {
                                        System.out.println();
                                        System.out.println("Do you want to continue viewing farmer's acitivity logs?");
                                        System.out.println("Yes - Please enter: 1");
                                        System.out.println("No - Please enter: -1");
                                        System.out.print("Enter the option's number: ");
                                        try {
                                            commandChosen = Integer.parseInt(scanner.next());
                                            if (commandChosen == 1) {
                                                break;
                                            } else if (commandChosen == -1) {
                                                farmerDVP = false;
                                                break;
                                            }
                                        } catch (NumberFormatException ex) {
                                            System.out.println();
                                            System.out.println(
                                                    "Invalid input, Please enter the command number that be displayed only.");
                                            System.out.println();
                                            continue;
                                        }
                                    }
                                } else {
                                    System.out.println();
                                    System.out.println(
                                            "Invalid input, Please enter the command number that be displayed only.");
                                    System.out.println();
                                    continue;
                                }
                            } catch (NumberFormatException ex) {
                                System.out.println();
                                System.out
                                        .println(
                                                "Invalid input, Please enter the command number that be displayed only.");
                                System.out.println();
                                continue;
                            }
                        }
                    } else {
                        System.out.println();
                        System.out.println("Sorry, that is no farms data");
                        System.out.println();
                    }
                } else if (commandChosen == -1) {
                    dataVisualizationProcessing = false;
                    break;
                } else {
                    System.out.println();
                    System.out.println("Invalid input, Please enter the command number that be displayed only.");
                    System.out.println();
                }
            } catch (NumberFormatException ex) {
                System.out.println();
                System.out.println("Invalid input, Please enter the command number that be displayed only.");
                System.out.println();
                continue;
            }
        }
    }
}
