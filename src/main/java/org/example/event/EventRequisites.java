package org.example.event;
import lombok.*;

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
    private Double corrAss;
    private Double ass;
    private String bankName;
    private Integer requisitesId;
}
