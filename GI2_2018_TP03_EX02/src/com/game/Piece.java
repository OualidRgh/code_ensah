package com.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.game.exceptions.ImpossibleDeplacementException;

/**
 * Repr�sente une pi�ce
 * 
 * @author Boudaa
 *
 */
public abstract class Piece {

	// constantes qui d�finit les d�placements
	public static final int ADVANCE = 1;
	public static final int BACK = 2;
	public static final int TOP = 3;
	public static final int BOTTOM = 4;
	public static final int DIAG_TOP_ADVANCE = 5;
	public static final int DIAG_TOP_BACK = 6;
	public static final int DIAG_BOTTOM_ADVANCE = 7;
	public static final int DIAG_BOTTOM_BACK = 8;

	// Directions possibles
	public static final int[] DIRECTIONS = { ADVANCE, BACK, TOP, BOTTOM, DIAG_TOP_ADVANCE, DIAG_TOP_BACK,
			DIAG_BOTTOM_ADVANCE, DIAG_BOTTOM_BACK };

	/** R�ference l'�chequier */
	protected Echiquier echquier;

	/** position de la piece dans l'echequier */
	protected Point position;

	/** Couleur d'une piece */
	protected int couleur;

	/** Constructeur */
	public Piece(Point p, int c, Echiquier ech) {
		position = p;
		couleur = c;
		echquier = ech;
	}

	/**
	 * chaque piece � sa propre fa�on de se d�placer cette m�thode est abstraite et
	 * sera donc impl�ment�e diff�rement dans chaque type de piece
	 */
	public abstract void isPossibleMove(Point pos) throws ImpossibleDeplacementException;

	/**
	 * chque piece a sa propre facon pour calculer la force c'est pour cette raison
	 * cette m�thode est d�clar�e abstraite
	 */
	public abstract int getPower();

	/** permet d'avoir une representation sous forme d'une chaine de caracteres */
	public abstract String getStringRepresentation();

	/**
	 * Cette m�thode permet de d�placer une piece
	 * 
	 * @param direction
	 * @param nbrCase
	 * @throws ImpossibleDeplacementException
	 */
	public void seDeplacer(int direction, int nbrCase) throws ImpossibleDeplacementException {

		// on obtient la position apres le d�placement, si ce d�placement est possible
		// pour la piece appelante
		// cette m�thode fait un appel � la m�thode isPossibleMove qui est propre �
		// chaque type de piece
		Point tempPos = getPositionAfterMoveIfPossible(direction, nbrCase);

		// Si on arrive � ce point alors le d�placement est possible et il reste �
		// �liminer la piece adversaire si elle est dans tempPos

		Piece p = echquier.getPieceAt(tempPos);

		if (echquier.getPieceAt(tempPos) != null) {
			echquier.removePiece(p);
		}

		// mettre � jour la position de la piece
		position = tempPos;

		echquier.inverserTour();

	}

	/**
	 * Permet de d�t�rminer tous les d�placements possibles d'une pi�ce
	 * 
	 * @param p
	 * @param couleur
	 * @return
	 */
	public List<Point> getPossibleMoves() {

		List<Point> moves = new ArrayList<Point>();

		for (int it : DIRECTIONS) {
			try {
				moves.add(getPositionAfterMoveIfPossible(it, 1));

			} catch (ImpossibleDeplacementException ex) {
				// on ignore volentairement cette exception
				// si position ill�gale on ajoute pas le d�palcement dans la liste moves
			}
		}

		return moves;

	}

