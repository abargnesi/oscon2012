#!/usr/bin/env bash
# build_ubuntu.sh - Builds jdk8 + jdk8/lambda on Ubuntu 12.04

# install jdk8 build dependencies
sudo apt-get install \
  libasound2-dev \
  libfreetype6-dev \
  libcups2-dev \
  libxext-dev \
  libxrender-dev \
  libxtst-dev \
  gawk \
  g++

# set ALT_BOOTDIR to JDK 6/7, using $JAVA_HOME
if [ -z $JAVA_HOME ]; then
    echo "JAVA_HOME must be set to JDK 6 or 7."
    exit 1
fi
export ALT_BOOTDIR=$JAVA_HOME

hash hg > /dev/null 2>&1
if [ $? != 0 ]; then
    echo "hg command is not on your PATH.  install mercurial if necessary."
    exit 1
fi

# install base jdk8
hg clone http://hg.openjdk.java.net/jdk8/jdk8 jdk8
cd jdk8
sh ./get_source.sh
make sanity && make all
cd ../

# install jdk8 + lambda
hg clone http://hg.openjdk.java.net/lambda/lambda jdk8-lambda
cd jdk8-lambda
sh ./get_source.sh
make sanity && make all
cd ../
