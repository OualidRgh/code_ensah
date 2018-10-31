package com.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.bo.Compte;
import com.bo.Personne;
import com.exception.CompteNotFoundException;
import com.exception.CompteOperationException;
import com.ihm.ConsoleApp;

/**
 * Cette classe offre les services de gestion des comptes
 * 
 * @author boudaa
 *
 */
public class CompteManager {

	private Logger LOGGER = Logger.getLogger(ConsoleApp.class);

	/** Pour stocker les comptes */
	private List<Compte> listeComptes = new ArrayList<Compte>();

	/**
	 * cr�diter un compte
	 * 
	 * @param c
	 *            un compte
	 * @param m
	 *            le montant
	 */
	public void crediter(Compte c, double m) {

		LOGGER.info("Le compte " + c.getNum() + " a r�alis� une op�ration cr�diter");

		c.setSolde(c.getSolde() + m);

	}

	/**
	 * R�cup�re un compte par son num�ro
	 * 
	 * @param num
	 * @return
	 * @throws CompteNotFoundException
	 */
	public Compte getCompteByNum(int num) throws CompteNotFoundException {

		Compte compte = null;

		for (Compte it : listeComptes) {

			if (it.getNum() == num) {
				compte = it;
				break;
			}

		}

		if (compte == null) {
			// Si compte introuvable on l�ve une exception
			throw new CompteNotFoundException("Le compte  avec le num " + num + " n'est existe pas");

		}

		return compte;

	}

	/**
	 * Permet de faire un virement
	 * 
	 * @param ce
	 * @param cr
	 * @param m
	 * @throws CompteOperationException
	 */
	public void virement(Compte ce, Compte cr, double m) throws CompteOperationException {

		debiter(ce, m);
		crediter(cr, m);

		LOGGER.info("Le compte " + ce.getNum() + " a r�alis� un virement vers " + cr.getNum());

	}

	/**
	 * D�biter un compte
	 * 
	 * @param c
	 *            le compte � d�biter
	 * @param m
	 *            le montant
	 * @throws CompteOperationException
	 */
	public void debiter(Compte c, double m) throws CompteOperationException {

		if (m > c.getDebitMax()) {

			LOGGER.error("Le compte " + c.getNum() + " essaie de faire une Op�ration impossible car le d�bit maximal est atteint");

			throw new CompteOperationException("Op�ration impossible car votre d�bit maximal est " + c.getDebitMax());
		}

		if (c.getSolde() - m < -c.getDecouvertMax()) {

			throw new CompteOperationException("Le d�bit est impossible car solde- m < dm");
		}

		c.setSolde(c.getSolde() - m);

	}

	/**
	 * Permet de cr�er un compte
	 * 
	 * @param p
	 *            une personne
	 */
	public Compte createCompte(Personne p) {

		boolean exist = false;
		int num = 0;
		do {

			exist = false;
			num = generateIdCompte();

			for (Compte it : listeComptes) {

				if (it.getNum() == num)
					exist = true;
			}

		} while (exist);

		Compte c = new Compte();

		// renseigner les informations du compte
		c.setNum(num);
		c.setTitulaire(p);

		// Ajouter le compte � la liste
		listeComptes.add(c);

		return c;

	}

	/**
	 * Affiche le conteny de la banque
	 */
	public void showBanque() {

		System.out.println("-------showBanque-------");

		for (Compte it : listeComptes) {

			System.out.println(it);
		}

	}

	/**
	 * m�thode interne pour g�n�rer les num�ro al�atoirement
	 * 
	 * @return
	 */
	public int generateIdCompte() {

		Random r = new Random();
		int i = 0;
		int id = 0;
		while (i < 8) {
			id += r.nextInt(10) * (int) Math.pow(10, i);
			i++;

		}

		return id;

	}

	/**
	 * m�thode interne pour g�n�rer les num�ro al�atoirement
	 * 
	 * @return
	 * @deprecated
	 */
	private int generateIdCompteV2() {

		String txt = "0123456789";
		StringBuffer randomTxt = new StringBuffer();

		while (randomTxt.length() != 8) {

			int indice = (int) (Math.random() * 10);

			randomTxt.append(txt.charAt(indice));

		}

		return Integer.valueOf(randomTxt.toString());

	}

}
