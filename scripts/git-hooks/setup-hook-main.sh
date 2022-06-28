#! /bin/bash -

### Set Global
export HOOK_DIR=".git/hooks/"
export HOOK_FILE="pre-commit"
export HOOK_PATH="$HOOK_DIR$HOOK_FILE"
git_dir=$(git rev-parse --show-toplevel)
export GIT_DIR=$git_dir

## scripts
export SCRIPT_CREATE_HOOK_DIR="$git_dir/scripts/git-hooks/create-hooks-dir.sh"
export SCRIPT_ADD_PRECOMMIT_FILE="$git_dir/scripts/git-hooks/add-precommit.sh"

export SCRIPT_LFS_AUTOADD_SNAPSHOT="$git_dir/scripts/git-hooks/lfs/lfs-autoadd-snapshot-pattern.sh"
export SCRIPT_SPOTLESS="$git_dir/scripts/git-hooks/spotless/spotless-apply.sh"

##############################

## Goto root
echo $GIT_DIR
pushd $GIT_DIR || exit

## creat hook directory and file
sh "$SCRIPT_CREATE_HOOK_DIR"

########## Add LFS ##############
sh "$SCRIPT_ADD_PRECOMMIT_FILE" "LFS" "sh $SCRIPT_LFS_AUTOADD_SNAPSHOT"
########## Add SPOTLESS ##############
sh "$SCRIPT_ADD_PRECOMMIT_FILE" "Spotless" "sh $SCRIPT_SPOTLESS"

# exit
popd || exit