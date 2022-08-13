#include "sdl.h"

int main(int argc, char **argv)
{
    initSDL();

    SetColor(255, 0, 0, 255);

    SDL_RenderDrawLine(renderer, 0, 0, 480, 250);

    while (!(event.type == SDL_QUIT))
    {
        SDL_Delay(10);
        SDL_PollEvent(&event);
        SDL_RenderPresent(renderer);
    }

    return 0;
}