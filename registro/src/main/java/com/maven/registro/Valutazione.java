package com.maven.registro;

public class Valutazione
{
    String matricola;
    int voto;
    String materia;
    public Valutazione(String matricola, int voto, String materia){
        this.matricola=matricola;
        this.voto=voto;
        this.materia=materia;
    }
    
    public int avarage(int somma){
        return somma+=voto;
    }
    
    public String toString(){
        String string = "Matricola: " + matricola + " Voto: " + voto + " Materia: " + materia;
        return string;
    }
}
