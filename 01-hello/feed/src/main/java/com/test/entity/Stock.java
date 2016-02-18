package com.test.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 股票 实体类
 */
public class Stock implements Serializable {

	private static final long serialVersionUID = -8334804402463267285L;

	protected String symbol; //代码
	protected String name;  //名称
    protected double open;  //今开
    protected double change; //涨跌幅度
    protected double last;  //最新价
    protected double low;   //最低
    protected double high;  //最高
    protected Date date;    //时间

    public Stock() {
    }

    public Stock(String symbol, String name, double open) {
        this.symbol = symbol;
        this.name = name;
        this.open = open;
        this.high = open;
        this.low = open;
        this.last = open;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", low=" + low +
                ", high=" + high +
                ", open=" + open +
                ", last=" + last +
                ", change=" + change +
                ", date=" + date +
                '}';
    }

    public double getChange() {
		return change;
	}
	public void setChange(double change) {
		this.change = change;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLast() {
		return last;
	}
	public void setLast(double last) {
		this.last = last;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}