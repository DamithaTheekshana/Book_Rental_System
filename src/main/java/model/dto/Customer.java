package model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {

    private String Cust_ID;
    private String Name;
    private String Phone;
    private String Email;
    private String Address;
}
