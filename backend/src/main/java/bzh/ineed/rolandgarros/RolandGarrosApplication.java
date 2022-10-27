package bzh.ineed.rolandgarros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RestController
public class RolandGarrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(RolandGarrosApplication.class, args);
	}

	// Toutes les requêtes du serveur localhost:9000 vers /api sont autorisés
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**").allowedOrigins("http://localhost:9000");
			}
		};
	}

	@GetMapping("/api/welcome")
	public String hello(@RequestParam(value = "name", defaultValue = "") String name) {
		return String.format("Welcome %s to the 🎾 Roland Garros website!", name);
	}

}
