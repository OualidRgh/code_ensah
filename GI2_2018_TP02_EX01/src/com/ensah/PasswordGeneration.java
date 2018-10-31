package com.ensah;

import java.util.Random;

/**
 * Solution de l'exercice 01, dont lequel il est demand� d'impl�menter deux
 * algorithmes simples pour g�n�rer une chaine de caract�res al�atoire
 * 
 * @author boudaa
 *
 */
public class PasswordGeneration {

	/**
	 * G�n�re une chaine al�atoire
	 * 
	 * @param n
	 *            : nombre de caract�res de la chaine
	 * @return la chaine al�atoire
	 */
	public static String generatePassword(int n) {

		// ici on consid�re que nous voulons avoir des acaract�res ayant un code dans
		// l'intervalle [33 ,126[

		// tableau de caract�res
		char[] tab = new char[n];

		int i = 0;

		// cette classe permet de g�n�rer des nombres al�atoires
		Random r = new Random();

		while (i < n) {

			// on g�n�re un nombre al�atoire par la m�thode r.nextInt(93) dans l'intervalle
			// 0,93 puis on applique un d�calage pour retrouver l'interval 33-126
			// le resultat est converit en char puis stock� dans le tableau de caract�res
			tab[i++] = (char) (33 + r.nextInt(93));

		}

		// on convertit le tableau de caract�res en une chaine de caract�res
		return new String(tab);

	}

	/**
	 * impl�mentation avec le deuxi�me algorithme demand� dans la question 02
	 * 
	 * @param n
	 *            : nombre de caract�res de la chaine
	 * @return la chaine al�atoire
	 */
	public static String generatePasswordV2(int n) {

		// on contruit le tableau qui stocke l'ensemble des caract�res autoris�s
		char[] tab = new char[94];
		for (int i = 33, j = 0; i < 126; i++, j++) {
			tab[j] = (char) i;
		}

		// Chaine vide
		StringBuilder password = new StringBuilder();

		// On g�n�re la chaine al�atoire
		while (password.length() < n) {

			// On g�n�re un nombre entier qui represente un indice dans le tableau tab puis
			// on acc�de au caract�re associ� puis on l'ajoute � la fin de la chaine
			// alatoire
			// randomInt est une m�thode qui nous avons cod� en se basant sur la m�thode
			// Math.Random voir ci-dessous son code
			password.append(tab[randomInt(0, 93)]);

		}

		// conversion en chaine de caract�re puis retour du r�sultat
		return password.toString();

	}

	/**
	 * M�thode qui retourne un entier dans l'intervalle [a,b[
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int randomInt(int a, int b) {

		// on a 0<Math.random()<1
		// donc (b-a)* 0 < (b-a) Math.random() < (b-a) => (b-a)* 0 +a < (b-a)
		// Math.random()+a < (b-a)+a
		// a < (b-a) Math.random() + a < b

		return (int) (a + (b - a) * Math.random());
	}

	public static void main(String[] args) {

		// Avec le premier algorithme
		System.out.println(generatePassword(8));

		// avec le deuxi�me
		System.out.println(generatePasswordV2(8));

	}

}
