
# MiSight: Mine Data Management System Documentation
## Digitalizing Mining Data Management

## Capstone Overview
MiSight is an innovative Mine Data Management System designed to transform how mining companies in Newfoundland & Labrador handle real-time environmental and safety monitoring. The system enables streamlined data collection, automated reporting, and enhanced transparency for stakeholders. By centralizing and automating data management, MiSight addresses key industry challenges including data inaccuracy, inefficiencies in manual reporting, and the increasing demand for transparency in environmental and safety data.

## Why MiSight
MiSight was selected as the project due to its critical need in the mining industry, especially in regions with strict environmental monitoring and safety reporting requirements. Mining operations in Newfoundland & Labrador face challenges related to manual data entry, regulatory reporting, and providing transparent information to stakeholders. MiSight aims to digitize these processes, offering real-time insights, reducing errors, and providing easy access to data for regulatory compliance, making it an essential tool for modern mining operations.

### Executive Summary
As a Communications Principal Advisor for an Iron Ore Mine in Labrador, managing environmental data, safety reports, and stakeholder communications involves countless hours handling spreadsheets, emails, and paper reports. Every day, valuable time is spent chasing departments for updates, reconciling inconsistent data formats, and manually compiling reports for government bodies and community stakeholders. The most pressing challenge comes from community concerns about dust levels, where the current paper-based system makes quick responses nearly impossible.

Misight was developed to digitalize this process, transforming paper-based and scattered digital records into a centralized, accessible system. While maintaining the human element of data collection, Misight provides a structured digital platform for input, storage, and retrieval of critical mining operation data.

### Current Business Challenge

#### Daily Operational Pain Points
1. **Environmental Monitoring Documentation**
   - Paper forms filled out at monitoring stations
   - Data manually transferred to spreadsheets
   - Multiple versions of spreadsheets circulating
   - Hours spent consolidating information
   - No central repository for historical data

2. **Safety Reporting Reality**
   - Incident reports on paper forms
   - Near-miss documentation often delayed
   - Manual compilation for monthly reports
   - Time-consuming trend analysis
   - Difficult to track historical patterns

3. **Stakeholder Communication Struggles**
   - Delayed responses to community dust concerns
   - Manual compilation of government reports
   - No easy access to historical data
   - Inconsistent reporting formats
   - Time-consuming data validation

4. **Data Management Issues**
   - Scattered information across departments
   - Inconsistent data formats
   - No standardized input process
   - Difficult audit trails
   - Limited access control


## 2. Business Case
The mining industry faces several significant pain points, including:
- **Inefficient manual reporting**: Mining companies rely on outdated methods for tracking environmental and safety data, which are time-consuming and prone to errors.
- **Regulatory compliance**: Ensuring compliance with environmental laws and safety regulations requires accurate and timely reporting.
- **Stakeholder demand for transparency**: Communities and regulatory bodies require clear, accessible data on mining operations.

MiSight addresses these issues by automating data entry, ensuring accuracy, and providing a digital, centralized solution for environmental and safety reporting. This reduces labor costs, minimizes compliance risks, and fosters stronger relationships with stakeholders by providing access to transparent and up-to-date data.

## 3. System Overview
MiSight is built on a client-server architecture with a Spring Boot backend and PostgreSQL database. The system organizes data through a series of models, repositories, services, and controllers, each serving a specific role in processing and storing environmental and safety information. User roles such as Mine Administrator and Community Stakeholder are integrated into the system to provide tailored access and functionality based on each user’s needs and responsibilities.

## 4. Use Cases
MiSight supports a range of user scenarios to ensure comprehensive coverage of typical mining operation requirements:
- **Mine Administrator logging safety data**: A Mine Administrator can log safety incidents and near-misses, track trends, and generate regulatory reports.
- **Community Stakeholder accessing environmental data**: A stakeholder can view real-time environmental data, such as air quality readings, particularly when concerned about dust levels during high winds.
- **System Administrator Role Management**: A system administrator can manage user accounts, assign roles, and ensure that sensitive data is securely accessed by authorized personnel only.

## 5. Technical Specifications
MiSight is built with a Spring Boot server, using a RESTful HTTP API to communicate with the client. The backend is powered by PostgreSQL, which organizes data in tables such as Pollutants, EnvironmentalData, SafetyReports, and Mines. Client interactions with the server involve CRUD operations, managed through a secure API with role-based access.

## 6. API Endpoint Documentation
MiSight's API provides endpoints for all major operations in the system:
- **POST /api/mines**: Creates a new mine record.
- **GET /api/environmental-data**: Retrieves environmental data, filtered by pollutant type and date range.
- **POST /api/safety-reports**: Submits a safety report, including details on incidents and near misses.

Each endpoint follows RESTful principles and includes role-based access control to ensure only authorized users can perform certain actions.

## 7. Tree & Database Structure
The project follows a structured directory layout to separate core components:
```
misight/
├── src/
│   ├── main/java/com/misight/
│   │   ├── models/
│   │   ├── repositories/
│   │   ├── services/
│   │   └── controllers/
└── resources/
```
The PostgreSQL database schema consists of tables for key data entities such as Pollutants, EnvironmentalData, SafetyReports, and Mines. Each table has relationships with others to support efficient querying and reporting.

## 8. Deployment & Future Features Phases
In the final phase, MiSight will be containerized using Docker to facilitate easy deployment across different environments. Additionally, the following features are planned for future versions:
- **Web Interface**: A user-friendly web interface to complement the existing CLI.
- **Production Tracking**: Integration for tracking mining production rates and environmental impacts in real-time.
- **Public Stakeholder Portal**: A secure portal for public access to environmental data and safety reports, increasing transparency.

## 9. GitHub & CI/CD
MiSight uses GitHub for version control and collaboration. The repository follows a clear branching strategy for development, with distinct branches for features, bug fixes, and releases.

Continuous Integration and Deployment (CI/CD) is handled through GitHub Actions, automating unit tests and deployments. This ensures high code quality and streamlined updates.

## 10. Conclusion and Vision
MiSight is positioned to transform data management in mining operations by offering real-time data reporting, enhanced safety compliance, and transparent data access for stakeholders. The future of MiSight includes scaling its capabilities for broader application, enhancing regulatory compliance, and incorporating advanced features like predictive analytics and AI-driven decision support.
