GPP = g++
SRC = prog.cpp
OBJ = $(SRC:%.cpp=%.o)
EXE = exec

all: $(OBJ)
	$(GPP) -o $(EXE) $(OBJ)

clean:
	rm -f *~ *.o $(EXE)

.SUFFIXES: .cpp .o

%.o: %.cpp
	$(GPP) -c $<