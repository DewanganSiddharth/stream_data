package org.stream.SDM.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbAccess {

	Connection con=null;
	DbAccess()
	{
		con=null;
	}
	public boolean start()
	{
		try{
		Class.forName("com.mysql.jdbc.Driver");

		this.con=DriverManager.getConnection("jdbc:mysql://localhost:3306/DSM","root","Sidshanu8793$");

		return true;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
			
	}
	public boolean stop()
	{
		try{
			this.con.close();
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
		
	}

	
}
