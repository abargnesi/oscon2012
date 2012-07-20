#!/usr/bin/env bash
# build_ubuntu.sh - Builds jdk8 + jdk8/lambda on Ubuntu 12.04

echo "Checking for jdk boostrap (6 or 7)."
if [ -z $JAVA_HOME ]; then
    update-alternatives --list javac > /dev/null 2>&1
    if [ $? == 0 ]; then
        JAVAC_PATH=$(update-alternatives --list javac)
        JAVA_HOME=${JAVAC_PATH%bin/javac}
    fi
fi
if [ -z $JAVA_HOME ]; then    
    echo "JAVA_HOME not set to JDK 6 or 7, will attempt install."
    echo -n "install jdk7 to bootstrap build [Y/n]? "
    read JDK_INSTALL
    if [ "$JDK_INSTALL" == "y" ] || [ "$JDK_INSTALL" == "Y" ]; then
        sudo apt-get -y install openjdk-7-jdk > /dev/null >&1
        JAVAC_PATH=$(update-alternatives --list javac)
        JAVA_HOME=${JAVAC_PATH%bin/javac}
    else
        echo "Need jdk to proceed."
        echo "  ..failed"
        exit 1
    fi
fi
export ALT_BOOTDIR=$JAVA_HOME
unset JAVA_HOME
echo "  ..passed"

echo "Checking for ant bootstrap."
hash ant > /dev/null 2>&1
if [ $? != 0 ]; then
    echo "ant command is not on your PATH."
    echo -n "install ant to bootstrap build [Y/n]? "
    read ANT_INSTALL
    if [ "$ANT_INSTALL" == "y" ] || [ "$ANT_INSTALL" == "Y" ]; then
        sudo apt-get -y install ant > /dev/null >&1
    else
        echo "Need ant to proceed."
        echo "  ..failed"
        exit 1
    fi
fi
echo "  ..passed"

echo "Checking for mercurial for fetching jdk8 sources."
hash hg > /dev/null 2>&1
if [ $? != 0 ]; then
    # not found, ask to install
    echo "hg command is not on your PATH."
    echo -n "install mercurial [Y/n]? "
    read HG_INSTALL
    if [ "$HG_INSTALL" == "y" ] || [ "$HG_INSTALL" == "Y" ]; then
        sudo apt-get -y install mercurial > /dev/null >&1
    else
        echo "Need mercurial to proceed."
        echo "  ..failed"
        exit 1
    fi
fi
echo "  ..passed"

echo "Resolving system libraries for jdk8 build."
sudo apt-get -y install \
  libasound2-dev \
  libfreetype6-dev \
  libcups2-dev \
  libxext-dev \
  libxrender-dev \
  libxtst-dev \
  gawk \
  g++ > /dev/null >&1
if [ $? != 0 ]; then
    echo "Failed to install dependencies."
    echo "  ..failed"
    exit 1
fi
echo "  ..passed"

echo "Fetching jdk8 sources."
if [ -d "jdk8" ]; then
    cd jdk8
    hg update
    sh ./get_source.sh > /dev/null 2>&1
else
    hg clone http://hg.openjdk.java.net/jdk8/jdk8 jdk8
    cd jdk8
    sh ./get_source.sh > /dev/null 2>&1
fi
echo "  ..passed"

echo "Building jdk8."
make all > ../build_log.jdk8 2>&1
if [ $? != 0 ]; then
    echo "Failed to build jdk8."
    exit 2
fi
echo "  ..passed"
cd ../

echo "Fetching jdk8 lambda sources."
if [ -d "jdk8-lambda" ]; then
    cd jdk8-lambda
    hg update
    sh ./get_source.sh
else
    hg clone http://hg.openjdk.java.net/lambda/lambda jdk8-lambda
    cd jdk8-lambda
    sh ./get_source.sh
fi
echo "  ..passed"

echo "Building jdk8-lambda."
make all > ../build_log.jdk8lambda 2>&1
if [ $? != 0 ]; then
    echo "Failed to build jdk8-lambda."
    exit 3
fi
echo "  ..passed"
cd ../
