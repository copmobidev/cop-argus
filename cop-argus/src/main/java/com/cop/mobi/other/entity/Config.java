package com.cop.mobi.other.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author chris.liu
 * 
 */
public class Config {
	private List<NameValuePair> params = new ArrayList<NameValuePair>();

	public Config() {

	}

	public void addConfig(NameValuePair param) {
		this.params.add(param);
	}

	@Override
	public String toString() {
		String[] tmp = new String[params.size()];
		for (int i = 0; i < params.size(); ++i) {
			NameValuePair pair = params.get(i);
			tmp[i] = String.format("\"%s\":\"%s\"", pair.getKey(),
					pair.getVal());
		}
		return String.format("{%s}", StringUtils.join(tmp));
	}
}
