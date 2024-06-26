#include <iostream>
#include <fstream>
#include <string>
#include <QApplication>
#include <filesystem>
#include "MainWindow.h"
#include "Album.h"
#include "ImageOperation.h"
bool readAlbumFile(std::string albumFilename, Album* album);
void print2DArray(ImageData* imageData);
int main(int argc, char** argv) 
{
    std::string albumFilename("../album/album.txt");
    
    // Input the album filename.
    std::cout << "Enter album filename: \n";
    //std::getline(std::cin, albumFilename);

    // Initialise QT.
    QApplication app(argc, argv);
    
    // Construct an album object.
    Album* album = new Album();
    
    int exitCode = 1;
    
    // Read an album file.
    ImageData* imageData = new ImageData(10,10);
    imageData->setPixel(0, 0, 1);
    ImageOperation* imageOperation = new ImageOperation();
    print2DArray(imageData);
    std::cout << "\n\n";
    ImageData* newImageData = imageOperation->doOperation(imageData);
    print2DArray(newImageData);
    // if(readAlbumFile(albumFilename, album))
    // {
    //     // Construct a window object on the stack.
        
    //     //MainWindow window(album);

    //     // Run the GUI.
    //     //window.show();
    //     //exitCode = app.exec();
    // }
    // else
    // {
    //     std::cerr << "Error while reading " << albumFilename << std::endl;
    //     exitCode = 1;
    // }
    return exitCode;
}

/**
 * Reads an album file, given a filename and an Album object. Returns true if
 * successful, or false if the file could not be read.
 */
bool readAlbumFile(std::string albumFilename, Album* album)
{
    // Open file for reading. (c_str() converts a C++ std::string to a C-style char*.)
    std::ifstream file(albumFilename.c_str());
    std::filesystem::path directoryPath = std::filesystem::path(albumFilename).parent_path();
    
    while(file.good()) // 'good' returns true until end-of-file or an error occurs.
    {
        std::string imageFilename, imageCaption;
        
        std::getline(file, imageFilename, ':');
        std::getline(file, imageCaption);
        
        if(imageFilename.size() > 0)
        {
            // *** Insert your code here to add a new image to the album. ***
            std::string imagePath = directoryPath.string() + "/" + imageFilename;
            album->addImage(Image(imagePath, imageCaption));
            
            // std::cout << "Image: " << imageFilename << " Caption: " << imageCaption << std::endl;
           std::cout << "Image: " << imagePath << " Caption: " << imageCaption << std::endl;
        }
    }
    
    // Return "true" if we reached the end-of-file, meaning success. "false" implies an error.
    return file.eof(); 
}

void print2DArray(ImageData* imageData)
{
    for(int x = 0; x < imageData->getWidth(); x++)
    {
        for(int y = 0; y < imageData->getHeight(); y++)
        {
            std::cout << imageData->getPixel(x, y) << " ";
        }
        std::cout << std::endl;
    }
}
