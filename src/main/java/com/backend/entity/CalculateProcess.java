package com.backend.entity;

import com.backend.enums.OdemeTip;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

//@Entity
//@Audited
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalculateProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long kilograms;
    private String description;
    private long price;
    @Enumerated(EnumType.ORDINAL)
    private OdemeTip odemeTip;
    @OneToOne(cascade = CascadeType.ALL)
    private Product product;
    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreatedDate;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date UpdateDate;
}
