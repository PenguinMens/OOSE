Image Viewer -- C++, Qt
=======================

This is an (unfinished) C++-based image viewer, using the Qt GUI. It requires that your system has
'Qt' 5 installed, both the binaries and header files, and all their dependencies, and both the 
'make' and 'cmake' tools.

For RedHat/Fedora systems, you generally need to install 'qt5-qtbase' and 'qt5-qtbase-devel'` 
(and everything on which they depend).

Qt is a C++-based GUI framework (see www.qt.io for general information).

Generally, you can build the system like this:

$ cmake .
$ make

(Note the dot after 'cmake', telling it to build the current directory. A common alternative is to 
change into the build/ directory first, then run 'cmake ..', thus placing all auto-generated files 
in build/.)

This should produce an executable file called 'imageviewer', which you can run directly.
