package com.cop.argus.car.entity;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author chris.liu
 * 
 */
public class CarBrand {
	private int id; // 品牌ID
	@Expose
	private String manufacturer; // 制造商
	@Expose
	private String brand; // 品牌
	@Expose
	private String model; // 型号
	@Expose
	private double engine; // 排量
	@Expose
	private String color; // 车体颜色
	@Expose
	private double configParam; // 配置参数

	public CarBrand(int id, String manufacturer, String brand, String model,
			double engine, String color, double configParam) {
		this.id = id;
		this.manufacturer = manufacturer;
		this.brand = brand;
		this.model = model;
		this.engine = engine;
		this.color = color;
		this.configParam = configParam;
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
