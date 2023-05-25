package com.maven.registro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit test for simple App.
 */
class AppTest {
    Registro r = new Registro();

    @Test
    public void registraVotoTest() {
        r.registraVoto("Luca", 10, "Matematica");
        r.registraVoto("Luca", 5, "Italiano");
        r.registraVoto("Marco", 6, "Matematica");
        assertEquals(2, r.registro.get("Luca").size());
    }

    @Test
    public void mediaAlunnoTest(){
        r.registraVoto("Luca", 10, "Matematica");
        r.registraVoto("Luca", 5, "Italiano");
        r.registraVoto("Marco", 6, "Matematica");
        r.registraVoto("Marco", 2, "Matematica");
        assertEquals(7.5, r.mediaAlunno("Luca"));
    }

    @Test
    public void mediaMateriaTest(){
        r.registraVoto("Luca", 10, "Matematica");
        r.registraVoto("Luca", 5, "Italiano");
        r.registraVoto("Luca", 6, "Matematica");
        r.registraVoto("Marco", 2, "Matematica");
        assertEquals(8, r.mediaMateria("Luca", "Matematica"));
    }

    @Test
    public void alunniInsufficentiTest(){
        r.registraVoto("Luca", 10, "Matematica");
        r.registraVoto("Luca", 5, "Italiano");
        r.registraVoto("Luca", 6, "Matematica");
        r.registraVoto("Marco", 2, "Matematica");
        assertEquals(1, r.alunniInsufficenti().count());
    }

    @Test
    public void alunniInsufficentiMateriaTest(){
        r.registraVoto("Luca", 10, "Matematica");
        r.registraVoto("Luca", 5, "Italiano");
        r.registraVoto("Nicola", 6, "Italiano");
        r.registraVoto("Marco", 2, "Italiano");
        assertEquals(2, r.alunniInsufficentiMateria("Italiano").count());
    }

    @Test
    public void alunniMaiInsufficentiTest(){
        r.registraVoto("Luca", 10, "Matematica");
        r.registraVoto("Luca", 5, "Italiano");
        r.registraVoto("Nicola", 6, "Italiano");
        r.registraVoto("Marco", 2, "Italiano");
        assertEquals(1, r.alunniMaiInsufficenti().count());
    }

    @Test
    public void matricolaNonRegistrataExceptionTest(){
        assertThrows(Registro.matricolaNonRegistrataException.class, () -> r.mediaAlunno("Luca"));
    }

    @Test
    public void votoNonValidoExceptionTest(){
        assertThrows(Registro.votoNonValidoException.class, () -> r.registraVoto("Luca", 11, "Matematica"));
    }
}