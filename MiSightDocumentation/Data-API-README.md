
# Misight: Data Model & API Documentation

## Overview
Misight’s architecture is based on an HTTP RESTful API client-server model, where the server exposes endpoints for data management, and the client interacts with these endpoints via `RestTemplate` for seamless data retrieval, addition, and reporting. This document provides detailed information on data models and REST API endpoints that form the core of the client-server interaction.

## Data Models
### Mines
- **Attributes**: `id`, `name`, `location`
- **Relationship**: Linked to monitoring stations.

### MonitoringStations
- **Attributes**: `id`, `location`
- **Relationship**: Linked to mines.

### EnvironmentalData
- **Attributes**: `id`, `pollutantId`, `stationId`, `measuredValue`

### Pollutants
- **Attributes**: `id`, `name`, `unit`, `benchmarkValue`

## Entity Relationships
1. Mines have multiple MonitoringStations.
2. MonitoringStations collect EnvironmentalData for Pollutants.

## API Endpoints
### Environmental Data Endpoints
- **GET /api/environmental-data**: Retrieve all data.
- **POST /api/environmental-data**: Add new data.
- **GET /api/environmental-data/mine/{mineId}**: Retrieve by mine.

### Safety Data Endpoints
- **GET /api/safety-data**: Retrieve all records.
- **POST /api/safety-data**: Add a new incident.

## Data Examples
**Environmental Data**:
```json
{
  "stationId": 101,
  "pollutantId": 5,
  "measuredValue": 45.7
}
```
**Safety Incident**:
```json
{
  "mineId": 3,
  "incidentType": "Near Miss",
  "lossTimeIncidents": 1
}
```

## Usage Scenarios
- Retrieve environmental data by mine: `GET /api/environmental-data/mine/{mineId}`
- Log a safety incident: `POST /api/safety-data`
