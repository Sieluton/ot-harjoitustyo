# Sudoku

Sovelluksen tarkoitus on tarjota helppo ja mukava tapa ratkaista 9x9 sudokuja ilman virheitä. Sovellus tarkistaa ruudukon sisällön sääntöjen mukaisuutta ja ilmoittaa jos se huomaa virheen.
Tarjoaa myös mahdollisuuden tallettaa sudoku ja jatkaa sitä myöhemmin.

## Dokumentaatio


[Käyttöohje](https://github.com/Sieluton/ot-harjoitustyo/blob/master/Sudoku/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/Sieluton/ot-harjoitustyo/blob/master/Sudoku/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/Sieluton/ot-harjoitustyo/blob/master/Sudoku/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/Sieluton/ot-harjoitustyo/blob/master/Sudoku/dokumentaatio/testausdokumentti.md)

[Tuntikirjanpito](https://github.com/Sieluton/ot-harjoitustyo/blob/master/Sudoku/dokumentaatio/tuntikirjanpito.md)


## Releaset

[Viikko 5](https://github.com/Sieluton/ot-harjoitustyo/releases/tag/viikko5)

## Komentorivitoiminnot

### Ohjelman suoritus

Ohjelman voi suorittaa komennolla

```
mvn compile exec:java -Dexec.mainClass=sudoku.ui.SudokuUi
```

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn test jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _Sudoku-1.0-SNAPSHOT.jar_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/mluukkai/OtmTodoApp/blob/master/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_