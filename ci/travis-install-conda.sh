set -e -x

wget https://repo.anaconda.com/miniconda/Miniconda3-latest-${conda_os}-x86_64.sh -O ~/miniconda.sh
bash ~/miniconda.sh -b -p ~/miniconda
source ~/miniconda/bin/activate

# TF 2.2.0 is not available on conda for OS X yet, let's stick to 2.0.0 for both OS X and Linux
conda install --yes --quiet --channel=conda-forge tensorflow=2.0.0 keras

set +e +x
