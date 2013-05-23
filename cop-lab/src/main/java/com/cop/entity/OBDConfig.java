package com.cop.entity;

/**
 * 
 * @author chris.liu
 * 
 */
public class OBDConfig {
	public String obdProtocol;
	public String obdSerialId;
	public String vin;
	public String cid;

	@Override
	public String toString() {
		return String.format("%s\t%s\t%s\t%s", obdProtocol, obdSerialId, vin,
				cid);
	}
}
