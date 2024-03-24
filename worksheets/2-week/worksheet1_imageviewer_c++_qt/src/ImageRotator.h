#ifndef IMAGEROTATOR_H
#define IMAGEROTATOR_H

#include "ImageOperation.h"

class ImageRotator : public ImageOperation
{
public:
    ImageRotator();
    ~ImageRotator() override;
    virtual int getNewWidth(ImageData *imageData) override;
    virtual int getNewHeight(ImageData *imageData) override;
    virtual int getNewPixel(ImageData *imageData, int x, int y) override;
};

#endif // IMAGEROTATOR_H