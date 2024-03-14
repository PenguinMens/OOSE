#include "ImageData.h"


int ImageData::getPixel(int x, int y)
{
    return image[x][y];
}

void ImageData::setPixel(int x, int y, int value)
{
    image[x][y] = value;
}

