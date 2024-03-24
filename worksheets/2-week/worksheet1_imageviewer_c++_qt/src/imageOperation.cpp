#include "ImageOperation.h"

ImageOperation::ImageOperation() {}

ImageOperation::~ImageOperation() {}

ImageData* ImageOperation::doOperation(ImageData *imageData)
{
    int newWidth = getNewWidth(imageData);
    int newHeight = getNewHeight(imageData);
    ImageData newImageData(newWidth, newHeight);
    for(int x = 0; x < newWidth; x++){
        for(int y = 0; y < newHeight; y++){
            newImageData.setPixel(x, y, getNewPixel(imageData, x, y));
        }
    }
    return &newImageData;
}
