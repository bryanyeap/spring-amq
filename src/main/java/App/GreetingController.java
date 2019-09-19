package App;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path="/GreetingMsg")
public class GreetingController {

    @PostMapping(path="/PostMsg", consumes="application/json", produces="application/json")
    public void postGreeting(@RequestBody Greetings greeting) {

        System.out.println("Received greeting: " + greeting.toString());

    }
}
