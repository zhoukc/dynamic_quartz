package club.javaprod.quartz.dynamic_quartz.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ViewController {

    @GetMapping("")
    public String index() {
        return "index";
    }
}