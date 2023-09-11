package com.montaury.cesar;

public class ErreurCleNegative extends RuntimeException {
  public ErreurCleNegative(int cle) {
    super("La cle '" + cle + "' est negative, elle doit toujours etre >= à 0");
  }
}
