package co.ReaperDev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {
    private int serviceId;
    private int carId;
    private String servDetail;
    private String date;
    private double cost;
    private String servMileage;
}
