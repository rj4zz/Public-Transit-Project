package com.transitmonitor.fleet.fleet_monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FleetMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FleetMonitorApplication.class, args);
	}

}
