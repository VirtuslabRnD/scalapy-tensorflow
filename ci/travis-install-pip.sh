set -e -x

pip config set global.progress_bar off
pip install --upgrade pip
pip install numpy
pip install tensorflow==2.2.0
pip install keras

set +e +x