	/**
	 * Cette m�thode permet d'avoir la nouvelle position d'une piece apres son
	 * d�placement, cette m�thode v�rifie que ce deplacement est possible @see
	 * isPossibleMove
	 * 
	 * @param direction
	 *            le sens de d�placement
	 * 
	 * @param nbrCase
	 *            nombre de cases
	 * 
	 * @return
	 * @throws ImpossibleDeplacementException
	 */
	private Point getPositionAfterMoveIfPossible(int direction, int nbrCase) throws ImpossibleDeplacementException {
		Point newpos = null;

		if (direction == ADVANCE) {

			// si la pi�ce est noir
			if (couleur == Color.NOIR) {
				newpos = new Point(position.getX(), position.getY() + nbrCase);
			} else if (couleur == Color.BLANC) {
				// pi�ce blanche
				newpos = new Point(position.getX(), position.getY() - nbrCase);
			}
		} else if (direction == BACK) {

			if (couleur == Color.BLANC) {
				newpos = new Point(position.getX(), position.getY() + nbrCase);
			} else if (couleur == Color.NOIR) {
				newpos = new Point(position.getX(), position.getY() - nbrCase);
			}

		} else if (direction == TOP) {

			newpos = new Point(position.getX() - nbrCase, position.getY());

		} else if (direction == BOTTOM) {

			newpos = new Point(position.getX() + nbrCase, position.getY());

		} else if (direction == DIAG_BOTTOM_ADVANCE) {

			if (couleur == Color.BLANC) {
				newpos = new Point(position.getX() + nbrCase, position.getY() - nbrCase);
			} else if (couleur == Color.NOIR) {
				newpos = new Point(position.getX() + nbrCase, position.getY() + nbrCase);
			}

		} else if (direction == DIAG_BOTTOM_BACK) {

			if (couleur == Color.BLANC) {
				newpos = new Point(position.getX() + nbrCase, position.getY() + nbrCase);
			} else if (couleur == Color.NOIR) {
				newpos = new Point(position.getX() + nbrCase, position.getY() - nbrCase);
			}

		} else if (direction == DIAG_TOP_ADVANCE) {

			if (couleur == Color.BLANC) {
				newpos = new Point(position.getX() - nbrCase, position.getY() - nbrCase);
			} else if (couleur == Color.NOIR) {
				newpos = new Point(position.getX() - nbrCase, position.getY() + nbrCase);
			}

		} else if (direction == DIAG_TOP_BACK) {
			if (couleur == Color.BLANC) {
				newpos = new Point(position.getX() - nbrCase, position.getY() + nbrCase);
			} else if (couleur == Color.NOIR) {
				newpos = new Point(position.getX() - nbrCase, position.getY() - nbrCase);
			}

		}

		if (!Echiquier.isPosInEchequier(newpos)) {
			throw new ImpossibleDeplacementException();
		}

		Piece p = echquier.getPieceAt(newpos);

		if (p != null && (p.getCouleur() == couleur || (p.getCouleur() != couleur && getPower() < p.getPower())))

		{
			throw new ImpossibleDeplacementException();
		}

		// v�rification des regles de d�placement de la piece
		isPossibleMove(newpos);

		// la nouvelle position
		return newpos;
	}

	/**
	 * Cette m�thode permet de g�n�rer un d�placement al�atoire pour une piece
	 * donn�e, elle prend en compte les regles de deplacement de chaque piece
	 */
	public void randomDeplacement() {

		// On obtient les d�placements possibles d'une piece, en prenant en compte les
		// regles de d�placement g�n�rales et les regles de chaque piece
		List<Point> possibleMoves = getPossibleMoves();

		// Un entier par hazard dans l'intervale [0,possibleMoves.size()-1]
		int randomIndex = new Random().nextInt(possibleMoves.size());

		// On traite le cas ou la case contient une piece adverse on l'�l�mine de
		// l'echequier
		Point temp = possibleMoves.get(randomIndex);
		Piece p = echquier.getPieceAt(temp);
		// il contient une piece adverse
		if (p != null) {
			echquier.removePiece(p);
		}

		// on change la case de la piece
		position = temp;

		// On passe la main � l'autre joueur
		echquier.inverserTour();

	}

	public int getCouleur() {
		return couleur;
	}

	public Point getPosition() {
		return position;
	}

	public String toString() {
		return "Piece [echquier=" + echquier + ", position=" + position + ", couleur=" + couleur + "]";

	}

}
