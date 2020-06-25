set -e -x

wget https://repo.anaconda.com/miniconda/Miniconda3-latest-${conda_os}-x86_64.sh -O ~/miniconda.sh
bash ~/miniconda.sh -b -p ~/miniconda
source ~/miniconda/bin/activate

conda install --yes --quiet --channel=conda-forge tensorflow=${conda_tf_version}

set +e +x
