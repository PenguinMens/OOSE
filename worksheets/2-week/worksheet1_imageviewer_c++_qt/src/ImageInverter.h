#ifndef IMAGEINVERTER_H
#define IMAGEINVERTER_H

#include "ImageOperation.h"

class ImageInverter : public ImageOperation
{ 
public:
    ImageInverter();
    ~ImageInverter() override;
    virtual int getNewWidth(ImageData *imageData) override;
    virtual int getNewHeight(ImageData *imageData) override;
    virtual int getNewPixel(ImageData *imageData, int x, int y) override;
};

#endif // IMAGEINVERTER_H