package com.cop.mobi.mycar.entity;

/**
 * 
 * @author zhufeng.liu
 * 
 */
public enum Span {
	PIECE(0), WEEK(1), MONTH(2), YEAR(3);

	private int span;

	Span(int span) {
		this.span = span;
	}

	public int getSpan() {
		return span;
	}
}
