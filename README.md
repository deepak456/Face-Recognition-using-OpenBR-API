# Face-Recognition-using-OpenBR-API
Face Recognition Project using Spring, angularjS and OpenBR as well as Train data using OpenNLP Maxent API

==============================================================
1.Install GCC 4.9.2

  $ sudo apt-get update
  $ sudo apt-get install build-essential
  
2.Install CMake 3.0.2

  $ sudo apt-get install cmake cmake-curses-gui
  
3.Download OpenCV 2.4.11, note Build OpenCV with video support

  $ cd ~/Downloads
  $ unzip opencv-2.4.11.zip
  $ cd opencv-2.4.11
  $ mkdir build
  $ cd build
  $ cmake -DCMAKE_BUILD_TYPE=Release ..
  $ make -j4
  $ sudo make install
  $ cd ../..

4.Install Qt 5.4.1

  $ sudo apt-get install qt5-default libqt5svg5-dev qtcreator
  
5.Create a GitHub account, follow their instructions for setting up Git.

  5.1 $sudo apt-get install git
  $ git clone https://github.com/biometrics/openbr.git
  $ cd openbr
  $ git checkout v1.1.0
  $ git submodule init
  $ git submodule update
  
6.Build OpenBR!

  $ mkdir build # from the OpenBR root directory
  $ cd build
  $ cmake -DCMAKE_BUILD_TYPE=Release ..
  $ make -j4
  $ sudo make install
