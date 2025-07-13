package com.backend.entity;

import com.backend.enums.AlisSatis;
import com.backend.enums.OdemeTip;
import com.backend.enums.ParaTipi;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(name = "product_in_or_out",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"customer_id", "product_id", "created_at"})
        })
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ProductInOrOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;
    private long kilograms;
    private long price;
    @Enumerated(EnumType.STRING)
    private OdemeTip odemeTip;
    @Enumerated(EnumType.STRING)
    private AlisSatis alisSatis;
    @Enumerated(EnumType.STRING)
    private ParaTipi paraTipi;
    private double dovizKuru;
    private boolean active = true;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
