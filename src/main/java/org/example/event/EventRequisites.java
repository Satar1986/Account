package org.example.event;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequisites {
    private String externalId;
    private String clientId;
    private String nameCompany;
    private Long inn;
    private Integer kpp;
    private Long ogrn;
    private String businessAddress;
    private String address;
    private Integer rcbic;
    private BigDecimal corrAss;
    private BigDecimal ass;
    private String bankName;
    private Integer requisitesId;
}
