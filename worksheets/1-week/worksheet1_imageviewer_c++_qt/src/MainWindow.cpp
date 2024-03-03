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
    
    setBaseSize(400, 400);
    
    imageWidget = new QLabel();
    // *** Fix this code so that it loads the initial (first) image. ***
    imageWidget->setPixmap(QPixmap("[Initial image filename]"));

    QScrollArea* scroller = new QScrollArea();
    scroller->setWidget(imageWidget);
    setCentralWidget(scroller);
    
    toolbar = new QToolBar();
    toolbar->addAction("Previous", this, SLOT(prevBtnHandler()));
    toolbar->addAction("Next", this, SLOT(nextBtnHandler()));    
    addToolBar(toolbar);
    
    captionWidget = new QStatusBar();
    setStatusBar(captionWidget);
    // *** Fix this code so that it displays the caption for the first image. ***
    captionWidget->showMessage("[Initial image caption]");
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
    imageWidget->setPixmap(QPixmap("[Previous image filename]"));
    captionWidget->showMessage("[Previous image caption]");
}

/**
 * Called to handle "next" button clicks.
 */
void MainWindow::nextBtnHandler()
{
    // *** Fix this code so that it actually displays the next image & caption. ***
    imageWidget->setPixmap(QPixmap("[Next image filename]"));
    captionWidget->showMessage("[Next image caption]");
}

