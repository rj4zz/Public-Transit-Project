# Public Transit Fleet Monitor

This project implements a real-time fleet monitoring system designed to ingest public transit vehicle position data, detect operational anomalies, and provide system status information.

## Key Features

*   **GTFS-Realtime Data Ingestion:** Automatically fetches and processes real-time `VehiclePosition` data from GTFS-Realtime feeds, persisting current fleet status.
*   **Anomaly Detection:**
    *   **Stalled Vehicles:** Identifies vehicles that have ceased reporting movement beyond a configurable threshold.
    *   **Vehicle Bunching:** (Planned/Defined) Framework in place for detecting vehicles that are traveling too close together.
*   **Data Persistence:** Utilizes Spring Data JPA to store vehicle position history and detected anomalies in a PostgreSQL database.
*   **RESTful API:** Exposes endpoints for monitoring system health and retrieving detected anomalies.
*   **Containerization:** Packaged with Docker for streamlined deployment and environment consistency.

## Technologies Used

*   **Backend:** Spring Boot, Java 21
*   **Build Tool:** Maven
*   **Database:** PostgreSQL
*   **Data Formats:** GTFS-Realtime (Protocol Buffers)
*   **Containerization:** Docker
*   **Testing:** JUnit 5, Testcontainers

## Setup & (Test) Run

The project can be built and run using Maven or deployed via Docker:

1.  **Build with Maven:**
    ```bash
    mvn clean package
    ```
2.  **Run with Docker:**
    First, build the application image:
    ```bash
    docker build -t fleet-monitor .
    ```
    Next, start the PostGIS database container:
    ```bash
    docker run --name transitdb -e POSTGRES_DB=fleet_monitor -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgis/postgis:latest
    ```
    Finally, run the `fleet-monitor` application, linking it to the database:
    ```bash
    docker run --name fleet-monitor-app --link fleet-monitor-db:db -e SPRING_DATASOURCE_URL="jdbc:postgresql://db:5432/fleet_monitor" -e SPRING_DATASOURCE_USERNAME="fm_user" -e SPRING_DATASOURCE_PASSWORD="mysecretpassword" -p 8080:8080 -d fleet-monitor
    ```

## Endpoints

*   `/api/status`: Retrieve the operational status of the monitoring system.
*   `/api/anomalies`: Access a list of all detected anomalies.