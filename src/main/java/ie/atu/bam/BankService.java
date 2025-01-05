package ie.atu.bam;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

        while(!bankRepository.existsByIBAN(randIBAN)){
            bankAccount.setBalance(0);
            bankAccount.setUID(uID);
            bankAccount.setIBAN(randIBAN);
            bankRepository.save(bankAccount);
            return "Account Created";
        }

        return null;
    }

    public List<BankAccount> loginAcc(Long uID){
        return bankRepository.findByuID(uID);
    }

}

