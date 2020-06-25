set -e -x

brew tap homebrew/cask-versions
brew tap adoptopenjdk/openjdk
brew cask install adoptopenjdk/openjdk/adoptopenjdk8
PATH=/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/bin:$PATH
export PATH

brew install re2

set +e +x
