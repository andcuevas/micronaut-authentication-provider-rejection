package micronaut.custom.authentication;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller
public class RootController {
    @Get("/")
    String hello() {
        return "ok";
    }
}
