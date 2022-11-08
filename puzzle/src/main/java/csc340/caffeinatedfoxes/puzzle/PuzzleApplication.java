package csc340.caffeinatedfoxes.puzzle;

import org.springframework.boot.SpringApplication;

//attempts to automatically configure your Spring application based on the jar dependencies that you have added (pom)
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @author smuska, sdbridges, bwoods
 * The main class for running the application.
 * Last Updated: 11/8/2022
 */
//excluding these two that cause errors related to sql and accessing pages respectively
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class  })
public class PuzzleApplication {

	public static void main(String[] args) {
//              Bootstraps a spring application as a stand-alone application using PuzzleApplication  class as the entry point
		SpringApplication.run(PuzzleApplication.class, args);
	}

}
