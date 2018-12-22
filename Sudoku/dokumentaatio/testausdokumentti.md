# Testausdokumentti

Ohjelmaa on testattu automatisoiduin yksikkötestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

### sovelluslogiikka

Automatisoitujen testien ytimen moudostavat sovelluslogiikkaa, eli pakkauksen [sudoku.domain](https://github.com/Sieluton/ot-harjoitustyo/tree/master/Sudoku/src/main/java/sudoku/domain)
luokkaa [SudokuGridTest](https://github.com/Sieluton/ot-harjoitustyo/blob/master/Sudoku/src/test/java/sudoku/domain/SudokuGridTest.java) metodeja testaavat yksikkötestit.

### SudokuDao luokka

Ei ole testattu ollenkaan yksikkötesteillä. Kaikki tieto mikä tietokantaan on ei pitäisi voida olla puutteellinen.

### Testauskattavuus

Jacoco ei jostain syystä suostu enää luomaan rapottia, mutta testien määrän perusteella arvioisin kattavuuden olevan aika alhainen.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi

Sovellus on haettu ja sitä on testattu [käyttöohjeen](https://github.com/Sieluton/ot-harjoitustyo/blob/master/Sudoku/dokumentaatio/kayttoohje.md) kuvaamalla tavalla Windows 10 käyttöjärjestelmällä.
Sovellusta on testattu sekä tilanteissa, joissa tietokanta on jo ollut olemassa ja myös silloin kun sitä ei ole jolloin sovellus luo uuden.

### Toiminnallisuudet

Kaikki [määrittelydokumentin](https://github.com/Sieluton/ot-harjoitustyo/blob/master/Sudoku/dokumentaatio/vaatimusmaarittely.md) ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. Kaikkien toiminnallisuuksien yhteydessä on yritetty rikkoa systeemi.

## Sovellukseen jääneet laatuongelmat

Sovellus ei anna tällä hetkellä virheilmoituksia, jos tietokantaan ei jostain syystä toimi. Tämän ei kuitenkaan pitäisi olla ongelma.