package com.cop.argus.car.service.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class CarBrandPo {
	private int id;
	private String manufacturer;
	private String brand;
	private String model;
	private double engine;
	private String color;
	private double configParam;

	public CarBrandPo() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getEngine() {
		return engine;
	}

	public void setEngine(double engine) {
		this.engine = engine;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getConfigParam() {
		return configParam;
	}

	public void setConfigParam(double configParam) {
		this.configParam = configParam;
	}
}
