# Sudoku

## Dokumentaatio

[vaatimusmäärittely](https://github.com/Sieluton/ot-harjoitustyo/blob/master/Sudoku/dokumentaatio/vaatimusmaarittely.md)

[tuntikirjanpito](https://github.com/Sieluton/ot-harjoitustyo/blob/master/Sudoku/dokumentaatio/tuntikirjanpito.md)


## Releaset

Tulee myöhemmin

## Komentorivitoiminnot

### Ohjelman suoritus

Ohjelman voi suorittaa komentoriviltä  komennolla

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
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_