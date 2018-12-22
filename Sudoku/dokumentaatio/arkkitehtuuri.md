# Arkkitehtuurikuvaus


## Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin pakkausrakenne on seuraava:

<img src="https://raw.githubusercontent.com/Sieluton/ot-harjoitustyo/master/Sudoku/dokumentaatio/kuvat/rakennekuvaus.png" width="160">

Pakkaus _sudoku.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän _sudoku.domain_ sovelluslogiikan ja _sudoku.dao_ tietojen pysyväistallennuksesta vastaavan koodin.


## Käyttöliittymä

Käyttöliittymä on toteutettu kahdella ''päänäkymällä'' ja näitä avustavilla ''ponnahdusikkunoilla''.

Pääikkuna pysyy kokoajan päällä ja jotkut napit avaavat ponnahdusikkunan, joka puolestaan voi vaihtaa pääikkunan näkymää.
Koko käyttöliittymä on toteutettu [sudoku.ui.SudokuUi](https://github.com/Sieluton/ot-harjoitustyo/blob/master/Sudoku/src/main/java/sudoku/ui/SudokuUi.java) luokkaan.

Käyttöliittymä ja sovelluslogiikka parhaan mukaan eristetty toisista ja tietakanta kyselyt toteutetaan myös sovelluslogiikan kautta.

Aina kun sudokussa valitsee tai muokkaa ruutua niin koko sudoku kenttä piirretään uudestaan.
Tämä on toteutettu [drawOnCanvas](https://github.com/Sieluton/ot-harjoitustyo/blob/master/Sudoku/src/main/java/sudoku/ui/SudokuUi.java#L292)
metodilla joka piirtää sudoku ruudukon.


## Sovelluslogiikka

Koko sovelluslogiikka on toteuttu luokassa [sudoku.domain.SudokuGrid](https://github.com/Sieluton/ot-harjoitustyo/blob/master/Sudoku/src/main/java/sudoku/domain/SudokuGrid.java).

_SudokuGrid_ pääsee käsiksi tietokannan rajapintaan. Se toimii tässä käyttöliittymän ja tietokannan välikätenä.

_SudokuGrid_ myös pitää tallella sen hetkisen sudokun tiedot.


## Tietokannan rajapinta

Tietokannan rajapinta on toteuttu luokassa [sudoku.dao.SudokuDao](https://github.com/Sieluton/ot-harjoitustyo/blob/master/Sudoku/src/main/java/sudoku/dao/SudokuDao.java).

_SudokuDao_ hoitaa tiedon tallennuksen tietokantaan ja sen hakemisen sieltä sekä tietokannan luomisen.
Se on toteutettu _sqlite_ tietokannalla.

## Päätoiminnallisuudet

#### tyhjän sudokun aloitus

Kun käyttäjä on uuden pelin valikossa ja painaa _Empty Sudoku_ nappia etenee sovellus seuraavasti:

<img src="https://raw.githubusercontent.com/Sieluton/ot-harjoitustyo/master/Sudoku/dokumentaatio/kuvat/sekvenssikaavio/getNew.png">


#### sudokun tallennus

Kun käyttäjä haluaa tallettaa sudokunsa tallennuspaikkaan 1 painamalla _Slot1_ nappia etenee sovellus seuraavasti:

<img src="https://raw.githubusercontent.com/Sieluton/ot-harjoitustyo/master/Sudoku/dokumentaatio/kuvat/sekvenssikaavio/saveGame.png">


## Rakenteen parannuksia

Käyttöliittymää voisi harkita jakamaan muutamaan omaan luokkaansa, sillä tällä hetkellä se alkaa olemaan jo aika pitkä.

Sovelluslogiikan puolella jos tekee lisäyksiä kannataa ne laittaa jo omaan luokkiinsa ja muutamaa metodia voisi parannella.