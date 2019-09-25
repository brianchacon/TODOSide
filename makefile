
JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
		main.java \
		Estilo.java \
		Lienzo.java \
		archivo.java \
		Menu.java \
		popUp.java \
		Ventana.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
		$(RM) *.class
