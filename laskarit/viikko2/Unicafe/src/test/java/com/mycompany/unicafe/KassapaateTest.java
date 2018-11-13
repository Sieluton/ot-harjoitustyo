/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sami
 */
public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(0);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void uudenKassapaatteenRahaOikein(){
        assertEquals(kassa.kassassaRahaa(), 100000);
    }
    
    @Test
    public void uudenKassapaatteenEdullisiaMyynnitOikein(){
        assertEquals(kassa.edullisiaLounaitaMyyty(), 0);
    }
    
    @Test
    public void uudenKassapaatteenMaukkaitaMyynnitOikein(){
        assertEquals(kassa.maukkaitaLounaitaMyyty(), 0);
    }
    
    @Test
    public void josMaksuRiittavaEdullinenKassaRaha(){
        kassa.syoEdullisesti(240);
        assertEquals(kassa.kassassaRahaa(), 100240);
    }
    
    @Test
    public void josMaksuRiittavaMaukasKassaRaha(){
        kassa.syoMaukkaasti(400);
        assertEquals(kassa.kassassaRahaa(), 100400);
    }
    
    @Test
    public void josMaksuRiittavaEdullinenOstoMaara(){
        kassa.syoEdullisesti(240);
        assertEquals(kassa.edullisiaLounaitaMyyty(), 1);
    }
    
    @Test
    public void josMaksuRiittavaMaukasOstoMaara(){
        kassa.syoMaukkaasti(400);
        assertEquals(kassa.maukkaitaLounaitaMyyty(), 1);
    }
    
    @Test
    public void josMaksuRiittavaEdullinenVaihtoraha(){
        assertEquals(kassa.syoEdullisesti(240), 0);
    }
    
    @Test
    public void josMaksuRiittavaMaukasVaihtoraha(){
        assertEquals(kassa.syoMaukkaasti(400), 0);
    }
    
    @Test
    public void josMaksuEiRiittavaEdullinenKassaRaha(){
        kassa.syoEdullisesti(200);
        assertEquals(kassa.kassassaRahaa(), 100000);
    }
    
    @Test
    public void josMaksuEiRiittavaMaukasKassaRaha(){
        kassa.syoMaukkaasti(399);
        assertEquals(kassa.kassassaRahaa(), 100000);
    }
    
    @Test
    public void josMaksuEiRiittavaEdullinenOstoMaara(){
        kassa.syoEdullisesti(239);
        assertEquals(kassa.edullisiaLounaitaMyyty(), 0);
    }
    
    @Test
    public void josMaksuEiRiittavaMaukasOstoMaara(){
        kassa.syoMaukkaasti(399);
        assertEquals(kassa.maukkaitaLounaitaMyyty(), 0);
    }
    
    @Test
    public void josMaksuEiRiittavaEdullinenVaihtoraha(){
        assertEquals(kassa.syoEdullisesti(239), 239);
    }
    
    @Test
    public void josMaksuEiRiittavaMaukasVaihtoraha(){
        assertEquals(kassa.syoMaukkaasti(399), 399);
    }
    
    
    
    

    
    @Test
    public void josKorttimaksuRiittavaEdullinenKassaRaha(){
        kortti.lataaRahaa(10000);
        kassa.syoEdullisesti(kortti);
        assertEquals(kassa.kassassaRahaa(), 100000);
    }
    
    @Test
    public void josKorttimaksuRiittavaMaukasKassaRaha(){
        kortti.lataaRahaa(10000);
        kassa.syoMaukkaasti(kortti);
        assertEquals(kassa.kassassaRahaa(), 100000);
    }
    
    @Test
    public void josKorttimaksuRiittavaEdullinenOstoMaara(){
        kortti.lataaRahaa(10000);
        kassa.syoEdullisesti(kortti);
        assertEquals(kassa.edullisiaLounaitaMyyty(), 1);
    }
    
    @Test
    public void josKorttimaksuRiittavaMaukasOstoMaara(){
        kortti.lataaRahaa(10000);
        kassa.syoMaukkaasti(kortti);
        assertEquals(kassa.maukkaitaLounaitaMyyty(), 1);
    }
    
    @Test
    public void josKorttimaksuRiittavaEdullinenTrue(){
        kortti.lataaRahaa(10000);
        assertTrue(kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void josKorttimaksuRiittavaMaukasTrue(){
        kortti.lataaRahaa(10000);
        assertTrue(kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void josKorttimaksuEiRiittavaEdullinenKassaRaha(){
        kassa.syoEdullisesti(kortti);
        assertEquals(kassa.kassassaRahaa(), 100000);
    }
    
    @Test
    public void josKorttimaksuEiRiittavaMaukasKassaRaha(){
        kassa.syoMaukkaasti(kortti);
        assertEquals(kassa.kassassaRahaa(), 100000);
    }
    
    @Test
    public void josKorttimaksuEiRiittavaEdullinenOstoMaara(){
        kassa.syoEdullisesti(kortti);
        assertEquals(kassa.edullisiaLounaitaMyyty(), 0);
    }
    
    @Test
    public void josKorttimaksuEiRiittavaMaukasOstoMaara(){
        kassa.syoMaukkaasti(kortti);
        assertEquals(kassa.maukkaitaLounaitaMyyty(), 0);
    }
    
    @Test
    public void josKorttimaksuEiRiittavaEdullinenFalse(){
        assertFalse(kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void josKorttimaksuEiRiittavaMaukasFalse(){
        assertFalse(kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void kortinSaldoNousee(){
        kassa.lataaRahaaKortille(kortti, 5000);
        assertEquals(kortti.saldo(), 5000);
    }
    
    @Test
    public void kassanSaldoNouseeKortilleLadatessa(){
        kassa.lataaRahaaKortille(kortti, 5000);
        assertEquals(kassa.kassassaRahaa(), 105000);
    }
    
    @Test
    public void negatiivisenSummanLatausKortille(){
        kassa.lataaRahaaKortille(kortti, -1);
        assertEquals(kassa.kassassaRahaa(), 100000);
    }
    
}
