package com.cop.mobi.mycar.entity;

/**
 * 
 * @author zhufeng.liu
 * 
 */
public class DateSpan {

	private Span span;
	private long beginTime;
	private long endTime;

	public DateSpan(Span span, long beginTime, long endTime) {
		this.span = span;
		this.beginTime = beginTime;
		this.endTime = endTime;
	}

	public DateSpan(int span, long beginTime, long endTime) {
		this.beginTime = beginTime;
		this.endTime = endTime;
		switch (span) {
		case 1:
			this.span = Span.WEEK;
			break;
		case 2:
			this.span = Span.MONTH;
			break;
		case 3:
			this.span = Span.YEAR;
			break;
		default:
			this.span = Span.PIECE;
			break;
		}
	}

	public Span getSpan() {
		return span;
	}

	public void setSpan(Span span) {
		this.span = span;
	}

	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return String.format("%s(%d-%d)", span, beginTime, endTime);
	}

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
}
