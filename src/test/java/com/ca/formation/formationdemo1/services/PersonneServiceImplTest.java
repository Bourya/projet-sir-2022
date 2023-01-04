package com.ca.formation.formationdemo1.services;

import com.ca.formation.formationdemo1.exception.ResourceNotFoundException;
import com.ca.formation.formationdemo1.models.Personne;
import com.ca.formation.formationdemo1.repositories.PersonneRepository;
import com.ca.formation.formationdemo1.services.impl.PersonneServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class PersonneServiceImplTest {

  @Mock
  PersonneRepository personneRepository;

  @InjectMocks
  private PersonneServiceImpl personneServiceImpl;

  @Test
  public void ajouterPersonne() {
    Personne personne = new Personne("tonux", "samb", 50);
    personne.setId(1L);
    when(personneRepository.save(any())).thenReturn(personne);

    Personne personneResponse = personneServiceImpl.addPersonne(new Personne("tonux", "samb", 50));

    assertNotNull(personneResponse);

    verify(personneRepository, atLeastOnce()).save(any());
  }

  // TODO: ajouter les autres tests sur methodes
  //getPersonnes
  @Test
  public void getAll(){
    Personne personne1=new Personne("Ndiaye", "Ndeye Boury", 22);
    personne1.setId(1L);
    Personne personne2=new Personne("Ndiaye", "test", 21);
    personne2.setId(2L);
    Personne personne3=new Personne("Ndiaye", "test", 20);
    personne3.setId(3L);
    List<Personne> ls = new ArrayList<>();
    ls.add(personne1);
    ls.add(personne2);
    ls.add(personne3);
    when(personneRepository.findAll()).thenReturn(ls);
    List<Personne> list=personneServiceImpl.getPersonnes();
    assertNotNull(list);
    System.out.println("Taille:"+list.size());
    assertEquals(list.size(),3);
    assertEquals(ls,list);


  }
  //getPersonne
  @Test
  public void getPers() throws ResourceNotFoundException {
    Personne personne=new Personne("Ndiaye", "Ndeye Boury", 22);
    personne.setId(1L);
    when(personneRepository.findById(1L)).thenReturn(Optional.of(personne));
    Personne personneGet=personneServiceImpl.getPersonne(1L);
    assertNotNull(personneGet);
    assertEquals(personneGet.getNom(),"Ndiaye");
  }
  //updatePersonne
  @Test
  public void modifPers() throws ResourceNotFoundException {
    Personne personne=new Personne("Ndiaye", "Ndeye Boury", 22);
    personne.setId(1L);
    when(personneRepository.findById(1L)).thenReturn(Optional.of(personne));
  personne.setPrenom("Boury");
  personneServiceImpl.updatePersonne(1L,personne);
    assertEquals("Boury",personne.getPrenom());
   verify(personneRepository, times(1)).save(personne);
  }
  //deletePersonne
  @Test
  public void supPers(){
    Personne personne=new Personne("Ndiaye", "Ndeye Boury", 22);
    personne.setId(1L);
  // when(personneRepository.findById(1L)).thenReturn(Optional.of(personne));
    personneServiceImpl.deletePersonne(1L);
    verify(personneRepository, times(1)).deleteById(1L);
  }
  //getPersonneParNom
  @Test
  public void getPersParNom(){
    Personne personne=new Personne("Ndiaye", "Ndeye Boury", 22);
    personne.setId(1L);
    List<Personne> ls = new ArrayList<>();
    ls.add(personne);
    when(personneRepository.findByNom("Ndiaye")).thenReturn(ls);
    Personne personneGet=personneServiceImpl.getPersonneParNom("Ndiaye").get(0);
    assertNotNull(personneGet);
    assertEquals(personneGet.getNom(),"Ndiaye");
  }
}
