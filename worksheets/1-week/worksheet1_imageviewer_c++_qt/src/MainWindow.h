#ifndef MAINWINDOW_H
#define	MAINWINDOW_H

#include <memory>
#include <QMainWindow>
#include <QToolBar>
#include <QLabel>
#include <QStatusBar>

#include "Album.h"

/**
 * Represents our window, inheriting from QMainWindow.
 */
class MainWindow : public QMainWindow
{
    Q_OBJECT /* This a macro defined by QT to do its own trickery. 
                It's not important for understanding C++. */
    
    public:
        MainWindow(Album* newAlbum);  // Constructor
        ~MainWindow();                // Destructor
        
        // Accessor for the album (not really needed here, but just for illustration).
        Album* getAlbum() const;
        
    private:
        void setImage();  // Helper method to set the image and caption.
        // Album field, containing image information.
        Album* album;
        
        // -- ADDED --
        int index;
        // --
        
        // Fields for GUI widgets.
        QToolBar* toolbar;         // For showing the "previous" and "next" buttons
        QLabel* imageWidget;       // For showing the image
        QStatusBar* captionWidget; // For showing the caption

        
    private slots:  /* Note: QT extends C++ slightly here. "Slots" (QT only) are methods that are 
                       called when certain "signals" occur. This is a kind of event-driven 
                       programming, similar to what we'll talk about in Lecture 5. */
                       
        // Called to handle button presses
        void nextBtnHandler();
        void prevBtnHandler();
        
};

#endif
