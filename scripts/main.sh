#!/bin/sh

#Make sure that it is run on project root
echo "===Clone Script"
script="scripts/newProject.sh"
clonePath="../newProject.sh"
echo "Copy $script to $clonePath"
cp $script $clonePath
echo "===Done Clone Script"


cloneRealPath=$( realpath $clonePath )
cloneDirName=$( dirname $cloneRealPath )
chmod 777 $cloneRealPath
echo "===GoTo: $cloneDirName"
cd $cloneDirName

echo "Start Clone project"
$cloneRealPath "$@"

echo "===Clean script"
rm -rf $cloneRealPath
