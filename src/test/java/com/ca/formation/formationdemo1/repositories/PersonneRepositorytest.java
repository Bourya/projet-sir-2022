package com.ca.formation.formationdemo1.repositories;

import com.ca.formation.formationdemo1.models.Personne;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PersonneRepositorytest {

  @Autowired
  PersonneRepository personneRepository;

  @Test
  public void ajouterPersonne() {
    Personne personne = personneRepository.save(new Personne("tonux", "samb", 50));
    assertNotNull(personne);
    assertEquals(personne.getNom(), "tonux");
  }

  // TODO: ajouter un test sur les autres methodes comme delete, findByNom, etc...
  @Test
  public void findById() {
    /*Personne personne = personneRepository.save(new Personne("Ndiaye", "Ndeye Boury", 22));
    personne.setId(1L);
    Optional<Personne> p=personneRepository.findById(1L);
    //Personne pers=p;
    assertNotNull(p);
    assertEquals(p.getPrenom(), "Ndeye Boury");*/
  }
  @Test
  public void findByNom() {
    Personne personne = personneRepository.save(new Personne("Ndiaye", "Ndeye Boury", 22));
    Personne p=personneRepository.findByNom("Ndiaye").get(0);
    assertNotNull(p);
    assertEquals(p.getPrenom(), "Ndeye Boury");
  }
  @Test
  public void findByNomAndPrenom() {
    Personne personne = personneRepository.save(new Personne("Ndiaye", "Ndeye Boury", 22));
    Personne p=personneRepository.findByNomAndPrenom("Ndiaye","Ndeye Boury").get(0);
    assertNotNull(p);
    assertEquals(p.getAge(),22);
  }
}
