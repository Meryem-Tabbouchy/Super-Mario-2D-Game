package com.accueil.start;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

public class ProfilViewOnly extends JFrame{


		// le joueur
			private static Player joueur;
			private static Profil profilJoueur;
			private static String urlImage;
			

			Date datefacture;
			private String nomfacture;
			private int Qteproduit;
			
			// les champs du formulaire
			JPanel pan, pannomclient, panprenom, panage, panusername,pansupporttext;
			JLabel nom, prenom, username, age;
			JPanel pan1,pan2,pan3,pan4,pan5,pan6;
			// image du joueur
			JPanel imageJoueur;
			// affichages 
			JTextPane pantext,pantextNom, pantextPrenom, pantextAge, pantextUsername;
			
			JButton quitter= new JButton("Quitter");
			
			
			public static Profil getProfilJoueur() {
				return profilJoueur;
			}


			public static void setProfilJoueur(Profil profilJoueur) {
				ProfilViewOnly.profilJoueur = profilJoueur;
			}


			public Player getJoueur() {
				return joueur;
			}


			public void setJoueur(Player joueur) {
				this.joueur = joueur;
			}
			public ProfilViewOnly() {
				
				// TODO Auto-generated constructor stub
						this.setSize(500,650);
						this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						this.setResizable(false);

						JButton modify= new JButton("modifier");
						JButton Valider= new JButton("Valider");
						modify.setPreferredSize(new Dimension(80,32));
						Valider.setPreferredSize(new Dimension(80,32));
						quitter.setPreferredSize(new Dimension(80,32));
						//listeners pour ajouter ou supprimer une facture
						
						modify.addActionListener(new ActionListener()
								{
							

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								// rendre l'affichage modifiable
								modifyingSettings();
								
							}
								});
						
						Valider.addActionListener(new ActionListener()
						{
					public void actionPerformed(ActionEvent e)
					{
						validateur();
					}
						});
						
						quitter.addActionListener(new ActionListener()
						{
					public void actionPerformed(ActionEvent e)
					{
						SwingUtilities.getWindowAncestor(pan).dispose();
					}
						});
						
						
						
						// panel principal 
						pan= new JPanel();
						pan.setLayout(new FlowLayout());
						
						//nom Joueur
						nom= new JLabel("Nom Joueur: ");
						pantextNom= new JTextPane();
						pantextNom.setText("Toma");// nom par défaut
						pantextNom.setPreferredSize(new Dimension(200,35));
						pantextNom.setEditable(false);
						pantextNom.setBackground(Color.LIGHT_GRAY);
						pannomclient= new JPanel();
						pannomclient.setPreferredSize(new Dimension(450,62));
						pannomclient.setBorder(BorderFactory.createTitledBorder("Prenom Joueur"));
						pannomclient.add(nom);
						pannomclient.add(pantextNom);
						
						//prenom Joueur
						prenom= new JLabel("Prenom Joueur: ");
						pantextPrenom= new JTextPane();
						pantextPrenom.setText("Oliver");// prenom par défaut
						pantextPrenom.setPreferredSize(new Dimension(200,35));
						pantextPrenom.setEditable(false);
						pantextPrenom.setBackground(Color.LIGHT_GRAY);
						panprenom= new JPanel();
						panprenom.setPreferredSize(new Dimension(450,62));
						
						panprenom.setBorder(BorderFactory.createTitledBorder("Prenom Joueur"));
						panprenom.add(prenom);
						panprenom.add(pantextPrenom);
						
						//Username Joueur
						username= new JLabel("Username : ");
						pantextUsername= new JTextPane();
						pantextUsername.setText("hyperfast7");// username par défaut
						pantextUsername.setPreferredSize(new Dimension(200,35));
						pantextUsername.setEditable(false);
						pantextUsername.setBackground(Color.LIGHT_GRAY);
						panusername= new JPanel();
						panusername.setPreferredSize(new Dimension(450,62));
						
