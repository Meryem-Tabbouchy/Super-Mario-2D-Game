package com.accueil.start;

public class Player {
	// informations necessaires sur le joueur
	
	private String image;
	private String nom;
	private String prenom;
	private String username;
	private int age;
	
	

	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public Player() {
		// TODO Auto-generated constructor stub
	}



	public Player(String image, String nom, String prenom, String username, int age) {
		super();
		this.image = image;
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.age = age;
	}



	public Player(String username) {
		super();
		this.username = username;
	}
	
	
	

}
