package com.montaury.cesar;

import java.util.Set;

public class ChiffreCesar {
  private static final int NB_LETTRES_ALPHABET = 26;
  private static final Set<Character> SEPARATEURS = Set.of(' ', ',', '?', ';', '.', ':', '/', '!', '{', '}', '[', ']', '(', ')', '\'', '\"', '\\');

  public String chiffrer(String aChiffrer, int cle) {
    if (cle < 0) {
      throw new ErreurCleNegative(cle);
    }
    String chaineChiffree = "";
    for (int i = 0; i < aChiffrer.length(); i++) {
      char caractereAChiffrer = aChiffrer.charAt(i);
      char caractereChiffre = switch (typeCaractere(caractereAChiffrer)) {
        case MINUSCULE -> chiffrerCaractereAlphabetique(caractereAChiffrer, 'a', cle);
        case MAJUSCULE -> chiffrerCaractereAlphabetique(caractereAChiffrer, 'A', cle);
        case SEPARATEUR -> caractereAChiffrer;
        case NON_SUPPORTE -> '*';
      };
      chaineChiffree += caractereChiffre;
    }
    return chaineChiffree;
  }

  private static TypeCaractere typeCaractere(char caractere) {
    if (caractere >= 'a' && caractere <= 'z') {
      return TypeCaractere.MINUSCULE;
    }
    if (caractere >= 'A' && caractere <= 'Z') {
      return TypeCaractere.MAJUSCULE;
    }
    if (SEPARATEURS.contains(caractere)) {
      return TypeCaractere.SEPARATEUR;
    }
    return TypeCaractere.NON_SUPPORTE;
  }

  private char chiffrerCaractereAlphabetique(char caractere, char base, int cle) {
    var decalage = cle % NB_LETTRES_ALPHABET;
    var rangCaractereCourant = caractere - base;
    var rangCaractereChiffre = (rangCaractereCourant + decalage) % NB_LETTRES_ALPHABET;
    return (char) (base + rangCaractereChiffre);
  }

  private enum TypeCaractere {
    MINUSCULE, MAJUSCULE, SEPARATEUR, NON_SUPPORTE
  }
}
