#!/usr/bin/env bash
SOURCE="${BASH_SOURCE[0]}"
SCRIPT=${1}
WEBINF=${2}
DINPUT=/tmp/MDS2
export AMBRHOME=/Users/andrea/bin/amber16


if pgrep -x "ccptraj" > /dev/null
then
    echo "Running"

else
    cd $DINPUT/tmp
    rm $DINPUT/tmp/*
    python $SCRIPT/create_bv_inpt.py -v nh -p /tmp/MDS2/prot.pdb -t /tmp/MDS2/prod?.nc > /tmp/MDS2/mds2.in
    $AMBRHOME/bin/cpptraj -i /tmp/MDS2/mds2.in
    python $SCRIPT/csv2json.py
    cp /tmp/MDS2/prot.pdb $WEBINF/../html/data/
    cp $DINPUT/tmp/ired_res.json $WEBINF/../html/data/


fi

