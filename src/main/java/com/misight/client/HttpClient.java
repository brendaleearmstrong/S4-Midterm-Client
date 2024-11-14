package com.misight.client;6


import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

import com.misight.client.model.EnvironmentalData;
import com.misight.client.model.Mines;
import com.misight.client.model.Pollutants;
import com.misight.client.model.MonitoringStations;
import com.misight.client.model.Provinces;
import com.misight.client.model.Minerals;
import com.misight.client.model.User;
import com.misight.client.model.SafetyData;
import com.misight.client.model.SafetyData.SafetyLevel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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

	private void addUser() {
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		System.out.print("Enter role: ");
		String role = scanner.nextLine();

		User user = new User();
		user.setUsername(username);
		user.setPassword("defaultPassword");

		String url = BASE_URL + "/users";
		ResponseEntity<String> response = restTemplate.postForEntity(URI.create(url), user, String.class);
		System.out.println("User added: " + response.getBody());
	}

	private void updateUser() {
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
		System.out.println("User updated.");
	}

	private void deleteUser() {
		System.out.print("Enter user ID to delete: ");
		Long userId = scanner.nextLong();
		scanner.nextLine();

		String url = BASE_URL + "/users/" + userId;
		restTemplate.delete(URI.create(url));
		System.out.println("User deleted.");
	}

	private void viewAllUsers() {
		String url = BASE_URL + "/users";
		ResponseEntity<String> response = restTemplate.getForEntity(URI.create(url), String.class);
		System.out.println("Users: " + response.getBody());
	}

	private void mineAdminMenu() {
		while (true) {
			System.out.println("\n══════════ MINE ADMINISTRATOR DASHBOARD ══════════");
			System.out.println("1. Manage Environmental Data");
			System.out.println("2. Manage Safety Reports");
			System.out.println("3. View Mine Details");
			System.out.println("4. Log Out");
			System.out.print("Enter choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1 -> environmentalDataMenu();
				case 2 -> safetyReportsMenu();
				case 3 -> viewAllMines();
				case 4 -> { return; }
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
			System.out.println("4. Log Out");
			System.out.print("Enter choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1 -> viewAllEnvironmentalData();
				case 2 -> viewAllSafetyReports();
				case 3 -> viewAllMines();
				case 4 -> { return; }
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
			System.out.println("9. Return to Dashboard");
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
				case 9 -> { return; }
				default -> System.out.println("Invalid choice.");
			}
		}
	}

	private void addMine() {
		System.out.print("Enter mine name: ");
		String name = scanner.nextLine();
		System.out.print("Enter location: ");
		String location = scanner.nextLine();
		System.out.print("Enter company: ");
		String company = scanner.nextLine();

		Mines mine = new Mines();
		mine.setName(name);
		mine.setLocation(location);
		mine.setCompany(company);

		String url = BASE_URL + "/mines";
		ResponseEntity<String> response = restTemplate.postForEntity(URI.create(url), mine, String.class);
		System.out.println("Mine added: " + response.getBody());
	}

	private void updateMine() {
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
		System.out.println("Mine updated.");
	}

	private void deleteMine() {
		System.out.print("Enter mine ID to delete: ");
		Long mineId = scanner.nextLong();
		scanner.nextLine();

		String url = BASE_URL + "/mines/" + mineId;
		restTemplate.delete(URI.create(url));
		System.out.println("Mine deleted.");
	}

	private void viewAllMines() {
		String url = BASE_URL + "/mines";
		ResponseEntity<String> response = restTemplate.getForEntity(URI.create(url), String.class);
		System.out.println("Mines: " + response.getBody());
	}

	private void addMineral() {
		System.out.print("Enter mineral name: ");
		String name = scanner.nextLine();

		Minerals mineral = new Minerals();
		mineral.setName(name);

		String url = BASE_URL + "/minerals";
		ResponseEntity<String> response = restTemplate.postForEntity(URI.create(url), mineral, String.class);
		System.out.println("Mineral added: " + response.getBody());
	}

	private void updateMineral() {
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
		System.out.println("Mineral updated.");
	}

	private void deleteMineral() {
		System.out.print("Enter mineral ID to delete: ");
		Long mineralId = scanner.nextLong();
		scanner.nextLine();

		String url = BASE_URL + "/minerals/" + mineralId;
		restTemplate.delete(URI.create(url));
		System.out.println("Mineral deleted.");
	}

	private void viewAllMinerals() {
		String url = BASE_URL + "/minerals";
		ResponseEntity<String> response = restTemplate.getForEntity(URI.create(url), String.class);
		System.out.println("Minerals: " + response.getBody());
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

	private void addPollutant() {
		System.out.print("Enter pollutant name: ");
		String name = scanner.nextLine();
		System.out.print("Enter benchmark value: ");
		double benchmark = scanner.nextDouble();
		scanner.nextLine();

		Pollutants pollutant = new Pollutants();
		pollutant.setName(name);
		pollutant.setBenchmarkValue(benchmark);

		String url = BASE_URL + "/pollutants";
		ResponseEntity<String> response = restTemplate.postForEntity(URI.create(url), pollutant, String.class);
		System.out.println("Pollutant added: " + response.getBody());
	}

	private void updatePollutant() {
		System.out.print("Enter pollutant ID to update: ");
		Long pollutantId = scanner.nextLong();
		scanner.nextLine();
		System.out.print("Enter new pollutant name: ");
		String name = scanner.nextLine();
		System.out.print("Enter new benchmark value: ");
		double benchmark = scanner.nextDouble();
		scanner.nextLine();

		Pollutants pollutant = new Pollutants();
		pollutant.setId(pollutantId);
		pollutant.setName(name);
		pollutant.setBenchmarkValue(benchmark);

		String url = BASE_URL + "/pollutants/" + pollutantId;
		restTemplate.put(URI.create(url), pollutant);
		System.out.println("Pollutant updated.");
	}

	private void deletePollutant() {
		System.out.print("Enter pollutant ID to delete: ");
		Long pollutantId = scanner.nextLong();
		scanner.nextLine();

		String url = BASE_URL + "/pollutants/" + pollutantId;
		restTemplate.delete(URI.create(url));
		System.out.println("Pollutant deleted.");
	}

	private void viewAllPollutants() {
		String url = BASE_URL + "/pollutants";
		ResponseEntity<String> response = restTemplate.getForEntity(URI.create(url), String.class);
		System.out.println("Pollutants: " + response.getBody());
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

	private void addMonitoringStation() {
		System.out.print("Enter station name: ");
		String name = scanner.nextLine();
		System.out.print("Enter location: ");
		String location = scanner.nextLine();

		MonitoringStations station = new MonitoringStations();
		station.setLocation(location);

		String url = BASE_URL + "/monitoringStations";
		ResponseEntity<String> response = restTemplate.postForEntity(URI.create(url), station, String.class);
		System.out.println("Monitoring Station added: " + response.getBody());
	}

	private void updateMonitoringStation() {
		System.out.print("Enter station ID to update: ");
		Long stationId = scanner.nextLong();
		scanner.nextLine();
		System.out.print("Enter new location: ");
		String location = scanner.nextLine();

		MonitoringStations station = new MonitoringStations();
		station.setId(stationId);
		station.setLocation(location);

		String url = BASE_URL + "/monitoringStations/" + stationId;
		restTemplate.put(URI.create(url), station);
		System.out.println("Monitoring Station updated.");
	}

	private void deleteMonitoringStation() {
		System.out.print("Enter station ID to delete: ");
		Long stationId = scanner.nextLong();
		scanner.nextLine();

		String url = BASE_URL + "/monitoringStations/" + stationId;
		restTemplate.delete(URI.create(url));
		System.out.println("Monitoring Station deleted.");
	}

	private void viewAllMonitoringStations() {
		String url = BASE_URL + "/monitoringStations";
		ResponseEntity<String> response = restTemplate.getForEntity(URI.create(url), String.class);
		System.out.println("Monitoring Stations: " + response.getBody());
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

	private void addProvince() {
		System.out.print("Enter province name: ");
		String name = scanner.nextLine();

		Provinces province = new Provinces();
		province.setName(name);

		String url = BASE_URL + "/provinces";
		ResponseEntity<String> response = restTemplate.postForEntity(URI.create(url), province, String.class);
		System.out.println("Province added: " + response.getBody());
	}

	private void updateProvince() {
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
		System.out.println("Province updated.");
	}

	private void deleteProvince() {
		System.out.print("Enter province ID to delete: ");
		Long provinceId = scanner.nextLong();
		scanner.nextLine();

		String url = BASE_URL + "/provinces/" + provinceId;
		restTemplate.delete(URI.create(url));
		System.out.println("Province deleted.");
	}

	private void viewAllProvinces() {
		String url = BASE_URL + "/provinces";
		ResponseEntity<String> response = restTemplate.getForEntity(URI.create(url), String.class);
		System.out.println("Provinces: " + response.getBody());
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

	private void addEnvironmentalData() {
		System.out.print("Enter pollutant ID: ");
		Long pollutantId = scanner.nextLong();
		System.out.print("Enter monitoring station ID: ");
		Long stationId = scanner.nextLong();
		System.out.print("Enter measured value: ");
		Double measuredValue = scanner.nextDouble();
		scanner.nextLine();

		EnvironmentalData ed = new EnvironmentalData();
		ed.setMeasuredValue(measuredValue);
		ed.setMeasurementDate(LocalDateTime.now());

		String url = BASE_URL + "/environmentalData";
		ResponseEntity<String> response = restTemplate.postForEntity(URI.create(url), ed, String.class);
		System.out.println("Environmental Data added: " + response.getBody());
	}

	private void updateEnvironmentalData() {
		System.out.print("Enter environmental data ID to update: ");
		Long edId = scanner.nextLong();
		System.out.print("Enter new measured value: ");
		Double measuredValue = scanner.nextDouble();
		scanner.nextLine();

		EnvironmentalData ed = new EnvironmentalData();
		ed.setMeasuredValue(measuredValue);
		ed.setMeasurementDate(LocalDateTime.now());

		String url = BASE_URL + "/environmentalData/" + edId;
		restTemplate.put(URI.create(url), ed);
		System.out.println("Environmental Data updated.");
	}

	private void deleteEnvironmentalData() {
		System.out.print("Enter environmental data ID to delete: ");
		Long edId = scanner.nextLong();
		scanner.nextLine();

		String url = BASE_URL + "/environmentalData/" + edId;
		restTemplate.delete(URI.create(url));
		System.out.println("Environmental Data entry deleted.");
	}

	private void viewAllEnvironmentalData() {
		String url = BASE_URL + "/environmentalData";
		ResponseEntity<String> response = restTemplate.getForEntity(URI.create(url), String.class);
		System.out.println("Environmental Data Entries: " + response.getBody());
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

	private void addSafetyReport() {
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

		String url = BASE_URL + "/safetyData";
		ResponseEntity<String> response = restTemplate.postForEntity(URI.create(url), report, String.class);
		System.out.println("Safety Report added: " + response.getBody());
	}

	private void updateSafetyReport() {
		System.out.print("Enter safety report ID to update: ");
		Long reportId = scanner.nextLong();
		System.out.print("Enter new loss time incidents: ");
		int lossTimeIncidents = scanner.nextInt();
		System.out.print("Enter new near misses: ");
		int nearMisses = scanner.nextInt();
		scanner.nextLine();

		SafetyData report = new SafetyData();
		report.setDateRecorded(LocalDate.now());
		report.setLostTimeIncidents(lossTimeIncidents);
		report.setNearMisses(nearMisses);
		report.setSafetyLevel(SafetyLevel.NEEDS_IMPROVEMENT);

		String url = BASE_URL + "/safetyData/" + reportId;
		restTemplate.put(URI.create(url), report);
		System.out.println("Safety Report updated.");
	}

	private void deleteSafetyReport() {
		System.out.print("Enter safety report ID to delete: ");
		Long reportId = scanner.nextLong();
		scanner.nextLine();

		String url = BASE_URL + "/safetyData/" + reportId;
		restTemplate.delete(URI.create(url));
		System.out.println("Safety Report deleted.");
	}

	private void viewAllSafetyReports() {
		String url = BASE_URL + "/safetyData";
		ResponseEntity<String> response = restTemplate.getForEntity(URI.create(url), String.class);
		System.out.println("Safety Reports: " + response.getBody());
	}
}
