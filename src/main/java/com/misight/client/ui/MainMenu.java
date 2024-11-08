package com.misight.client.ui;

import com.misight.client.auth.AuthenticationService;
import com.misight.client.model.User;
import com.misight.client.service.DataVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class MainMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final AuthenticationService authService;
    private final AdminMenu adminMenu;
    private final MineAdminMenu mineAdminMenu;
    private final StakeholderMenu stakeholderMenu;
    private final DataVerificationService verificationService;

    @Autowired
    public MainMenu(AuthenticationService authService, AdminMenu adminMenu,
                    MineAdminMenu mineAdminMenu, StakeholderMenu stakeholderMenu,
                    DataVerificationService verificationService) {
        this.authService = authService;
        this.adminMenu = adminMenu;
        this.mineAdminMenu = mineAdminMenu;
        this.stakeholderMenu = stakeholderMenu;
        this.verificationService = verificationService;
    }

    public void start() {
        displayWelcome();
        try {
            verificationService.verifySystemConnectivity();
            while (true) {
                if (!authService.isAuthenticated()) {
                    handleLogin();
                } else {
                    displayMainMenu();
                }
            }
        } catch (Exception e) {
            handleSystemError(e);
        }
    }

    private void displayWelcome() {
        System.out.println("====================================");
        System.out.println("Welcome to Misight Management System");
        System.out.println("====================================");
        System.out.println("Version 1.0");
        System.out.println("Connecting to server...");
    }

    private void handleLogin() {
        int attempts = 0;
        while (attempts < 3) {
            System.out.println("\n=== Misight System Login ===");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            try {
                if (authService.login(username, password)) {
                    User user = authService.getCurrentUser().orElseThrow();
                    System.out.println("\nLogin successful!");
                    System.out.println("Welcome, " + user.getUsername() + "!");
                    displayUserPrivileges(user);
                    return;
                } else {
                    attempts++;
                    System.out.println("Invalid credentials. Attempts remaining: " + (3 - attempts));
                }
            } catch (Exception e) {
                System.out.println("Login error: " + e.getMessage());
                attempts++;
            }
        }
        System.out.println("Maximum login attempts exceeded. Please try again later.");
        System.exit(1);
    }

    private void displayUserPrivileges(User user) {
        System.out.println("\nYour access privileges:");
        user.getPrivileges().forEach(privilege ->
                System.out.println("- " + privilege.getName())
        );
    }

    private void displayMainMenu() {
        while (authService.isAuthenticated()) {
            System.out.println("\n=== Misight Management System ===");
            System.out.println("Current User: " + authService.getCurrentUser().get().getUsername());
            System.out.println("1. Admin Menu");
            System.out.println("2. Mine Admin Menu");
            System.out.println("3. Stakeholder Menu");
            System.out.println("4. Change Password");
            System.out.println("5. View System Status");
            System.out.println("6. Logout");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "1":
                        if (authService.hasRole("ADMIN")) {
                            adminMenu.show();
                        } else {
                            System.out.println("Access denied: Insufficient privileges");
                        }
                        break;
                    case "2":
                        if (authService.hasRole("MINE_ADMIN")) {
                            mineAdminMenu.show();
                        } else {
                            System.out.println("Access denied: Insufficient privileges");
                        }
                        break;
                    case "3":
                        stakeholderMenu.show();
                        break;
                    case "4":
                        changePassword();
                        break;
                    case "5":
                        displaySystemStatus();
                        break;
                    case "6":
                        performLogout();
                        return;
                    case "0":
                        exitSystem();
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                handleMenuError(e);
            }
        }
    }

    private void changePassword() {
        System.out.println("\n=== Change Password ===");
        try {
            System.out.print("Enter current password: ");
            String currentPassword = scanner.nextLine();
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            System.out.print("Confirm new password: ");
            String confirmPassword = scanner.nextLine();

            if (!newPassword.equals(confirmPassword)) {
                System.out.println("New passwords do not match!");
                return;
            }

            if (newPassword.length() < 8) {
                System.out.println("New password must be at least 8 characters long!");
                return;
            }

            authService.changePassword(currentPassword, newPassword);
            System.out.println("Password changed successfully!");
        } catch (Exception e) {
            System.out.println("Failed to change password: " + e.getMessage());
        }
    }

    private void displaySystemStatus() {
        try {
            verificationService.verifySystemConnectivity();
            System.out.println("\n=== System Status ===");
            System.out.println("Server Connection: OK");
            System.out.println("Database Connection: OK");
            System.out.println("Current User: " + authService.getCurrentUser().get().getUsername());
            System.out.println("Session Active: Yes");
        } catch (Exception e) {
            System.out.println("\nSystem Status Check Failed:");
            System.out.println(e.getMessage());
        }
    }

    private void performLogout() {
        try {
            authService.logout();
            System.out.println("Logged out successfully.");
        } catch (Exception e) {
            System.out.println("Error during logout: " + e.getMessage());
        }
    }

    private void exitSystem() {
        try {
            authService.logout();
        } catch (Exception e) {
            // Ignore logout errors during exit
        }
        System.out.println("Thank you for using Misight Management System.");
        System.out.println("Exiting...");
        System.exit(0);
    }

    private void handleSystemError(Exception e) {
        System.err.println("Critical system error: " + e.getMessage());
        System.err.println("Please contact system administrator.");
        System.exit(1);
    }

    private void handleMenuError(Exception e) {
        System.out.println("Error processing request: " + e.getMessage());
        System.out.println("Please try again or contact support if the issue persists.");
    }
}
