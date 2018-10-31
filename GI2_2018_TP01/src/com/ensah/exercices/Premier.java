package com.ensah.exercices;

import java.util.Scanner;

/**
 * Exercice 01 - TP 01
 * 
 * @author boudaa
 * 
 *         ENSA Al Hoceima, 2018/2019
 */
public class Premier {

	public static boolean isPrime(int n) {

		// Si n �gale 0 ce n'est pas un nombre premier
		if (n == 0) {
			return false;
		}

		// si n �gale 1 ou 2 c'est un nombre premier

		if (n == 1 || n == 2) {
			return true;
		}

		// si c'est un nombre pair ce n'est pas un nombre premier
		if (n % 2 == 0) {
			return false;
		}

		// sinon on va faire une boucle pour d�cider
		// en effet Si un entier n n�est divisible par aucun entier compris entre 2 et
		// la racine carr�e de n , alors n est premier
		// ce n'est pas la peine de perdre en performance en divisant sur des nombres
		// pairs
		for (int i = 3; i <= Math.sqrt(n); i = i + 2) {
			if (n % i == 0) {
				return false;
			}
		}

		// si on arrive ici c�d le nombre est premier
		return true;

	}

	public static void main(String[] args) {
		int n;
		// Si le nombre est pass� comme param�tre au programme
		if (args.length != 0) {

			// convertir le param�tre pass� � la commande en entier
			n = Integer.valueOf(args[0]);

		} else {
			// Lire le nombre au clavier
			Scanner s = new Scanner(System.in);
			n = s.nextInt();
		}

		// On app�le la m�thode static permettant de faire la v�rification
		if (isPrime(n)) {
			System.out.println("Le nombre est premier");
		} else {
			System.out.println("Le nombre n'est pas premier");
		}
	}

}
