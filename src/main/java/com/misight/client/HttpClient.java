package com.misight.client;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

import com.misight.client.model.EnvironmentalData;
import com.misight.client.model.Minerals;
import com.misight.client.model.Mines;
import com.misight.client.model.MonitoringStations;
import com.misight.client.model.Pollutants;
import com.misight.client.model.Provinces;
import com.misight.client.model.SafetyData;
import com.misight.client.model.SafetyData.SafetyLevel;
import com.misight.client.model.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

public class HttpClient {
    private final String BASE_URL = "http://localhost:8080/api";
    private final RestTemplate restTemplate = new RestTemplate();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        HttpClient client = new HttpClient();
        client.mainMenu();
    }

    private void mainMenu() {
        while (true) {
            System.out.println("\n════════════════ MISIGHT SYSTEM LOGIN ════════════════");
            System.out.println("Select your role:");
            System.out.println("1. System Administrator");
            System.out.println("2. Mine Administrator");
            System.out.println("3. Community Stakeholder");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> systemAdminMenu();
                case 2 -> mineAdminMenu();
                case 3 -> communityStakeholderMenu();
                case 4 -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void systemAdminMenu() {
        while (true) {
            System.out.println("\n══════════ SYSTEM ADMINISTRATOR DASHBOARD ══════════");
            System.out.println("1. Manage Users");
            System.out.println("2. Manage Mines & Minerals");
            System.out.println("3. Manage Pollutants");
            System.out.println("4. Manage Monitoring Stations");
            System.out.println("5. Manage Provinces");
            System.out.println("6. Log Out");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> userManagementMenu();
                case 2 -> minesAndMineralsManagementMenu();
                case 3 -> pollutantsManagementMenu();
                case 4 -> monitoringStationsMenu();
                case 5 -> provincesMenu();
                case 6 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void mineAdminMenu() {
        while (true) {
            System.out.println("\n══════════ MINE ADMINISTRATOR DASHBOARD ══════════");
            System.out.println("1. Manage Environmental Data");
            System.out.println("2. Manage Safety Reports");
            System.out.println("3. View Mine Details");
            System.out.println("4. View Mine Minerals");
            System.out.println("5. Log Out");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> environmentalDataMenu();
                case 2 -> safetyReportsMenu();
                case 3 -> viewAllMines();
                case 4 -> viewMineMinerals();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void communityStakeholderMenu() {
        while (true) {
            System.out.println("\n══════════ COMMUNITY STAKEHOLDER DASHBOARD ══════════");
            System.out.println("1. View Environmental Data");
            System.out.println("2. View Safety Reports");
            System.out.println("3. View Mine Information");
            System.out.println("4. View Mine Minerals");
            System.out.println("5. Log Out");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> viewAllEnvironmentalData();
                case 2 -> viewAllSafetyReports();
                case 3 -> viewAllMines();
                case 4 -> viewMineMinerals();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void userManagementMenu() {
        while (true) {
            System.out.println("\n══════════ USER MANAGEMENT ══════════");
            System.out.println("1. Add New User");
            System.out.println("2. Update Existing User");
            System.out.println("3. Delete User");
            System.out.println("4. View All Users");
            System.out.println("5. Return to Dashboard");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addUser();
                case 2 -> updateUser();
                case 3 -> deleteUser();
                case 4 -> viewAllUsers();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void minesAndMineralsManagementMenu() {
        while (true) {
            System.out.println("\n══════════ MINES & MINERALS MANAGEMENT ══════════");
            System.out.println("1. Add New Mine");
            System.out.println("2. Update Existing Mine");
            System.out.println("3. Delete Mine");
            System.out.println("4. View All Mines");
            System.out.println("5. Add New Mineral");
            System.out.println("6. Update Existing Mineral");
            System.out.println("7. Delete Mineral");
            System.out.println("8. View All Minerals");
            System.out.println("9. Add Mineral to Mine");
            System.out.println("10. Remove Mineral from Mine");
            System.out.println("11. View Mine Minerals");
            System.out.println("12. Return to Dashboard");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addMine();
                case 2 -> updateMine();
                case 3 -> deleteMine();
                case 4 -> viewAllMines();
                case 5 -> addMineral();
                case 6 -> updateMineral();
                case 7 -> deleteMineral();
                case 8 -> viewAllMinerals();
                case 9 -> addMineralToMine();
                case 10 -> removeMineralFromMine();
                case 11 -> viewMineMinerals();
                case 12 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void pollutantsManagementMenu() {
        while (true) {
            System.out.println("\n══════════ POLLUTANT MANAGEMENT ══════════");
            System.out.println("1. Add New Pollutant");
            System.out.println("2. Update Pollutant");
            System.out.println("3. Delete Pollutant");
            System.out.println("4. View All Pollutants");
            System.out.println("5. Return to Dashboard");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addPollutant();
                case 2 -> updatePollutant();
                case 3 -> deletePollutant();
                case 4 -> viewAllPollutants();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void monitoringStationsMenu() {
        while (true) {
            System.out.println("\n══════════ MONITORING STATIONS MANAGEMENT ══════════");
            System.out.println("1. Add New Monitoring Station");
            System.out.println("2. Update Monitoring Station");
            System.out.println("3. Delete Monitoring Station");
            System.out.println("4. View All Monitoring Stations");
            System.out.println("5. Return to Dashboard");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addMonitoringStation();
                case 2 -> updateMonitoringStation();
                case 3 -> deleteMonitoringStation();
                case 4 -> viewAllMonitoringStations();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void environmentalDataMenu() {
        while (true) {
            System.out.println("\n══════════ ENVIRONMENTAL DATA MANAGEMENT ══════════");
            System.out.println("1. Add New Environmental Data Entry");
            System.out.println("2. Update Environmental Data Entry");
            System.out.println("3. Delete Environmental Data Entry");
            System.out.println("4. View All Environmental Data Entries");
            System.out.println("5. Return to Dashboard");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addEnvironmentalData();
                case 2 -> updateEnvironmentalData();
                case 3 -> deleteEnvironmentalData();
                case 4 -> viewAllEnvironmentalData();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void safetyReportsMenu() {
        while (true) {
            System.out.println("\n══════════ SAFETY REPORTS MANAGEMENT ══════════");
            System.out.println("1. Add New Safety Report");
            System.out.println("2. Update Safety Report");
            System.out.println("3. Delete Safety Report");
            System.out.println("4. View All Safety Reports");
            System.out.println("5. Return to Dashboard");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addSafetyReport();
                case 2 -> updateSafetyReport();
                case 3 -> deleteSafetyReport();
                case 4 -> viewAllSafetyReports();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void provincesMenu() {
        while (true) {
            System.out.println("\n══════════ PROVINCES MANAGEMENT ══════════");
            System.out.println("1. Add New Province");
            System.out.println("2. Update Province");
            System.out.println("3. Delete Province");
            System.out.println("4. View All Provinces");
            System.out.println("5. Return to Dashboard");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addProvince();
                case 2 -> updateProvince();
                case 3 -> deleteProvince();
                case 4 -> viewAllProvinces();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // User Management Methods
    private void addUser() {
        try {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            String url = BASE_URL + "/users";
            ResponseEntity<User> response = restTemplate.postForEntity(URI.create(url), user, User.class);
            System.out.println("User added successfully with ID: " + response.getBody().getId());
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to add user: " + e.getMessage());
        }
    }

    private void updateUser() {
        try {
            System.out.print("Enter user ID to update: ");
            Long userId = scanner.nextLong();
            scanner.nextLine();
            System.out.print("Enter new username: ");
            String username = scanner.nextLine();

            User user = new User();
            user.setId(userId);
            user.setUsername(username);

            String url = BASE_URL + "/users/" + userId;
            restTemplate.put(URI.create(url), user);
            System.out.println("User updated successfully");
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to update user: " + e.getMessage());
        }
    }

    private void deleteUser() {
        try {
            System.out.print("Enter user ID to delete: ");
            Long userId = scanner.nextLong();
            scanner.nextLine();

            String url = BASE_URL + "/users/" + userId;
            restTemplate.delete(URI.create(url));
            System.out.println("User deleted successfully");
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to delete user: " + e.getMessage());
        }
    }

    private void viewAllUsers() {
        try {
            String url = BASE_URL + "/users";
            ResponseEntity<User[]> response = restTemplate.getForEntity(URI.create(url), User[].class);
            User[] users = response.getBody();
            if (users != null && users.length > 0) {
                System.out.println("\nUsers List:");
                for (User user : users) {
                    System.out.println("ID: " + user.getId() + ", Username: " + user.getUsername());
                }
            } else {
                System.out.println("No users found");
            }
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to retrieve users: " + e.getMessage());
        }
    }

    // Mines Management Methods
    private void addMine() {
        try {
            System.out.print("Enter mine name: ");
            String name = scanner.nextLine();
            System.out.print("Enter location: ");
            String location = scanner.nextLine();
            System.out.print("Enter company: ");
            String company = scanner.nextLine();
            System.out.print("Enter province ID: ");
            Long provinceId = scanner.nextLong();
            scanner.nextLine();

            Mines mine = new Mines();
            mine.setName(name);
            mine.setLocation(location);
            mine.setCompany(company);

            Provinces province = new Provinces();
            province.setId(provinceId);
            mine.setProvince(province);

            String url = BASE_URL + "/mines";
            ResponseEntity<Mines> response = restTemplate.postForEntity(URI.create(url), mine, Mines.class);
            System.out.println("Mine added successfully with ID: " + response.getBody().getId());
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to add mine: " + e.getMessage());
        }
    }

    private void updateMine() {
        try {
            System.out.print("Enter mine ID to update: ");
            Long mineId = scanner.nextLong();
            scanner.nextLine();
            System.out.print("Enter new mine name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new location: ");
            String location = scanner.nextLine();
            System.out.print("Enter new company: ");
            String company = scanner.nextLine();

            Mines mine = new Mines();
            mine.setId(mineId);
            mine.setName(name);
            mine.setLocation(location);
            mine.setCompany(company);

            String url = BASE_URL + "/mines/" + mineId;
            restTemplate.put(URI.create(url), mine);
            System.out.println("Mine updated successfully");
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to update mine: " + e.getMessage());
        }
    }

    private void deleteMine() {
        try {
            System.out.print("Enter mine ID to delete: ");
            Long mineId = scanner.nextLong();
            scanner.nextLine();

            String url = BASE_URL + "/mines/" + mineId;
            restTemplate.delete(URI.create(url));
            System.out.println("Mine deleted successfully");
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to delete mine: " + e.getMessage());
        }
    }

    private void viewAllMines() {
        try {
            String url = BASE_URL + "/mines";
            ResponseEntity<Mines[]> response = restTemplate.getForEntity(URI.create(url), Mines[].class);
            Mines[] mines = response.getBody();
            if (mines != null && mines.length > 0) {
                System.out.println("\nMines List:");
                for (Mines mine : mines) {
                    System.out.println("ID: " + mine.getId() + ", Name: " + mine.getName() +
                            ", Location: " + mine.getLocation() + ", Company: " + mine.getCompany());
                }
            } else {
                System.out.println("No mines found");
            }
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to retrieve mines: " + e.getMessage());
        }
    }

    private void addMineral() {
        try {
            System.out.print("Enter mineral name: ");
            String name = scanner.nextLine();

            Minerals mineral = new Minerals();
            mineral.setName(name);

            String url = BASE_URL + "/minerals";
            ResponseEntity<Minerals> response = restTemplate.postForEntity(URI.create(url), mineral, Minerals.class);
            System.out.println("Mineral added successfully with ID: " + response.getBody().getId());
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to add mineral: " + e.getMessage());
        }
    }

    private void updateMineral() {
        try {
            System.out.print("Enter mineral ID to update: ");
            Long mineralId = scanner.nextLong();
            scanner.nextLine();
            System.out.print("Enter new mineral name: ");
            String name = scanner.nextLine();

            Minerals mineral = new Minerals();
            mineral.setId(mineralId);
            mineral.setName(name);

            String url = BASE_URL + "/minerals/" + mineralId;
            restTemplate.put(URI.create(url), mineral);
            System.out.println("Mineral updated successfully");
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to update mineral: " + e.getMessage());
        }
    }

    private void deleteMineral() {
        try {
            System.out.print("Enter mineral ID to delete: ");
            Long mineralId = scanner.nextLong();
            scanner.nextLine();

            String url = BASE_URL + "/minerals/" + mineralId;
            restTemplate.delete(URI.create(url));
            System.out.println("Mineral deleted successfully");
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to delete mineral: " + e.getMessage());
        }
    }

    private void viewAllMinerals() {
        try {
            String url = BASE_URL + "/minerals";
            ResponseEntity<Minerals[]> response = restTemplate.getForEntity(URI.create(url), Minerals[].class);
            Minerals[] minerals = response.getBody();
            if (minerals != null && minerals.length > 0) {
                System.out.println("\nMinerals List:");
                for (Minerals mineral : minerals) {
                    System.out.println("ID: " + mineral.getId() + ", Name: " + mineral.getName());
                }
            } else {
                System.out.println("No minerals found");
            }
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to retrieve minerals: " + e.getMessage());
        }
    }

    private void addMineralToMine() {
        try {
            System.out.print("Enter mine ID: ");
            Long mineId = scanner.nextLong();
            System.out.print("Enter mineral ID: ");
            Long mineralId = scanner.nextLong();
            scanner.nextLine();

            String url = BASE_URL + "/mines/" + mineId + "/minerals/" + mineralId;
            ResponseEntity<Void> response = restTemplate.postForEntity(URI.create(url), null, Void.class);
            System.out.println("Mineral added to mine successfully");
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to add mineral to mine: " + e.getMessage());
        }
    }

    private void removeMineralFromMine() {
        try {
            System.out.print("Enter mine ID: ");
            Long mineId = scanner.nextLong();
            System.out.print("Enter mineral ID: ");
            Long mineralId = scanner.nextLong();
            scanner.nextLine();

            String url = BASE_URL + "/mines/" + mineId + "/minerals/" + mineralId;
            restTemplate.delete(URI.create(url));
            System.out.println("Mineral removed from mine successfully");
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to remove mineral from mine: " + e.getMessage());
        }
    }

    private void viewMineMinerals() {
        try {
            System.out.print("Enter mine ID: ");
            Long mineId = scanner.nextLong();
            scanner.nextLine();

            String url = BASE_URL + "/mines/" + mineId + "/minerals";
            ResponseEntity<Minerals[]> response = restTemplate.getForEntity(URI.create(url), Minerals[].class);
            Minerals[] minerals = response.getBody();
            if (minerals != null && minerals.length > 0) {
                System.out.println("\nMine Minerals List:");
                for (Minerals mineral : minerals) {
                    System.out.println("ID: " + mineral.getId() + ", Name: " + mineral.getName());
                }
            } else {
                System.out.println("No minerals found for this mine");
            }
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to retrieve mine minerals: " + e.getMessage());
        }
    }

    // Environmental Data Management Methods
    private void addEnvironmentalData() {
        try {
            System.out.print("Enter mine ID: ");
            Long mineId = scanner.nextLong();
            System.out.print("Enter pollutant ID: ");
            Long pollutantId = scanner.nextLong();
            System.out.print("Enter monitoring station ID: ");
            Long stationId = scanner.nextLong();
            System.out.print("Enter measured value: ");
            Double measuredValue = scanner.nextDouble();
            scanner.nextLine();

            EnvironmentalData data = new EnvironmentalData();
            data.setMeasuredValue(measuredValue);
            data.setMeasurementDate(LocalDateTime.now());

            Mines mine = new Mines();
            mine.setId(mineId);
            data.setMine(mine);

            Pollutants pollutant = new Pollutants();
            pollutant.setId(pollutantId);
            data.setPollutant(pollutant);

            MonitoringStations station = new MonitoringStations();
            station.setId(stationId);
            data.setMonitoringStation(station);

            String url = BASE_URL + "/environmental-data";
            ResponseEntity<EnvironmentalData> response = restTemplate.postForEntity(URI.create(url), data, EnvironmentalData.class);
            System.out.println("Environmental data added successfully with ID: " + response.getBody().getId());
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to add environmental data: " + e.getMessage());
        }
    }

    private void updateEnvironmentalData() {
        try {
            System.out.print("Enter environmental data ID to update: ");
            Long dataId = scanner.nextLong();
            System.out.print("Enter new measured value: ");
            Double measuredValue = scanner.nextDouble();
            scanner.nextLine();

            EnvironmentalData data = new EnvironmentalData();
            data.setId(dataId);
            data.setMeasuredValue(measuredValue);
            data.setMeasurementDate(LocalDateTime.now());

            String url = BASE_URL + "/environmental-data/" + dataId;
            restTemplate.put(URI.create(url), data);
            System.out.println("Environmental data updated successfully");
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to update environmental data: " + e.getMessage());
        }
    }

    private void deleteEnvironmentalData() {
        try {
            System.out.print("Enter environmental data ID to delete: ");
            Long dataId = scanner.nextLong();
            scanner.nextLine();

            String url = BASE_URL + "/environmental-data/" + dataId;
            restTemplate.delete(URI.create(url));
            System.out.println("Environmental data deleted successfully");
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to delete environmental data: " + e.getMessage());
        }
    }

    private void viewAllEnvironmentalData() {
        try {
            String url = BASE_URL + "/environmental-data";
            ResponseEntity<EnvironmentalData[]> response = restTemplate.getForEntity(URI.create(url), EnvironmentalData[].class);
            EnvironmentalData[] dataEntries = response.getBody();
            if (dataEntries != null && dataEntries.length > 0) {
                System.out.println("\nEnvironmental Data List:");
                for (EnvironmentalData data : dataEntries) {
                    System.out.println("ID: " + data.getId() +
                            ", Mine: " + data.getMine().getName() +
                            ", Value: " + data.getMeasuredValue() +
                            ", Date: " + data.getMeasurementDate());
                }
            } else {
                System.out.println("No environmental data found");
            }
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to retrieve environmental data: " + e.getMessage());
        }
    }

    // Safety Reports Management Methods
    private void addSafetyReport() {
        try {
            System.out.print("Enter mine ID: ");
            Long mineId = scanner.nextLong();
            System.out.print("Enter loss time incidents: ");
            int lossTimeIncidents = scanner.nextInt();
            System.out.print("Enter near misses: ");
            int nearMisses = scanner.nextInt();
            scanner.nextLine();

            SafetyData report = new SafetyData();
            report.setDateRecorded(LocalDate.now());
            report.setLostTimeIncidents(lossTimeIncidents);
            report.setNearMisses(nearMisses);
            report.setSafetyLevel(SafetyLevel.NEEDS_IMPROVEMENT);

            Mines mine = new Mines();
            mine.setId(mineId);
            report.setMine(mine);

            String url = BASE_URL + "/safety-data";
            ResponseEntity<SafetyData> response = restTemplate.postForEntity(URI.create(url), report, SafetyData.class);
            System.out.println("Safety report added successfully with ID: " + response.getBody().getId());
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to add safety report: " + e.getMessage());
        }
    }

    private void updateSafetyReport() {
        try {
            System.out.print("Enter safety report ID to update: ");
            Long reportId = scanner.nextLong();
            System.out.print("Enter new loss time incidents: ");
            int lossTimeIncidents = scanner.nextInt();
            System.out.print("Enter new near misses: ");
            int nearMisses = scanner.nextInt();
            scanner.nextLine();

            SafetyData report = new SafetyData();
            report.setId(reportId);
            report.setDateRecorded(LocalDate.now());
            report.setLostTimeIncidents(lossTimeIncidents);
            report.setNearMisses(nearMisses);
            report.setSafetyLevel(SafetyLevel.NEEDS_IMPROVEMENT);

            String url = BASE_URL + "/safety-data/" + reportId;
            restTemplate.put(URI.create(url), report);
            System.out.println("Safety report updated successfully");
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to update safety report: " + e.getMessage());
        }
    }

    private void deleteSafetyReport() {
        try {
            System.out.print("Enter safety report ID to delete: ");
            Long reportId = scanner.nextLong();
            scanner.nextLine();

            String url = BASE_URL + "/safety-data/" + reportId;
            restTemplate.delete(URI.create(url));
            System.out.println("Safety report deleted successfully");
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to delete safety report: " + e.getMessage());
        }
    }

    private void viewAllSafetyReports() {
        try {
            String url = BASE_URL + "/safety-data";
            ResponseEntity<SafetyData[]> response = restTemplate.getForEntity(URI.create(url), SafetyData[].class);
            SafetyData[] reports = response.getBody();
            if (reports != null && reports.length > 0) {
                System.out.println("\nSafety Reports List:");
                for (SafetyData report : reports) {
                    System.out.println("ID: " + report.getId() +
                            ", Mine: " + report.getMine().getName() +
                            ", Lost Time Incidents: " + report.getLostTimeIncidents() +
                            ", Near Misses: " + report.getNearMisses() +
                            ", Safety Level: " + report.getSafetyLevel());
                }
            } else {
                System.out.println("No safety reports found");
            }
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to retrieve safety reports: " + e.getMessage());
        }
    }

    // Monitoring Stations Management Methods
    private void addMonitoringStation() {
        try {
            System.out.print("Enter station location: ");
            String location = scanner.nextLine();
            System.out.print("Enter province ID: ");
            Long provinceId = scanner.nextLong();
            scanner.nextLine();

            MonitoringStations station = new MonitoringStations();
            station.setLocation(location);

            Provinces province = new Provinces();
            province.setId(provinceId);
            station.setProvince(province);

            String url = BASE_URL + "/monitoring-stations";
            ResponseEntity<MonitoringStations> response = restTemplate.postForEntity(URI.create(url), station, MonitoringStations.class);
            System.out.println("Monitoring station added successfully with ID: " + response.getBody().getId());
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to add monitoring station: " + e.getMessage());
        }
    }

    private void updateMonitoringStation() {
        try {
            System.out.print("Enter station ID to update: ");
            Long stationId = scanner.nextLong();
            scanner.nextLine();
            System.out.print("Enter new location: ");
            String location = scanner.nextLine();

            MonitoringStations station = new MonitoringStations();
            station.setId(stationId);
            station.setLocation(location);

            String url = BASE_URL + "/monitoring-stations/" + stationId;
            restTemplate.put(URI.create(url), station);
            System.out.println("Monitoring station updated successfully");
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to update monitoring station: " + e.getMessage());
        }
    }

    private void deleteMonitoringStation() {
        try {
            System.out.print("Enter station ID to delete: ");
            Long stationId = scanner.nextLong();
            scanner.nextLine();

            String url = BASE_URL + "/monitoring-stations/" + stationId;
            restTemplate.delete(URI.create(url));
            System.out.println("Monitoring station deleted successfully");
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to delete monitoring station: " + e.getMessage());
        }
    }

    private void viewAllMonitoringStations() {
        try {
            String url = BASE_URL + "/monitoring-stations";
            ResponseEntity<MonitoringStations[]> response = restTemplate.getForEntity(URI.create(url), MonitoringStations[].class);
            MonitoringStations[] stations = response.getBody();
            if (stations != null && stations.length > 0) {
                System.out.println("\nMonitoring Stations List:");
                for (MonitoringStations station : stations) {
                    System.out.println("ID: " + station.getId() +
                            ", Location: " + station.getLocation() +
                            ", Province: " + station.getProvince().getName());
                }
            } else {
                System.out.println("No monitoring stations found");
            }
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to retrieve monitoring stations: " + e.getMessage());
        }
    }

    // Pollutants Management Methods
    private void addPollutant() {
        try {
            System.out.print("Enter pollutant name: ");
            String name = scanner.nextLine();
            System.out.print("Enter pollutant category: ");
            String category = scanner.nextLine();
            System.out.print("Enter unit of measurement: ");
            String unit = scanner.nextLine();
            System.out.print("Enter benchmark value: ");
            double benchmarkValue = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Enter benchmark type: ");
            String benchmarkType = scanner.nextLine();

            Pollutants pollutant = new Pollutants();
            pollutant.setName(name);
            pollutant.setCategory(category);
            pollutant.setUnit(unit);
            pollutant.setBenchmarkValue(benchmarkValue);
            pollutant.setBenchmarkType(benchmarkType);

            String url = BASE_URL + "/pollutants";
            ResponseEntity<Pollutants> response = restTemplate.postForEntity(URI.create(url), pollutant, Pollutants.class);
            System.out.println("Pollutant added successfully with ID: " + response.getBody().getId());
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to add pollutant: " + e.getMessage());
        }
    }

    private void updatePollutant() {
        try {
            System.out.print("Enter pollutant ID to update: ");
            Long pollutantId = scanner.nextLong();
            scanner.nextLine();
            System.out.print("Enter new pollutant name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new benchmark value: ");
            double benchmarkValue = scanner.nextDouble();
            scanner.nextLine();

            Pollutants pollutant = new Pollutants();
            pollutant.setId(pollutantId);
            pollutant.setName(name);
            pollutant.setBenchmarkValue(benchmarkValue);

            String url = BASE_URL + "/pollutants/" + pollutantId;
            restTemplate.put(URI.create(url), pollutant);
            System.out.println("Pollutant updated successfully");
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to update pollutant: " + e.getMessage());
        }
    }

    private void deletePollutant() {
        try {
            System.out.print("Enter pollutant ID to delete: ");
            Long pollutantId = scanner.nextLong();
            scanner.nextLine();

            String url = BASE_URL + "/pollutants/" + pollutantId;
            restTemplate.delete(URI.create(url));
            System.out.println("Pollutant deleted successfully");
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to delete pollutant: " + e.getMessage());
        }
    }

    private void viewAllPollutants() {
        try {
            String url = BASE_URL + "/pollutants";
            ResponseEntity<Pollutants[]> response = restTemplate.getForEntity(URI.create(url), Pollutants[].class);
            Pollutants[] pollutants = response.getBody();
            if (pollutants != null && pollutants.length > 0) {
                System.out.println("\nPollutants List:");
                for (Pollutants pollutant : pollutants) {
                    System.out.println("ID: " + pollutant.getId() +
                            ", Name: " + pollutant.getName() +
                            ", Category: " + pollutant.getCategory() +
                            ", Benchmark: " + pollutant.getBenchmarkValue() +
                            " " + pollutant.getUnit());
                }
            } else {
                System.out.println("No pollutants found");
            }
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to retrieve pollutants: " + e.getMessage());
        }
    }

    // Province Management Methods
    private void addProvince() {
        try {
            System.out.print("Enter province name: ");
            String name = scanner.nextLine();

            Provinces province = new Provinces();
            province.setName(name);

            String url = BASE_URL + "/provinces";
            ResponseEntity<Provinces> response = restTemplate.postForEntity(URI.create(url), province, Provinces.class);
            System.out.println("Province added successfully with ID: " + response.getBody().getId());
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to add province: " + e.getMessage());
        }
    }

    private void updateProvince() {
        try {
            System.out.print("Enter province ID to update: ");
            Long provinceId = scanner.nextLong();
            scanner.nextLine();
            System.out.print("Enter new province name: ");
            String name = scanner.nextLine();

            Provinces province = new Provinces();
            province.setId(provinceId);
            province.setName(name);

            String url = BASE_URL + "/provinces/" + provinceId;
            restTemplate.put(URI.create(url), province);
            System.out.println("Province updated successfully");
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to update province: " + e.getMessage());
        }
    }

    private void deleteProvince() {
        try {
            System.out.print("Enter province ID to delete: ");
            Long provinceId = scanner.nextLong();
            scanner.nextLine();

            String url = BASE_URL + "/provinces/" + provinceId;
            restTemplate.delete(URI.create(url));
            System.out.println("Province deleted successfully");
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to delete province: " + e.getMessage());
        }
    }

    private void viewAllProvinces() {
        try {
            String url = BASE_URL + "/provinces";
            ResponseEntity<Provinces[]> response = restTemplate.getForEntity(URI.create(url), Provinces[].class);
            Provinces[] provinces = response.getBody();
            if (provinces != null && provinces.length > 0) {
                System.out.println("\nProvinces List:");
                for (Provinces province : provinces) {
                    System.out.println("ID: " + province.getId() + ", Name: " + province.getName());
                }
            } else {
                System.out.println("No provinces found");
            }
        } catch (HttpClientErrorException e) {
            System.out.println("Failed to retrieve provinces: " + e.getMessage());
        }
    }
}
>>>>>>> Stashed changes

