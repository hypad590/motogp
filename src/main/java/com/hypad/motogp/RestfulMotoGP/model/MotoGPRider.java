package com.hypad.motogp.RestfulMotoGP.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_motogpdriver")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotoGPRider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "pos")
    private Integer position;
    @Column(name = "riderName")
    private String riderName;
    @Column(name = "wins")
    private Integer wins;
}
