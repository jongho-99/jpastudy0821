package com.kh.app08_1.state;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name="STATE")
public class StateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stateNo;

    private String stateName;


}
