package model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private String User_ID;
    private String Name;
    private String Password;
    private String Role;
}
