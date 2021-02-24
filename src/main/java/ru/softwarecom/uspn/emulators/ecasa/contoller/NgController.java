package ru.softwarecom.uspn.emulators.ecasa.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NgController {

    @SuppressWarnings({"RegExpRedundantEscape", "MVCPathVariableInspection", "SpringMVCViewInspection"})
    @GetMapping(
            value = "/**/{[path:[^\\.]*}"//,

            // Запрос должен содержать заголовок "Accept" со значением "text/html"
//            produces = "text/html"
    )
    public String redirect() {
        // Forward to home page so that route is preserved.
        return "forward:/";
    }
}
