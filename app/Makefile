.PHONY: build

run-dist:
    java -jar ./app/build/libs/app.jar

clean:
    ./gradlew clean

build:
    ./gradlew build

run:
    ./gradlew run

runjson1:
    java -jar ./build/libs/app.jar ./src/test/resources/file1.json ./src/test/resources/file2.json

runjson2:
    java -jar ./build/libs/app.jar ./src/test/resources/file3.json ./src/test/resources/file4.json

runyaml1:
    java -jar ./build/libs/app.jar ./src/test/resources/file1.yaml ./src/test/resources/file2.yaml

runyaml2:
    java -jar ./build/libs/app.jar ./src/test/resources/file3.yaml ./src/test/resources/file4.yaml