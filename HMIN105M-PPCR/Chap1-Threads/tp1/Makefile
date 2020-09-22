GPP = gcc
SRC = exo1.c
OBJ = $(SRC:%.c=%.o)
EXE = exec

all: $(OBJ)
	$(GPP) -o $(EXE) $(OBJ) -lpthread 

clean:
	rm -f *~ *.o $(EXE)

.SUFFIXES: .c .o

%.o: %.c
	$(GPP) -c $<