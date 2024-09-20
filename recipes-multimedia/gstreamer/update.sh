#!/bin/bash
if [ $# -ne 1 ]; then
   echo "Usage: ./update.sh <new-version>"
   echo "   eg: ./update.sh 1.24.8"
   exit
fi
CURRENT="$(basename $(ls gstreamer1.0_*.bb) .bb | cut -f 2 -d '_')"
echo "$CURRENT -> $1"
read -p "Are you sure? " -n 1 -r
echo    # (optional) move to a new line
if [[ $REPLY =~ ^[Yy]$ ]]; then
    for oldrecipe in *${CURRENT}*; do
        recipe=$(basename ${oldrecipe} ${CURRENT}.bb)${1}.bb
        pnreal=$(grep PNREAL\ = ${oldrecipe} | cut -f 2 -d '"')
        uri=$(grep SRC_URI\ = ${oldrecipe} | cut -f 2 -d '"' | cut -f 1 -d ' ' | sed "s/\${PV}/${1}/")
        if [ ! -z "${pnreal}" ]; then
            uri=$(echo ${uri} | sed "s/\${PNREAL}/${pnreal}/g")
        fi
        mkdir tmp-work
        cd tmp-work
        wget $uri
        sha=$(sha256sum * | cut -f 1 -d ' ')
        cd ..
        rm -rf tmp-work
        sed -i "s/SRC_URI\[sha256sum\].*/SRC_URI\[sha256sum\] = \"${sha}\"/" ${oldrecipe}
        git mv ${oldrecipe} ${recipe}
    done
fi
