package atm.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//controller class to serve ui
@Controller
public class AtmUIController {
    
    //ui html code mapping
    @RequestMapping("/index")
    public String index() throws Exception {
        return "index";
    }
}
