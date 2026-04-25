package tech.csm.CustomerManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cust_id")
    private Integer custId;

    @Column(name="cust_name")
    private String custName;

    @Column(name="cust_add")
    private String custAdd; //address

    @Column(name="cust_phone")
    private String custPhone;

    @Column(name="cust_email")
    private String custEmail;

    @Enumerated(EnumType.STRING)
    @Column(name="cust_type")
    private CustType custType;

    public enum CustType { SILVER, GOLD, DIAMOND }

    // discount logic
    public double getDiscount() {
        return switch (custType) {
            case SILVER -> 10.0;
            case GOLD -> 15.0;
            case DIAMOND -> 20.0;
        };
    }
}
