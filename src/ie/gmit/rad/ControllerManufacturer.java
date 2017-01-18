package ie.gmit.rad;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

@ManagedBean
@ViewScoped
public class ControllerManufacturer {

	private ArrayList<Manufacturer> manufacturers;
	DAO dao;

	public ControllerManufacturer() throws Exception {
		dao = new DAO();
	}

	public void LoadManufacturers() throws Exception {
		manufacturers = dao.getManufacturerDetails();
	}

	public ArrayList<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(ArrayList<Manufacturer> manufacturer) {
		this.manufacturers = manufacturer;
	}

	public String addManufacturers(Manufacturer manufacturer) throws Exception {
		DAO dao = new DAO();

		try {
			dao.addManufacturer(manufacturer);

		} catch (CommunicationsException e) {
			FacesMessage sqlError = new FacesMessage("Error: Cannot connect to Database");
			FacesContext.getCurrentInstance().addMessage(null, sqlError);
			return null;

		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			FacesMessage sqlError = new FacesMessage(
					"Error: Duplicate entry " + manufacturer.getManuCode() + " for key PRIMARY");
			FacesContext.getCurrentInstance().addMessage(null, sqlError);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return "list_manufacturers";
	}

	public void deleteManufacturers(Manufacturer manufacturer) throws Exception {

		try {
			DAO dao = new DAO();
			dao.deleteManufacturer(manufacturer);
		} catch (CommunicationsException e) {
			FacesMessage sqlError = new FacesMessage("Error: Cannot connect to Database");
			FacesContext.getCurrentInstance().addMessage(null, sqlError);

		}
	}

	public String UpdateManufacturers(String manuCode) throws Exception {
		Manufacturer simgleManufacturer = DAO.getSingleManufacturer(manuCode);
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = externalContext.getRequestMap();
		requestMap.put("manufacturer", simgleManufacturer);

		return "update_manufacturer";
	}

	public String saveManufacturer(Manufacturer manu) throws Exception {
		DAO dao = new DAO();
		try {
			dao.saveManufacturer(manu);

			return "list_manufacturers?faces-redirect=true";

		} catch (CommunicationsException e) {
			FacesMessage sqlError = new FacesMessage("Error: Cannot connect to Database");
			FacesContext.getCurrentInstance().addMessage(null, sqlError);
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
