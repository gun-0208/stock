package com.model;

import java.time.LocalDate;

public class Stock {
	private LocalDate date;
	private int close;
	private int diff;

	private int open;
	private int high;
	private int low;
	private int volume;

	public Stock(Builder builder) {
		this.date = builder.date;
		this.diff = builder.diff;
		this.close = builder.close;
		this.open = builder.open;
		this.high = builder.high;
		this.low = builder.low;
		this.volume = builder.volume;
	}
	
	@Override
	public String toString() {
		return "Stock [date=" + date + ", close=" + close + ", diff=" + diff + ", open=" + open + ", high=" + high
				+ ", low=" + low + ", volume=" + volume + "]";
	}

	public static class Builder {
		private LocalDate date;
		private int close;
		private int diff;
		private int open;
		private int high;
		private int low;
		private int volume;

		public Builder() {

		}

		public Builder date(LocalDate date) {
			this.date = date;
			return this;
		}

		public Builder diff(int diff) {
			this.diff = diff;
			return this;
		}

		public Builder open(int open) {
			this.open = open;
			return this;
		}
		
		public Builder close(int close) {
			this.close = close;
			return this;
		}

		public Builder high(int high) {
			this.high = high;
			return this;
		}

		public Builder low(int low) {
			this.low = low;
			return this;
		}
		
		public Builder volume(int volume) {
			this.volume = volume;
			return this;
		}
		
		public Stock build() {
			return new Stock(this);
		}
	}
	public LocalDate getDate() {
		return date;
	}
	
	public int getClose() {
		return close;
	}
	
	public int getDiff() {
		return diff;
	}
	
	public int getOpen() {
		return open;
	}
	
	public int getHigh() {
		return high;
	}
	
	public int getLow() {
		return low;
	}
	
	public int getVolume() {
		return volume;
	}
}
