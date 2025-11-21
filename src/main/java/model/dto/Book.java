package model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

    private String Book_ID;
    private String Title;
    private String Author;
    private int Qty;
    private String Language;
}
