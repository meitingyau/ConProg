package FarmerSimulation;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import FarmerSimulation.Stores.ActivityStore;
import FarmerSimulation.Stores.FarmStore;
import FarmerSimulation.Stores.FarmerStore;
import FarmerSimulation.Stores.FertilizerStore;
import FarmerSimulation.Stores.PesticideStore;
import FarmerSimulation.Stores.PlantStore;

public class DataVisualization {

    private static ActivityStore activityStore = new ActivityStore();
    private static FarmerStore farmerStore = new FarmerStore();;
    private static FarmStore farmStore = new FarmStore();;
    private static FertilizerStore fertilizerStore = new FertilizerStore();
    private static PesticideStore pesticideStore = new PesticideStore();
    private static PlantStore plantStore = new PlantStore();
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private static Scanner scanner = new Scanner(System.in);
    private static int write = 0;
    private static File fileObj = new File("ActivityLogs.txt");
    private static List<String> contents = new ArrayList<String>();

    public static void writting(List<String> arrayOfLine) { // function writting activity logs into text file
        java.util.Date dateTimeNow = new java.util.Date();
        try {
            if (fileObj.createNewFile()) {
                System.out.println("File created: " + fileObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            System.out.println("writting . . . . . .");
            FileWriter fileWriter = new FileWriter("ActivityLogs.txt");
            fileWriter.write("Written at: " + dateTimeNow + " \n");
            for (String line : arrayOfLine) {
                fileWriter.write(line);
            }
            fileWriter.write("- - - - - - - - End Writting - - - - - - - -\n");
            fileWriter.close();
            System.out.println("Successfully wrote Activity Logs into the file.");
        } catch (IOException e) {
            System.out.println("IO Error");
            e.printStackTrace();
        }
    }

    public static void option(String farmWithType, int commandChosen) { // function to prompt dynamic option for user to
                                                                        // visualize the logs
        System.out.println();
        System.out.println(
                "Enter the command's number to visualize the activities data:");
        System.out.println(
                "To display normal " + farmWithType + " activity logs - Please enter: " + commandChosen);
        System.out.println(
                "To display all " + farmWithType + " activity logs between two dates - Please enter: 5");
        System.out.println(
                "To display summarized " + farmWithType
                        + " activity logs between two dates with selected field and row - Please enter: 6");
        System.out.println("To quit the farm data visualization - Please enter: -1");
        System.out.print("Enter the command number: ");
    }

    public static void invalidInput() { // function to prompt invalid input error messenge to user
        System.out.println();
        System.out.println("Invalid input, Please enter the command number that be displayed only.");
        System.out.println();
    }

    public static void noData(String data) { // function to prompt no data messenge to user
        System.out.println();
        System.out.println("Sorry, that is no " + data + " data");
        System.out.println();
    }

    public static void continueViewing(String category) { // function to ask user whether want to continue the data
                                                          // visualization
        System.out.println();
        System.out.println(
                "Do you want to continue viewing " + category + " acitivity logs?");
        System.out.println("Yes - Please enter: 1");
        System.out.println("No - Please enter: -1");
        System.out.print("Enter the option's number: ");
    }

    public static void writeTextFile() { // function to ask user whether want to print logs into text file
        System.out.println();
        System.out.println("Do you want to write the Activity Logs into text file?");
        System.out.println("Yes - Please enter: 1");
        System.out.println("No - Please enter: -1");
        System.out.print("Enter the option's number: ");
    }

    // function print user logs by relative farm id, type, and a date range set by
    // user
    public static void printByFarmIdAndTypeAndDateRange(int commandChosen, int farmChosen, String farmName,
            String typeName) {
        List<Date> listOfDate = new ArrayList<Date>();
        Date startDate, endDate = new Date();
        String inputDate;
        Boolean farmWithTypeAndDateRangeDVP = true, firstTime = true;

        while (farmWithTypeAndDateRangeDVP) {
            startDate = null;
            endDate = null;
            System.out.println();
            System.out.println(
                    "Please Enter the date in format yyyy-MM-dd");
            System.out.println(
                    "For example 2000-12-21");
            System.out.println(
                    "And make sure the end date must later than the start date entered");
            System.out.println(
                    "And make sure the both of the date entered aren't out of range below: ");
            listOfDate = activityStore.findEarliestAndLatestDateByFarmIdAndType(farmChosen, typeName);
            if (listOfDate.size() > 0) {
                System.out.print("Enter the first date: ");
                try { // start checking validation of date entered by user
                    if (firstTime) { // avoid over read line
                        firstTime = false;
                        scanner.nextLine();
                    }
                    inputDate = scanner.nextLine();
                    startDate = dateFormatter.parse(inputDate);
                    if (listOfDate.get(0).compareTo(startDate) > 0 || startDate.compareTo(listOfDate.get(1)) > 0) {
                        System.out.println();
                        System.out.println("Start date is out of date range");
                        continue;
                    }
                    System.out.print("Enter the second date: ");
                    inputDate = scanner.nextLine();
                    endDate = dateFormatter.parse(inputDate);
                    if (endDate.compareTo(listOfDate.get(1)) > 0 || listOfDate.get(0).compareTo(endDate) > 0) {
                        System.out.println();
                        System.out.println("End date is out of date range");
                        continue;
                    } else if (startDate.compareTo(endDate) > 0) {
                        System.out.println();
                        System.out.println("End date must later than the start date entered");
                        continue;
                    } else if (startDate.compareTo(endDate) < 0 || startDate.compareTo(endDate) == 0) {
                        // the dates are validated
                        List<Activity> activityLogs = activityStore.findByFarmIdAndTypeAndDateRange(farmChosen,
                                typeName,
                                dateFormatter.format(startDate), dateFormatter.format(endDate));
                        if (activityLogs.size() > 0) {
                            System.out.println();
                            System.out.println("The Activity Logs of Farm "
                                    + farmName + " with " + typeName + " : ");
                            for (Activity activity : activityLogs) {
                                System.out
                                        .println(activity.getAction() + " "
                                                + activity.getType()
                                                + " Field "
                                                + activity.getField() + " Row "
                                                + activity.getRow() + " "
                                                + activity.getQuantity() + " "
                                                + activity.getUnit() + " "
                                                + activity.getDate() + " by Farmer ID: "
                                                + activity.getUserId());
                                contents.add(activity.getAction() + " "
                                        + activity.getType()
                                        + " Field "
                                        + activity.getField() + " Row "
                                        + activity.getRow() + " "
                                        + activity.getQuantity() + " "
                                        + activity.getUnit() + " "
                                        + activity.getDate() + " by Farmer ID: "
                                        + activity.getUserId()
                                        + "\n");
                            }
                            while (true) { // text file generation
                                writeTextFile();
                                try {
                                    write = Integer.parseInt(scanner.next());
                                    if (write == 1) {
                                        writting(contents);
                                        contents.clear();
                                        write = 0;
                                        break;
                                    } else if (write == -1) {
                                        write = 0;
                                        break;
                                    } else {
                                        invalidInput();
                                        continue;
                                    }
                                } catch (NumberFormatException ex) {
                                    invalidInput();
                                    continue;
                                }
                            }
                            System.out.println();
                        } else {
                            noData("Activity Logs of Farm " + farmName + " with " + typeName);
                        }
                        while (true) { // asking user want to continue or not
                            continueViewing("Farm " + farmName + " with " + typeName);
                            try {
                                commandChosen = Integer.parseInt(scanner.next());
                                if (commandChosen == 1) {
                                    firstTime = true;
                                    break;
                                } else if (commandChosen == -1) {
                                    farmWithTypeAndDateRangeDVP = false;
                                    break;
                                }
                            } catch (NumberFormatException ex) {
                                invalidInput();
                                continue;
                            }
                        }
                    }
                } catch (ParseException ex) {
                    System.out.println("Please enter the date according to the format");
                    continue;
                }
            } else {
                farmWithTypeAndDateRangeDVP = false;
                break;
            }
        }
    }

    // function print user summarized logs by relative farm id, type, and a date
    // range set by
    // user
    public static void printSummarizedLogs(int commandChosen, int farmChosen, String farmName,
            String typeName) {
        List<Date> listOfDate = new ArrayList<Date>();
        List<Integer> listOfField, listOfRow = new ArrayList<Integer>();
        Date startDate, endDate = new Date();
        String inputDate;
        Boolean summarizedLogsDVP = true, fieldDVP = true, rowDVP = true, firstTime = true;
        int fieldChosen, rowChosen;

        while (summarizedLogsDVP) {
            fieldDVP = true;
            rowDVP = true;
            startDate = null;
            endDate = null;
            System.out.println();
            System.out.println(
                    "Please Enter the date in format yyyy-MM-dd");
            System.out.println(
                    "For example 2000-12-21");
            System.out.println(
                    "And make sure the end date must later than the start date entered");
            System.out.println(
                    "And make sure the both of the date entered aren't out of range below: ");
            listOfDate = activityStore.findEarliestAndLatestDateByFarmIdAndType(farmChosen, typeName);
            if (listOfDate.size() > 0) {
                System.out.print("Enter the first date: ");
                try {
                    if (firstTime) {
                        firstTime = false;
                        scanner.nextLine();
                    }
                    inputDate = scanner.nextLine();
                    startDate = dateFormatter.parse(inputDate);
                    if (listOfDate.get(0).compareTo(startDate) > 0 || startDate.compareTo(listOfDate.get(1)) > 0) {
                        System.out.println("Start date is out of date range");
                        continue;
                    }
                    System.out.print("Enter the second date: ");
                    inputDate = scanner.nextLine();
                    endDate = dateFormatter.parse(inputDate);
                    if (endDate.compareTo(listOfDate.get(1)) > 0 || listOfDate.get(0).compareTo(endDate) > 0) {
                        System.out.println("End date is out of date range");
                        continue;
                    } else if (startDate.compareTo(endDate) > 0) {
                        System.out.println("End date must later than the start date entered");
                        continue;
                    } else if (startDate.compareTo(endDate) < 0 || startDate.compareTo(endDate) == 0) {
                        listOfField = activityStore.findFieldsByFarmIdAndTypeAndDateRange(farmChosen, typeName,
                                dateFormatter.format(startDate), dateFormatter.format(endDate));
                        if (listOfField.size() > 0) {
                            while (fieldDVP) {
                                rowDVP = true;
                                System.out.println();
                                System.out.println("The field of Farm "
                                        + farmName + " with " + typeName + " : ");
                                for (int field : listOfField) {
                                    System.out.println("Field " + field + " - Please enter: " + field);
                                }
                                System.out.print("Enter the field's number: ");
                                try {
                                    fieldChosen = Integer.parseInt(scanner.next());
                                    if (listOfField.contains(fieldChosen)) {
                                        listOfRow = activityStore.findRowsByFarmIdAndTypeAndDateRangeAndField(
                                                farmChosen,
                                                typeName,
                                                dateFormatter.format(startDate), dateFormatter.format(endDate),
                                                fieldChosen);
                                        if (listOfRow.size() > 0) {
                                            while (rowDVP) {
                                                System.out.println();
                                                System.out
                                                        .println("The row of in field " + fieldChosen + " of the Farm "
                                                                + farmName + " with " + typeName + " : ");
                                                for (int row : listOfRow) {
                                                    System.out.println("Row " + row + " - Please enter: " + row);
                                                }
                                                System.out.print("Enter the row's number: ");
                                                try {
                                                    rowChosen = Integer.parseInt(scanner.next());
                                                    if (listOfRow.contains(rowChosen)) {
                                                        contents.add(
                                                                activityStore.printSummarizedLogs(farmChosen, typeName,
                                                                        fieldChosen,
                                                                        rowChosen, dateFormatter.format(startDate),
                                                                        dateFormatter.format(endDate)));
                                                        while (true) {
                                                            writeTextFile();
                                                            try {
                                                                write = Integer.parseInt(scanner.next());
                                                                if (write == 1) {
                                                                    writting(contents);
                                                                    contents.clear();
                                                                    write = 0;
                                                                    break;
                                                                } else if (write == -1) {
                                                                    write = 0;
                                                                    break;
                                                                } else {
                                                                    invalidInput();
                                                                    continue;
                                                                }
                                                            } catch (NumberFormatException ex) {
                                                                invalidInput();
                                                                continue;
                                                            }
                                                        }
                                                        System.out.println();
                                                    } else {
                                                        invalidInput();
                                                        continue;
                                                    }
                                                    while (true) {
                                                        continueViewing(
                                                                "row of in field " + fieldChosen + " of the Farm "
                                                                        + farmName + " with " + typeName);
                                                        try {
                                                            commandChosen = Integer.parseInt(scanner.next());
                                                            if (commandChosen == 1) {
                                                                commandChosen = 6;
                                                                break;
                                                            } else if (commandChosen == -1) {
                                                                rowDVP = false;
                                                                break;
                                                            }
                                                        } catch (NumberFormatException ex) {
                                                            invalidInput();
                                                            continue;
                                                        }
                                                    }
                                                } catch (NumberFormatException ex) {
                                                    invalidInput();
                                                    continue;
                                                }
                                            }
                                        } else {
                                            noData("row in that field " + fieldChosen + "of Farm " + farmName + " with "
                                                    + typeName);
                                        }
                                    } else {
                                        invalidInput();
                                        continue;
                                    }
                                    while (true) {
                                        continueViewing("field " + fieldChosen + " of the Farm "
                                                + farmName + " with " + typeName);
                                        try {
                                            commandChosen = Integer.parseInt(scanner.next());
                                            if (commandChosen == 1) {
                                                commandChosen = 6;
                                                break;
                                            } else if (commandChosen == -1) {
                                                fieldDVP = false;
                                                break;
                                            }
                                        } catch (NumberFormatException ex) {
                                            invalidInput();
                                            continue;
                                        }
                                    }
                                } catch (NumberFormatException ex) {
                                    invalidInput();
                                    continue;
                                }
                            }
                        } else {
                            noData("field in Farm " + farmName + " with " + typeName);
                        }
                        while (true) {
                            continueViewing("Farm " + farmName + " with " + typeName);
                            try {
                                commandChosen = Integer.parseInt(scanner.next());
                                if (commandChosen == 1) {
                                    firstTime = true;
                                    break;
                                } else if (commandChosen == -1) {
                                    summarizedLogsDVP = false;
                                    break;
                                }
                            } catch (NumberFormatException ex) {
                                invalidInput();
                                continue;
                            }
                        }
                    }
                } catch (ParseException ex) {
                    System.out.println("Please enter the date according to the format");
                    continue;
                }
            } else {
                summarizedLogsDVP = false;
                break;
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        boolean dataVisualizationProcessing = true, farmerDVP = true,
                farmDVP = true, farmOnlyDVP = true, farmPlantDVP = true;
        int commandChosen, farmerChosen, farmChosen, plantChosen, fertilizerChosen, pesticideChosen;
        while (dataVisualizationProcessing) {
            farmDVP = true;
            farmOnlyDVP = true;
            farmPlantDVP = true;
            farmerDVP = true;
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
                        farmOnlyDVP = true;
                        farmPlantDVP = true;
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
                                HashMap<String, String> farms = farmStore.getFarms();
                                if (farms.size() > 0) {
                                    while (farmOnlyDVP) {
                                        farmPlantDVP = true;
                                        System.out.println();
                                        System.out.println(
                                                "Please Enter the respective farm's number to see farm's activity logs");
                                        for (Entry<String, String> entry : farms.entrySet()) {
                                            System.out.println(entry.getValue() + " - Please Enter: " + entry.getKey());
                                        }
                                        System.out.print("Enter the farm's number: ");
                                        try {
                                            farmChosen = Integer.parseInt(scanner.next());
                                            if (farms.containsKey(String.valueOf(farmChosen))) {
                                                if (commandChosen == 0) { // display all activity logs for a target farm
                                                                          // only
                                                    List<Activity> activityLogs = activityStore
                                                            .findByFarmId(farmChosen);
                                                    if (activityLogs.size() > 0) {
                                                        System.out.println();
                                                        System.out.println("The Activity Logs of Farm "
                                                                + farms.get(String.valueOf(farmChosen)) + " : ");
                                                        for (Activity activity : activityLogs) {
                                                            System.out
                                                                    .println(activity.getAction() + " "
                                                                            + activity.getType()
                                                                            + " Field "
                                                                            + activity.getField() + " Row "
                                                                            + activity.getRow() + " "
                                                                            + activity.getQuantity() + " "
                                                                            + activity.getUnit() + " "
                                                                            + activity.getDate() + " by Farmer ID: "
                                                                            + activity.getUserId());
                                                            contents.add(activity.getAction() + " "
                                                                    + activity.getType()
                                                                    + " Field "
                                                                    + activity.getField() + " Row "
                                                                    + activity.getRow() + " "
                                                                    + activity.getQuantity() + " "
                                                                    + activity.getUnit() + " "
                                                                    + activity.getDate() + " by Farmer ID: "
                                                                    + activity.getUserId()
                                                                    + "\n");
                                                        }
                                                        while (true) {
                                                            writeTextFile();
                                                            try {
                                                                write = Integer.parseInt(scanner.next());
                                                                if (write == 1) {
                                                                    writting(contents);
                                                                    contents.clear();
                                                                    write = 0;
                                                                    break;
                                                                } else if (write == -1) {
                                                                    write = 0;
                                                                    break;
                                                                } else {
                                                                    invalidInput();
                                                                    continue;
                                                                }
                                                            } catch (NumberFormatException ex) {
                                                                invalidInput();
                                                                continue;
                                                            }
                                                        }
                                                        System.out.println();
                                                    } else {
                                                        noData("Activity Logs of Farm "
                                                                + farms.get(String.valueOf(farmChosen)));
                                                    }
                                                    while (true) {
                                                        continueViewing("farm's");
                                                        try {
                                                            commandChosen = Integer.parseInt(scanner.next());
                                                            if (commandChosen == 1) {
                                                                commandChosen = 0;
                                                                break;
                                                            } else if (commandChosen == -1) {
                                                                commandChosen = 0;
                                                                farmOnlyDVP = false;
                                                                break;
                                                            }
                                                        } catch (NumberFormatException ex) {
                                                            invalidInput();
                                                            continue;
                                                        }
                                                    }
                                                } else if (commandChosen == 2) {
                                                    option("farm with plant", commandChosen);
                                                    try { // copy from here
                                                        commandChosen = Integer.parseInt(scanner.next());
                                                        if (commandChosen == 2 || commandChosen == 5
                                                                || commandChosen == 6) {
                                                            HashMap<String, String> plants = plantStore
                                                                    .findByFarmId(farmChosen);
                                                            if (plants.size() > 0) {
                                                                while (farmPlantDVP) {
                                                                    System.out.println();
                                                                    System.out.println(
                                                                            "Please Enter the respective plant's number to see the activity logs");
                                                                    for (Entry<String, String> entry : plants
                                                                            .entrySet()) {
                                                                        System.out.println(entry.getValue()
                                                                                + " - Please Enter: " + entry.getKey());
                                                                    }
                                                                    System.out.print("Enter the plant's number: ");
                                                                    try {
                                                                        plantChosen = Integer.parseInt(scanner.next());
                                                                        if (plants.containsKey(
                                                                                String.valueOf(plantChosen))) {
                                                                            if (commandChosen == 2) {
                                                                                List<Activity> activityLogs = activityStore
                                                                                        .findByFarmIdAndPlantId(
                                                                                                farmChosen,
                                                                                                plantChosen);
                                                                                if (activityLogs.size() > 0) {
                                                                                    System.out.println();
                                                                                    System.out
                                                                                            .println(
                                                                                                    "The Activity Logs of Farm "
                                                                                                            + farms.get(
                                                                                                                    String
                                                                                                                            .valueOf(
                                                                                                                                    farmChosen))
                                                                                                            + " with "
                                                                                                            + plants.get(
                                                                                                                    String
                                                                                                                            .valueOf(
                                                                                                                                    plantChosen))
                                                                                                            + " : ");
                                                                                    for (Activity activity : activityLogs) {
                                                                                        System.out.println(activity
                                                                                                .getAction()
                                                                                                + " "
                                                                                                + activity.getType()
                                                                                                + " Field "
                                                                                                + activity.getField()
                                                                                                + " Row "
                                                                                                + activity.getRow()
                                                                                                + " "
                                                                                                + activity.getQuantity()
                                                                                                + " "
                                                                                                + activity.getUnit()
                                                                                                + " "
                                                                                                + activity.getDate());
                                                                                        contents.add(activity
                                                                                                .getAction()
                                                                                                + " "
                                                                                                + activity.getType()
                                                                                                + " Field "
                                                                                                + activity.getField()
                                                                                                + " Row "
                                                                                                + activity.getRow()
                                                                                                + " "
                                                                                                + activity.getQuantity()
                                                                                                + " "
                                                                                                + activity.getUnit()
                                                                                                + " "
                                                                                                + activity.getDate()
                                                                                                + "\n");
                                                                                    }
                                                                                    while (true) {
                                                                                        writeTextFile();
                                                                                        try {
                                                                                            write = Integer.parseInt(
                                                                                                    scanner.next());
                                                                                            if (write == 1) {
                                                                                                writting(contents);
                                                                                                contents.clear();
                                                                                                write = 0;
                                                                                                break;
                                                                                            } else if (write == -1) {
                                                                                                write = 0;
                                                                                                break;
                                                                                            } else {
                                                                                                invalidInput();
                                                                                                continue;
                                                                                            }
                                                                                        } catch (NumberFormatException ex) {
                                                                                            invalidInput();
                                                                                            continue;
                                                                                        }
                                                                                    }
                                                                                    System.out.println();
                                                                                } else {
                                                                                    noData("Activity Logs of Farm "
                                                                                            + farms.get(String
                                                                                                    .valueOf(
                                                                                                            farmChosen))
                                                                                            + " with "
                                                                                            + plants.get(
                                                                                                    String
                                                                                                            .valueOf(
                                                                                                                    plantChosen)));
                                                                                }
                                                                                while (true) {
                                                                                    continueViewing("farm with plant");
                                                                                    try {
                                                                                        commandChosen = Integer
                                                                                                .parseInt(
                                                                                                        scanner.next());
                                                                                        if (commandChosen == 1) {
                                                                                            commandChosen = 2;
                                                                                            break;
                                                                                        } else if (commandChosen == -1) {
                                                                                            commandChosen = 2;
                                                                                            farmPlantDVP = false;
                                                                                            break;
                                                                                        }
                                                                                    } catch (NumberFormatException ex) {
                                                                                        invalidInput();
                                                                                        continue;
                                                                                    }
                                                                                }
                                                                            } else if (commandChosen == 5) { // display
                                                                                                             // all " +
                                                                                                             // farmWithType
                                                                                                             // + "
                                                                                                             // activity
                                                                                                             // logs
                                                                                                             // between
                                                                                                             // two
                                                                                                             // dates
                                                                                printByFarmIdAndTypeAndDateRange(
                                                                                        commandChosen, farmChosen,
                                                                                        farms.get(
                                                                                                String.valueOf(
                                                                                                        farmChosen)),
                                                                                        plants.get(String
                                                                                                .valueOf(plantChosen)));
                                                                                commandChosen = 2;
                                                                                farmPlantDVP = false;
                                                                            } else if (commandChosen == 6) { // "To
                                                                                                             // display
                                                                                                             // summarized
                                                                                                             // logs
                                                                                printSummarizedLogs(commandChosen,
                                                                                        farmChosen, farms.get(
                                                                                                String.valueOf(
                                                                                                        farmChosen)),
                                                                                        plants.get(String
                                                                                                .valueOf(plantChosen)));
                                                                                commandChosen = 2;
                                                                                farmPlantDVP = false;
                                                                            }
                                                                        } else {
                                                                            invalidInput();
                                                                            continue;
                                                                        }
                                                                    } catch (NumberFormatException ex) {
                                                                        invalidInput();
                                                                        continue;
                                                                    }
                                                                }
                                                            } else {
                                                                noData("plants");
                                                            }
                                                        } else if (commandChosen == -1) {
                                                            commandChosen = 2;
                                                            continue;
                                                        } else {
                                                            invalidInput();
                                                            continue;
                                                        }
                                                    } catch (NumberFormatException ex) { // copy to here
                                                        invalidInput();
                                                        continue;
                                                    }
                                                } else if (commandChosen == 3) { // display all activity logs for a
                                                                                 // target farm with fertilizer
                                                    option("farm with fertilizer", commandChosen);
                                                    try {
                                                        commandChosen = Integer.parseInt(scanner.next());
                                                        if (commandChosen == 3 || commandChosen == 5
                                                                || commandChosen == 6) {
                                                            HashMap<String, String> fertilizers = fertilizerStore
                                                                    .findByFarmId(farmChosen);
                                                            if (fertilizers.size() > 0) {
                                                                while (farmPlantDVP) {
                                                                    System.out.println();
                                                                    System.out.println(
                                                                            "Please Enter the respective fertilizer's number to see the activity logs");
                                                                    for (Entry<String, String> entry : fertilizers
                                                                            .entrySet()) {
                                                                        System.out.println(entry.getValue()
                                                                                + " - Please Enter: " + entry.getKey());
                                                                    }
                                                                    System.out.print("Enter the fertilizer's number: ");
                                                                    try {
                                                                        fertilizerChosen = Integer
                                                                                .parseInt(scanner.next());
                                                                        if (fertilizers.containsKey(
                                                                                String.valueOf(fertilizerChosen))) {
                                                                            if (commandChosen == 3) { // display all
                                                                                                      // activity logs
                                                                                                      // for a target
                                                                                                      // farm with
                                                                                                      // fertilizer
                                                                                List<Activity> activityLogs = activityStore
                                                                                        .findByFarmIdAndPlantId(
                                                                                                farmChosen,
                                                                                                fertilizerChosen);
                                                                                if (activityLogs.size() > 0) {
                                                                                    System.out.println();
                                                                                    System.out
                                                                                            .println(
                                                                                                    "The Activity Logs of Farm "
                                                                                                            + farms.get(
                                                                                                                    String
                                                                                                                            .valueOf(
                                                                                                                                    farmChosen))
                                                                                                            + " with "
                                                                                                            + fertilizers
                                                                                                                    .get(
                                                                                                                            String
                                                                                                                                    .valueOf(
                                                                                                                                            fertilizerChosen))
                                                                                                            + " : ");
                                                                                    for (Activity activity : activityLogs) {
                                                                                        System.out.println(activity
                                                                                                .getAction()
                                                                                                + " "
                                                                                                + activity.getType()
                                                                                                + " Field "
                                                                                                + activity.getField()
                                                                                                + " Row "
                                                                                                + activity.getRow()
                                                                                                + " "
                                                                                                + activity.getQuantity()
                                                                                                + " "
                                                                                                + activity.getUnit()
                                                                                                + " "
                                                                                                + activity.getDate());
                                                                                        contents.add(activity
                                                                                                .getAction()
                                                                                                + " "
                                                                                                + activity.getType()
                                                                                                + " Field "
                                                                                                + activity.getField()
                                                                                                + " Row "
                                                                                                + activity.getRow()
                                                                                                + " "
                                                                                                + activity.getQuantity()
                                                                                                + " "
                                                                                                + activity.getUnit()
                                                                                                + " "
                                                                                                + activity.getDate()
                                                                                                + "\n");
                                                                                    }
                                                                                    while (true) {
                                                                                        writeTextFile();
                                                                                        try {
                                                                                            write = Integer.parseInt(
                                                                                                    scanner.next());
                                                                                            if (write == 1) {
                                                                                                writting(contents);
                                                                                                contents.clear();
                                                                                                write = 0;
                                                                                                break;
                                                                                            } else if (write == -1) {
                                                                                                write = 0;
                                                                                                break;
                                                                                            } else {
                                                                                                invalidInput();
                                                                                                continue;
                                                                                            }
                                                                                        } catch (NumberFormatException ex) {
                                                                                            invalidInput();
                                                                                            continue;
                                                                                        }
                                                                                    }
                                                                                    System.out.println();
                                                                                } else {
                                                                                    noData("Activity Logs of Farm "
                                                                                            + farms.get(String
                                                                                                    .valueOf(
                                                                                                            farmChosen))
                                                                                            + " with "
                                                                                            + fertilizers.get(
                                                                                                    String
                                                                                                            .valueOf(
                                                                                                                    fertilizerChosen)));
                                                                                }
                                                                                while (true) {
                                                                                    continueViewing(
                                                                                            "farm with fertilizer");
                                                                                    try {
                                                                                        commandChosen = Integer
                                                                                                .parseInt(
                                                                                                        scanner.next());
                                                                                        if (commandChosen == 1) {
                                                                                            commandChosen = 3;
                                                                                            break;
                                                                                        } else if (commandChosen == -1) {
                                                                                            commandChosen = 3;
                                                                                            farmPlantDVP = false;
                                                                                            break;
                                                                                        }
                                                                                    } catch (NumberFormatException ex) {
                                                                                        invalidInput();
                                                                                        continue;
                                                                                    }
                                                                                }
                                                                            } else if (commandChosen == 5) { // display
                                                                                                             // all " +
                                                                                                             // farmWithType
                                                                                                             // + "
                                                                                                             // activity
                                                                                                             // logs
                                                                                                             // between
                                                                                                             // two
                                                                                                             // dates
                                                                                printByFarmIdAndTypeAndDateRange(
                                                                                        commandChosen, farmChosen,
                                                                                        farms.get(
                                                                                                String.valueOf(
                                                                                                        farmChosen)),
                                                                                        fertilizers.get(String
                                                                                                .valueOf(
                                                                                                        fertilizerChosen)));
                                                                                commandChosen = 3;
                                                                                farmPlantDVP = false;
                                                                            } else if (commandChosen == 6) { // "To
                                                                                                             // display
                                                                                                             // summarized
                                                                                                             // logs
                                                                                printSummarizedLogs(commandChosen,
                                                                                        farmChosen, farms.get(
                                                                                                String.valueOf(
                                                                                                        farmChosen)),
                                                                                        fertilizers.get(String
                                                                                                .valueOf(
                                                                                                        fertilizerChosen)));
                                                                                commandChosen = 3;
                                                                                farmPlantDVP = false;
                                                                            }
                                                                        } else {
                                                                            invalidInput();
                                                                            continue;
                                                                        }
                                                                    } catch (NumberFormatException ex) {
                                                                        invalidInput();
                                                                        continue;
                                                                    }
                                                                }
                                                            } else {
                                                                noData("fertilizers");
                                                            }
                                                        } else if (commandChosen == -1) {
                                                            commandChosen = 3;
                                                            continue;
                                                        } else {
                                                            invalidInput();
                                                            continue;
                                                        }
                                                    } catch (NumberFormatException ex) {
                                                        invalidInput();
                                                        continue;
                                                    }
                                                } else if (commandChosen == 4) { // display all activity logs for a
                                                                                 // target farm with pesticide
                                                    option("farm with pesticide", commandChosen);
                                                    try {
                                                        commandChosen = Integer.parseInt(scanner.next());
                                                        if (commandChosen == 4 || commandChosen == 5
                                                                || commandChosen == 6) {
                                                            HashMap<String, String> pesticides = pesticideStore
                                                                    .findByFarmId(farmChosen);
                                                            if (pesticides.size() > 0) {
                                                                while (farmPlantDVP) {
                                                                    System.out.println();
                                                                    System.out.println(
                                                                            "Please Enter the respective pesticide's number to see the activity logs");
                                                                    for (Entry<String, String> entry : pesticides
                                                                            .entrySet()) {
                                                                        System.out.println(entry.getValue()
                                                                                + " - Please Enter: " + entry.getKey());
                                                                    }
                                                                    System.out.print("Enter the plant's number: ");
                                                                    try {
                                                                        pesticideChosen = Integer
                                                                                .parseInt(scanner.next());
                                                                        if (pesticides.containsKey(
                                                                                String.valueOf(pesticideChosen))) {
                                                                            if (commandChosen == 4) { // display all
                                                                                                      // activity logs
                                                                                                      // for a target
                                                                                                      // farm with
                                                                                                      // pesticide
                                                                                List<Activity> activityLogs = activityStore
                                                                                        .findByFarmIdAndPlantId(
                                                                                                farmChosen,
                                                                                                pesticideChosen);
                                                                                if (activityLogs.size() > 0) {
                                                                                    System.out.println();
                                                                                    System.out
                                                                                            .println(
                                                                                                    "The Activity Logs of Farm "
                                                                                                            + farms.get(
                                                                                                                    String
                                                                                                                            .valueOf(
                                                                                                                                    farmChosen))
                                                                                                            + " with "
                                                                                                            + pesticides
                                                                                                                    .get(
                                                                                                                            String
                                                                                                                                    .valueOf(
                                                                                                                                            pesticideChosen))
                                                                                                            + " : ");
                                                                                    for (Activity activity : activityLogs) {
                                                                                        System.out.println(activity
                                                                                                .getAction()
                                                                                                + " "
                                                                                                + activity.getType()
                                                                                                + " Field "
                                                                                                + activity.getField()
                                                                                                + " Row "
                                                                                                + activity.getRow()
                                                                                                + " "
                                                                                                + activity.getQuantity()
                                                                                                + " "
                                                                                                + activity.getUnit()
                                                                                                + " "
                                                                                                + activity.getDate());
                                                                                        contents.add(activity
                                                                                                .getAction()
                                                                                                + " "
                                                                                                + activity.getType()
                                                                                                + " Field "
                                                                                                + activity.getField()
                                                                                                + " Row "
                                                                                                + activity.getRow()
                                                                                                + " "
                                                                                                + activity.getQuantity()
                                                                                                + " "
                                                                                                + activity.getUnit()
                                                                                                + " "
                                                                                                + activity.getDate()
                                                                                                + "\n");
                                                                                    }
                                                                                    while (true) {
                                                                                        writeTextFile();
                                                                                        try {
                                                                                            write = Integer.parseInt(
                                                                                                    scanner.next());
                                                                                            if (write == 1) {
                                                                                                writting(contents);
                                                                                                contents.clear();
                                                                                                write = 0;
                                                                                                break;
                                                                                            } else if (write == -1) {
                                                                                                write = 0;
                                                                                                break;
                                                                                            } else {
                                                                                                invalidInput();
                                                                                                continue;
                                                                                            }
                                                                                        } catch (NumberFormatException ex) {
                                                                                            invalidInput();
                                                                                            continue;
                                                                                        }
                                                                                    }
                                                                                    System.out.println();
                                                                                } else {
                                                                                    noData("Activity Logs of Farm "
                                                                                            + farms.get(String
                                                                                                    .valueOf(
                                                                                                            farmChosen))
                                                                                            + " with "
                                                                                            + pesticides.get(
                                                                                                    String
                                                                                                            .valueOf(
                                                                                                                    pesticideChosen)));
                                                                                }
                                                                                while (true) {
                                                                                    continueViewing(
                                                                                            "farm with pesticide");
                                                                                    try {
                                                                                        commandChosen = Integer
                                                                                                .parseInt(
                                                                                                        scanner.next());
                                                                                        if (commandChosen == 1) {
                                                                                            commandChosen = 4;
                                                                                            break;
                                                                                        } else if (commandChosen == -1) {
                                                                                            commandChosen = 4;
                                                                                            farmPlantDVP = false;
                                                                                            break;
                                                                                        }
                                                                                    } catch (NumberFormatException ex) {
                                                                                        invalidInput();
                                                                                        continue;
                                                                                    }
                                                                                }
                                                                            } else if (commandChosen == 5) { // display
                                                                                                             // all " +
                                                                                                             // farmWithType
                                                                                                             // + "
                                                                                                             // activity
                                                                                                             // logs
                                                                                                             // between
                                                                                                             // two
                                                                                                             // dates
                                                                                printByFarmIdAndTypeAndDateRange(
                                                                                        commandChosen, farmChosen,
                                                                                        farms.get(
                                                                                                String.valueOf(
                                                                                                        farmChosen)),
                                                                                        pesticides.get(String
                                                                                                .valueOf(
                                                                                                        pesticideChosen)));
                                                                                commandChosen = 4;
                                                                                farmPlantDVP = false;
                                                                            } else if (commandChosen == 6) { // "To
                                                                                                             // display
                                                                                                             // summarized
                                                                                                             // logs
                                                                                printSummarizedLogs(commandChosen,
                                                                                        farmChosen, farms.get(
                                                                                                String.valueOf(
                                                                                                        farmChosen)),
                                                                                        pesticides.get(String
                                                                                                .valueOf(
                                                                                                        pesticideChosen)));
                                                                                commandChosen = 4;
                                                                                farmPlantDVP = false;
                                                                            }
                                                                        } else {
                                                                            invalidInput();
                                                                            continue;
                                                                        }
                                                                    } catch (NumberFormatException ex) {
                                                                        invalidInput();
                                                                        continue;
                                                                    }
                                                                }
                                                            } else {
                                                                noData("pesticides");
                                                            }
                                                        } else if (commandChosen == -1) {
                                                            commandChosen = 4;
                                                            continue;
                                                        } else {
                                                            invalidInput();
                                                            continue;
                                                        }
                                                    } catch (NumberFormatException ex) {
                                                        invalidInput();
                                                        continue;
                                                    }
                                                } else {
                                                    invalidInput();
                                                    continue;
                                                }
                                            } else {
                                                invalidInput();
                                                continue;
                                            }
                                        } catch (NumberFormatException ex) {
                                            invalidInput();
                                            continue;
                                        }
                                    }
                                } else {
                                    noData("farms");
                                }
                            } else if (commandChosen == -1) {
                                farmDVP = false;
                                break;
                            }
                        } catch (NumberFormatException ex) {
                            invalidInput();
                            continue;
                        }
                    }
                } else if (commandChosen == 1) { // display all activity logs for a target farmer
                    farmerChosen = -1;
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
                                if (farmers.containsKey(String.valueOf(farmerChosen))) {
                                    List<Activity> activityLogs = activityStore.findByUserId(farmerChosen);
                                    if (activityLogs.size() > 0) {
                                        System.out.println();
                                        System.out.println("The Activity Logs of Farmer "
                                                + farmers.get(String.valueOf(farmerChosen)) + " : ");
                                        for (Activity activity : activityLogs) {
                                            System.out.println(activity.getAction() + " " + activity.getType()
                                                    + " Field "
                                                    + activity.getField() + " Row " + activity.getRow() + " "
                                                    + activity.getQuantity() + " " + activity.getUnit() + " "
                                                    + activity.getDate() + " in Farm ID: " + activity.getFarmId());
                                            contents.add(activity.getAction() + " " + activity.getType()
                                                    + " Field "
                                                    + activity.getField() + " Row " + activity.getRow() + " "
                                                    + activity.getQuantity() + " " + activity.getUnit() + " "
                                                    + activity.getDate() + " in Farm ID: " + activity.getFarmId()
                                                    + "\n");
                                        }
                                        while (true) {
                                            writeTextFile();
                                            try {
                                                write = Integer.parseInt(scanner.next());
                                                if (write == 1) {
                                                    writting(contents);
                                                    contents.clear();
                                                    write = 0;
                                                    break;
                                                } else if (write == -1) {
                                                    write = 0;
                                                    break;
                                                } else {
                                                    invalidInput();
                                                    continue;
                                                }
                                            } catch (NumberFormatException ex) {
                                                invalidInput();
                                                continue;
                                            }
                                        }
                                        System.out.println();
                                    } else {
                                        noData("Activity Logs of Farmer "
                                                + farmers.get(String.valueOf(farmerChosen)));
                                    }
                                    while (true) {
                                        continueViewing("farmer's");
                                        try {
                                            commandChosen = Integer.parseInt(scanner.next());
                                            if (commandChosen == 1) {
                                                break;
                                            } else if (commandChosen == -1) {
                                                farmerDVP = false;
                                                break;
                                            }
                                        } catch (NumberFormatException ex) {
                                            invalidInput();
                                            continue;
                                        }
                                    }
                                } else {
                                    invalidInput();
                                    continue;
                                }
                            } catch (NumberFormatException ex) {
                                invalidInput();
                                continue;
                            }
                        }
                    } else {
                        noData("farms");
                    }
                } else if (commandChosen == -1) {
                    dataVisualizationProcessing = false;
                    break;
                } else {
                    invalidInput();
                }
            } catch (NumberFormatException ex) {
                invalidInput();
                continue;
            }
        }
    }
}
