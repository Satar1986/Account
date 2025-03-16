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
    private long inn;
    private int kpp;
    private long ogrn;
    private String business_address;
    private String address;
    private int rcbic;
    private float corr_ass;
    private float ass;
    private String bank_name;
}
