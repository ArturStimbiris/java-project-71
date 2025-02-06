.PHONY: build

run-dist:
	java -jar ./app/build/libs/app.jar

clean:
	cd ./app && ./gradlew clean

build:
	cd ./app && ./gradlew build

cleanbuild:
	cd ./app && ./gradlew clean build

run:
	cd ./app && ./gradlew run

runjson:
	java -jar ./app/build/libs/app.jar ./app/src/test/resources/file1.json ./app/src/test/resources/file2.json

runyaml:
	java -jar ./app/build/libs/app.jar ./app/src/test/resources/file1.yaml ./app/src/test/resources/file2.yaml

runyamlp:
	java -jar ./app/build/libs/app.jar -f plain ./app/src/test/resources/file1.yaml ./app/src/test/resources/file2.yaml

runyamlj:
	java -jar ./app/build/libs/app.jar -f json ./app/src/test/resources/file1.yaml ./app/src/test/resources/file2.yaml

runjsonj:
	java -jar ./app/build/libs/app.jar -f json ./app/src/test/resources/file1.json ./app/src/test/resources/file2.json