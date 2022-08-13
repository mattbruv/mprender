#include "sdl.h"

SDL_Renderer *renderer = NULL;
SDL_Window *window = NULL;
SDL_Event event;

int width = 500;
int height = 500;

void SetColor(char r, char g, char b, char a)
{
    SDL_SetRenderDrawColor(renderer, r, g, b, a);
}

void PutPixel(int x, int y)
{
    SDL_RenderDrawPoint(renderer, x, y);
}

void initSDL()
{
    SDL_Init(SDL_INIT_VIDEO);
    SDL_CreateWindowAndRenderer(width, height, 0, &window, &renderer);
    SDL_SetRenderDrawColor(renderer, 0, 0, 0, 0);
    SDL_RenderClear(renderer);
    SDL_RenderPresent(renderer);
}

void destroySDL()
{
    SDL_DestroyRenderer(renderer);
    SDL_DestroyWindow(window);
    SDL_Quit();
}