						panusername.setBorder(BorderFactory.createTitledBorder("Username"));
						panusername.add(username);
						panusername.add(pantextUsername);
						
						
						//Age Joueur
						age= new JLabel("Age : ");
						pantextAge= new JTextPane();
						pantextAge.setText("18 ans");// age par défaut
						pantextAge.setPreferredSize(new Dimension(200,35));
						pantextAge.setEditable(false);
						pantextAge.setBackground(Color.LIGHT_GRAY);
						panage= new JPanel();
						panage.setPreferredSize(new Dimension(450,62));
						
						panage.setBorder(BorderFactory.createTitledBorder("Age"));
						panage.add(age);
						panage.add(pantextAge);
					
					
					
						//panel support text
						pansupporttext= new JPanel();
						pantext= new JTextPane();
						pantext.setPreferredSize(new Dimension(450,90));
						pantext.setText("CLIQUEZ SUR 'modififier' POUR MODIFIER \n"
								+ "Ou CLIQUEZ SUR 'Valider' POUR VALIDER \n "
								+ "VOS MODIFICATIONS");
						pantext.setEditable(false);
						pantext.setBackground(Color.LIGHT_GRAY);
						pansupporttext.setPreferredSize(new Dimension(450,100));
						pansupporttext.add(pantext);
						
						// tous les pannels intermediaires
						pan1= new JPanel();
						pan1.setPreferredSize(new Dimension(450,24));
						pan1.setBackground(Color.BLUE);
						
						pan2= new JPanel();
						pan2.setPreferredSize(new Dimension(450,24));
						pan2.setBackground(Color.GREEN);
						
						pan3= new JPanel();
						pan3.setPreferredSize(new Dimension(450,24));
						pan3.setBackground(Color.YELLOW);
						
						pan4= new JPanel();
						pan4.setPreferredSize(new Dimension(450,24));
						pan4.setBackground(Color.CYAN);
						
						pan5= new JPanel();
						pan5.setPreferredSize(new Dimension(450,24));
						pan5.setBackground(Color.ORANGE);
						
						pan6= new JPanel();
						pan6.setPreferredSize(new Dimension(450,24));
						pan6.setBackground(Color.BLUE);
						
						//ajout au panel principal
						pan.add(pan1);
						pan.add(pannomclient);
						pan.add(pan2);
						pan.add(panprenom);
						pan.add(pan3);
						pan.add(panusername);
						pan.add(pan4);
						pan.add(panage);
						pan.add(pan5);
						pan.add(pansupporttext);
						pan.add(pan6);
						pan.add(modify);
						pan.add(Valider);
						pan.add(quitter);
						
						this.setContentPane(pan);
						this.setVisible(true);
						
					}
			// changer les paramètres si on veut modifier des champs et enrégistrer les donnés
		public void modifyingSettings()
		{
			// rendre éditable les champs et chaner leur couleur d'arrière plan
			pantextNom.setEditable(true);
			pantextPrenom.setEditable(true);
			pantextAge.setEditable(true);
			pantextUsername.setEditable(true);
		// ensuite on change la couleur de fond
			pantextNom.setBackground(Color.white);
			pantextPrenom.setBackground(Color.white);
			pantextAge.setBackground(Color.white);
			pantextUsername.setBackground(Color.white);
		
			
			
		}
		
		private void validateur()
		{
			// on recueil le text saisi et on modify
						pantextNom.setText(pantextNom.getText());
						pantextPrenom.setText(pantextPrenom.getText());
						pantextAge.setText(pantextAge.getText());
						pantextUsername.setText(pantextUsername.getText());
						pantext.setText("LES MODIFICATION ONT ÉTÉ ENREGISTRÉES");
						pantext.setBackground(Color.GREEN);
			
						// rendre non éditable les champs et chaner leur couleur d'arrière plan
						pantextNom.setEditable(false);
						pantextPrenom.setEditable(false);
						pantextAge.setEditable(false);
						pantextUsername.setEditable(false);
						// on rend la couleur de base
						pantextAge.setBackground(Color.LIGHT_GRAY);
						pantextNom.setBackground(Color.LIGHT_GRAY);
						pantextPrenom.setBackground(Color.LIGHT_GRAY);
						pantextUsername.setBackground(Color.LIGHT_GRAY);
			
		}

		
	}


