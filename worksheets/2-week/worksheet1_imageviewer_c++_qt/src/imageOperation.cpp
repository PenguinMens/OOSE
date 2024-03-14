#include "ImageOperation.h"

ImageOperation::ImageOperation() {}

ImageOperation::~ImageOperation() {}

int ImageOperation::getNewWidth(ImageData *imageData) {
    // TODO: Implement getNewWidth method

    return imageData->getWidth() / 2;
}

int ImageOperation::getNewHeight(ImageData *imageData) {
    // TODO: Implement getNewHeight method
    return 0;
}

int ImageOperation::getNewPixel(ImageData *imageData, int x, int y) {
    // TODO: Implement getNewPixel method
    return 0;
}