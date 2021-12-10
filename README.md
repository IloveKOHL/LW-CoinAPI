# LW-CoinAPI

Das LW-CoinAPI Plugin ist für Minecraft Spigot ausgelegt und bietet eine Große
funktionalität.
Es beinhaltet eine vielfältige API, die unten näher erklärt wird.
Fast alles ist in der ```config.yml``` einstellbar.
Die MySQL verbindung erfolgt nach den einstellen der ```mysql.yml```.

## Spieler-Befehle

```
Command                                   | Permission              | Funktion

/coins                                    | -------------           | Zeigt dir deinen aktuellen Coins
```


## Admin-Befehle

```
Command                                         | Permission              | Funktion

/coins <player>                                 | lwc.seeother            | Zeigt dir die Coins eines anderen Spielers
/coins <set | add | remove> <Player> <Amount>   | lwc.admin               | Setzt / Gibt / Nimmt einen Spieler seine Coins
```


## Konfigurationshilfe

### config.yml
```
// Prefix
// FirstJoinCoins, heißt wie viele Coins soll ein Spieler erhalten wenn er zum ersten mal Join min. 1

Prefix: '&8[&5LW&8-&3Coins&8]'
FirstJoinCoins: 10

```

### MySQL.yml

```
// use MUSS auf true gestzt werden

mysql:
  use: true
  host: 0.0.0.0
  port: 3306
  user: <user>
  datenbase: <database>
  passwort: ******
  
```

# Viel Spaß wünschen
## LvckyAPI & IloveKOHL | Developer

