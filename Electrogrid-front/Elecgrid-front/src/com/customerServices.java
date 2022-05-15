package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class customerServices {
	
	//connection
			private Connection connect() {
				Connection con = null;
				try {
					//Class.forName("com.mysql.cj.jdbc.Driver");
					Class.forName("com.mysql.jdbc.Driver");
					
					String url = String.format("jdbc:mysql://127.0.0.1:3306/elecgrid-front");
					String username = "root";
					String password = "";
					
					con = DriverManager.getConnection(url,username, password);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return con;
			}

			//insert employee
			public String insertCus(String cus_name, String cus_mobile, String cus_email) {
				
				String output = "";

				try {
					
					Connection con = connect();
					
					if(con == null)
					{return "Error while connecting to the database for inserting data";}
					
					String insertCus = "insert into customer (`cus_id`, `cus_name`, `cus_mobile`, `cus_email`)" + "values(?,?,?,?)";
					
					PreparedStatement ps = con.prepareStatement(insertCus);
					ps.setInt(1, 0);
					ps.setString(2, cus_name);
					ps.setString(3, cus_mobile);
					ps.setString(4, cus_email);

					ps.execute();
					con.close();
					
					String newCustomer = viewCus();
					output ="{\"status\":\"success\",\"data\":\""+newCustomer+"\"}";
					//output = "Inserted "+ e_name + " Successfully";

				} catch(Exception e) {
					output = "{\"status\":\"error\", \"data\":\"Error while inserting the customer.\"}"; 
					//output = "Error While inserting the customer.";
					System.err.println(e.getMessage());
				}

				return output;
			}

			//view customer details
			public String viewCus() {
				
				String output ="";
				
				try {
					
					Connection con = connect();
					
					if (con==null)
					{ return "Error!! While connecting to the database for read the Customers";}
					
					// Prepare the html table to be displayed
					output = "<table border='1'><tr><th>Customer Name</th><th>Mobile No:</th>" +
					"<th>E mail</th>" +
					"<th>Update</th><th>Remove</th></tr>";
					
					String query = "select * from customer";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					
					while(rs.next()) {
						
						String cus_id = Integer.toString(rs.getInt("cus_id"));
						String cus_name = rs.getString("cus_name");
						String cus_mobile = rs.getString("cus_mobile");
						String cus_email = rs.getString("cus_email");
						
						// Add into the html table
						output += "<tr><td><input id='customerUpdate' name='customerUpdate' type='hidden' value='"+cus_id+"'>"+ cus_name +"</td>"; 
						//output += "<td>" + cus_name + "</td>";
						output += "<td>" + cus_mobile + "</td>";
						output += "<td>" + cus_email + "</td>";
						
						// buttons
						
						output += "<td><input name='btnUpdate' type='button' value='Update' "
								 + "class='btnUpdate btn btn-secondary' data-e_id='" + cus_id + "'></td>"
								 + "<td><input name='btnRemove' type='button' value='Remove' "
								 + "class='btnRemove btn btn-danger' data-e_id='" + cus_id + "'></td></tr>"; 
						
						/*output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='cus_id' type='hidden' value='" + eid
						+ "'>" + "</form></td></tr>";
						*/
					}
					
					con.close();
					
					//Complete the html table
					
					output += "</table>";
				} catch (Exception e) {
					output = "Error while fetching customers details.";
					System.err.println(e.getMessage());
				}
				return output;
			}
			
			//update customer details
			public String updateCus(String cus_id, String cus_name, String cus_mobile, String cus_email) {
				
				String output="";
				
				try {
					
					Connection con = connect();
					
					if (con==null)
					{ return "Error!! While connecting to the database for updating the " + cus_name;}
					
					// create a prepared statement
					String query = "UPDATE customer SET cus_name=?, cus_mobile=?, cus_email=? WHERE cus_id=?";
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					// binding values
					preparedStmt.setString(1, cus_name);
					preparedStmt.setString(2, cus_mobile);
					preparedStmt.setString(3, cus_email);
					preparedStmt.setInt(4,Integer.parseInt(cus_id));
					
					// execute the statement
					preparedStmt.execute();
					
					con.close();
					String newCustomer = viewCus();
					output = "{\"status\":\"success\",\"data\":\""+newCustomer+"\"}"; 
					//output = "Updated successfully";
					
				} catch (Exception e) {
					
					output = "{\"status\":\"error\",\"data\":\"Error while updating the customer.\"}"; 
					//output = "Error while updating the " + e_name;
					System.err.println(e.getMessage());
				}
				
				return output;
			}
			
			//delete customer from db
			public String deleteCus(String cus_id)
			{
				String output = "";
				
				try
				{
				Connection con = connect();
				
				if (con == null)
				{return "Error while connecting to the database for deleting."; }
				
				// create a prepared statement
				String query = "delete from employee where cus_id=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(cus_id));
				
				// execute the statement
				preparedStmt.execute();
				
				con.close();
				String newCustomer = viewCus();
				output = "{\"status\":\"success\",\"data\":\""+newCustomer+"\"}"; 
				//output = "Deleted successfully";
				}
				catch (Exception e)
				{
					//output = "Error while deleting the customer.";
					output = "{\"status\":\"error\",\"data\":\"Error while deleting the customer.\"}";
					System.err.println(e.getMessage());
				}
			return output;
			}
		
			

}
