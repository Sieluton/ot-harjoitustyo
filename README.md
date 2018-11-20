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

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_