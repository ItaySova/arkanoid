# itay sova

compile: bin
	javac -cp biuoop-1.4.jar:src -d bin src/*/*.java src/*/*/*.java src/Ass7Game.java

jar:
	jar cfm ass7game.jar manifest.mf -C bin . -C resources . 

run:
	java -cp biuoop-1.4.jar:bin:resources game.Ass7Game

bin:
	mkdir bin