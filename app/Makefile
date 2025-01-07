.PHONY: build

run-dist:
	java -jar ./app/build/libs/app.jar
clean:
	./gradlew clean
build:
	./gradlew build
run:
	./gradlew run
runjson:
	java -jar ./build/libs/app.jar ./src/test/resources/file1.json ./src/test/resources/file2.json
runyaml:
	java -jar ./build/libs/app.jar ./src/test/resources/file1.yaml ./src/test/resources/file2.yaml