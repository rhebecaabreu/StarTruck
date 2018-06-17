package com.example.progmobile.startruck.model.bean;

public class Vehicle {
    //plate, year of manufacture, mark, type of fuel, color
    private int id;
    private String nameVehicle;
    private String typeVehicle;
    private String plate;
    private String mark;
    private String fuelType; //gasoline, ethanol, diesel, natural gas
    private int axesNumber;
    private int capacity;
    private String status;
    private String observation;
    private int userId;

    public Vehicle(){

    }
    public Vehicle(String name, String type, String plate, String mark, String status){
        this.nameVehicle = name;
        this.typeVehicle = type;
        this.plate = plate;
        this.mark = mark;
        this.status = status;
    }

    public Vehicle(String name, String type, String plate, String mark) {
        this.nameVehicle = name;
        this.typeVehicle = type;
        this.plate = plate;
        this.mark = mark;
    }

    public Vehicle(String name, String type, String plate, String mark, String status, int axesNumber, int capacity, String observation, String fuel) {
        this.nameVehicle = name;
        this.typeVehicle = type;
        this.plate = plate;
        this.mark = mark;
        this.status = status;
        this.axesNumber = axesNumber;
        this.capacity = capacity;
        this.observation = observation;
        this.fuelType = fuel;
    }

    public Vehicle(String nameVehicle, String typeVehicle) {
        this.nameVehicle = nameVehicle;
        this.typeVehicle = typeVehicle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNameVehicle() {
        return nameVehicle;
    }

    public void setNameVehicle(String nameVehicle) {
        this.nameVehicle = nameVehicle;
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

    public void setId(int id) {
        this.id = id;
    }
}

