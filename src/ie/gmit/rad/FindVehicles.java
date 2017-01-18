package ie.gmit.rad;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class FindVehicles {
	private String priceType;
	private Integer price;
	private String colour;
	private String fuel;

	public FindVehicles() {

	}

	public FindVehicles(String priceType, Integer price, String colour, String fuel) {
		this.priceType = priceType;
		this.price = price;
		this.colour = colour;
		this.fuel = fuel;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
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

}
