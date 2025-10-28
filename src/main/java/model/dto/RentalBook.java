package model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RentalBook {

    private String Rental_ID;
    private String Cust_ID;
    private String Book_ID;
    private Date Rental_Date;
    private Date Due_Date;
    private Date Return_Date;
    private int Overdue_Days;
    private double Fine_Amount;
}
