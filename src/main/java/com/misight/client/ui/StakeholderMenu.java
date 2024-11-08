package com.misight.client.ui;

import com.misight.client.service.reporting.*;
import com.misight.client.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Map;

@Component
public class StakeholderMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final EnvironmentalDataReportService environmentalReportService;
    private final SafetyDataReportService safetyReportService;
    private final MinesReportService minesReportService;

    @Autowired
    public StakeholderMenu(
            EnvironmentalDataReportService environmentalReportService,
            SafetyDataReportService safetyReportService,
            MinesReportService minesReportService) {
        this.environmentalReportService = environmentalReportService;
        this.safetyReportService = safetyReportService;
        this.minesReportService = minesReportService;
    }

    public void show() {
        while (true) {
            System.out.println("\n=== Stakeholder Menu ===");
            System.out.println("1. View Environmental Reports");
            System.out.println("2. View Safety Reports");
            System.out.println("3. View Mine Information");
            System.out.println("4. Export Reports");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    viewEnvironmentalReports();
                    break;
                case "2":
                    viewSafetyReports();
                    break;
                case "3":
                    viewMineInformation();
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

    private void viewEnvironmentalReports() {
        try {
            System.out.print("Enter Mine ID: ");
            Long mineId = Long.parseLong(scanner.nextLine());
            System.out.print("Enter Year: ");
            int year = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Month (1-12): ");
            int month = Integer.parseInt(scanner.nextLine());

            Map<String, Object> report = environmentalReportService.generateMonthlyReport(mineId, year, month);
            displayReport("Environmental Report", report);
        } catch (Exception e) {
            System.out.println("Error viewing report: " + e.getMessage());
        }
    }

    private void viewSafetyReports() {
        try {
            System.out.print("Enter Mine ID: ");
            Long mineId = Long.parseLong(scanner.nextLine());
            System.out.print("Enter Year: ");
            int year = Integer.parseInt(scanner.nextLine());

            Map<String, Object> report = safetyReportService.generateIncidentSummary(mineId, year);
            displayReport("Safety Report", report);
        } catch (Exception e) {
            System.out.println("Error viewing report: " + e.getMessage());
        }
    }

    private void viewMineInformation() {
        try {
            System.out.print("Enter Mine ID: ");
            Long mineId = Long.parseLong(scanner.nextLine());

            Map<String, Object> mineInfo = minesReportService.generateMineOverview(mineId);
            displayReport("Mine Information", mineInfo);
        } catch (Exception e) {
            System.out.println("Error viewing mine information: " + e.getMessage());
        }
    }

    private void exportReports() {
        try {
            System.out.println("\n=== Export Reports ===");
            System.out.println("1. Export Environmental Report");
            System.out.println("2. Export Safety Report");
            System.out.print("Select report type: ");

            String choice = scanner.nextLine();
            System.out.print("Enter Mine ID: ");
            Long mineId = Long.parseLong(scanner.nextLine());
            System.out.print("Enter format (PDF/CSV): ");
            String format = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "1":
                    exportEnvironmentalReport(mineId, format);
                    break;
                case "2":
                    exportSafetyReport(mineId, format);
                    break;
                default:
                    System.out.println("Invalid option selected.");
            }
        } catch (Exception e) {
            System.out.println("Error exporting report: " + e.getMessage());
        }
    }

    private void exportEnvironmentalReport(Long mineId, String format) {
        try {
            Map<String, Object> reportData = environmentalReportService.generateMonthlyReport(
                    mineId,
                    LocalDateTime.now().getYear(),
                    LocalDateTime.now().getMonthValue()
            );
            byte[] exportedReport = environmentalReportService.exportReport(reportData, format);
            System.out.println("Report exported successfully!");
        } catch (Exception e) {
            System.out.println("Error exporting environmental report: " + e.getMessage());
        }
    }

    private void exportSafetyReport(Long mineId, String format) {
        try {
            Map<String, Object> reportData = safetyReportService.generateIncidentSummary(
                    mineId,
                    LocalDateTime.now().getYear()
            );
            byte[] exportedReport = safetyReportService.exportReport(reportData, format);
            System.out.println("Report exported successfully!");
        } catch (Exception e) {
            System.out.println("Error exporting safety report: " + e.getMessage());
        }
    }

    private void displayReport(String title, Map<String, Object> report) {
        System.out.println("\n=== " + title + " ===");
        report.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}