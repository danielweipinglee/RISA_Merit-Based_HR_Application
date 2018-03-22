package Servlet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.DataSet;
import DatabaseDao.DatabaseDao;
import Regex.ValidationRegex;

/**
 * Servlet implementation class GetUserName
 */
@WebServlet("/GetUserName")
public class GetUserName extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ValidationRegex validationRegex = new ValidationRegex();
		String userName = request.getParameter("uname");
		System.out.println(userName);
		DatabaseDao dao = new DatabaseDao();
		dao.getInfomation();
		for( DataSet data : dao.getData()) {
			System.out.println(data.getId() + " " + data.getPassword() +" " + data.getUserName() );
			System.out.println(validationRegex.isValidPassword(data.getPassword()));
		}
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
/*	
	public String getHtmlString(String filepath, List<DataSet> producet) {
		BufferedReader reader = new BufferedReader(new FileReader(filepath));
		String line = "";
		StringBuffer buffer = new StringBuffer();
		
	}
	*/

}
