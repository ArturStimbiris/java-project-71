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

runjson1:
	java -jar ./app/build/libs/app.jar ./app/src/test/resources/file1.json ./app/src/test/resources/file2.json

runjson2:
	java -jar ./app/build/libs/app.jar ./app/src/test/resources/file3.json ./app/src/test/resources/file4.json

runyaml1:
	java -jar ./app/build/libs/app.jar ./app/src/test/resources/file1.yaml ./app/src/test/resources/file2.yaml

runyaml2:
	java -jar ./app/build/libs/app.jar ./app/src/test/resources/file3.yaml ./app/src/test/resources/file4.yaml

runyaml2p:
	java -jar ./app/build/libs/app.jar -f plain ./app/src/test/resources/file3.yaml ./app/src/test/resources/file4.yaml