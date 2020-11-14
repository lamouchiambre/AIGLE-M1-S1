GPP = gcc
SRC = client.c calculator.c functions.c
OBJ = $(SRC:%.c=%.o)
EXE = exec

all: $(OBJ)
	$(GPP) -o $(EXE) $(OBJ) -lpthread 

clean:
	rm -f *~ $(EXE)

.SUFFIXES: .c .o

%.o: %.c
	$(GPP) -c $<