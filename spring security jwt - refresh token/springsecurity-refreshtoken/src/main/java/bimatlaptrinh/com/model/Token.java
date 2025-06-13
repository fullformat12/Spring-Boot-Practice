package bimatlaptrinh.com.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Token {
    @Id
    @GeneratedValue
    private Long id;
    private String token;          // refresh JWT
    private boolean revoked;
    private Date expiryDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}