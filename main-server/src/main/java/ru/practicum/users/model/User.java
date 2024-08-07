package ru.practicum.users.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@DynamicUpdate
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(name = "user_email_unique", columnNames = "email")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    Long id;

    @Column(name = "email", nullable = false, length = 254)
    @EqualsAndHashCode.Include
    String email;

    @Column(name = "name", nullable = false, length = 250)
    String name;
}
