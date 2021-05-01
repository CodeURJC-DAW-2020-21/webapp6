package es.sixshop;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static final int SIZE_PAGE = 10;
	public static final String DEFAULT_PAGE = "0";
	
	public static final Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "products");
	public static final String PRODUCTS_FOLDER = "imageProducts";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}