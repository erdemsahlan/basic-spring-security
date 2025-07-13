package com.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String productDescription;
    private boolean active;
    @Column(nullable = true)
    private long totalAmounth;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreatedDate;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date UpdateDate;
}
