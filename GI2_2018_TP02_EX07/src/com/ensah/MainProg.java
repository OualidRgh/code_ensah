package com.ensah;

import java.util.List;

/**
 * Programme pour tester l'exercice 07
 * 
 * @author boudaa
 *
 */
public class MainProg {

	public static void main(String[] args) {

		// Cr�ation d'un objet pour la gestion des messages
		MessageManger messageManger = new MessageManger();

		// Cr�ation de quelques messages
		Message m1 = new Message(1, "titre1", "22/10/2018", "je suis un message exemple");
		Message m2 = new Message(2, "titre2", "22/10/2018", "je suis un message exemple");
		Message m3 = new Message(3, "titre3", "22/10/2018", "je suis un message exemple");

		// Enregistrer les messages
		messageManger.dddMessage(m1);
		messageManger.dddMessage(m2);
		messageManger.dddMessage(m3);

		// Afficher les messages enregistr�e
		messageManger.display();

		// mettre � jour les informations du message ayant l'id = 1
		messageManger.updateMessage(new Message(1, "remerciement", "22/12/2015", "Merci pour ton effort"));
		// On affiche le contenu de la liste
		messageManger.display();

		// On affiche l'�l�ment qui se trouve au d�but de la liste
		System.out.println("--------Message au d�but--------");
		System.out.println(messageManger.getFirstMessage());

		// On affiche l'�l�ment qui se trouve � la fin de la liste
		System.out.println("--------Message � la fin--------");
		System.out.println(messageManger.getLastMessage());

		// Supprimer le message ayant l'id = 3 puis r�afficher la liste
		messageManger.deleteMessage(3);
		messageManger.display();

		// On effectue une recherche par titre
		List<Message> results = messageManger.findMessageByTitle("titre2");
		// afficher les r�sultats
		messageManger.displaySearchResult(results);

		// On effectue une deuxi�me recherche par titre
		List<Message> results2 = messageManger.findMessageByTitle("titreX");
		// afficher les r�sultats
		messageManger.displaySearchResult(results2);

	}

}
