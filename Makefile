run-dist:
	./app/build/install/app/bin/app
run-json:
	./app/build/install/app/bin/app json1.json json2.json

build-dist:
	make -C app build-dist

build-run: 
	make -C app build-run

build:
	make -C app build

report:
	make -C app report

.PHONY: build
