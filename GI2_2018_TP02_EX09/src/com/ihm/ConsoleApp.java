package com.ihm;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bll.CompteManager;
import com.bo.Compte;
import com.bo.Personne;
import com.exception.CompteNotFoundException;
import com.exception.CompteOperationException;

/**
 * La partie HIM de l'application
 * 
 * @author boudaa
 *
 */
public class ConsoleApp {

	public static int mainMenu() {

		System.out.println("--------APPLICATION GESTION COMPTE----------");

		System.out.println("1- Cr�er un compte  ");
		System.out.println("2- D�biter un compte ");
		System.out.println("3- Cr�diter un compte ");
		System.out.println("4- Virement ");
		System.out.println("5- Afficher un compte");
		System.out.println("6- Afficher tous les comptes");
		System.out.println("7- Quitter");
		System.out.println("Choisir une option ");

		Scanner sc = new Scanner(System.in);
		int s = sc.nextInt();

		return s;

	}

	public static void main(String[] args) {

		CompteManager compteManager = new CompteManager();

		do {
			Scanner sc = new Scanner(System.in);

			int choice = mainMenu();

			switch (choice) {
			case 1:
				Personne p = new Personne();
				System.out.println("Entrer le nom de la personne");
				p.setNom(sc.nextLine());
				System.out.println("Entrer le pr�nom de la personne");
				p.setPrenom(sc.nextLine());
				Compte newCompte = compteManager.createCompte(p);
				System.out.println("Compte cr�e avec succ�s");
				System.out.println(newCompte);
				break;

			case 2:
				Compte compte = null;
				try {
					System.out.println("Entrer le num�ro de compte");
					compte = compteManager.getCompteByNum(sc.nextInt());
					System.out.println("Entrer le montant de d�bit");

					compteManager.debiter(compte, sc.nextDouble());

				} catch (CompteNotFoundException ex) {

					System.err.println("le compte indiqu� n'existe pas");
				}

				catch (CompteOperationException e) {

					System.err.println(e.getMessage());

				}

				break;

			case 3:
				System.out.println("Entrer le num�ro de compte");
				Compte compte2 = null;
				try {
					compte2 = compteManager.getCompteByNum(sc.nextInt());
					System.out.println("Entrer le montant de cr�dit");
					compteManager.crediter(compte2, sc.nextDouble());

				} catch (CompteNotFoundException ex) {

					System.err.println("le compte indiqu� n'existe pas");
				}

				break;

			case 4:
				try {
					System.out.println("Entrer le num�ro de compte � d�biter ");
					Compte c1 = compteManager.getCompteByNum(sc.nextInt());

					System.out.println("Entrer le num�ro de compte � cr�diter ");
					Compte c2 = compteManager.getCompteByNum(sc.nextInt());

					System.out.println("Entrer le montant de virement");
					compteManager.virement(c1, c2, sc.nextDouble());
				} catch (CompteNotFoundException ex) {

					System.err.println("le compte indiqu� n'existe pas");
				}

				catch (CompteOperationException e) {

					System.err.println(e.getMessage());

				}
				break;

			case 5:

				System.out.println("Entrer le num�ro de compte � afficher ");
				try {
					System.out.println(compteManager.getCompteByNum(sc.nextInt()).toString());
				} catch (CompteNotFoundException ex) {

					System.err.println("le compte indiqu� n'existe pas");
				}

				break;

			case 6:

				compteManager.showBanque();

				break;

			case 7:

				System.exit(0);

			default:
				System.out.println("veuillez choisir une option de 1 � 7");
				break;
			}

		} while (true);
	}

}
