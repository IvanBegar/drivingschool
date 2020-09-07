package com.begar.demo.dto.response;

public class IncomeForPeriodDTO {

    private double incomeForPeriod;

    public IncomeForPeriodDTO(double incomeForPeriod) {
        this.incomeForPeriod = incomeForPeriod;
    }

    public IncomeForPeriodDTO() {
    }

    public double getIncomeForPeriod() {
        return incomeForPeriod;
    }

    public void setIncomeForPeriod(double incomeForPeriod) {
        this.incomeForPeriod = incomeForPeriod;
    }
}
