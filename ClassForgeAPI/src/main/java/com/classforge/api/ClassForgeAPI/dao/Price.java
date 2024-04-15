package com.classforge.api.ClassForgeAPI.dao;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Prices")
public class Price {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "price")
    private Double price;

    @Column(name = "timestamp")
    private java.sql.Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name="relation_id")
    private teacher_student relation;


}
