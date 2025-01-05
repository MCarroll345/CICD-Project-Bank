package ie.atu.bam;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update Customer c set c.address = ?2 where c.address =?1")
    int addressUpdate(String oldaddress, String newaddress);

    @Transactional
    @Modifying
    @Query("update Customer c set c.phonenm = ?2 where c.phonenm =?1")
    int phonenmUpdate(int oldphonenm, int newphonenm);

    @Transactional
    @Modifying
    @Query("update Customer c set c.email = ?2 where c.email =?1")
    int emailUpdate(String oldemail, String newemail);

    @Query("select c.bankBalance from Customer c where c.username = ?1")
    float balanceReturn(String usrnm);

    @Transactional
    @Modifying
    @Query("update Customer c set c.bankBalance = ?1 where c.username =?2")
    int newBalance(float nb, String usrnm);

    boolean existsByUsernameAndPassword(String username, String password);


}
