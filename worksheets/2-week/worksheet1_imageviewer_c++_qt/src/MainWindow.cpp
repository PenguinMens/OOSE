#include "MainWindow.h"
#include <iostream>
#include <QPixmap>
#include <QScrollArea>

/**
 * Constructor. We must initialise the GUI here, which includes creating and 
 * adding the GUI "widgets" and setting up callback functions.
 */
MainWindow::MainWindow(Album* newAlbum) :
    album(newAlbum)
{
    // Set the window title (using a superclass method).
    setWindowTitle("Qt Image Viewer");
    
    setBaseSize(1920, 1080);
    
    imageWidget = new QLabel();
    
    imageWidget->setPixmap(QPixmap(album->getImageAtIndex(0).getFilename().c_str()));

    QScrollArea* scroller = new QScrollArea();
    scroller->setWidget(imageWidget);
    setCentralWidget(scroller);
    scroller->setWidgetResizable(true);
    toolbar = new QToolBar();
    toolbar->addAction("Previous", this, SLOT(prevBtnHandler()));
    toolbar->addAction("Next", this, SLOT(nextBtnHandler()));    
    addToolBar(toolbar);
    
    captionWidget = new QStatusBar();
    setStatusBar(captionWidget);

    captionWidget->showMessage(album->getImageAtIndex(0).getCaption().c_str());
    index = 0;
}

/**
 * Destructor. Destroy the album object.
 */
MainWindow::~MainWindow()
{    
    delete album;    
    delete toolbar;
    
    /* Note: while imageWidget and captionWidget also need to be deleted, the superclass 
     * (QMainWindow) takes over this responsibility when we call setCentralWidget() and 
     * setStatusBar(). It would be an error to do it again here. */
}

/**
 * Retrieves the album by reference.
 */
Album* MainWindow::getAlbum() const
{
    return album;
}

/**
 * Called to handle "previous" button clicks.
 */
void MainWindow::prevBtnHandler()
{
    // *** Fix this code so that it actually displays the previous image & caption. ***
    index = (index - 1) % album->getImageCount();
    imageWidget->setPixmap(QPixmap(album->getImageAtIndex(index).getFilename().c_str()));
    captionWidget->showMessage(album->getImageAtIndex(index).getCaption().c_str());
}

/**
 * Called to handle "next" button clicks.
 */
void MainWindow::nextBtnHandler()
{
    // *** Fix thiis code so that t actually displays the next image & caption. ***
    index = index+1;
    if (index >= album->getImageCount())    index = album->getImageCount()-1;
    
    
    imageWidget->setPixmap(QPixmap(QPixmap(album->getImageAtIndex(index).getFilename().c_str())));
    captionWidget->showMessage(album->getImageAtIndex(index).getCaption().c_str());
}

