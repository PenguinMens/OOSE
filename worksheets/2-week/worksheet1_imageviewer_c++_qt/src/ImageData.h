
#ifndef IMAGEDATA_H
#define	IMAGEDATA_H


class ImageData
{
    private:
        int** image;

    public:
        ImageData(int width, int height){
            image = new int*[width];
            for(int i = 0; i < width; i++){
                image[i] = new int[height];
            }
        }
        ~ImageData();
        void setPixel(int x, int y, int value);
        int getPixel(int x, int y);
};

#endif	/* IMAGEDATA_H */