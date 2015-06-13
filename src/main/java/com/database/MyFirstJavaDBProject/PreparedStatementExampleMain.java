package com.database.MyFirstJavaDBProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PreparedStatementExampleMain {

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

			PreparedStatement preparedStatement = connection.prepareStatement("Select * from Student");

			ResultSet results = preparedStatement.executeQuery();

			while (results.next()) {
				System.out.println("Roll No: " + results.getString("RollNo")
						+ ", Name: " + results.getString("Name")
						+ ", Address: " + results.getString("address"));
			}
			
			
			int roll = 5;
			String name = "Vyan Kaushik";
			String address = "Cambridge";
			
			preparedStatement = connection.prepareStatement("Insert into student values (?,?,?)");
			preparedStatement.setInt(0, roll);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, address);	
		
			
			int numberOfRecordsUpdated = preparedStatement.executeUpdate();
			if(numberOfRecordsUpdated == 1){
				System.out.println("Record Successfully Inserted into database");
			}else{
				System.out.println("Unable to Insert record into database");
			}
			
			preparedStatement = connection.prepareStatement("Update student set name=? , address=? where rollno=?");
			preparedStatement.setInt(2, roll);
			preparedStatement.setString(0, name);
			preparedStatement.setString(1, address);	
			numberOfRecordsUpdated = preparedStatement.executeUpdate();
			if(numberOfRecordsUpdated == 1){
				System.out.println("Record Successfully Updated in the database");
			}else{
				System.out.println("Unable to update record in the database");
			}
			
			preparedStatement = connection.prepareStatement("delete from student where rollno=?");
			preparedStatement.setInt(0, roll);
			numberOfRecordsUpdated = preparedStatement.executeUpdate();
			if(numberOfRecordsUpdated == 1){
				System.out.println("Record Successfully deleted from the database");
			}else{
				System.out.println("Unable to delete record from the database");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Unable to eshtablish database connection");
		}

	}

}
