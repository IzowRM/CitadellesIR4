package controleur;

import Exeption.OuiNonException;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Interaction {
    private static Scanner sc = new Scanner(System.in);

    public static int lireUnEntier() {
        int i = 0;
        boolean continu = true;
        do {
            try {
                i = sc.nextInt();
                continu = false;
            } catch (InputMismatchException e) {
                System.out.print("Veuillez rentrer un chiffre : ");
                sc.next(); // passe l'entier pour Viter de boucler
            }
        } while(continu);
        return i;
    }

    // renvoie un entier lu au clavier compris dans l'intervalle
    //     [borneMin, borneMax]
    //     modification des variables d'entrée pour plus de flexibilité.
    public static int lireUnEntier(int borneMin, int borneMax,String msg) {
        int i = 0;
        boolean continu = true;
        do {
            try {
                System.out.print(msg+" "+ borneMin + " et " + borneMax + " : ");
                i = sc.nextInt();
                if (i >= borneMin && i <= borneMax) {
                    continu = false; // L'entier est dans la plage spécifiée, la boucle s'arrête
                } else {
                    System.out.println("L'entier doit être compris entre " + borneMin + " et " + borneMax + ".");
                    continu = true; // L'entier n'est pas dans la plage, la boucle continue
                }
            } catch (InputMismatchException e) {
                System.out.println("Ce n'est pas un entier valide.");
                sc.next(); // Passe l'entrée incorrecte pour éviter de boucler
                continu = true; // La boucle continue
            }
        } while (continu);
        return i;
    }

    // lit les r?ponses "oui", "non", "o" ou "n" et renvoie un bool?en
    public static boolean lireOuiOuNon() {
        boolean retour = false;

        while (true) {
            try {
                System.out.print("Veuillez répondre par 'oui' (o) ou 'non' (n) : ");
                String choix = sc.nextLine().toLowerCase();

                if (choix.equals("oui") || choix.equals("o")) {
                    retour = true;
                    break; // Sortir de la boucle si la réponse est valide
                } else if (choix.equals("non") || choix.equals("n")) {
                    retour = false;
                    break; // Sortir de la boucle si la réponse est valide
                } else {
                    throw new OuiNonException("Réponse invalide. Veuillez répondre par 'oui' (o) ou 'non' (n).");
                }
            } catch (OuiNonException e) {
                System.out.println(e.getMessage()); // Afficher le message d'erreur personnalisé
            }
        }

        return retour;
    }

    // renvoie une cha?ne de caract?re lue au clavier:
    public static String lireUneChaine() {

        String retour = "";
        retour= sc.nextLine();
        return retour;
    }
    public static int returnInt(int i){
        Random random = new Random();

        // Générer un chiffre aléatoire entre 0 (inclus) et 4 (exclus)
        return random.nextInt(i);
    }

}