#!/usr/bin/env bash

export chromedriver_version=".chromedriver-version";
export base_url="https://chromedriver.storage.googleapis.com";

download_latest_chrome_driver(){
    wget -O $1 https://chromedriver.storage.googleapis.com/$3/$1;
    unzip -o $1 && rm $1;
    mv chromedriver $2;
}

installed_version(){
    if [ -e $chromedriver_version ]
    then
        installed_version=$(cat $chromedriver_version);
    else
        installed_version=0;
    fi
    printf $installed_version;
}

latest_available_version() {
    latest_release_version=$(curl --max-time 10 $base_url/LATEST_RELEASE);
    if [[ $latest_release_version == "" ]]
    then
        printf $(installed_version);
    else
        printf $latest_release_version;
    fi
}

export latest_available_version=$(latest_available_version);
export installed_version=$(installed_version);

if [[ $latest_available_version != $installed_version ]]
then
    download_latest_chrome_driver chromedriver_mac64.zip chromedriver_mac $latest_available_version;
    download_latest_chrome_driver chromedriver_linux64.zip chromedriver_linux_64bit $latest_available_version;
    curl --max-time 10 $base_url/LATEST_RELEASE > $chromedriver_version;
else
    echo "Version upto date";
fi