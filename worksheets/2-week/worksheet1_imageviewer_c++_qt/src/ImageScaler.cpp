#include "ImageScaler.h"

ImageScaler::ImageScaler() {
    // Constructor implementation
}

ImageScaler::~ImageScaler() {
    // Destructor implementation
}

int ImageScaler::getNewWidth(ImageData *imageData) {
    return imageData->getWidth() /2;
}

int ImageScaler::getNewHeight(ImageData *imageData) {
    return imageData->getHeight() / 2;
}

int ImageScaler::getNewPixel(ImageData *imageData, int x, int y) {
    return imageData->getPixel(x * 2, y * 2);
}
// Add your private member variable and function implementations here