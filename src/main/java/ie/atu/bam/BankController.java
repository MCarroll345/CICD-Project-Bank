package ie.atu.bam;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/bank")
@RestController
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/getAll")
    public List<BankAccount> get(){
        return bankService.getAll();
    }

    @PostMapping("/create/{uID}")
    public String makeAccount(@PathVariable Long uID){
        System.out.println("Response gotten");
        bankService.createA(uID);
        return "Account created";
    }

    @GetMapping
    public List<BankAccount> loginAcc(Long uID){
        return bankService.loginAcc(uID);
    }

}
