package csc340.caffeinatedfoxes.puzzle;

import org.springframework.boot.SpringApplication;

//attempts to automatically configure your Spring application based on the jar dependencies that you have added (pom)
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PuzzleApplication {

	public static void main(String[] args) {
//              Bootstraps a spring application as a stand-alone application using PuzzleApplication  class as the entry point
		SpringApplication.run(PuzzleApplication.class, args);
	}

}
