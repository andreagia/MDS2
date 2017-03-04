#!/usr/bin/env bash
SOURCE="${BASH_SOURCE[0]}"
SCRIPT=${1}
DINPUT=/tmp/MDS2
export AMBRHOME=/Users/andrea/bin/amber16


if pgrep -x "ccptraj" > /dev/null
then
    echo "Running"

else
    cd $DINPUT/tmp
    rm ./*
    python $SCRIPT/create_bv_inpt.py python -v nh -p /tmp/MDS2/prot_wat.pdb -t /tmp/MDS2/prod?.nc
    /Users/andrea/bin/amber16/bin/cpptraj -i /tmp/MDS2/mds2.in
fi

