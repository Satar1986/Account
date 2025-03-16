package org.example.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Account {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CCustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id",updatable = false, nullable = false)
    private UUID id;

    @Column(name = "external_id", nullable = false,length = 50)
    private String externalId;

    @Column(name = "client_id",nullable = false,length = 20)
    private String client_id;
    @Column(name = "name_account",nullable = false,length = 20)
    private String name_account;
    @Column(name = "sum")
    private Double sum;
    @Column(name = "currency",nullable = false,length = 20)
    private String currency;
    @Column(name = "interest_rate",nullable = false)
    private Double interest_rate;
    @Column(name = "interest_is_paid",nullable = false,length = 20)
    private String interest_is_paid;
    @Column(name = "min_remainder",nullable = false)
    private Double min_remainder;
    @Column(name = "created_time")
    private Date created;
    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @Column(name = "updated_time")
    private Date updated;
    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

}
