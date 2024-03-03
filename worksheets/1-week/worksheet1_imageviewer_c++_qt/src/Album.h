/*
 * Represents a collection of image metadata.
 */

#ifndef ALBUM_H
#define	ALBUM_H

#include "Image.h"
#include <vector>

class Album 
{
    private:
        std::vector<Image> images;

    public:
        Album() {
        // *** Insert initialisation code here (if needed) ***
        }
           
        ~Album();

        void addImage(const Image& image);
        void removeImage(const std::string& imagePath);
        size_t getImageCount() const;
        Image getImageAtIndex(size_t index) const; // Note: Returning by value or reference depends on your use case
        Image getImageByPath(std::string path) const;
        int getIndexByPath(std::string path) const;

};

#endif

