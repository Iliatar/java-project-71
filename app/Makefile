run-dist:
	./build/install/app/bin/app
run-json:
	./build/install/app/bin/app src/test/resources/json1.json src/test/resources/json2.json

build-dist:
	./gradlew clean
	./gradlew build
	./gradlew installDist

build-run: build-dist run-dist

build:
	./gradlew clean build

report:
	./gradlew jacocoTestReport

.PHONY: build
