#! /bin/bash -

name=$1
run_lfs_script=$2
thisName="[add-precommit.sh]"
echo "$run_lfs_script"

# Check lfs task already exist
if grep -q "$run_lfs_script" "$HOOK_PATH"; then
  echo "$thisName LFS task already exist"
  exit 0
fi

# Update hook file
echo "$thisName Update '$HOOK_PATH' with '$run_lfs_script'"
echo "
### Start $name ###
$run_lfs_script
### End $name ###" >> "$HOOK_PATH"