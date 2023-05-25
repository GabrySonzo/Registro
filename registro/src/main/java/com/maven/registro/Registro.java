package com.maven.registro;

import java.util.HashMap;
import java.util.*;
import java.util.stream.*;

public class Registro{
    
    public class matricolaNonRegistrataException extends RuntimeException{}
    public class votoNonValidoException extends RuntimeException{}
    
    HashMap<String, LinkedList<Valutazione>> registro;
    public Registro(){
        registro = new HashMap<String, LinkedList<Valutazione>>();
    }
    
    public void registraVoto(String matricola, int voto, String materia){
        if(voto < 0 || voto > 10){
            throw new votoNonValidoException();
        }
        if(registro.get(matricola)==null){
            registro.put(matricola, new LinkedList<Valutazione>());
        }
        LinkedList<Valutazione>  l = registro.get(matricola);
        l.add(new Valutazione(matricola,voto,materia));
    }
    
    public void visualizzaVoti(String matricola) throws Registro.matricolaNonRegistrataException {
        if(!registro.containsKey(matricola)){
            throw new matricolaNonRegistrataException();
        }
        registro.get(matricola).stream().forEach(System.out::println);
    }
    
    public double mediaAlunno(String matricola) throws Registro.matricolaNonRegistrataException {
        if(!registro.containsKey(matricola)){
            throw new matricolaNonRegistrataException();
        }
        double somma = registro.get(matricola).stream().reduce(0, (partial, e) -> partial+e.voto, Integer::sum);
        double media = somma/registro.get(matricola).size();
        return media;
    }
    
    public double mediaMateria(String matricola, String materia){
        if(!registro.containsKey(matricola)){
            throw new matricolaNonRegistrataException();
        }
        double somma = registro.get(matricola).stream().filter(a -> a.materia == materia).reduce(0, (partial, e) -> partial+e.voto, Integer::sum);
        double count = registro.get(matricola).stream().filter(a -> a.materia == materia).count();
        double media = somma/count;
        return media;
    }
    
    public Stream<String> alunniInsufficenti(){
        return registro.keySet().stream().filter( a -> mediaAlunno(a) < 6);
    }
    
    public Stream<String> alunniInsufficentiMateria(String materia){
        return registro.keySet().stream().filter( a -> mediaMateria(a, materia) < 6);
    }
    
    public Stream<String> alunniMaiInsufficenti(){
        return registro.keySet().stream().filter( a -> registro.get(a).stream().allMatch(v -> v.voto >= 6));
    }
}
