package fr.epsi.training.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/add-user")
public class UserAddServlet extends HttpServlet{
	
	private static final long serialVersionUID = 7781356013175618928L;

	@Resource(name = "personDataSource")
	private DataSource datasource;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/addUser.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String firstname = req.getParameter("firstname"); 
		String lastname = req.getParameter("lastname");
		int age = 0;
		try
		{
			age = Integer.parseInt(req.getParameter("age"));
		}
		catch(NumberFormatException e)
		{
			resp.sendRedirect(req.getContextPath());
		}
		
		/* Et la couche de sécurité ? => age <= 0 possible !*/
		
		String sql = "INSERT INTO `Person` (`id`, `firstname`, `lastname`, `age`, `dateCreation`)"
						+ "VALUES (NULL, ?, ?, ?, CURRENT_DATE());";
			
		try (Connection connection = datasource.getConnection();
				PreparedStatement smt = connection.prepareStatement(sql))
		{
					
			smt.setString(1, firstname);
			smt.setString(2, lastname);
			smt.setInt(3, age);
					
			smt.executeUpdate();		
		}
		catch(SQLException e)
		{
			throw new ServletException(e);
		}		
		
		// on redirige vers l'url de base qui correspond à la racine de notre application
		resp.sendRedirect(req.getContextPath());
	}
}
