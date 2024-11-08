package com.misight.client.ui;

import com.misight.client.service.setup.*;
import com.misight.client.model.*;
import com.misight.client.service.DataVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Component
public class AdminMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService;
    private final MinesService minesService;
    private final MineralsService mineralsService;
    private final ProvincesService provincesService;
    private final DataVerificationService verificationService;

    @Autowired
    public AdminMenu(UserService userService, MinesService minesService,
                     MineralsService mineralsService, ProvincesService provincesService,
                     DataVerificationService verificationService) {
        this.userService = userService;
        this.minesService = minesService;
        this.mineralsService = mineralsService;
        this.provincesService = provincesService;
        this.verificationService = verificationService;
    }

    public void show() {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. User Management");
            System.out.println("2. Mine Management");
            System.out.println("3. Mineral Management");
            System.out.println("4. Province Management");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();
            try {
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
                    case "0":
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // User Management Methods
    private void userManagement() {
        while (true) {
            System.out.println("\n=== User Management ===");
            System.out.println("1. List all users");
            System.out.println("2. Add new user");
            System.out.println("3. Update user");
            System.out.println("4. Delete user");
            System.out.println("5. Manage user privileges");
            System.out.println("0. Back");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();
            try {
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
                    case "5":
                        manageUserPrivileges();
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void listUsers() {
        try {
            List<User> users = userService.getAllUsers();
            System.out.println("\n=== User List ===");
            users.forEach(user -> {
                System.out.println("\nID: " + user.getId());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Privileges: " + user.getPrivileges());
                System.out.println("-----------------");
            });
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

            if (username.trim().isEmpty() || password.trim().isEmpty()) {
                System.out.println("Username and password cannot be empty.");
                return;
            }

            User newUser = new User(username, password);
            User created = userService.createUser(newUser);
            verificationService.verifyDataPersistence(created.getId(), "USER");
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

            Optional<User> existingUser = userService.getUserById(id);
            if (existingUser.isEmpty()) {
                System.out.println("User not found with ID: " + id);
                return;
            }

            System.out.print("Enter new username (or press Enter to keep current): ");
            String username = scanner.nextLine();
            System.out.print("Enter new password (or press Enter to keep current): ");
            String password = scanner.nextLine();

            User updatedUser = existingUser.get();
            if (!username.trim().isEmpty()) {
                updatedUser.setUsername(username);
            }
            if (!password.trim().isEmpty()) {
                updatedUser.setPassword(password);
            }

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

            System.out.print("Are you sure you want to delete this user? (y/n): ");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                userService.deleteUser(id);
                System.out.println("User deleted successfully");
            } else {
                System.out.println("Deletion cancelled");
            }
        } catch (Exception e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }

    private void manageUserPrivileges() {
        try {
            System.out.println("\n=== Manage User Privileges ===");
            System.out.print("Enter user ID: ");
            Long userId = Long.parseLong(scanner.nextLine());

            Optional<User> user = userService.getUserById(userId);
            if (user.isEmpty()) {
                System.out.println("User not found");
                return;
            }

            System.out.println("Current privileges: " + user.get().getPrivileges());
            System.out.println("\nAvailable privileges:");
            System.out.println("1. ADMIN");
            System.out.println("2. MINE_ADMIN");
            System.out.println("3. STAKEHOLDER");

            System.out.print("Enter privilege numbers (comma-separated): ");
            String[] selections = scanner.nextLine().split(",");

            Set<Privileges> newPrivileges = new HashSet<>();
            for (String selection : selections) {
                switch (selection.trim()) {
                    case "1":
                        newPrivileges.add(new Privileges("ADMIN"));
                        break;
                    case "2":
                        newPrivileges.add(new Privileges("MINE_ADMIN"));
                        break;
                    case "3":
                        newPrivileges.add(new Privileges("STAKEHOLDER"));
                        break;
                }
            }

            userService.updateUserPrivileges(userId, newPrivileges);
            System.out.println("Privileges updated successfully");
        } catch (Exception e) {
            System.out.println("Error managing privileges: " + e.getMessage());
        }
    }

    // Mine Management Methods
    private void mineManagement() {
        while (true) {
            System.out.println("\n=== Mine Management ===");
            System.out.println("1. List all mines");
            System.out.println("2. Add new mine");
            System.out.println("3. Update mine");
            System.out.println("4. Delete mine");
            System.out.println("5. Manage mine minerals");
            System.out.println("0. Back");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "1":
                        listMines();
                        break;
                    case "2":
                        addMine();
                        break;
                    case "3":
                        updateMine();
                        break;
                    case "4":
                        deleteMine();
                        break;
                    case "5":
                        manageMinerals();
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void listMines() {
        try {
            List<Mines> mines = minesService.getAllMines();
            System.out.println("\n=== Mine List ===");
            mines.forEach(mine -> {
                System.out.println("\nID: " + mine.getId());
                System.out.println("Name: " + mine.getName());
                System.out.println("Location: " + mine.getLocation());
                System.out.println("Company: " + mine.getCompany());
                System.out.println("Province: " +
                        (mine.getProvince() != null ? mine.getProvince().getName() : "N/A"));
                System.out.println("Minerals: " + mine.getMinerals());
                System.out.println("-----------------");
            });
        } catch (Exception e) {
            System.out.println("Error fetching mines: " + e.getMessage());
        }
    }

    private void addMine() {
        try {
            System.out.println("\n=== Add New Mine ===");
            System.out.print("Enter mine name: ");
            String name = scanner.nextLine();
            System.out.print("Enter location: ");
            String location = scanner.nextLine();
            System.out.print("Enter company name: ");
            String company = scanner.nextLine();

            // Get province
            System.out.print("Enter province ID: ");
            Long provinceId = Long.parseLong(scanner.nextLine());
            Optional<Provinces> province = provincesService.getProvinceById(provinceId);
            if (province.isEmpty()) {
                System.out.println("Province not found");
                return;
            }

            Mines newMine = new Mines(name, company, location, province.get());
            Mines created = minesService.createMine(newMine);
            verificationService.verifyDataPersistence(created.getId(), "MINE");
            System.out.println("Mine created successfully with ID: " + created.getId());
        } catch (Exception e) {
            System.out.println("Error creating mine: " + e.getMessage());
        }
    }

    private void updateMine() {
        try {
            System.out.println("\n=== Update Mine ===");
            System.out.print("Enter mine ID: ");
            Long id = Long.parseLong(scanner.nextLine());

            Optional<Mines> existingMine = minesService.getMineById(id);
            if (existingMine.isEmpty()) {
                System.out.println("Mine not found");
                return;
            }

            System.out.print("Enter new name (or press Enter to keep current): ");
            String name = scanner.nextLine();
            System.out.print("Enter new location (or press Enter to keep current): ");
            String location = scanner.nextLine();
            System.out.print("Enter new company name (or press Enter to keep current): ");
            String company = scanner.nextLine();

            Mines updatedMine = existingMine.get();
            if (!name.trim().isEmpty()) updatedMine.setName(name);
            if (!location.trim().isEmpty()) updatedMine.setLocation(location);
            if (!company.trim().isEmpty()) updatedMine.setCompany(company);

            minesService.updateMine(id, updatedMine);
            System.out.println("Mine updated successfully");
        } catch (Exception e) {
            System.out.println("Error updating mine: " + e.getMessage());
        }
    }

    private void deleteMine() {
        try {
            System.out.println("\n=== Delete Mine ===");
            System.out.print("Enter mine ID: ");
            Long id = Long.parseLong(scanner.nextLine());

            System.out.print("Are you sure you want to delete this mine? (y/n): ");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                minesService.deleteMine(id);
                System.out.println("Mine deleted successfully");
            } else {
                System.out.println("Deletion cancelled");
            }
        } catch (Exception e) {
            System.out.println("Error deleting mine: " + e.getMessage());
        }
    }

    private void manageMinerals() {
        try {
            System.out.println("\n=== Manage Mine Minerals ===");
            System.out.print("Enter mine ID: ");
            Long mineId = Long.parseLong(scanner.nextLine());

            Optional<Mines> mine = minesService.getMineById(mineId);
            if (mine.isEmpty()) {
                System.out.println("Mine not found");
                return;
            }

            Set<Minerals> currentMinerals = mine.get().getMinerals();
            System.out.println("Current minerals: " + currentMinerals);

            List<Minerals> allMinerals = mineralsService.getAllMinerals();
            System.out.println("\nAvailable minerals:");
            allMinerals.forEach(mineral ->
                    System.out.println(mineral.getId() + ": " + mineral.getName()));

            System.out.print("Enter mineral IDs to add (comma-separated): ");
            String[] mineralIds = scanner.nextLine().split(",");

            Set<Minerals> newMinerals = new HashSet<>();
            for (String mineralId : mineralIds) {
                try {
                    Long id = Long.parseLong(mineralId.trim());
                    mineralsService.getMineralById(id).ifPresent(newMinerals::add);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid mineral ID: " + mineralId);
                }
            }

            Mines updated = minesService.updateMineMinerals(mineId, newMinerals);
            System.out.println("Mine minerals updated successfully");
        } catch (Exception e) {
            System.out.println("Error managing minerals: " + e.getMessage());
        }
    }

    // Mineral Management Methods
    private void mineralManagement() {
        while (true) {
            System.out.println("\n=== Mineral Management ===");
            System.out.println("1. List all minerals");
            System.out.println("2. Add new mineral");
            System.out.println("3. Update mineral");
            System.out.println("4. Delete mineral");
            System.out.println("0. Back");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "1":
                        listMinerals();
                        break;
                    case "2":
                        addMineral();
                        break;
                    case "3":
                        updateMineral();
                        break;
                    case "4":
                        deleteMineral();
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void listMinerals() {
        try {
            List<Minerals> minerals = mineralsService.getAllMinerals();
            System.out.println("\n=== Mineral List ===");
            minerals.forEach(mineral -> {
                System.out.println("\nID: " + mineral.getId());
                System.out.println("Name: " + mineral.getName());
                System.out.println("Associated Mines: " + mineral.getMines().stream()
                        .map(Mines::getName)
                        .collect(Collectors.joining(", ")));
                System.out.println("-----------------");
            });
        } catch (Exception e) {
            System.out.println("Error fetching minerals: " + e.getMessage());
        }
    }

    private void addMineral() {
        try {
            System.out.println("\n=== Add New Mineral ===");
            System.out.print("Enter mineral name: ");
            String name = scanner.nextLine();

            if (name.trim().isEmpty()) {
                System.out.println("Mineral name cannot be empty");
                return;
            }

            Minerals newMineral = new Minerals(name);
            Minerals created = mineralsService.createMineral(newMineral);
            verificationService.verifyDataPersistence(created.getId(), "MINERAL");
            System.out.println("Mineral created successfully with ID: " + created.getId());
        } catch (Exception e) {
            System.out.println("Error creating mineral: " + e.getMessage());
        }
    }

    private void updateMineral() {
        try {
            System.out.println("\n=== Update Mineral ===");
            System.out.print("Enter mineral ID: ");
            Long id = Long.parseLong(scanner.nextLine());

            Optional<Minerals> existingMineral = mineralsService.getMineralById(id);
            if (existingMineral.isEmpty()) {
                System.out.println("Mineral not found");
                return;
            }

            System.out.print("Enter new name (or press Enter to keep current): ");
            String name = scanner.nextLine();

            if (!name.trim().isEmpty()) {
                Minerals updatedMineral = existingMineral.get();
                updatedMineral.setName(name);
                mineralsService.updateMineral(id, updatedMineral);
                System.out.println("Mineral updated successfully");
            } else {
                System.out.println("No changes made");
            }
        } catch (Exception e) {
            System.out.println("Error updating mineral: " + e.getMessage());
        }
    }

    private void deleteMineral() {
        try {
            System.out.println("\n=== Delete Mineral ===");
            System.out.print("Enter mineral ID: ");
            Long id = Long.parseLong(scanner.nextLine());

            Optional<Minerals> mineral = mineralsService.getMineralById(id);
            if (mineral.isEmpty()) {
                System.out.println("Mineral not found");
                return;
            }

            if (!mineral.get().getMines().isEmpty()) {
                System.out.println("Warning: This mineral is associated with mines. " +
                        "Deleting it will remove all associations.");
                System.out.print("Do you still want to proceed? (y/n): ");
                if (!scanner.nextLine().equalsIgnoreCase("y")) {
                    System.out.println("Deletion cancelled");
                    return;
                }
            }

            mineralsService.deleteMineral(id);
            System.out.println("Mineral deleted successfully");
        } catch (Exception e) {
            System.out.println("Error deleting mineral: " + e.getMessage());
        }
    }
}
