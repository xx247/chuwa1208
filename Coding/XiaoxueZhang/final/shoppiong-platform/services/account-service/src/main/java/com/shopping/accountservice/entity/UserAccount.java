package com.shopping.accountservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "user_accounts")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserAccount {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true, length = 320)
    private String email;

    @Column(nullable = false, length = 60)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Address> addresses = new ArrayList<>();

    public Optional<Address> getShippingAddress() {
        return addresses.stream().filter(a -> a.getType() == AddressType.SHIPPING).findFirst();
    }

    public Optional<Address> getBillingAddress() {
        return addresses.stream().filter(a -> a.getType() == AddressType.BILLING).findFirst();
    }
}