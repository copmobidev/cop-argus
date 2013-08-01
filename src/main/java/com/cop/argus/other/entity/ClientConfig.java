package com.cop.argus.other.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cop.argus.common.util.DataFormater;
import com.google.gson.annotations.Expose;

/**
 * 
 * @author chris.liu
 * 
 */
public class ClientConfig {

	@Expose
	private List<String> names;

	@Expose
	private String name;

	@Expose
	private Integer uid;

	private Double age;

	@Expose
	private List<Feedback> feedbacks;

	public ClientConfig(List<String> names, String name, Integer uid,
			List<Feedback> feedbacks) {
		this.names = names;
		this.name = name;
		this.uid = uid;
		this.age = 1.0;
		this.feedbacks = feedbacks;
	}

	public static void main(String[] args) {
		List<String> names = new ArrayList<String>();
		names.add("chris");
		names.add("lily");

		List<Feedback> feedbacks = new ArrayList<Feedback>();
		Feedback fd = new Feedback(1, "not bad", new Date().getTime());
		feedbacks.add(fd);
		ClientConfig cc = new ClientConfig(names, "test", 1, feedbacks);

		System.out.println(DataFormater.format(cc));
	}
}
