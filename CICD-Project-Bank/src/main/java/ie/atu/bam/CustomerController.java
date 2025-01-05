package ie.atu.bam;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getAll")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer) {
        if (customerService.createCustomer(customer) == 1) {
            return new ResponseEntity<>("Person created successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Username taken", HttpStatus.PRECONDITION_FAILED);
        }
    }

    @GetMapping("/login/{usrnm}/{psswrd}")
    public List<Customer> loginCust(@PathVariable String usrnm,@PathVariable String psswrd){
        return customerService.loginCust(usrnm,psswrd);
    }

    @PutMapping("withDep/usrnm/{inout}/{num}")
    public ResponseEntity<?> withdrawDeposit(@PathVariable String usrnm,@PathVariable String inout, @PathVariable int num){
        if(num <= 0){
            return new ResponseEntity<String>("Cannot be a negative number", HttpStatus.BAD_REQUEST);
        }
        switch (customerService.withDep(usrnm,inout,num)){
            case
        }

    }


    @PutMapping("/updateAddress/{oldaddress}/{newaddress}")
    public ResponseEntity<?> updateAddress(@PathVariable String oldaddress, @PathVariable String newaddress){
        if(customerService.updateAddress(oldaddress,newaddress)==1){
            return new ResponseEntity<>("Update Successful", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Update failed", HttpStatus.PRECONDITION_FAILED);
        }
    }

    @PutMapping("/updateEmail/{oldemail}/{newemail}")
    public ResponseEntity<?> updateEmail(@PathVariable String oldemail, @PathVariable String newemail){
        if(customerService.updateEmail(oldemail,newemail)==1){
            return new ResponseEntity<>("Update Successful", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Update failed", HttpStatus.PRECONDITION_FAILED);
        }
    }

    @PutMapping("/updatePhonenm/{oldphonenm}/{newphonenm}")
    public ResponseEntity<?> updatePhonenm(@PathVariable int oldphonenm, @PathVariable int newphonenm){
        if(customerService.updatePhonenm(oldphonenm,newphonenm)==1){
            return new ResponseEntity<>("Update Successful", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Update failed", HttpStatus.PRECONDITION_FAILED);
        }
    }

}
