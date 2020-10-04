package co.ReaperDev.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarEntity {
    private int carId;
    private int userId;
    private String make;
    private String model;
    private String year;
    private int mpg;
}

