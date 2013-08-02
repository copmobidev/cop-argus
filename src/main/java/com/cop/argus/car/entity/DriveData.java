package com.cop.argus.car.entity;

import java.util.List;

import com.google.gson.annotations.Expose;

/**
 * 业务层所需行程数据
 * 
 * @author chris.liu
 * 
 */
public class DriveData {
	@Expose
	private DataSummary dataSummary;
	@Expose
	private List<DataPiece> dataPieces;

	public DriveData(DataSummary dataSummary, List<DataPiece> dataPieces) {
		this.dataSummary = dataSummary;
		this.dataPieces = dataPieces;
	}
}
