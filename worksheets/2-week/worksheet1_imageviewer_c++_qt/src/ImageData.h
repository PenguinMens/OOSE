
#ifndef IMAGEDATA_H
#define	IMAGEDATA_H


class ImageData
{
    private:
        int** image;
        int width;
        int height;

    public:
        ImageData(int width, int height);
        ~ImageData();
        void setPixel(int x, int y, int value);
        int getPixel(int x, int y);
        int getWidth();
        int getHeight();
};

#endif	/* IMAGEDATA_H */