package com.ca.formation.formationdemo1.repositories;

import com.ca.formation.formationdemo1.models.Personne;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
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
    Personne personne = personneRepository.save(new Personne("Ndiaye", "Ndeye Boury", 22));
    personne.setId(1L);
    Optional<Personne> p = personneRepository.findById(1L);
    //System.out.println(p.isPresent());
    assertNotNull(p);

    //System.out.println(p.get().getPrenom());
   //assertEquals(p.getPrenom(), "Ndeye Boury");
  }
  @Test
  public void delete(){
    Personne personne = personneRepository.save(new Personne("Ndiaye", "Ndeye Boury", 22));
    personne.setId(1L);
    personneRepository.delete(personne);
    Optional<Personne> p=personneRepository.findById(1L);
    assertEquals(p, Optional.empty());
  }
  public void ageGreaterThan(){
    Personne personne = personneRepository.save(new Personne("Ndiaye", "Ndeye Boury", 22));
    List<Personne> list=(List<Personne>) personneRepository.findAll();
    System.out.println(list.size());
    List<Personne> list2= personneRepository.ageGreaterThan(22);
    System.out.println(list2.size());
    assertEquals(list2.size(), 2);

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
  @Test
  public void findAll() {
    Personne personne = personneRepository.save(new Personne("Ndiaye", "Ndeye Boury", 22));
    personneRepository.save(new Personne("Ndiaye", "Ndeye Boury", 23));
    personneRepository.save(new Personne("Ndiaye", "Ndeye Boury", 24));
    List<Personne> list=(List<Personne>) personneRepository.findAll();
   System.out.println(list.size());
    assertNotNull(list);
    assertEquals(list.size(),5);
  }
}
