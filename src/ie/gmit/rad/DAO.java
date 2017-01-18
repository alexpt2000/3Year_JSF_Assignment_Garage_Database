package ie.gmit.rad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

public class DAO {
	private static DataSource mysqlDS;

	public DAO() throws Exception {
		Context context = new InitialContext();
		String dataBase = "java:comp/env/jdbc/garage";
		mysqlDS = (DataSource) context.lookup(dataBase);
	}

	/*
	 * Manufacturer methods
	 * *************************************************************************
	 * ****
	 */

	public ArrayList<Manufacturer> getManufacturerDetails() throws Exception {
		ArrayList<Manufacturer> manufacturers = new ArrayList<>();
		try {
			Connection conn = mysqlDS.getConnection();

			PreparedStatement myStmt = conn.prepareStatement("select * " + "from manufacturer");

			// myStmt.setString(1, name);
			ResultSet rs = myStmt.executeQuery();
			while (rs.next()) {
				String manuCode = rs.getString("manu_code");
				String manuName = rs.getString("manu_name");
				String manuDetails = rs.getString("manu_details");
				manufacturers.add(new Manufacturer(manuCode, manuName, manuDetails));
			}

			conn.close();

			return manufacturers;
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
	}

	public static Manufacturer getSingleManufacturer(String mcode) throws Exception {

		Manufacturer simgleManufacturers = new Manufacturer();
		try {
			Connection conn = mysqlDS.getConnection();

			PreparedStatement myStmt = conn.prepareStatement("select * from manufacturer where manu_code like ?");

			myStmt.setString(1, mcode);

			ResultSet rs = myStmt.executeQuery();
			while (rs.next()) {
				String manuCode = rs.getString("manu_code");
				String manuName = rs.getString("manu_name");
				String manuDetails = rs.getString("manu_details");

				simgleManufacturers = new Manufacturer(manuCode, manuName, manuDetails);
			}
			conn.close();

			return simgleManufacturers;
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
	}

	public void addManufacturer(Manufacturer manufacturer) throws SQLException {

		Connection conn = mysqlDS.getConnection();

		try {

			PreparedStatement myAdd = conn.prepareStatement(
					"insert into manufacturer (manu_code, manu_name, manu_details) values " + "(?, ?, ?)");

			myAdd.setString(1, manufacturer.getManuCode());
			myAdd.setString(2, manufacturer.getManuName());
			myAdd.setString(3, manufacturer.getManuDetails());

			// execute insert SQL stetement
			myAdd.executeUpdate();
		}

		finally {
			conn.close();

		}

	}

	public void deleteManufacturer(Manufacturer manufacturer) throws SQLException {
		Connection conn = mysqlDS.getConnection();

		try {
			PreparedStatement myAdd = conn.prepareStatement("delete from manufacturer where manu_code=?");
			myAdd.setString(1, manufacturer.getManuCode());

			// execute insert SQL stetement
			myAdd.executeUpdate();
		}

		catch (SQLException e) {

			if (e instanceof SQLIntegrityConstraintViolationException) {
				FacesMessage message = new FacesMessage("1 Error: " + e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();

		}
	}

	public void saveManufacturer(Manufacturer manufacturer) throws SQLException {

		Connection conn = mysqlDS.getConnection();

		try {

			PreparedStatement myAdd = conn.prepareStatement(
					"update manufacturer set manu_code=?, manu_name=?, manu_details=? where manu_code like ?");

			myAdd.setString(1, manufacturer.getManuCode());
			myAdd.setString(2, manufacturer.getManuName());
			myAdd.setString(3, manufacturer.getManuDetails());
			myAdd.setString(4, manufacturer.getManuCode());

			// execute insert SQL stetement
			myAdd.executeUpdate();
		}

		catch (SQLException e) {

			if (e instanceof SQLIntegrityConstraintViolationException) {
				FacesMessage message = new FacesMessage("1 Error: " + e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				FacesMessage message = new FacesMessage("Code " + manufacturer.getManuCode() + " Successfully update");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		conn.close();

	}

	/*
	 * Manufacturer Model
	 * *************************************************************************
	 * ****
	 */

	public ArrayList<Model> getModelDetails() throws Exception {
		ArrayList<Model> models = new ArrayList<>();

		try {
			Connection conn = mysqlDS.getConnection();

			PreparedStatement myStmt = conn.prepareStatement("select * " + "from model");

			// myStmt.setString(1, name);
			ResultSet rs = myStmt.executeQuery();
			while (rs.next()) {
				String manuCode = rs.getString("manu_code");
				String modelCode = rs.getString("model_code");
				String modelName = rs.getString("model_name");
				String modelDesc = rs.getString("model_desc");

				models.add(new Model(manuCode, modelCode, modelName, modelDesc));
			}

			conn.close();
			return models;
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
			FacesContext.getCurrentInstance().addMessage(null, message);

			return null;
		}
	}

	public void addModel(Model model) throws SQLException {

		Connection conn = mysqlDS.getConnection();

		try {

			PreparedStatement myAdd = conn.prepareStatement(
					"insert into model (manu_code, model_code, model_name, model_desc) values " + "(?, ?, ?, ?)");

			myAdd.setString(1, model.getManuCode());
			myAdd.setString(2, model.getModelCode());
			myAdd.setString(3, model.getModelName());
			myAdd.setString(4, model.getModelDesc());

			// execute insert SQL stetement
			myAdd.executeUpdate();
		} finally {
			conn.close();

		}

	}

	/*
	 * Manufacturer Vehicle
	 * *************************************************************************
	 * ****
	 */

	public ArrayList<Vehicle> getVehicleDetails() throws Exception {
		ArrayList<Vehicle> vehicles = new ArrayList<>();
		try {
			Connection conn = mysqlDS.getConnection();

			PreparedStatement myStmt = conn.prepareStatement("select * " + "from vehicle");

			// myStmt.setString(1, name);
			ResultSet rs = myStmt.executeQuery();
			while (rs.next()) {

				String reg = rs.getString("reg");
				String manuCode = rs.getString("manu_code");
				String modelCode = rs.getString("model_code");
				int mileage = rs.getInt("mileage");
				float price = rs.getFloat("price");
				String colour = rs.getString("colour");
				String fuel = rs.getString("fuel");

				vehicles.add(new Vehicle(reg, manuCode, modelCode, mileage, price, colour, fuel));
			}

			conn.close();

			return vehicles;
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
			FacesContext.getCurrentInstance().addMessage(null, message);

			return null;
		}
	}

	public static VehicleDetails getSingleVehicleDetails(String regV) throws Exception {

		VehicleDetails simgleVehicle = new VehicleDetails();
		try {
			Connection conn = mysqlDS.getConnection();

			PreparedStatement myStmt = conn.prepareStatement(
					"select * from vehicle ve inner join manufacturer ma on ve.manu_code = ma.manu_code inner join model mo on ve.model_code = mo.model_code where ve.reg like ?");

			myStmt.setString(1, regV);

			ResultSet rs = myStmt.executeQuery();

			rs.next();
			String reg = rs.getString("reg");
			String manuCode = rs.getString("manu_code");
			String manuName = rs.getString("manu_name");
			String manuDetails = rs.getString("manu_details");
			String modelCode = rs.getString("model_code");
			String modelName = rs.getString("model_name");
			String modelDesc = rs.getString("model_desc");
			int mileage = rs.getInt("mileage");
			float price = rs.getFloat("price");
			String colour = rs.getString("colour");
			String fuel = rs.getString("fuel");

			simgleVehicle = new VehicleDetails(reg, manuCode, manuName, manuDetails, modelCode, modelName, modelDesc,
					mileage, price, colour, fuel);

			conn.close();

			return simgleVehicle;
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
			FacesContext.getCurrentInstance().addMessage(null, message);

			return null;
		}
	}

	public void addVehicle(Vehicle vehicle) throws SQLException {

		Connection conn = mysqlDS.getConnection();

		try {

			PreparedStatement myAdd = conn.prepareStatement(
					"insert into vehicle (reg, manu_code, model_code, mileage, price, colour, fuel) values "
							+ "(?, ?, ?, ?, ?, ?, ?)");

			myAdd.setString(1, vehicle.getReg());
			myAdd.setString(2, vehicle.getManuCode());
			myAdd.setString(3, vehicle.getModelCode());
			myAdd.setInt(4, vehicle.getMileage());
			myAdd.setFloat(5, vehicle.getPrice());
			myAdd.setString(6, vehicle.getColour());
			myAdd.setString(7, vehicle.getFuel());

			// execute insert SQL stetement
			myAdd.executeUpdate();
		}

		finally {
			conn.close();

		}

	}

	public ArrayList<Vehicle> getFindVehicle(FindVehicles search) throws Exception {
		ArrayList<Vehicle> vehicles = new ArrayList<>();
		try {
			Connection conn = mysqlDS.getConnection();

			String query = "";

			if (search.getPrice() != null) {

				if (search.getPriceType().equalsIgnoreCase("lessthan")) {
					query = " and price < ?";
				} else if (search.getPriceType().equalsIgnoreCase("greaterthan")) {
					query = " and price > ?";
				} else if (search.getPriceType().equalsIgnoreCase("equal")) {
					query = " and price = ?";
				}

			}

			if (search.getColour() != "") {
				query += " and colour like ?";
			}

			PreparedStatement myStmt = conn.prepareStatement(
					"select * from vehicle ve inner join manufacturer ma on ve.manu_code = ma.manu_code inner join model mo on ve.model_code = mo.model_code where fuel like ?"
							+ query);

			myStmt.setString(1, search.getFuel());

			if (search.getPrice() != null && search.getColour() != "") {
				myStmt.setInt(2, search.getPrice());
				myStmt.setString(3, search.getColour());
			}

			else if (search.getPrice() != null) {
				myStmt.setInt(2, search.getPrice());
			}

			else if (search.getColour() != "") {
				myStmt.setString(2, search.getColour());
			}

			ResultSet rs = myStmt.executeQuery();
			while (rs.next()) {

				String reg = rs.getString("reg");
				String manuCode = rs.getString("manu_code");
				String manuName = rs.getString("manu_name");
				String modelCode = rs.getString("model_code");
				String modelName = rs.getString("model_name");
				int mileage = rs.getInt("mileage");
				float price = rs.getFloat("price");
				String colour = rs.getString("colour");
				String fuel = rs.getString("fuel");

				vehicles.add(new Vehicle(reg, manuCode, modelCode, mileage, price, colour, fuel, manuName, modelName));
			}

			conn.close();

			return vehicles;
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
			FacesContext.getCurrentInstance().addMessage(null, message);

			return null;
		}
	}

}
