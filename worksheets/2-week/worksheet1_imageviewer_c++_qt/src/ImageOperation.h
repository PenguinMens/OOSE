#ifndef IMAGEOPERATION_H
#define IMAGEOPERATION_H

#include "ImageData.h"
class ImageOperation
{
    protected: 
        virtual int getNewWidth(ImageData *imageData);
        virtual int getNewHeight(ImageData *imageData);
        virtual int getNewPixel(ImageData *imageData,int x, int y);
    public:
        ImageOperation();
        ImageData* doOperation(ImageData *imageData);
        virtual ~ImageOperation();

};
#endif // IMAGEOPERATION_H