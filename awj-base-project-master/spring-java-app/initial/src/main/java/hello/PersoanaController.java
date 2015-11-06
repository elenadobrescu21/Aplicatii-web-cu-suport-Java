package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersoanaController {

    private static final String template = "Hello, %s!";
    //private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/persoana")
    public Persoana persoana(@RequestParam(value="prenume", defaultValue="Elena") String prenume) {
		
        return new Persoana("dobrescu",String.format(template, prenume) );
                           
    }
}
