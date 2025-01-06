package ie.atu.bam;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("/bank")
@RestController
@CrossOrigin
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
    public String makeAccount(@Valid @PathVariable Long uID){
        System.out.println("Response gotten");
        bankService.createA(uID);
        return "Account created";
    }

    @GetMapping("/login/{uID}")
    public List<Object> loginAcc(@Valid @PathVariable Long uID){
        return bankService.loginAcc(uID);
    }

    @GetMapping("/getRecs/{uID}")
    public List<Object> getRecs(@PathVariable Long uID){
        return bankService.returnRecs(uID);
    }

    @PutMapping("/transfer/{IBAN1}/{IBAN2}/{num}")
    public ResponseEntity<String> transferRec(@PathVariable int IBAN1, @PathVariable int IBAN2,@PathVariable float num){
        return bankService.transfer(IBAN1, IBAN2, num);
    }

    @PutMapping("withDep/{uID}/{inout}/{num}")
    public ResponseEntity<String> withdrawDeposit(@Valid @PathVariable Long uID,@PathVariable String inout, @PathVariable float num){
        if(num <= 0){
            return new ResponseEntity<>("Cannot be a negative number", HttpStatus.BAD_REQUEST);
        }
        return bankService.withDep(uID, inout, num);
    }



}
