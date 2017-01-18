package ie.gmit.rad;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

@ManagedBean
public class ControllerModel {

	private ArrayList<Model> models;
	DAO dao;

	public ControllerModel() throws Exception {
		dao = new DAO();
	}

	public void LoadModels() throws Exception {
		models = dao.getModelDetails();
	}

	public ArrayList<Model> getModels() {
		return models;
	}

	public void setModels(ArrayList<Model> model) {
		this.models = model;
	}

	public String addModels(Model model) throws Exception {
		DAO dao = new DAO();

		try {
			dao.addModel(model);

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

		return "list_model";
	}

}
