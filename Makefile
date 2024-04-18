run-dist:
	./App/build/install/App/bin/App
run-json:
	./App/build/install/App/bin/App json1.json json2.json

build-dist:
	make -C app build-dist

build-run: 
	make -C app build-run

build:
	make -C app build

report:
	make -C app report

.PHONY: build
