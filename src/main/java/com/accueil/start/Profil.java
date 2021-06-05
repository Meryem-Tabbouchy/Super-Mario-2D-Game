package main.java.com.accueil.start;


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

public class Profil extends JFrame{
// le joueur
	private Player joueur;
	

	Date datefacture;
	private String nomfacture;
	private int Qteproduit;

	
	
	public Player getJoueur() {
		return joueur;
	}


	public void setJoueur(Player joueur) {
		this.joueur = joueur;
	}


	public Profil() {
		// TODO Auto-generated constructor stub
				this.setSize(500,650);
				this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				this.setResizable(false);

				JButton ajouterf= new JButton("Valider");
				JButton anul= new JButton("Annuler");
				ajouterf.setPreferredSize(new Dimension(80,32));
				anul.setPreferredSize(new Dimension(80,32));
				//listeners pour ajouter ou supprimer une facture
				
				ajouterf.addActionListener(new ActionListener()
						{
					

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
					}
						});
				anul.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				
			}
				});
				
				JPanel pan, pannomfacture, pannomclient, panProduit,panidclient,pansupporttext;
				JLabel nom,idclient,produits,nomfac;
				JPanel pan1,pan2,pan3,pan4,pan5,pan6;
				pan= new JPanel();
				pan.setLayout(new FlowLayout());
				//nom client
				nom= new JLabel("Nom Client: ");
				JTextField Tnom= new JTextField();
				Tnom.setPreferredSize(new Dimension(200,35));
				pannomclient= new JPanel();
				pannomclient.setPreferredSize(new Dimension(450,62));
				pannomfacture= new JPanel();
				pannomclient.setBorder(BorderFactory.createTitledBorder("Nom client"));
				pannomclient.add(nom);
				pannomclient.add(Tnom);
				
				
				
				// produit
				panProduit= new JPanel();
				JTextField Tproduit= new JTextField();
				JButton ajouproduit= new JButton("Ajouter");
				JLabel Qte= new JLabel("Qte:");
				JTextField qt= new JTextField();
				JTextField prix= new JTextField();
				prix.setPreferredSize(new Dimension(60,35));
				JLabel lprix= new JLabel("Prix:");
				
				Tproduit.setPreferredSize(new Dimension(100,35));
				panProduit.setPreferredSize(new Dimension(450,62));
				panProduit.setBorder(BorderFactory.createTitledBorder("Produits "));
				produits= new JLabel("Produit : ");
				panProduit.add(lprix);
				panProduit.add(prix);
				panProduit.add(Qte);
				panProduit.add(qt);
				panProduit.add(produits);
				panProduit.add(Tproduit);
				panProduit.add(ajouproduit);
				qt.setPreferredSize(new Dimension(35,35));

			
				
				//ID CLIENT
				panidclient= new JPanel();
				JTextField Tidclient= new JTextField();
				idclient= new JLabel("ID client: ");
				Tidclient.setPreferredSize(new Dimension(200,35));
				panidclient.setPreferredSize(new Dimension(450,62));
				panidclient.setBorder(BorderFactory.createTitledBorder("ID client"));
				panidclient.add(idclient);
				panidclient.add(Tidclient);
				
				//NOM FACTURE
				pannomfacture= new JPanel();
				JTextField Tfacture= new JTextField();
				Tfacture.setPreferredSize(new Dimension(200,35));
				nomfac= new JLabel("Nom Facture: ");
				pannomfacture.setPreferredSize(new Dimension(450,62));
				pannomfacture.setBorder(BorderFactory.createTitledBorder("Facture"));
				pannomfacture.add(nomfac);
				pannomfacture.add(Tfacture);
				
				//panel support text
				pansupporttext= new JPanel();
				JTextPane pantext= new JTextPane();
				pantext.setSize(new Dimension(428,87));
				pantext.setEditable(false);
				pansupporttext.setPreferredSize(new Dimension(450,100));
				JScrollPane scroltext= new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scroltext.setPreferredSize(new Dimension(430,90));
				scroltext.add(pantext);
				pansupporttext.add(scroltext);
				
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
				
				
				ajouproduit.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				try {
				pantext.setText("         "+Tproduit.getText()+"  est ajouté..!\n\t Nombre :"+Integer.parseInt(qt.getText())+"\n\tPrix: "+Double.parseDouble(prix.getText())+"  DH"+"\n\t Total: "+Integer.parseInt(qt.getText())*Double.parseDouble(prix.getText())+"  DH");
				}
				catch(Exception x)
				{
					//Erreursfentre err= new Erreursfentre();
					//err.changer("Vous avez mal saisi un champs\n assurez-vous de respecter les fomats de chque champ");
					
				}
			}
				});
				//ajout au panel principal
				pan.add(pan1);
				pan.add(pannomclient);
				pan.add(pan2);
				pan.add(panidclient);
				pan.add(pan3);
				pan.add(pannomfacture);
				pan.add(pan4);
				pan.add(panProduit);
				pan.add(pan5);
				pan.add(pansupporttext);
				pan.add(pan6);
				pan.add(ajouterf);
				pan.add(anul);
				
				this.setContentPane(pan);
				this.setVisible(true);
				
			}

}
