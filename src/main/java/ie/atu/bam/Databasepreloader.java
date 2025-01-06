package ie.atu.bam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Databasepreloader implements CommandLineRunner {
    private final BankRepository bankRepository;
    public Databasepreloader(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        BankAccount bank1 = new BankAccount(0,1234, 1L);
        BankAccount bank2 = new BankAccount(100,2345, 2L);
        BankAccount bank3 = new BankAccount(100000000,3456, 3L);
        BankAccount bank4 = new BankAccount(100,4567, 4L);
        bankRepository.save(bank1);
        bankRepository.save(bank2);
        bankRepository.save(bank3);
        bankRepository.save(bank4);
    }
}
