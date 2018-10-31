package com.ensah.forms;

public class Boule extends Forme {

	private double rayon;

	public Boule(Point3D pCentre, double pDencite, double pRayon) {
		super(pCentre, pDencite);
		rayon = pRayon;
	}

	public String toString() {
		String s = super.toString();
		return s + "rayon :" + rayon;
	}

	public double calculerSurface() {
		// l'impl�mentation de cette m�thode n'est pas demand�e dans l'exercice

		return 4 * Math.PI * rayon * rayon;
	}

	public double calculerVolume() {

		// l'impl�mentation de cette m�thode n'est pas demand�e dans l'exercice

		return ((4 * Math.PI) * Math.pow(rayon, 3)) / 3;
	}

}
