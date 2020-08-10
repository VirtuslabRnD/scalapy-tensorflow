set -e -u

wget https://repo.anaconda.com/miniconda/Miniconda3-latest-${conda_os}-x86_64.sh -O ~/miniconda.sh
bash ~/miniconda.sh -b -p ~/miniconda
source ~/miniconda/bin/activate

conda install --yes --quiet python=3.7
conda install --yes --quiet tensorflow=${conda_tf_version}
# There is a difference in versions of BLAS library between numpy 1.18 and 1.19. 
# It caused segfaults so numpy is kept on 1.18 for now. 
conda install --yes --quiet numpy=1.18.5
set +e +u
