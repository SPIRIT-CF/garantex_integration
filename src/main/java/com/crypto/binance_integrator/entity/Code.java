package com.crypto.binance_integrator.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "codes", uniqueConstraints = {
        @UniqueConstraint(name = "name_unique", columnNames = "name")
})
public class Code {

    public Code(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "code", fetch = FetchType.LAZY)
    private List<Price> prices;


}
