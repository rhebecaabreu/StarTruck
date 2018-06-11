package com.example.progmobile.startruck.model.bean;

public class Vehicle {
    //plate, year of manufacture, mark, type of fuel, color
    private int id;
    private String typeVehicle;
    private String plate;
    private int yearManufacture;
    private String mark;
    private String fuelType; //gasoline, ethanol, diesel, natural gas
    private String color;
    private int axesNumber;
    private int capacity;
    private String observation;
    private int userId;


    public Vehicle(){

    }

    public Vehicle(int id, String typeVehicle, String plate, int yearManufacture, String mark, String fuelType, String color, int axesNumber, int capacity, String observation, int userId) {
        this.id = id;
        this.typeVehicle = typeVehicle;
        this.plate = plate;
        this.yearManufacture = yearManufacture;
        this.mark = mark;
        this.fuelType = fuelType;
        this.color = color;
        this.axesNumber = axesNumber;
        this.capacity = capacity;
        this.observation = observation;
        this.userId = userId;
    }


    public int getId() {
        return id;
    }


    public String getTypeVehicle() {
        return typeVehicle;
    }

    public void setTypeVehicle(String typeVehicle) {
        this.typeVehicle = typeVehicle;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public int getYearManufacture() {
        return yearManufacture;
    }

    public void setYearManufacture(int yearManufacture) {
        this.yearManufacture = yearManufacture;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAxesNumber() {
        return axesNumber;
    }

    public void setAxesNumber(int axesNumber) {
        this.axesNumber = axesNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

