package fr.epsi.training.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import fr.epsi.training.model.User;

@WebServlet({"/",  "/individus"})
public class UserListServlet extends HttpServlet {

  private static final long serialVersionUID = -4408866035171722406L;
  
  
  @Resource(name = "personDataSource")
  private DataSource datasource;

@Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
                 throws ServletException, IOException {
    
	ArrayList<User> users = new ArrayList<User>();
	
	try (Connection connection = datasource.getConnection();
		Statement smt = connection.createStatement();
		ResultSet rs = smt.executeQuery("SELECT * FROM Person"))
	{
		while (rs.next())
		{
			/* on créé une liste d'utilisateurs */
			
			int id = rs.getInt("id");
			String firstname = rs.getString("firstname");
			String lastname = rs.getString("lastname");
			int age = rs.getInt("age");
			Date creation = rs.getDate("dateCreation");

			User u = new User(lastname, firstname, age, creation);
			u.setId(id);
			
			users.add(u);
		}
	}
	catch(SQLException e)
	{
		throw new ServletException(e);
	}
	
	/* On sauvegarde la liste dans la requête : utilisation des attributs de la requête !!*/
	req.setAttribute("users", users);
	
	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listUser.jsp");
	dispatcher.forward(req, resp);
  }
}