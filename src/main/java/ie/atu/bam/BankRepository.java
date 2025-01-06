package ie.atu.bam;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface BankRepository extends JpaRepository<BankAccount, Long> {

    boolean existsByIBAN(int IBAN);

    boolean existsByuID(Long uID);

    List<Object> findByuID(Long uID);

    @Query("select b.balance from BankAccount b where b.IBAN= ?1")
    float findBalance(int IBAN);

    @Query("select b.IBAN from BankAccount b where b.Id = ?1")
    int findIBAN(Long uID);

    @Transactional
    @Modifying
    @Query("update BankAccount b set b.balance = ?2 where b.IBAN =?1")
    int balanceUpdate(int IBAN, float newBlnc);

}
