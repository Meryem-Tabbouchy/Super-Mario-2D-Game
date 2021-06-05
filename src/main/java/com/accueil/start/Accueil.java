package main.java.com.accueil.start;


import java.awt.*;
import java.util.*;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import main.java.com.TETOSOFT.tilegame.GameEngine;

import main.java.utils.GameLoader;



public class Accueil extends JFrame implements ActionListener{
static ArrayList liste= new ArrayList();

// start the game

private static boolean commencerjeu= false;


public static boolean isCommencerjeu() {
	return commencerjeu;
}


public static void setCommencerjeu(boolean commencerjeu) {
	Accueil.commencerjeu = commencerjeu;
}


// crétion profil du joeur
ProfilViewOnly profilJoueurView;
JLabel lab= new JLabel("TABLEAU    DE     BORD  SUPER MARIO ");
JPanel panellap= new JPanel();

JMenuBar monmenu =new JMenuBar();
// les menus
JMenu m1= new JMenu("JOUEUR");
JMenu m2= new JMenu("AJOUTER");
JMenu m5= new JMenu("SUPPRIMER");
JMenu m3 = new JMenu("USERS");
JMenu m4 = new JMenu("CLASSEMENT");
JMenu m6 = new JMenu("PARAMETRES");
JMenu m7 = new JMenu("Chercher");
JMenu q= new JMenu("Quitter");
JMenuItem qdef= new JMenuItem("Fermer tout");
 

// Theme client
JMenuItem iprop=  new JMenuItem("Prédiction");
JMenuItem i1=new JMenuItem("Tous Joueurs");
JMenuItem i2=new JMenuItem("Nouveau Joueur");
JMenuItem i3=new JMenuItem("Scores");
JMenuItem i4= new JMenuItem("Historique");
JMenuItem m31= new JMenuItem("Voir...");

/*Ajouter*/
JMenuItem i11= new JMenuItem("Joeur");
JMenuItem i12=new JMenuItem("Avatar");
JMenuItem i131= new JMenuItem("Son");
JMenuItem i14= new JMenuItem("Fond Ecran");

/*supprimer*/
JMenuItem i13=new JMenuItem("Joueur");
JMenuItem i132= new JMenuItem("Avatar");
JMenuItem iFecran= new JMenuItem("Fond Ecran");
JMenuItem iSon= new JMenuItem("Son");


JMenuItem i41=new JMenuItem("Afficher");

JMenuItem ajouter= new JMenuItem("Nouv onglet");
JTextField chercher= new JTextField(" un joueur... ");

JPanel text= new JPanel();
//Parametres
JMenuItem Profil= new JMenuItem("Profil");
JMenuItem Article= new JMenuItem("Reglages");



//JRadioButtonMenuItem b1= new JRadioButtonMenuItem("POP");
//JRadioButtonMenuItem b2= new JRadioButtonMenuItem("MOP");
//JRadioButtonMenuItem b3= new JRadioButtonMenuItem("NOP");
//ButtonGroup g =new ButtonGroup();


public Accueil()
{// 1--TOUT LE MENU 
	this.setSize(700,700);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setResizable(false);
	this.setLocationRelativeTo(null);
/*panel nord*/
	panellap.setLayout(new BorderLayout());
	panellap.setBorder(BorderFactory.createTitledBorder("Page d'accueil"));
	panellap.add(lab);
	JPanel langue= new JPanel();
	Object objet []= {"langue","Francias","Anglais","Arabe","Chinois"};
	JComboBox box= new JComboBox(objet);
	
	langue.add(box);
	langue.setBackground(Color.green);
	panellap.add(langue,BorderLayout.EAST);
	
	JPanel pan= new JPanel();
	chercher.setPreferredSize(new Dimension(100,30));
	JLabel lab= new JLabel("Chercher");
	lab.add(chercher);
	lab.setLayout(new FlowLayout());
	lab.setBackground(Color.blue);
	
	// creation des infos image accueil
	  String imgUrl="supermario.jpg";
	  ImageIcon icone = new ImageIcon(imgUrl);

	/*panel central*/
text.setLayout(new BorderLayout());
text.add(panellap,BorderLayout.NORTH);
	//JScrollPane scrol= new JScrollPane(text);	
	/*panel affichage de résultats*/
	JTextPane textpan= new JTextPane();
	textpan.setText("Vos résultats s'affichent\n ICI");
	JPanel p= new JPanel();
	p.setLayout(new FlowLayout());
	p.setBorder(BorderFactory.createTitledBorder("Bienvenue"));
	//p.add(textpan);
text.add(p,BorderLayout.CENTER);
	JPanel date,facture,enbas;
	date= new JPanel();
	JTextPane dateaff= new JTextPane();
	dateaff.setText("Date Aurd...\n \n "+new Date()+"\n\n Passez du bon temps \n\n é BORD..\n");
	dateaff.setPreferredSize(new Dimension(138,170));
	date.add(dateaff);
  date.setBorder(BorderFactory.createTitledBorder("LA DATE "));
  date.setPreferredSize(new Dimension(150,200));
  
  //label de l'image

facture = new JPanel();
JLabel iamgeAccueil= new JLabel(icone, JLabel.CENTER);


iamgeAccueil.setPreferredSize(new Dimension(330,383));
facture.setBorder(BorderFactory.createTitledBorder("Personalisation.."));
facture.add(iamgeAccueil);
facture.setPreferredSize(new Dimension(350,450));
facture.setBackground(Color.orange);
// les trois boutons d'action sur la page accueil
JButton startGame= new JButton("Jouer");
JButton reprendre= new JButton("Reprise");
JButton profil= new JButton("Profil");
facture.setLayout(new FlowLayout());


startGame.setPreferredSize(new Dimension(85,33));
reprendre.setPreferredSize(new Dimension(85,33));
profil.setPreferredSize(new Dimension(85,33));

JPanel panelaj= new JPanel();
JPanel panelan= new JPanel();
JPanel panreprendre= new JPanel();

panelaj.add(startGame);
panreprendre.add(reprendre);
panelan.add(profil);

panelaj.setPreferredSize(new Dimension(88,35));
panelan.setPreferredSize(new Dimension(88,35));
panreprendre.setPreferredSize(new Dimension(88,35));

panreprendre.setBackground(Color.green);
panelaj.setBackground(Color.green);
panelan.setBackground(Color.green);
facture.add(panelaj);
facture.add(panreprendre);
facture.add(panelan);
//pannel a gauche et droite
JLabel clientenbas = new JLabel("     VOTRE SATISFACTION , NOTRE OBJECTIF    ");
JPanel pancli1= new JPanel();
JPanel pancli2= new JPanel();

pancli1.setPreferredSize(new Dimension(100,29));
pancli1.setBackground(Color.BLUE);
pancli2.setPreferredSize(new Dimension(100,29));
pancli2.setBackground(Color.BLUE);
//panels en haut et en bas du panel bas
JPanel enhaut = new JPanel();
JPanel enbasbas= new JPanel();
enhaut.setPreferredSize(new Dimension(501,9));
enhaut.setBackground(Color.green);
enbasbas.setPreferredSize(new Dimension(501,9));
enbasbas.setBackground(Color.green);

enbas= new JPanel();
enbas.setBorder(BorderFactory.createTitledBorder("NOTRE SLOGAN"));
enbas.setPreferredSize(new Dimension(507,83));
//enbas.setBackground(Color.cyan);
enbas.add(enhaut);
enbas.add(pancli1);
enbas.add(clientenbas);
enbas.add(pancli2);
enbas.add(enbasbas);

p.add(date);
p.add(facture);
p.add(enbas);

	//bouton joueur
	this.q.add(qdef);
	this.m1.add(i1);
	this.m1.add(i2);
	this.m1.add(i4);
	this.m1.add(i3);
	this.m1.add(iprop);
	// ajouter
	this.m2.add(i11);
	this.m2.add(i12);
	this.m2.add(i131);
	this.m2.add(i14);
	/*supprimer*/
	this.m5.add(i13);
	this.m5.add(i132);
	this.m5.add(iFecran);
	this.m5.add(iSon);
	/*produit*/
	this.m3.add(m31);
	// Prametre
	this.m6.add(Profil);
	this.m6.add(Article);
	
	
	// ecouter les menus
	i2.addActionListener(new ActionListener()
	{
public void actionPerformed(ActionEvent e)
{
	
	//DialogueAjouter diag= new DialogueAjouter();
	//diag.setLocationRelativeTo(rootPane);
	//diag.setVisible(true);
}
	});
	
qdef.addActionListener(this);
	
	i11.addActionListener(new ActionListener()
			{
		public void actionPerformed(ActionEvent e)
		{
	//		DialogueAjouter diag= new DialogueAjouter();
		//	diag.setLocationRelativeTo(rootPane);
		//	diag.setVisible(true);
		}
			});
	

	i131.addActionListener(new ActionListener()
			{
		public void actionPerformed(ActionEvent e)
		{
			//Produit pro= new Produit();
			
			//pro.setLocationRelativeTo(rootPane);
			//pro.setVisible(true);
		}
			});
	
	i1.addActionListener(new ActionListener()
	{
public void actionPerformed(ActionEvent ptte)
{
	Object[][] donnest= new Object[100][5];

	
	try {
		Class.forName("org.postgresql.Driver");
		System.out.println("Driver O.K.");
		String url = "jdbc:postgresql://localhost:5432/mongestionnaire";
		String user = "postgres";
		 String passwd = "s4g25ma1ladbs";
		Connection conn = DriverManager.getConnection(url, user,passwd);
		System.out.println(" La Connexion est établie");
	
		Statement state = conn.createStatement();
		ResultSet result = state.executeQuery("SELECT * FROM public.\"Client\" ");
		
		
		//On récupére les MetaData
		ResultSetMetaData resultMeta = result.getMetaData();
		
		while(result.next())
		{
			int j= result.getRow();
			
		for(int i = 1; i <= resultMeta.getColumnCount(); i++)
		{
		
		donnest[j-1][i-1]=result.getObject(i).toString();
		
		}
		}
		//TableAffichage tabaf= new TableAffichage(donnest);
		//tabaf.setPreferredSize(new Dimension(500,450));
		//tabaf.setLocationRelativeTo(rootPane);
		

	result.close();
	state.close();
	} 
	catch (Exception ep) {
		ep.printStackTrace();
		}
}});
	
	Article.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent art)
		{
			Object [][] donnearticle= new Object[100][4];
			try {
				Class.forName("org.postgresql.Driver");
				System.out.println("Driver O.K.");
				String url = "jdbc:postgresql://localhost:5432/mongestionnaire";
				String user = "postgres";
				 String passwd = "s4g25ma1ladbs";
				Connection conn = DriverManager.getConnection(url, user,passwd);
				System.out.println(" La Connexion est établie");
			
				Statement state = conn.createStatement();
				ResultSet result = state.executeQuery("SELECT * FROM public.\"Cathegories\" ");
				
				
				//On récupére les MetaData
				ResultSetMetaData resultMeta = result.getMetaData();
				
				while(result.next())
				{
					int j= result.getRow();
					
				for(int i = 1; i <= resultMeta.getColumnCount(); i++)
				{
				
				donnearticle[j-1][i-1]=result.getObject(i).toString();
				
				}
				}
			//	Articles tabaf= new Articles(donnearticle);
				//tabaf.setPreferredSize(new Dimension(500,450));
				//tabaf.setLocationRelativeTo(rootPane);
				

			result.close();
			state.close();
			} 
			catch (Exception ep) {
				ep.printStackTrace();
				}
		}});

	
	m31.addActionListener(new ActionListener()
	{
public void actionPerformed(ActionEvent ptte)
{
	Object[][] donnest= new Object[100][5];

	
	try {
		Class.forName("org.postgresql.Driver");
		System.out.println("Driver O.K.");
		String url = "jdbc:postgresql://localhost:5432/mongestionnaire";
		String user = "postgres";
		 String passwd = "s4g25ma1ladbs";
		Connection conn = DriverManager.getConnection(url, user,passwd);
		System.out.println(" La Connexion est établie");
	
		Statement state = conn.createStatement();
		ResultSet result = state.executeQuery("SELECT * FROM public.\"Produit\" ");
		
		
		//On récupére les MetaData
		ResultSetMetaData resultMeta = result.getMetaData();
		
		while(result.next())
		{
			int j= result.getRow();
			
		for(int i = 1; i <= resultMeta.getColumnCount(); i++)
		{
		
		donnest[j-1][i-1]=result.getObject(i).toString();
		
		}
		}
		//AffichageProd tabaf= new AffichageProd(donnest);
		//tabaf.setPreferredSize(new Dimension(500,450));
		//tabaf.setLocationRelativeTo(rootPane);
		

	result.close();
	state.close();
	} 
	catch (Exception ep) {
		ep.printStackTrace();
		}
}});
	
	i12.addActionListener(new ActionListener()
	{
public void actionPerformed(ActionEvent e)
{ 
	//Facturer mafacture= new Facturer();
	//mafacture.setLocationRelativeTo(rootPane);

}
	});
	// listener commencer le jeu
