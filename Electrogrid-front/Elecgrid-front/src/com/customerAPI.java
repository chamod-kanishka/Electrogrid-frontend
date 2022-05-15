package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class customerAPI
 */
@WebServlet("/customerAPI")
public class customerAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//customerServices cusObj = new customerServices();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public customerAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		customerServices cusObj = new customerServices();
		String output = cusObj.insertEmp(request.getParameter("cus_name"),
				request.getParameter("cus_mobile"),
				request.getParameter("cus_email"));
				response.getWriter().write(output);
				
				doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Map paras = getParasMap(request);
		customerServices cusObj = new customerServices();
		String output = cusObj.updateEmp(paras.get("hidemployeeIDSave").toString(),
				paras.get("cus_name").toString(),
				paras.get("cus_mobile").toString(),
				paras.get("cus_email").toString());
				response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Map paras = getParasMap(request);
		customerServices empObj = new customerServices();
		String output = empObj.deleteEmp(paras.get("e_id").toString());
		response.getWriter().write(output);
		
	}
	
	
	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request)
		{
		Map<String, String> map = new HashMap<String, String>();
		try
		{
		Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		String queryString = scanner.hasNext() ?
		scanner.useDelimiter("\\A").next() : "";
		scanner.close();
		String[] params = queryString.split("&");
		for (String param : params)
		{
			String[] p = param.split("=");
		map.put(p[0], p[1]);
		}
		}
		catch (Exception e)
		{
		}
		return map;
		}

}
