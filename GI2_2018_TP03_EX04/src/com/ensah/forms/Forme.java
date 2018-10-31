package com.ensah.forms;

/**
 * Classe abstraite qui represente une forme
 * 
 * @author boudaa
 *
 */
public abstract class Forme {

	/** Centre de la forme */
	private Point3D centre;

	/** densit� de la forme */
	private double densite;

	/**
	 * Constructeur permettant d'initialiser une forme
	 * 
	 * @param pCentre
	 * @param pDencite
	 */
	public Forme(Point3D pCentre, double pDencite) {

		centre = pCentre;
		densite = pDencite;
	}

	/**
	 * m�thode abstraite, car chaque forme a sa propre formule pour le calcul de la
	 * surface
	 */
	public abstract double calculerSurface();

	/**
	 * m�thode abstraite, car chaque forme a sa propre formule pour le calcul de
	 * volume
	 */
	public abstract double calculerVolume();

	/**
	 * Le poid se clacul avec la meme forme, donc les autre forme n'auront qu'�
	 * h�riter cette m�thode
	 */
	public double calculerPoids() {

		return calculerVolume() * densite;
	}

	/** permet de d�placer une forme, en d�placant son centre */
	public void deplacer(double dx, double dy, double dz) {

		centre.deplacer(dx, dy, dz);
	}

	public String toString() {

		// On r�cup�re le nom de la classe
		String formName = getClass().getSimpleName();

		StringBuilder sb = new StringBuilder();

		// Concatination des informations d'une forme
		sb.append(formName);
		sb.append(": Centre = ");
		sb.append(centre.toString());
		sb.append("Densit�:");
		sb.append(densite);

		return sb.toString();

	}

	public Point3D getCentre() {
		return centre;
	}

	public void setCentre(Point3D centre) {
		this.centre = centre;
	}

	public double getDenSite() {
		return densite;
	}

	public void setDenSite(double densite) {
		this.densite = densite;
	}

}
