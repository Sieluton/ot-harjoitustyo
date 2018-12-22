# Käyttöohje

Lataa tiedosto [sudoku.jar](https://github.com/mluukkai/OtmTodoApp/releases/tag/0.1)


## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar sudoku.jar
```

## Alkunäkymä

Sovellus käynnistyy päämenu näkymään:

<img src="https://raw.githubusercontent.com/Sieluton/ot-harjoitustyo/master/Sudoku/dokumentaatio/kuvat/ui/mainMenu.png">

Ratkaisemaan sudokua pääsee painamalla _New Game_ nappia tai lataamalla tallennuksen _Load Game_ napin takaa.


## New Game

Tästä ponnahdusikkunasta voidaan valita halutaanko aloittaa tyhjällä ruudukolla vai valmiilla haasteella.

<img src="https://raw.githubusercontent.com/Sieluton/ot-harjoitustyo/master/Sudoku/dokumentaatio/kuvat/ui/newGameMenu.png">


## Load Game

Talletuspaikan valitsemalla päästää jatkamaan jo aikasemmin talletettua peliä. _Slot1_ sisältää jo ratkaistun sudokun, muut ovat tyhjiä.

<img src="https://raw.githubusercontent.com/Sieluton/ot-harjoitustyo/master/Sudoku/dokumentaatio/kuvat/ui/loadGameMenu.png">


## Pelinäkymä

Kun haluttu aloitus on valittu päästään pelinäkymään, jossa voidaan jo sitten aloittaa sudokun ratkaisu.
Tämä tapahtuu painamalla hiiren vasemmalla jotain ruutua ja ruudun muututtua vaalean siniseksi voidaan se täyttää painamalla numeronäppäintä.
Numerot 1-9 tulevat ruutuun ja 0 puolestaan tekee siitä tyhjän. Mikäli laitettu numero rikkoo sääntöjä, värjätään kaikki sääntöä rikkovat ruudut punertaviksi.

Hiiren oikealla näppäimellä voidaan ruutuja lukita, jolloin niitä ei voi enää muuttaa ja sen tausta muuttuu tummemmaksi.
Tämä voidaan kuitenkin perua uudella hiiren oikealla klikkauksella kyseisen ruudun päällä.

<img src="https://raw.githubusercontent.com/Sieluton/ot-harjoitustyo/master/Sudoku/dokumentaatio/kuvat/ui/gameWindowColors.png">
