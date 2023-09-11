package com.montaury.cesar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ChiffreCesarTest {

  private ChiffreCesar chiffreCesar;

  @BeforeEach
  void setUp() {
    chiffreCesar = new ChiffreCesar();
  }

  @Test
  void devrait_retourner_une_chaine_vide_quand_chaine_vide_en_entree() {
    var chaineChiffree = chiffreCesar.chiffrer("", 1);

    assertThat(chaineChiffree).isEmpty();
  }

  @Test
  void devrait_lever_une_erreur_si_la_cle_est_negative() {
    var erreur = catchThrowable(() -> chiffreCesar.chiffrer("texte", -1));

    assertThat(erreur)
      .isInstanceOf(ErreurCleNegative.class)
      .hasMessage("La cle '-1' est negative, elle doit toujours etre >= à 0");
  }

  @Test
  void devrait_retourner_la_chaine_d_origine_quand_la_cle_est_0() {
    var chaineChiffree = chiffreCesar.chiffrer("texte", 0);

    assertThat(chaineChiffree).isEqualTo("texte");
  }

  @Test
  void devrait_chiffrer_le_texte_quand_la_cle_est_entre_0_et_25() {
    var chaineChiffree = chiffreCesar.chiffrer("texte", 12);

    assertThat(chaineChiffree).isEqualTo("fqjfq");
  }

  @Test
  void devrait_conserver_les_majuscules_en_chiffrant_le_texte() {
    var chaineChiffree = chiffreCesar.chiffrer("TEXTE", 12);

    assertThat(chaineChiffree).isEqualTo("FQJFQ");
  }

  @Test
  void devrait_retourner_la_chaine_d_origine_quand_la_cle_est_26() {
    var chaineChiffree = chiffreCesar.chiffrer("texte", 26);

    assertThat(chaineChiffree).isEqualTo("texte");
  }

  @Test
  void devrait_chiffrer_le_texte_quand_la_cle_est_superieure_a_26() {
    var chaineChiffree = chiffreCesar.chiffrer("texte", 27);

    assertThat(chaineChiffree).isEqualTo("ufyuf");
  }

  @Test
  void devrait_ne_pas_chiffrer_les_caracteres_speciaux() {
    var chaineChiffree = chiffreCesar.chiffrer(" ,?;.:/!{}[]()'\\\"", 12);

    assertThat(chaineChiffree).isEqualTo(" ,?;.:/!{}[]()'\\\"");
  }

  @Test
  void devrait_retourner_une_etoile_pour_les_caracteres_non_chiffrables() {
    var chaineChiffree = chiffreCesar.chiffrer("àéèêëùï0123456789", 12);

    assertThat(chaineChiffree).isEqualTo("*****************");
  }
}