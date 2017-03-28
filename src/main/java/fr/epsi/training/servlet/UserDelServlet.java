package fr.epsi.training.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/delete-user")
public class UserDelServlet extends HttpServlet{
	
	private static final long serialVersionUID = 7781356013175618928L;

	@Resource(name = "personDataSource")
	private DataSource datasource;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		int id = Integer.parseInt(req.getParameter("userid"));			
		
		if (id < 0)
			this.deleteAllUser();
		else
		{		
			String sql = "DELETE FROM Person WHERE Person.id = ? ";
				
			try (Connection connection = datasource.getConnection();
					PreparedStatement smt = connection.prepareStatement(sql))
			{
				smt.setInt(1, id);
						
				smt.executeUpdate();		
			}
			catch(SQLException e)
			{
				throw new ServletException(e);
			}
		}
		
		// on redirige vers l'url de base qui correspond à la racine de notre application
		resp.sendRedirect(req.getContextPath());
	}
	
	private void deleteAllUser() throws ServletException
	{
		try (Connection connection = datasource.getConnection();
				Statement smt = connection.createStatement())			
		{
					smt.executeUpdate("TRUNCATE TABLE Person;");
		}
		catch(SQLException e)
		{
			throw new ServletException(e);
		}
	}
}
