package com.cop.mobi.mycar.entity;

/**
 * 
 * @author zhufeng.liu
 * 
 */
public class CarBrand {
	private Integer id;
	private String manufacturer; // 制造商
	private String brand; // 品牌
	private String model; // 型号
	private String engine; // 排量

	public CarBrand() {

	}

	public CarBrand(String manufacturer, String brand, String model,
			String engine) {
		this.manufacturer = manufacturer;
		this.brand = brand;
		this.model = model;
		this.engine = engine;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	@Override
	public String toString() {
		return String
				.format("{\"id\":%d,\"manufacturer\":\"%s\",\"brand\":\"%s\",\"engine\":\"%s\"}",
						id, manufacturer, brand, model, engine);
	}

	public String toLCString() {
		return String.format("%s|%s|%s|%s", manufacturer, brand, model, engine);
	}
}
