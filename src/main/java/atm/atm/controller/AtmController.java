package atm.atm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import atm.atm.service.AtmService;

//controller for rest service
@RestController
@RequestMapping("/atm")
public class AtmController {
    //autowire atmservice to create instance of the service class
    @Autowired
    private AtmService atmService;

    //global exception handler for this class
    @ExceptionHandler({ Exception.class })
    public String handleException(Exception exception) {
        //
        return exception.getMessage();
    }

    //get api to dispense amount. amount is fetched from path variable
    @GetMapping("dispense/{amount}")
    public HashMap<Integer,Integer> getCash(@PathVariable int amount) throws Exception {
        return atmService.dispense(amount);
    }

    
}
