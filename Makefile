SYSCONF_LINK = g++
CPPFLAGS     =
LDFLAGS      = 
LIBS         = -Iinclude -Llib
BULLSHIT     = -lmingw32 -lSDL2main -lSDL2

DESTDIR = ./
TARGET  = main

OBJECTS := $(patsubst %.cpp,%.o,$(wildcard *.cpp))

all: $(DESTDIR)$(TARGET)

$(DESTDIR)$(TARGET): $(OBJECTS)
	$(SYSCONF_LINK) -Wall $(LDFLAGS) -o $(DESTDIR)$(TARGET) $(OBJECTS) $(LIBS) $(BULLSHIT)

$(OBJECTS): %.o: %.cpp
	$(SYSCONF_LINK) -Wall $(CPPFLAGS) $(LIBS) -c $(CFLAGS) $< -o $@ $(BULLSHIT)

clean:
	-rm -f $(OBJECTS)
	-rm -f $(TARGET)
	-rm -f *.tga

