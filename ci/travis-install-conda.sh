set -e -u

wget https://repo.anaconda.com/miniconda/Miniconda3-latest-${conda_os}-x86_64.sh -O ~/miniconda.sh
bash ~/miniconda.sh -b -p ~/miniconda
source ~/miniconda/bin/activate

conda install --yes --quiet python=3.7
conda install --yes --quiet nomkl tensorflow=${conda_tf_version}

set +e +u
