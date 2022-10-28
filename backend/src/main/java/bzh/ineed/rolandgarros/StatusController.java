package bzh.ineed.rolandgarros;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class StatusController {
    private final AtomicLong counter = new AtomicLong();
    private static final String version = "1.0";
    private static final String template = "Welcome %s to the Roland Garros website!";

    @GetMapping("/api/status")
    public Status getStatus(@RequestParam(value = "name", defaultValue = "everyone") String name) {
        return new Status(counter.incrementAndGet(), version, String.format(template, name));
    }
}
