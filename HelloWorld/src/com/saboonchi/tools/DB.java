package com.saboonchi.tools;

import java.sql.*;




public class DB {
	public static void main(String[] args){
		System.out.println(connectToMySQL());
	}
	public static String connectToMySQL(){
		String str="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://nima.linkpc.net:3306/test1","nima","1243abdc");
			PreparedStatement statement = connection.prepareStatement("Select * from users");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				str = str + resultSet.getString(1)+" "+ resultSet.getString(2) + "</br>";
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
		
	}
}
