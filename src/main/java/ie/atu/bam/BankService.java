package ie.atu.bam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class BankService {

    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository){
        this.bankRepository = bankRepository;
    }

    public List<BankAccount> getAll(){
        return bankRepository.findAll();
    }

    public String createA(Long uID){
        BankAccount bankAccount = new BankAccount();
        int randIBAN = (int)(Math.random() * 10001);
        if (bankRepository.existsByuID(uID)){
            return "Account already exists";
        }

        while(!bankRepository.existsByIBAN(randIBAN)){
            bankAccount.setBalance(0);
            bankAccount.setUID(uID);
            bankAccount.setIBAN(randIBAN);
            bankRepository.save(bankAccount);
            return "Account Created";
        }

        return null;
    }

    public List<Object> loginAcc(Long uID){
        return bankRepository.findByuID(uID);
    }

    public ResponseEntity<String> withDep(Long uID, String inout, float num){
        float newBlnc = 0;
        switch (inout){
            case "withdraw":
                if(num > bankRepository.findBalance(uID)){
                    return new ResponseEntity<>("Insufficient balance", HttpStatus.BAD_REQUEST);
                }
                else{
                    newBlnc = bankRepository.findBalance(uID) - num;
                    bankRepository.balanceUpdate(uID, newBlnc);
                    return new ResponseEntity<>("Withdrawal made, new balance: " + newBlnc, HttpStatus.OK);
                }

            case "deposit":
                newBlnc = bankRepository.findBalance(uID) + num;
                bankRepository.balanceUpdate(uID, newBlnc);
                return new ResponseEntity<>("Deposit made, new balance: " + newBlnc, HttpStatus.OK);

            default:
                return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }

}

