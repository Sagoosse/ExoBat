package fr.epsi.training.model;

import java.util.Date;

public class User {
	private int id;
	private String lastname;
	private String firstname;
	private int age;
	private Date dateCreation;
	
	public User(String lastname, String firstname, int age, Date creation)
	{
		this.lastname = lastname;
		this.firstname = firstname;
		this.age = age;
		this.dateCreation = creation;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	
	public int getAge()
	{
		return age;
	}
	
	public void setAge(int age)
	{
		this.age = age;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public Date getDateCreation() {
		return dateCreation;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
}
