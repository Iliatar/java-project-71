run-dist:
	./App/build/install/App/bin/App
run-json:
	./App/build/install/App/bin/App json1.json json2.json

build-dist:
	make -C App build-dist

build-run: 
	make -C App build-run

build:
	make -C App build

report:
	make -C App report

.PHONY: build
