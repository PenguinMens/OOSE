#include "ImageInverter.h"

ImageInverter::ImageInverter() {
    // Constructor implementation
}

ImageInverter::~ImageInverter() {
    // Destructor implementation
}

int ImageInverter::getNewWidth(ImageData *imageData) {
    return imageData->getWidth();
}

int ImageInverter::getNewHeight(ImageData *imageData) {
    return imageData->getHeight();
}

int ImageInverter::getNewPixel(ImageData *imageData, int x, int y) {
    
    return ~imageData->getPixel(x, y); // Invert the pixel value
}
// Add your private member variable and function implementations here