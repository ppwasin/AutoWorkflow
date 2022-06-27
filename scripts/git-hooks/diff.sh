

# setup
pattern='**snapshots/**/*.png'
#pattern='*.png'

# Get git root directory
git_dir=`git rev-parse --show-toplevel`

# run git command on git root directory
pushd
cd $git_dir
output=`git diff origin/main --name-only --exit-code --stat -- $pattern`
popd

## replace newline with space
#output="${output//$'\n'/ }"
echo $output

if test -z "$output"
then
      echo "No diff on pattern: $pattern"
else
      echo "Has diff on pattern: $pattern"
      echo "add lfs track file with pattern: $pattern"
      git lfs track "$pattern"
fi
