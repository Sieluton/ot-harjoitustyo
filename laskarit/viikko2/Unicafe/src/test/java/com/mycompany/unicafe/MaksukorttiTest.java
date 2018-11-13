package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoOikein(){
        assertEquals(kortti.saldo(), 10);
    }
    
    @Test
    public void lataaminenKasvattaaSaldoa(){
        kortti.lataaRahaa(90);
        assertEquals(kortti.toString(), "saldo: 1.0");
    }
    
    @Test
    public void saldoPieneneeJosTarpeeksiMaksuun(){
        kortti.lataaRahaa(90);
        kortti.otaRahaa(50);
        assertEquals(kortti.toString(), "saldo: 0.50");
    }
    
    @Test
    public void saldoEiPieneneJosEiTarpeeksiMaksuun(){
        kortti.lataaRahaa(90);
        kortti.otaRahaa(110);
        assertEquals(kortti.toString(), "saldo: 1.0");
    }
    
    @Test
    public void palauttaaTrueJosRahatRiittävät(){
        assertTrue(kortti.otaRahaa(10));
    }
    
    @Test
    public void palauttaaFalseJosRahatEiRiitä(){
        assertFalse(kortti.otaRahaa(100));
    }
    
    @Test
    public void kortintoStringOikein(){
        assertEquals(kortti.toString(), "saldo: 0.10");
    }
    
}
