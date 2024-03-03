#include "Album.h"
#include <algorithm> 


Album::~Album() {
    // *** Insert destructor code here (if needed) ***
}
void Album::addImage(const Image& image) {
    images.push_back(image);
}

void Album::removeImage(const std::string& imagePath) {
    auto it = std::find_if(images.begin(), images.end(), [&imagePath](const Image& img) {
        return img.getFilename() == imagePath;
    });
    if (it != images.end()) {
        images.erase(it);
    }
}

size_t Album::getImageCount() const {
    return images.size();
}

Image Album::getImageAtIndex(size_t index) const {
    if (index < images.size()) {
        return images[index];
    }
    // Return a default Image or handle the error as appropriate
    // Note: Handling of this case should be carefully designed
    return Image("",""); // This is just a placeholder; adjust based on your error handling strategy
}

Image Album::getImageByPath(std::string path) const {
    auto it = std::find_if(images.begin(), images.end(), [&path](const Image& img) {
        return img.getFilename() == path;
    });
    if (it != images.end()) {
        return *it;
    }
    // Return a default Image or handle the error as appropriate
    // Note: Handling of this case should be carefully designed
    return Image("",""); // This is just a placeholder; adjust based on your error handling strategy
}

int Album::getIndexByPath(std::string path) const {
    auto it = std::find_if(images.begin(), images.end(), [&path](const Image& img) {
        return img.getFilename() == path;
    });
    if (it != images.end()) {
        return std::distance(images.begin(), it);
    }
    // Return a default value or handle the error as appropriate
    // Note: Handling of this case should be carefully designed
    return -1; // This is just a placeholder; adjust based on your error handling strategy
}
