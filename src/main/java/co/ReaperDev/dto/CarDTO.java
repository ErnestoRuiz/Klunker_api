package co.ReaperDev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private int carId;
    private int userId;
    private String make;
    private String model;
    private String year;
    private int mpg;
}
