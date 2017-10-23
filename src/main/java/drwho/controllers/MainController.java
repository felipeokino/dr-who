package drwho.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @RequestMapping("/v1/")
    @ResponseBody
    public String index() {
        return "Proudly handcrafted by dr-who" +
                ":)";
    }

}
