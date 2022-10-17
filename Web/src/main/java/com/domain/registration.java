package com.domain;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class registration
 */
@WebServlet("/registration")
public class registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
	    String uname = request.getParameter("name");
	    String uemail = request.getParameter("email");
	    String upwd = request.getParameter("pass");
	    String umobile_no = request.getParameter("contact");
	    RequestDispatcher dispatcher = null;
	    try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?SSL=false","root", "123456");
	    	PreparedStatement pst = con.prepareStatement("insert into users(uname,upwd,uemail,umobile_no) values(?,?,?,?)");
	    	pst.setString(1, uname);
	    	pst.setString(2,upwd);
	    	pst.setString(3,uemail);
	    	pst.setString(4,umobile_no);
	    	
	    	int rowCount =pst.executeUpdate();
	    	dispatcher = request.getRequestDispatcher("registration.jsp");
	    	if(rowCount>0) {
	    		request.setAttribute("status","success");
	    	}else {
	    		request.setAttribute("status","falied");
	    	}
	    	dispatcher.forward(request, response);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    
	    
	    
	}

}