startGame.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{ 
	// Start the game here, demmarage avec le bouton jouer de la page d'accueil
	Accueil.commencerjeu= true;
	
	Thread tcommence = new Thread(new Runnable() {
	    @Override
	    public void run() {
	      // new GameInitializer(userName, player, Constants.BLIND_STRUCTURE_FILES.get(blindStructure), handState);  
	    	GameEngine jeu= new GameEngine();
	    	jeu.run();
	    }

	   });
	tcommence.start();

	 
}


});

// listener voir le profil du joueur
profil.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{ 
profilJoueurView= new ProfilViewOnly();
profilJoueurView.setLocationRelativeTo(rootPane);
}

});
//boouton reprise

reprendre.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e)
		{
	
			// reprise du jeu ici dans son état initial
			
			// restauration du jeu depuis le fichier de sauvegarde
						Thread tcommence = new Thread(new Runnable() {
						    @Override
						    public void run() {
						    	//set up the game loader
					        	GameLoader gameLoader = new GameLoader();
					        	if(gameLoader.check()) {
					        		Accueil.commencerjeu= true;
					        		new GameEngine(gameLoader).run();
					        	}
					        	else {
					        		int choice = JOptionPane.showConfirmDialog(null, "Aucune partie n'est enregistrée. Voulez-vous commencer une nouvelle partie?", "", JOptionPane.YES_NO_OPTION);
					        		if(choice == JOptionPane.YES_OPTION) {
					        			Accueil.commencerjeu= true;
						        		new GameEngine().run();
					        		}
					        	}
						    }

						 });
						tcommence.start();
			
		
		}
		});


	i132.addActionListener(new ActionListener()
	{
public void actionPerformed(ActionEvent e)
{
	Object[][] donnes= new Object[100][5];

	
	try {
		Class.forName("org.postgresql.Driver");
		System.out.println("Driver O.K.");
		String url = "jdbc:postgresql://localhost:5432/mongestionnaire";
		String user = "postgres";
		 String passwd = "s4g25ma1ladbs";
		Connection conn = DriverManager.getConnection(url, user,passwd);
		System.out.println(" La Connexion est établie");
	
		Statement state = conn.createStatement();
		ResultSet result = state.executeQuery("SELECT * FROM public.\"Produit\" ");
		
		
		//On récupére les MetaData
		ResultSetMetaData resultMeta = result.getMetaData();
		//System.out.println("\n**********************************");
		//On affiche le nom des colonnes
		//for(int i = 1; i <= resultMeta.getColumnCount(); i++)
		
		//System.out.print("\t"+resultMeta.getColumnName(i).toUpperCase() + "\t *");
		//System.out.println("\n**********************************");
	
		
		while(result.next())
		{
			int j= result.getRow();
			
		for(int i = 1; i <= resultMeta.getColumnCount(); i++) 
		{
		
		donnes[j-1][i-1]=result.getObject(i).toString();
		
		}
		}
	//	AffichageProd tabaf= new AffichageProd(donnes);
		//tabaf.setPreferredSize(new Dimension(500,450));
		//tabaf.setLocationRelativeTo(rootPane);
		

	result.close();
	state.close();
	} 
	catch (Exception et) {
		et.printStackTrace();
		}


	
}
	});

	
	i13.addActionListener(new ActionListener()
	{
public void actionPerformed(ActionEvent e)
{
	Object[][] donnes= new Object[100][5];

	
	try {
		Class.forName("org.postgresql.Driver");
		System.out.println("Driver O.K.");
		String url = "jdbc:postgresql://localhost:5432/mongestionnaire";
		String user = "postgres";
		 String passwd = "s4g25ma1ladbs";
		Connection conn = DriverManager.getConnection(url, user,passwd);
		System.out.println(" La Connexion est établie");
	
		Statement state = conn.createStatement();
		ResultSet result = state.executeQuery("SELECT * FROM public.\"Client\" ");
		
		
		//On récupére les MetaData
		ResultSetMetaData resultMeta = result.getMetaData();
		//System.out.println("\n**********************************");
		//On affiche le nom des colonnes
		//for(int i = 1; i <= resultMeta.getColumnCount(); i++)
		
		//System.out.print("\t"+resultMeta.getColumnName(i).toUpperCase() + "\t *");
		//System.out.println("\n**********************************");
	
		
		while(result.next())
		{
			int j= result.getRow();
			
		for(int i = 1; i <= resultMeta.getColumnCount(); i++)
		{
		
		donnes[j-1][i-1]=result.getObject(i).toString();
		
		}
		}
//		TableAffichage tabaf= new TableAffichage(donnes);
		//tabaf.setPreferredSize(new Dimension(500,450));
	//	tabaf.setLocationRelativeTo(rootPane);
		

	result.close();
	state.close();
	} 
	catch (Exception et) {
		et.printStackTrace();
		}


	
}
	});

	
	i3.addActionListener(this);
	



	/*ajouter les tems au menu*/ 
	this.monmenu.add(m1);
	this.monmenu.add(m2);
	this.monmenu.add(m5);
	this.monmenu.add(m3);
	this.monmenu.add(m6);
	this.monmenu.add(m7);
	this.monmenu.add(chercher);
	this.monmenu.add(q);
	/* couleur menu*/
	
	monmenu.setBackground(Color.cyan);
	//afficher
	i41.addActionListener(new ActionListener()
			{
		public void actionPerformed(ActionEvent e)
		{ 
		
		
		}
			});
	/* creation de fenetre des dones*/
	JPanel affdonnes= new JPanel();
	affdonnes.setSize(100,50);
	affdonnes.setLayout(new BorderLayout());
	
	DefaultTreeModel mod;
	DefaultMutableTreeNode racine1= new DefaultMutableTreeNode("Scores");
	File dossier = new File("C://mondossier");
	 dossier.mkdir();
	 DefaultMutableTreeNode doc = new DefaultMutableTreeNode(dossier.getName());
	 racine1.add(doc);
		
	for(int i=0; i<7; i++)
	{
	DefaultMutableTreeNode racine= new DefaultMutableTreeNode("scores Niveau "+i);
		 for(int j=0; j< 4; j++)
		 {
			 DefaultMutableTreeNode sousnoeud= new DefaultMutableTreeNode("score niveau "+i+"Home:"+i);
			 racine.add(sousnoeud);
	
		 }
	racine1.add(racine);
		
	}
	JTree monarbre = new JTree(racine1);
	JScrollPane pop =new JScrollPane(monarbre);
	
		 mod= new DefaultTreeModel(racine1);
	mod.addTreeModelListener((TreeModelListener) new TreeModelListener()
			{

				@Override
				public void treeNodesChanged(TreeModelEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void treeNodesInserted(TreeModelEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void treeNodesRemoved(TreeModelEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void treeStructureChanged(TreeModelEvent e) {
					// TODO Auto-generated method stub
					
				}});
	
	//monarbre.setCellRenderer(model1);
	monarbre.addTreeSelectionListener(new TreeSelectionListener() {
public void valueChanged(TreeSelectionEvent e)	
{
	
	if(monarbre.getLastSelectedPathComponent()!=null)
	{
System.out.print(monarbre.getLastSelectedPathComponent().toString());
		
		
	}

}
	});
	
	
affdonnes.add(pop,BorderLayout.CENTER);
	
	
	
	
	
	// forcer le system a utiliser son affichage lie a chaque SE
	
	try {
		//On force é utiliser le é look and feel é du systéme
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		/*Ici on force tous les composants de notre fenétre (de donnes)a
		redessiner avec le é look and feel é du systéme*/
		SwingUtilities.updateComponentTreeUI(affdonnes);
		}
		catch (InstantiationException e) {}
		catch (ClassNotFoundException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		catch (IllegalAccessException e) {}
	
	affdonnes.setVisible(true);
	


	
	
	//2-- PANEL DE SAISI DE TEXTE
	pan.setLayout(new BorderLayout());
	pan.add(text,BorderLayout.CENTER);
	pan.add(affdonnes, BorderLayout.WEST);
	this.monmenu.setBackground(Color.cyan);
	this.setContentPane(pan);

	this.setJMenuBar(monmenu);
	this.setVisible(true);

}


public void actionPerformed(ActionEvent event) {
// TODO Auto-generated method stub
if ( event.getSource()== i1)
	{
	
	}
else if(event.getSource()==qdef)
{
	JOptionPane quitter= new JOptionPane();
	int i=	quitter.showConfirmDialog(null, "Voulez vous  vraiment quitter\n définitivement ?", "Quitter",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE );
	if(i==JOptionPane.YES_OPTION)
	{
		SwingUtilities.getWindowAncestor(this.getContentPane()).dispose();
	}
	else
	{
	
	}
}
else if (event.getSource()==i2)
{/*
	JTabbedPane dest= new JTabbedPane();
	PagePrincipale mooom= new PagePrincipale();
	dest.addTab("NOUVEL ONGLET", mooom);
	this.getContentPane().add(dest);*/
	 //DialogueAjouter diag= new DialogueAjouter();
	 //diag.setVisible(true);

	 
}

}
/* creation de l affichage de données*/

}