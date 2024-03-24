#include "ImageData.h"


ImageData::ImageData(int width, int height)
{
    this->width = width;
    this->height = height;
    image = new int*[width];
    for(int i = 0; i < width; i++){
        image[i] = new int[height];
    }
    for (int i = 0; i < width; i++)
    {
        for(int j = 0; j < height; j++){
            image[i][j] = 0;
        }
        /* code */
    }
    
}

int ImageData::getPixel(int x, int y)
{
    return image[x][y];
}

void ImageData::setPixel(int x, int y, int value)
{
    image[x][y] = value;
}


int ImageData::getWidth()
{
    return width;
}

int ImageData::getHeight()
{
    return height;
}

ImageData::~ImageData()
{
    for(int i = 0; i < width; i++){
        delete[] image[i];
    }
    delete[] image;
}
