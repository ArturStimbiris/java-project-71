run-dist:
	java -jar ./app/build/libs/app.jar
clean:
	./gradlew clean
build:
	./gradlew build
run:
	./gradlew run
trun:
	java -jar ./app/build/libs/app.jar file1.json file2.json