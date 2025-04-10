package org.example.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountEvent {
    private UUID id;
    private String externalId;
    private String clientId;
    private String nameAccount;
    private Double sum;
    private String currency;
    private Double interestRate;
    private String interestIsPaid;
    private Double minRemainder;
    private Date created;
    private Date updated;
}
