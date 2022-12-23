package fr.y0annd.boutique.app.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Rate {
	
	private IntegerProperty mValue;
	private IntegerProperty mMax;
	private DoubleProperty mRate;
	
	public Rate() {
		mValue = new SimpleIntegerProperty();
		mMax = new SimpleIntegerProperty();
		mRate = new SimpleDoubleProperty();
	}
	
	public Rate(int value, int max) {
		mValue = new SimpleIntegerProperty(value);
		mMax = new SimpleIntegerProperty(max);
		mRate = new SimpleDoubleProperty();
		mRate.bind(mValue.divide(mMax.doubleValue()));
	}

	public int getValue() {
		return mValue.get();
	}
	
	public IntegerProperty getValueProperty() {
		return mValue;
	}
	
	public void setValue(int val) {
		mValue.set(val);
	}
	
	public int getMax() {
		return mMax.get();
	}
	
	public IntegerProperty getMaxProperty() {
		return mMax;
	}
	public void setMax(int val) {
		mMax.set(val);
	}
	
	public double getRate() {
		return mRate.get();
	}
	
	public void setRate(double val) {
		mRate.set(val);
	}
	
	public DoubleProperty getRateProperty() {
		return mRate;
	}
}
