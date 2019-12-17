FUNCTION = hello-world-java
REGION = eu-west-1
PROFILE = jackson_development
VERSION = 1.0-SNAPSHOT

all: build

.PHONY: clean build deploy

clean:
	mvn clean

build: clean
	mvn compile -U && mvn package

create-function: build
	aws lambda create-function \
		--region=$(REGION) \
		--profile=jackson_development \
		--runtime java11 \
		--role arn:aws:iam::643008010459:role/default_lambda \
		--function-name $(FUNCTION) \
		--handler App::handleRequest \
		--zip-file fileb://target/$(FUNCTION)-${VERSION}.jar \
		--publish

update-function: build
	aws lambda update-function-code \
		--region=$(REGION) \
		--profile=jackson_development \
		--function-name hello-world-nodejs \
		--zip-file fileb://build/hello-world-nodejs.zip \
		--publish