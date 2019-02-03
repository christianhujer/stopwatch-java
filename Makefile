.PHONY: all
all:
	./mvnw verify

.PHONY: clean
clean::
	./mvnw clean
