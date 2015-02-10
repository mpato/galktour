ALL_CLASSES = $(shell find com/ -name "*.java")

JAVAC = javac
JAVAFLAGS = -cp ../lib/commons-io-2.4.jar:../lib/httpcore-4.2.4.jar:../lib/json-simple-1.1.1.jar
JAR = jar
JARFLAGS = cfm

all: ../galk.jar

../galk.jar: $(ALL_CLASSES) $(EMBEDDED_GZ) makefile
	@set -e; echo -e "CPIO\t$@";					\
	$(JAVAC) $(JAVAFLAGS) $(ALL_CLASSES);				\
	$(JAR) $(JARFLAGS) ../galk.jar Manifest.txt `find com/ -name "*.class"`        

clean:	
	rm ../galk.jar
	rm -rf *.d *.cp doc/
	find com/ -name "*.class" -exec rm -f {} \;

.PHONY: all
.PHONY: clean
