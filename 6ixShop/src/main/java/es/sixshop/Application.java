package es.sixshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public final static int SIZE_PAGE = 10;
	public final static String DEFAULT_PAGE = "0";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}