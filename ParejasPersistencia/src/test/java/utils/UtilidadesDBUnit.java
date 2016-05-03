
package utils;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlWriter;

public class UtilidadesDBUnit {

	public static void main(String[] args) {
		try {
			//generatePartialXML("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "root", "root", "test", "input");
			generatePartialXML("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/parejas", "root", "root", "parejas", "expected", "personas");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void generateXML(String driverName, String urlDB,
			String userDB, String passwordDB, String schemaBD, String nameXML)
			throws SQLException {

		Connection conn = null;

		try {
			// Connect to the database
//			DriverManager.registerDriver((Driver) Class.forName(driverName)
//					.newInstance());
			Class.forName(driverName);
			conn = DriverManager.getConnection(urlDB, userDB, passwordDB);

			IDatabaseConnection connection = new DatabaseConnection(conn);

			DatabaseSequenceFilter filter = new DatabaseSequenceFilter(
					connection);
			IDataSet datasetAll = new FilteredDataSet(filter,
					connection.createDataSet());
			QueryDataSet partialDataSet = new QueryDataSet(connection);

			String[] listTableNames = filter.getTableNames(datasetAll);
			for (int i = 0; i < listTableNames.length; i++) {
				final String tableName = listTableNames[i];
				// A�adir las tablas al DataSet, en este caso se a�aden todas
				partialDataSet.addTable(tableName);

			}

			// Especificar la ubicaci�n del fichaero a generar
			FlatXmlWriter datasetWriter = new FlatXmlWriter(
					new FileOutputStream("db/" + nameXML + ".xml"));

			// Generar el fichero
			datasetWriter.write(partialDataSet);

		} catch (Exception exc) {
			exc.printStackTrace();

		} finally {
			conn.close();
		}
	}

	public static void generatePartialXML(String driverName, String urlDB,
			String userDB, String passwordDB, String schemaBD, String nameXML, String table)
			throws SQLException {
		Connection conn = null;

		try {
			// Connectar con la BBDD
//			DriverManager.registerDriver((Driver) Class.forName(driverName)
//					.newInstance());
			Class.forName(driverName);
			conn = DriverManager.getConnection(urlDB, userDB, passwordDB);
			IDatabaseConnection connection = new DatabaseConnection(conn,
					schemaBD);

			QueryDataSet partialDataSet = new QueryDataSet(connection);
			// Especificar que tablas formaran parte del Dataset
			partialDataSet.addTable(table);

			// Especificar la ubicaci�n del fichaero a generar
			FlatXmlWriter datasetWriter = new FlatXmlWriter(
					new FileOutputStream("db/" + nameXML + ".xml"));

			// Generar el fichero
			datasetWriter.write(partialDataSet);

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			conn.close();
		}
	}

	/*public static void createData(String driverName, String urlDB,
			String userDB, String passworDB, String nameXML)
			throws SQLException {

		Connection conn = null;
		try {
			// Connect to the database
			DriverManager.registerDriver((Driver) Class.forName(driverName)
					.newInstance());
			conn = DriverManager.getConnection(urlDB, userDB, passworDB);
			IDatabaseConnection connection = new DatabaseConnection(conn);

			DatabaseOperation.INSERT.execute(connection, new FlatXmlDataSet(
					new FileInputStream("db/" + nameXML + ".xml")));

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public static void deleteData(String driverName, String urlDB,
			String userDB, String passworDB, String nameXML)
			throws SQLException {
		Connection conn = null;
		try {
			// Connect to the database
			DriverManager.registerDriver((Driver) Class.forName(driverName)
					.newInstance());
			conn = DriverManager.getConnection(urlDB, userDB, passworDB);
			IDatabaseConnection connection = new DatabaseConnection(conn);
			DatabaseOperation.DELETE.execute(connection, new FlatXmlDataSet(
					new FileInputStream("db/" + nameXML + ".xml")));

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			conn.close();
		}
	}*/

}