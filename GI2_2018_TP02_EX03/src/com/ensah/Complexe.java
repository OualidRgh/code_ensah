package com.ensah;

import java.util.regex.*;

/**
 * Classe qui pr�sente un nombre complexe
 * 
 * @author boudaa
 *
 */
public class Complexe {

	/** Partie r�el */
	private double a;

	/** Partie imaginaire */
	private double b;

	public Complexe() {
	}

	public Complexe(double pRel, double pImg) {
		a = pRel;
		b = pImg;
	}

	/**
	 * M�thode qui retourne le conjugu�
	 * 
	 * @return
	 */
	public Complexe conjugue() {

		return new Complexe(a, -b);
	}

	/**
	 * Permet de comparer deux objet de type Complexe
	 */
	public boolean equals(Object o) {

		// Si les deux r�f�rence sont identiques (r�f�rence la meme instance)
		if (o == this) {
			return true;
		}

		// Si l'objet pass� en param�tre est null donc il n y a pas l'�aglit�
		if (o == null) {
			return false;
		}

		// On v�rifie que l'objet appelant et l'objet pass� en param�tre sont de meme
		// type
		// On peut aussi utilis� l'op�rateur instanceof :if (o instanceof Complexe)
		// {...}

		if (o.getClass() == Complexe.class) {

			// Si oui alors on va forcer la conversion de o en Complexe
			Complexe c = (Complexe) o;

			// Pour avoir l'�galit� il faut que les parties r�els et imaginaire de l'objet
			// appelant et celui en param_tre soient �gales
			return (c.a == this.a && c.b == this.b);

		}

		return false;
	}

	public void setA(double pA) {
		a = pA;
	}

	public void setB(double pB) {
		b = pB;
	}

	public String toString() {
		return a + "+i" + b;
	}

	/**
	 * M�thode qui convertit une chaine de caract�res en un nombre complexe
	 * 
	 * @param pComplexe
	 * @return
	 */
	public static Complexe parseComplexe(String pComplexe) {

		// a+ib
		String pattern1 = "^(\\d+\\.?\\d*)\\+i(\\d+\\.?\\d*)$";

		// a+bi
		String pattern2 = "^(\\d+\\.?\\d*)\\+(\\d+\\.?\\d*)i$";

		// ib
		String pattern3 = "^(?:i(\\d+\\.?\\d*))$";

		// bi
		String pattern4 = "^(?:(\\d+\\.?\\d*)i)$";

		// a
		String pattern5 = "^(\\d+\\.?\\d*)$";

		
		
		
		
		// cr�er les objets Pattern associ�s
		Pattern r1 = Pattern.compile(pattern1);
		Pattern r2 = Pattern.compile(pattern2);
		Pattern r3 = Pattern.compile(pattern3);
		Pattern r4 = Pattern.compile(pattern4);
		Pattern r5 = Pattern.compile(pattern5);

		// cr�er les objets Matcher associ�s
		Matcher m1 = r1.matcher(pComplexe);
		Matcher m2 = r2.matcher(pComplexe);
		Matcher m3 = r3.matcher(pComplexe);
		Matcher m4 = r4.matcher(pComplexe);
		Matcher m5 = r5.matcher(pComplexe);

		if (m1.matches()) {
			return new Complexe(Double.valueOf(m1.group(1)), Double.valueOf(m1.group(2)));
		} else if (m2.matches()) {

			return new Complexe(Double.valueOf(m2.group(1)), Double.valueOf(m2.group(2)));
		}

		else if (m3.matches()) {

			return new Complexe(0, Double.valueOf(m3.group(1)));
		} else if (m4.matches()) {

			return new Complexe(0, Double.valueOf(m4.group(1)));
		} else if (m5.matches()) {

			return new Complexe(Double.valueOf(m5.group(1)), 0);
		}
		throw new RuntimeException();

	}

	/**
	 * M�thode pour faire un test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Cr�er un nombre complexe
		Complexe c = new Complexe(1, 9);
		// on l'affiche
		System.out.println(c);

		// On affiche son conjugu�
		System.out.println(c.conjugue());

		// On compare deux objet de type complexe
		Complexe c1 = new Complexe(1, 9);
		Complexe c2 = new Complexe(1.0, 9);
		if (c1.equals(c2)) {
			System.out.println("Les deux nombres sont �gaux");
		} else {
			System.out.println("Les deux nombres ne sont pas �gaux");
		}

		// remarquer que la comparaison c1==c2 donnera false
		System.out.println(c1 == c2);

		// Testes de la m�thode parseComplexe

		System.out.println(parseComplexe("2.2+3.6i"));
		System.out.println(parseComplexe("2.2+i3.6"));
		System.out.println(parseComplexe("3.6i"));
		System.out.println(parseComplexe("i3.6"));
		System.out.println(parseComplexe("2.2"));
	}

}
