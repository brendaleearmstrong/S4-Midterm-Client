package com.misight.client.ui;

import com.misight.client.service.setup.*;
import com.misight.client.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;
import java.util.List;

@Component
public class AdminMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService;
    private final MinesService minesService;
    private final MineralsService mineralsService;
    private final ProvincesService provincesService;

    @Autowired
    public AdminMenu(UserService userService, MinesService minesService,
                     MineralsService mineralsService, ProvincesService provincesService) {
        this.userService = userService;
        this.minesService = minesService;
        this.mineralsService = mineralsService;
        this.provincesService = provincesService;
    }

    public void show() {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. User Management");
            System.out.println("2. Mine Management");
            System.out.println("3. Mineral Management");
            System.out.println("4. Province Management");
            System.out.println("5. System Reports");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    userManagement();
                    break;
                case "2":
                    mineManagement();
                    break;
                case "3":
                    mineralManagement();
                    break;
                case "4":
                    provinceManagement();
                    break;
                case "5":
                    systemReports();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void userManagement() {
        while (true) {
            System.out.println("\n=== User Management ===");
            System.out.println("1. List all users");
            System.out.println("2. Add new user");
            System.out.println("3. Update user");
            System.out.println("4. Delete user");
            System.out.println("0. Back");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    listUsers();
                    break;
                case "2":
                    addUser();
                    break;
                case "3":
                    updateUser();
                    break;
                case "4":
                    deleteUser();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void listUsers() {
        try {
            List<User> users = userService.getAllUsers();
            System.out.println("\n=== User List ===");
            for (User user : users) {
                System.out.println("ID: " + user.getId());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Privileges: " + user.getPrivileges());
                System.out.println("-----------------");
            }
        } catch (Exception e) {
            System.out.println("Error fetching users: " + e.getMessage());
        }
    }

    private void addUser() {
        try {
            System.out.println("\n=== Add New User ===");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            User newUser = new User(username, password);
            User created = userService.createUser(newUser);
            System.out.println("User created successfully with ID: " + created.getId());
        } catch (Exception e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    private void updateUser() {
        try {
            System.out.println("\n=== Update User ===");
            System.out.print("Enter user ID: ");
            Long id = Long.parseLong(scanner.nextLine());
            System.out.print("Enter new username: ");
            String username = scanner.nextLine();
            System.out.print("Enter new password: ");
            String password = scanner.nextLine();

            User updatedUser = new User(username, password);
            updatedUser.setId(id);
            userService.updateUser(id, updatedUser);
            System.out.println("User updated successfully");
        } catch (Exception e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    private void deleteUser() {
        try {
            System.out.println("\n=== Delete User ===");
            System.out.print("Enter user ID: ");
            Long id = Long.parseLong(scanner.nextLine());
            userService.deleteUser(id);
            System.out.println("User deleted successfully");
        } catch (Exception e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }

private void reportsMenu() {
    while (true) {
        System.out.println("\n=== Reports Generation ===");
        System.out.println("1. Environmental Reports");
        System.out.println("2. Safety Reports");
        System.out.println("3. Compliance Reports");
        System.out.println("4. Export Reports");
        System.out.println("0. Back");
        System.out.print("Select an option: ");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                generateEnvironmentalReport();
                break;
            case "2":
                generateSafetyReport();
                break;
            case "3":
                generateComplianceReport();
                break;
            case "4":
                exportReports();
                break;
            case "0":
                return;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}

private void viewEnvironmentalData() {
    try {
        System.out.print("Enter Mine ID: ");
        Long mineId = Long.parseLong(scanner.nextLine());
        List<EnvironmentalData> data = environmentalDataService.getDataByMine(mineId);
        System.out.println("\n=== Environmental Data ===");
        for (EnvironmentalData reading : data) {
            System.out.println("ID: " + reading.getId());
            System.out.println("Date: " + reading.getMeasurementDate());
            System.out.println("Pollutant: " + reading.getPollutant().getName());
            System.out.println("Value: " + reading.getMeasuredValue());
            System.out.println("-------------------");
        }
    } catch (Exception e) {
        System.out.println("Error retrieving data: " + e.getMessage());
    }
}

private void addEnvironmentalReading() {
    try {
        EnvironmentalData newData = new EnvironmentalData();
        System.out.print("Enter Mine ID: ");
        Long mineId = Long.parseLong(scanner.nextLine());
        System.out.print("Enter Pollutant ID: ");
        Long pollutantId = Long.parseLong(scanner.nextLine());
        System.out.print("Enter Measured Value: ");
        Double value = Double.parseDouble(scanner.nextLine());

        // Set the values
        newData.setMeasurementDate(LocalDateTime.now());
        newData.setMeasuredValue(value);
        // Additional setters would be called here

        EnvironmentalData created = environmentalDataService.createData(newData);
        System.out.println("Reading added successfully with ID: " + created.getId());
    } catch (Exception e) {
        System.out.println("Error adding reading: " + e.getMessage());
    }
}

private void viewSafetyRecords() {
    try {
        System.out.print("Enter Mine ID: ");
        Long mineId = Long.parseLong(scanner.nextLine());
        List<SafetyData> records = safetyDataService.getDataByMine(mineId);
        System.out.println("\n=== Safety Records ===");
        for (SafetyData record : records) {
            System.out.println("ID: " + record.getId());
            System.out.println("Date: " + record.getDateRecorded());
            System.out.println("Lost Time Incidents: " + record.getLostTimeIncidents());
            System.out.println("Near Misses: " + record.getNearMisses());
            System.out.println("Safety Level: " + record.getSafetyLevel());
            System.out.println("-------------------");
        }
    } catch (Exception e) {
        System.out.println("Error retrieving records: " + e.getMessage());
    }
}

private void recordIncident() {
    try {
        SafetyData newIncident = new SafetyData();
        System.out.print("Enter Mine ID: ");
        Long mineId = Long.parseLong(scanner.nextLine());
        System.out.print("Enter Number of Lost Time Incidents: ");
        int lostTime = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Number of Near Misses: ");
        int nearMisses = Integer.parseInt(scanner.nextLine());

        newIncident.setDateRecorded(LocalDate.now());
        newIncident.setLostTimeIncidents(lostTime);
        newIncident.setNearMisses(nearMisses);
        // Additional setters would be called here

        SafetyData created = safetyDataService.createData(newIncident);
        System.out.println("Incident recorded successfully with ID: " + created.getId());
    } catch (Exception e) {
        System.out.println("Error recording incident: " + e.getMessage());
    }
}

private void generateEnvironmentalReport() {
    try {
        System.out.print("Enter Mine ID: ");
        Long mineId = Long.parseLong(scanner.nextLine());
        System.out.print("Enter Year: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Month (1-12): ");
        int month = Integer.parseInt(scanner.nextLine());

        var report = environmentalReportService.generateMonthlyReport(mineId, year, month);
        System.out.println("\n=== Environmental Report ===");
        System.out.println(report);
    } catch (Exception e) {
        System.out.println("Error generating report: " + e.getMessage());
    }
}

private void viewAlerts() {
    try {
        System.out.print("Enter Mine ID: ");
        Long mineId = Long.parseLong(scanner.nextLine());
        List<EnvironmentalData> alerts = environmentalDataService.getAlerts(mineId);
        System.out.println("\n=== Active Alerts ===");
        for (EnvironmentalData alert : alerts) {
            System.out.println("Alert ID: " + alert.getId());
            System.out.println("Time: " + alert.getMeasurementDate());
            System.out.println("Issue: " + alert.getNotes());
            System.out.println("-------------------");
        }
    } catch (Exception e) {
        System.out.println("Error retrieving alerts: " + e.getMessage());
    }
}
}
