package de.minorproject.machinelearning.classifier;

import java.sql.*;
public class MyConnect{

public static Connection myconnection()
{
	Connection cn=null;
	try
	{
	DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","SYSTEM","root"); 
	}catch(Exception e) {  System.out.println("Exception"+e); }
	return cn;
}
public static void main(String arg[])
{
	System.out.println("Connection Successful "+myconnection());
}
}