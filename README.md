# LW-CoinAPI

Das LW-CoinAPI Plugin ist für Minecraft Spigot ausgelegt und bietet eine große Funktionalität. 
Es ist funktionstüchtig ab der Version 1.8 bis Version 1.17.
Das Plugin beinhaltet eine vielfältige API, die unten näher erklärt wird.
So gut wie alles ist in der ```config.yml``` konfigurierbar.
Die MySQL-Verbindung erfolgt nach dem korrekten Einstellen der ```mysql.yml```.

[![JavaDocs](https://img.shields.io/badge/-Java%20Docs-8CA1AF.svg?logo=read-the-docs&logoColor=white&longCache=true&style=for-the-badge)](https://docs.lvckyworld.net/coinapi/)

## Spieler-Befehle

```md
Command                                         | Permission              | Funktion

/coins                                          | -------------           | Zeigt dir deinen aktuellen Coins
```


## Admin-Befehle

```
Command                                         | Permission              | Funktion

/coins <player>                                 | lwc.seeother            | Zeigt dir die Coins eines anderen Spielers
/coins <set | add | remove> <Player> <Amount>   | lwc.admin               | Setzt / Gibt / Nimmt einem Spieler seine Coins
```


## Konfigurationshilfe

### config.yml
```yaml
// Prefix
// FirstJoinCoins, heißt wie viele Coins soll ein Spieler erhalten wenn er zum ersten mal Join min. 1

Prefix: '&8[&5LW&8-&3Coins&8]'
FirstJoinCoins: 10

```

### MySQL.yml

```yaml
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

