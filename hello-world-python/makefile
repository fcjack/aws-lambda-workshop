FUNCTION = hello-world-python
REGION = eu-west-1
PROFILE = jackson_development

all: build

.PHONY: clean build deploy

clean:
	rm -rf build

build: clean
	mkdir -p build/
	zip -r build/$(FUNCTION).zip . -x "*.DS_Store*" "*.git*" "build*" "Makefile" "requirements.txt"

create-function: build
	aws lambda create-function \
		--region=$(REGION) \
		--profile=jackson_development \
		--runtime python3.8 \
		--role arn:aws:iam::643008010459:role/default_lambda \
		--function-name $(FUNCTION) \
		--handler lambda_function.lambda_handler \
		--zip-file fileb://build/$(FUNCTION).zip \
		--publish

update-function: build
	aws lambda update-function-code \
		--region=$(REGION) \
		--profile=jackson_development \
		--function-name hello-world-nodejs \
		--zip-file fileb://build/hello-world-nodejs.zip \
		--publish