package com.example.myapplication;

// Contient les opérations non reliées à l'affichage
// ou aux intéractions avec l'utilisateur
public class Logique {

    // crée un tableau d'entiers mélangés entre 0 et range
    public static int[] make_shuffled_tab(int range) {

        int[] tab = new int[range];

        for (int i = 0; i < range; i++) {
            tab[i] = i;
        }

        shuffle(tab);

        return tab;
    }

    // mélange un tableau d'entiers
    public static void shuffle(int[] tab){
        for (int i=0; i<tab.length; i++){
            int j = i + (int)(Math.random() * (((tab.length-1) - i) + 1));
            swap(tab, i, j);
        }
    }

    // échange deux entrées d'un tableau
    public static void swap(int[] tab, int i, int j){
        int temp = tab[i];
        tab[i] = tab[j];
        tab[j] = temp;
    }
}
