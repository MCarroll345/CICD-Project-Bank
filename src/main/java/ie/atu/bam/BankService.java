package ie.atu.bam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class BankService {
    private final ReceiptClient receiptClient;

    private final BankRepository bankRepository;
    public BankService(ReceiptClient receiptClient, BankRepository bankRepository) {
        this.receiptClient = receiptClient;
        this.bankRepository = bankRepository;
    }

    public List<BankAccount> getAll(){
        return bankRepository.findAll();
    }

    public ResponseEntity<String> transfer(int IBAN1, int IBAN2, float num){
        float newBlnc1 = 0;
        float newBlnc2 = 0;
        if(bankRepository.findBalance(IBAN1) < num){
            return new ResponseEntity<>("Insufficient balance", HttpStatus.BAD_REQUEST);
        }
        else{
            newBlnc1 = bankRepository.findBalance(IBAN1) - num;
            bankRepository.balanceUpdate(IBAN1, newBlnc1);
            newBlnc2 = bankRepository.findBalance(IBAN2) + num;
            bankRepository.balanceUpdate(IBAN2, newBlnc2);
            receiptClient.transferRec(IBAN1, IBAN2, num);
            return new ResponseEntity<>("Transfer successful ", HttpStatus.OK);
        }
    }

    public String createA(Long uID){
        BankAccount bankAccount = new BankAccount();
        int randIBAN = ((int)(Math.random() * 9001) + 1000);
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

    public List<Object> returnRecs(Long uID){
        return receiptClient.getRecs(uID);
    }

    public List<Object> loginAcc(Long uID){
        return bankRepository.findByuID(uID);
    }

    public ResponseEntity<String> withDep(int IBAN, String inout, float num){
        float newBlnc;
        switch (inout){
            case "withdraw":
                if(num > bankRepository.findBalance(IBAN)){
                    return new ResponseEntity<>("Insufficient balance", HttpStatus.BAD_REQUEST);
                }
                else{
                    newBlnc = bankRepository.findBalance(IBAN) - num;
                    bankRepository.balanceUpdate(IBAN, newBlnc);
                    receiptClient.createReceipt(IBAN,inout,num);
                    return new ResponseEntity<>("Withdrawal made, new balance: " + newBlnc, HttpStatus.OK);

                }

            case "deposit":
                newBlnc = bankRepository.findBalance(IBAN) + num;
                bankRepository.balanceUpdate(IBAN, newBlnc);
                receiptClient.createReceipt(IBAN,inout,num);
                return new ResponseEntity<>("Deposit made, new balance: " + newBlnc, HttpStatus.OK);

            default:
                return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }

}

