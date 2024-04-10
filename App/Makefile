run-dist:
	./build/install/App/bin/App
run-json:
	./build/install/App/bin/App json1.json json2.json

build-dist:
	./gradlew clean
	./gradlew build
	./gradlew installDist

build-run: build-dist run-dist

build:
	./gradlew clean build

report:
	make -C app report

.PHONY: build
