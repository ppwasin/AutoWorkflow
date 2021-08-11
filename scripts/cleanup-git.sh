# Remove all local and remote tags
git tag -d $(git tag -l)
git fetch
git push origin --delete $(git tag -l)
git tag -d $(git tag -l)

# Remove all branch except main
git branch | grep -v "main" | xargs git branch -D

git branch -r | grep 'origin' | grep -v 'main' | grep -v HEAD | cut -d/ -f2- | while read line; do git push origin :heads/$line; done;
