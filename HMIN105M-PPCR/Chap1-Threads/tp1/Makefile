GPP = gcc
SRC = exo3_try.c
OBJ = $(SRC:%.c=%.o) calculC.o
EXE = exec

all: $(OBJ)
	$(GPP) -o $(EXE) $(OBJ) -lpthread 

clean:
	rm -f *~ $(EXE)

.SUFFIXES: .c .o

%.o: %.c
	$(GPP) -c $<