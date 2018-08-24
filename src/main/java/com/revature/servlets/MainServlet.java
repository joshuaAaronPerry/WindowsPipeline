package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.testng.TestNG;

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public MainServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TestNG runner = new TestNG();
		List<String> files = new ArrayList<String>();
		files.add("src/main/java/com/revature/test/testng.xml");
		runner.setTestSuites(files);
		runner.setOutputDirectory("src/main/resources/testoutput");
		runner.run();
		request.getRequestDispatcher("src/main/resources/testoutput/index.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
