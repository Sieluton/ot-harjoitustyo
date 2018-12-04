# Sudoku

Sovelluksen tarkoitus on tarjota helppo ja mukava tapa ratkaista 9x9 sudokuja ilman virheitä. Sovellus tarkistaa ruudukon sisällön sääntöjen mukaisuutta ja ilmoittaa jos se huomaa virheen.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/Sieluton/ot-harjoitustyo/blob/master/Sudoku/dokumentaatio/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/Sieluton/ot-harjoitustyo/blob/master/Sudoku/dokumentaatio/tuntikirjanpito.md)


## Releaset

Tulee myöhemmin

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

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _Sudoku-1.0-SNAPSHOT.jar_

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/mluukkai/OtmTodoApp/blob/master/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_