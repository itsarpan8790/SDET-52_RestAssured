package GenericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseLibrary {

	Driver driverRef;
	Connection conn;

	/**
	 * This method is used to connect with Database
	 * 
	 * @author arpan
	 * @throws Throwable
	 */
	public void connectToDB() throws Throwable {
		driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		conn = DriverManager.getConnection(IConstants.dbURL, IConstants.dbUsername, IConstants.dbPassword);
	}

	/**This method will execute the query and return the data only if validation is successful
	 * @author arpan
	 * @param query
	 * @param column
	 * @param expData
	 * @return
	 * @throws SQLException
	 */
	public String readDataFromDBAndValidate(String query, int column, String expData) throws Throwable {
		Statement state = conn.createStatement();
		ResultSet result = state.executeQuery(query);

		boolean flag = false;

		while (result.next()) {
			String actData = result.getString(column);
			if (actData.equals(expData)) {
				flag = true;
				break;
			}

		}
		if (flag) {
			System.out.println("Passed and Verified");
			return expData;
		} else {
			System.out.println("Data not verified");
			return "";
		}

	}

	/**
	 * This method will close Database Connection
	 * 
	 * @throws Throwable
	 */
	public void closeDB() throws Throwable {
		conn.close();
	}

}
