package ie.gmit.rad;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

@ManagedBean
@ViewScoped
public class ControllerVehicle {

	private ArrayList<Vehicle> vehicles;
	DAO dao;
	// private Map<String, Object> requestMap = new HashMap<String, Object>();;
	private String simpleReg;

	public ControllerVehicle() throws Exception {
		dao = new DAO();
	}

	public void LoadVehicles() throws Exception {
		vehicles = dao.getVehicleDetails();
	}

	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(ArrayList<Vehicle> vehicle) {
		this.vehicles = vehicle;
	}

	public String getSimpleReg() {
		return simpleReg;
	}

	public void setSimpleReg(String simpleReg) {
		this.simpleReg = simpleReg;
	}

	public String addVehicles(Vehicle vehicle) throws Exception {
		DAO dao = new DAO();

		try {
			dao.addVehicle(vehicle);

		} catch (CommunicationsException e) {
			FacesMessage sqlError = new FacesMessage("Error: Cannot connect to Database");
			FacesContext.getCurrentInstance().addMessage(null, sqlError);
			return null;

		}

		catch (SQLException e) {
			FacesMessage message = new FacesMessage("1 Error: " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);

			return null;
		}

		return "list_vehicle";
	}

	public void findVehicles(FindVehicles search) throws Exception {
		vehicles = dao.getFindVehicle(search);
	}

	public String loadSimgleVehicle(String regV) throws Exception {
		VehicleDetails simgleVehicle = DAO.getSingleVehicleDetails(regV);
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

		Map<String, Object> requestMap = externalContext.getRequestMap();
		requestMap.put("thisVehicle", simgleVehicle);

		return "list_vehicle_details";
	}

}
