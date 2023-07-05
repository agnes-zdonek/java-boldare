# Java - Zadanie Rekrutacyjne Boldare
##### Java, Programowanie obiektowe, Git

## Dokumentacja
W folderze doc poprzez plik package-summary.html dostępna jest dokumentacja. 

## Diagram klas

```mermaid
flowchart LR
    A(Coin) --oB(PiggyBank)
    B --o C(KasaDrobnych)
    B --o D(Test)
    

style A stroke:#6cc570,stroke-width:7px
style B stroke:#bec56d,stroke-width:7px
style C stroke:#fdd023,stroke-width:7px
style D stroke:#f8d3dc,stroke-width:7px
```

## Instalacja i uruchamianie
Pobierz i rozpakuj repozytorium, wejdź do folderu gdzie znajduje się sub-folder src.
Następnie:
`cd src`               
`javac  -d bin  *.java`

By zobaczyć testy:
`java -cp bin Test` 
By uruchomić program:
`java -cp bin KasaDrobnych`

Przykładowy output terminala dla klasy Test i KasaDrobnych znajduje się w odpowiadających plikach log.

