package com.database.MyFirstJavaDBProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBMain {

	public static void main(String[] args) {
		try {
			System.out.println("Loading MY SQL Driver..............");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("My SQL Driver loaded successfully");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Unable to load My SQL driver");
		}

		try {
			System.out
					.println("Trying to eshtablish database connection................");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:7777/StudentDB", "root", "root");
			System.out.println("Connection Eshtablished successfully........");

			Statement statement = connection.createStatement();

			ResultSet results = statement.executeQuery("Select * from Student");

			while (results.next()) {
				System.out.println("Roll No: " + results.getString("RollNo")
						+ ", Name: " + results.getString("Name")
						+ ", Address: " + results.getString("address"));
			}
			
			int numberOfRecordsUpdated = statement.executeUpdate("Insert into student values (4,'Akshit Kaushik' , 'Lexington')");
			if(numberOfRecordsUpdated == 1){
				System.out.println("Record Successfully Inserted into database");
			}else{
				System.out.println("Unable to Insert record into database");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Unable to eshtablish database connection");
		}

	}

}
