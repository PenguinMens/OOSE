#ifndef IMAGESCALER_H
#define IMAGESCALER_H

#include "ImageOperation.h"

class ImageScaler : public ImageOperation{
public:
    ImageScaler();
    ~ImageScaler();
    virtual int getNewWidth(ImageData *imageData);
    virtual int getNewHeight(ImageData *imageData);
    virtual int getNewPixel(ImageData *imageData,int x, int y);
    // Add your public member functions here

private:
    // Add your private member variables and functions here

};

#endif // IMAGESCALER_H