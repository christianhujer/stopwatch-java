.PHONY: all
all:
	./mvnw verify

.PHONY: doc
doc:
	mkdir -p doc/api
	javadoc -sourcepath src/main/java -d doc/api com.nelkinda.stopwatch

.PHONY: clean
clean::
	./mvnw clean
