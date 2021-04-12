#!/bin/sh

runPath=$( realpath .)
echo "Run on $runPath"

parentDir=$( dirname $runPath)
echo $parentDir

#Make sure that it is run on project root
echo "===Clone Script"
script="scripts/newProject/newProject.sh"
clonePath="$parentDir/newProject.sh"
echo "Copy $script to $clonePath"
cp $script $clonePath
echo "===Done Clone Script"


# cloneRealPath=$( realpath $clonePath )
chmod 777 $clonePath
echo "===GoTo: $parentDir"
cd $parentDir

echo "Start Clone project"
$clonePath "$@"

echo "===Clean script"
rm -rf $clonePath
