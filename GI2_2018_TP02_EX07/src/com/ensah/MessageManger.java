package com.ensah;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Cette classe permet de g�rer les message
 * 
 * @author boudaa
 *
 */
public class MessageManger {

	/** permet de stocker les messages */
	private List<Message> messages = new LinkedList<Message>();

	/**
	 * cette m�thode permet de supprim� un messsage avec son identifiant
	 * 
	 * @param id
	 *            identifiant du message
	 * @return retourne l'�tat de succ�s ou non de cette m�thode
	 */
	public boolean deleteMessage(int id) {

		boolean found = false;
		int i = 0;
		// rechercher le message avec l'identifiant pass� en param�tre
		for (i = 0; i < messages.size(); i++) {

			if (messages.get(i).getId() == id) {
				found = true;
				break;
			}

		}

		if (found) {
			messages.remove(i);

		}

		return found;

	}

	/**
	 * Retourne le premier �l�ment dans la liste
	 * 
	 * @return
	 */
	public Message getFirstMessage() {

		return messages.get(0);
	}

	/**
	 * Retourne le dernier �l�ment dans la liste
	 * 
	 * @return
	 */
	public Message getLastMessage() {

		return messages.get(messages.size() - 1);
	}

	/**
	 * Rechercher un �l�ment par son identifiant
	 * 
	 * @param id
	 * @return
	 */
	public Message findMessageById(int id) {

		for (Message it : messages) {

			if (it.getId() == id)
				return it;
		}

		return null;
	}

	/**
	 * Mettre � jour un message par les donn�es pass�es dans l'objet en param�tre
	 * 
	 * @param m
	 * @return
	 */
	public boolean updateMessage(Message m) {

		Message msg = findMessageById(m.getId());

		if (msg != null) {
			msg.setContenu(m.getContenu());
			msg.setDate(m.getDate());
			msg.setTitre(m.getTitre());

			return true;

		}

		return false;

	}

	/**
	 * Recherche un message par son titre
	 * 
	 * @param pTitre
	 * @return
	 */
	public List<Message> findMessageByTitle(String pTitre) {
		List<Message> searchResult = new ArrayList<Message>();
		for (Message it : messages) {
			if (it.getTitre().equals(pTitre))
				searchResult.add(it);
		}

		return searchResult;
	}

	/**
	 * Affiche tous les messages de la liste
	 */
	public void display() {

		System.out.println("-----Contenu de la liste-----");
		for (Message it : messages) {
			System.out.println(it.toString());
		}

	}

	public void displaySearchResult(List<Message> results) {
		System.out.println("R�sultat de recherche :");
		if (results.size() == 0) {
			System.out.println("Il n y a aucun �l�ment qui correspond � votre recherche");
		} else {
			for (Message it : results) {
				System.out.println(it);
			}
		}
	}

	/**
	 * Ajoute un message dans la liste des messages
	 * 
	 * @param m
	 */
	public void dddMessage(Message m) {

		messages.add(m);
	}

	/**
	 * Retourne tous les messages
	 * 
	 * @return
	 */
	public List<Message> getAllMessages() {

		return messages;

	}

}
