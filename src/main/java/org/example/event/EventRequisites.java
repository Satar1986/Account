package org.example.event;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequisites {
    private String externalId;
    private String client_id;
    private String name_company;
    private Long inn;
    private Integer kpp;
    private Long ogrn;
    private String business_address;
    private String address;
    private Integer rcbic;
    private Double corr_ass;
    private Double ass;
    private String bank_name;
}
