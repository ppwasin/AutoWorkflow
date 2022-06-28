# setup
pattern='**snapshots/**/*.png'
#pattern='*.png'
tag="[lfs-autoadd-snapshot]"

# Get git root directory
git_dir=$(git rev-parse --show-toplevel)

# run git command on git root directory
pushd "$git_dir" || exit
output=$(git diff origin/main --name-only --exit-code --stat -- "$pattern")
popd || exit

## replace newline with space
#output="${output//$'\n'/ }"
echo "$output"

if test -z "$output"
then
      echo "$tag No diff on pattern: $pattern"
else
      echo "$tag Has diff on pattern: $pattern"
      echo "$tag add lfs track file with pattern: $pattern"
      git lfs track "$pattern"
      git add .gitattributes
fi
