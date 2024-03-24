#include "ImageRotator.h"

ImageRotator::ImageRotator() {
    // Constructor implementation
}

ImageRotator::~ImageRotator() {
    // Destructor implementation
}

int ImageRotator::getNewWidth(ImageData *imageData) {
    return imageData->getHeight();
}

int ImageRotator::getNewHeight(ImageData *imageData) {
    return imageData->getWidth();
}

int ImageRotator::getNewPixel(ImageData *imageData, int x, int y) {
    int newy = imageData->getHeight();
    return imageData->getPixel(newy - y , x);
}
// Add your private member variable and function implementations here