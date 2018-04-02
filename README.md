# TwentyOne
The programme plays a simplified game of Blackjack between two players: Sam and the Dealer. The game will be played with a custom deck if given a file path as a programme argument, or it will use a standard shuffled 52-card deck if no such argument is given.

The programme *requires* Java 10. To run the code we can compile and execute it directly:
```sh
$ mvnw clean compile exec:java -Dexec.mainClass="baardsen.twentyone.main.TwentyOne" -Dexec.args="deck.txt"
```
Or we can create a a jar first and then execute it:
```sh
$ mvnw clean package
$ java -jar target/twentyone-1.0.0.jar deck.txt
```
