set -e -x

# Remove pre-bundled libunwind
sudo find /usr -path '*/*libunwind*' -delete

# Use pre-bundled clang
export PATH=/usr/local/clang-5.0.0/bin:$PATH
export CXX=clang++

sudo apt-get update
sudo apt-get install -y libgc-dev liblzma-dev libre2-dev libunwind-dev

set +e +x
