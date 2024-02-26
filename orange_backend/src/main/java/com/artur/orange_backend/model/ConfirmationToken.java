package com.artur.orange_backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "confirmation_token_table")
public class ConfirmationToken {
    @Id
    @SequenceGenerator(
            name = "confirmation_token_id",
            sequenceName = "confirmation_token_id",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "confirmation_token_id",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;
}
