package org.example.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.*;




@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "state")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name_state",nullable = false,length = 20)
    private String name;
}
