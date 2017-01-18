package ie.gmit.rad;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean
public class Vehicle {

	private String reg;
	private String manuCode;
	private String modelCode;
	private int mileage;
	private float price;
	private String colour;
	private String fuel;

	private String manuName;
	private String modelName;

	public Vehicle() {

	}

	public Vehicle(String reg, String manuCode, String modelCode, int mileage, float price, String colour,
			String fuel) {
		this.reg = reg;
		this.manuCode = manuCode;
		this.modelCode = modelCode;
		this.mileage = mileage;
		this.price = price;
		this.colour = colour;
		this.fuel = fuel;
	}



	public Vehicle(String reg, String manuCode, String modelCode, int mileage, float price, String colour, String fuel,
			String manuName, String modelName) {
		this.reg = reg;
		this.manuCode = manuCode;
		this.modelCode = modelCode;
		this.mileage = mileage;
		this.price = price;
		this.colour = colour;
		this.fuel = fuel;
		this.manuName = manuName;
		this.modelName = modelName;
	}


	public String getReg() {
		return reg;
	}

	public void setReg(String reg) {
		this.reg = reg;
	}

	public String getManuCode() {
		return manuCode;
	}

	public void setManuCode(String manuCode) {
		this.manuCode = manuCode;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getManuName() {
		return manuName;
	}

	public void setManuName(String manuName) {
		this.manuName = manuName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModel_name(String modelName) {
		this.modelName = modelName;
	}


}
