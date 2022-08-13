#pragma once

#include <SDL2/SDL.h>

extern SDL_Renderer *renderer;
extern SDL_Window *window;
extern SDL_Event event;

void SetColor(char r, char g, char b, char a);
void PutPixel(int x, int y);

void initSDL();
void destroySDL();
