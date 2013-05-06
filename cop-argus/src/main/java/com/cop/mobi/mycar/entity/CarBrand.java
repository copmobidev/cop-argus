package com.cop.mobi.mycar.entity;

/**
 * 
 * @author zhufeng.liu
 * 
 */
public class CarBrand {
	private Integer id;
	private String brand; // 品牌
	private String model; // 型号
	private String engine; // 排量

	public CarBrand(String brand, String model, String engine) {
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
		return String.format("{\"id\":%d,\"brand\":\"%s\",\"engine\":\"%s\"}",
				id, brand, model, engine);
	}

	public String toLCString() {
		return String.format("%s|%s|%s", brand, model, engine);
	}
}
