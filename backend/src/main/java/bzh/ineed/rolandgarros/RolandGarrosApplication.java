package bzh.ineed.rolandgarros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RolandGarrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(RolandGarrosApplication.class, args);
	}

	@GetMapping("/welcome")
		public String hello(@RequestParam(value = "name", defaultValue = "") String name) {
		return String.format("Welcome %s to the 🎾 Roland Garros website!", name);
	}

}
