package ie.atu.bam;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotBlank
    private String name;

    @NotBlank(message = "Please provide a username")
    private String username;

    @Size(min = 8, message = "Password must have at least 8 characters")
    @NotBlank(message = "Please provide a password")
    private String password;

    @Min(16)
    private int age;

    @Email
    private String email;

    @NotNull
    private int phonenm;

    @NotBlank
    private String address;
}
