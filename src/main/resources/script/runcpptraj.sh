#!/usr/bin/env bash
SOURCE="${BASH_SOURCE[0]}"
SCRIPT=${1}
WEBINF=${2}
DINPUT=/tmp/mds2

export AMBRHOME=/usr/local/amber16
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:$AMBRHOME/lib

if pgrep -x "ccptraj" > /dev/null
then
    echo "Running"

else
    if [ -d "$DINPUT/tmp" ];
     then
        rm -rf $DINPUT/tmp/*
        mkdir $DINPUT/tmp/
        cd $DINPUT/tmp
    else
        mkdir $DINPUT/tmp/
        cd $DINPUT/tmp
    fi

    python $SCRIPT/create_bv_inpt.py -v nh -p $DINPUT/prot.pdb -t $DINPUT/prod?.nc > $DINPUT/mds2.in
    $AMBRHOME/bin/cpptraj -i $DINPUT/mds2.in
    python $SCRIPT/csv2json.py
    cp $DINPUT/prot.pdb $WEBINF
    cp $DINPUT/tmp/ired_res.json $WEBINF


fi

