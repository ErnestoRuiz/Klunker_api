package co.ReaperDev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicesMetrics {
    private double monthlyAverageOfYear;
    private double yearTotalCost;
    private double totalCost;
}
