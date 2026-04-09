import java.util.*;
import java.time.LocalDate;

public class Vehicle {
    private int vehicleID;
    private String vehicleNumber;
    private VehicleType vehicleType;
    private double dailyRentalCost;
    private VehicleStatus vehicleStatus;

    /* String vehicleCompany;
    String vehicleModelName;
    int hourlyRentalCost;
    int kmDriven;
    int average;
    int cc;
    int numOfSeat;
    */

    public Vehicle(int vehicleID, String vehicleNumber, VehicleType vehicleType) {
        this.vehicleID = vehicleID;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.vehicleStatus = VehicleStatus.AVAILABLE;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public double getDailyRentalCost() {
        return dailyRentalCost;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setDailyRentalCost(double dailyRentalCost) {
        this.dailyRentalCost = dailyRentalCost;
    }


    public void setStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }
}