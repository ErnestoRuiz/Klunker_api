package co.ReaperDev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicesMetrics {
    private int carId;
    private double totalCostAllServices;
    private double totalCostPastTwelveMo;
    private double pastTwelveMonthAverage;

    private double totalCostCurrentYear;
    private double totalCostLastYear;
    private double totalCostTwoYearsAgo;
    private double totalCostThreeYearsAgo;
    private double totalCostFourYearsAgo;
    private double totalCostFiveYearsAgo;

    private double monthlyAverageCurrentYear;
    private double monthlyAverageLastYear;
    private double monthlyAverageTwoYearsAgo;
    private double monthlyAverageThreeYearsAgo;
    private double monthlyAverageFourYearsAgo;
    private double monthlyAverageFiveYearsAgo;

    private double fiveYearTotalCost;
}
