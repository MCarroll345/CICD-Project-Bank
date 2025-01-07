package ie.atu.bam;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private float balance;

    private int IBAN;

    private Long uID;

    public BankAccount(float iBal, int iIBAN, Long iUID){
        balance = iBal;
        IBAN = iIBAN;
        uID = iUID;
    }

}
