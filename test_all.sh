#!/bin/bash

fol=( Distribuzione Soluzione )
nex=( 0 1 2 3 4 6 7 8 9 ) #es 5 non in C
for i in ${nex[@]}
do
    for j in ${fol[@]}
    do
    make -C $i*/$j/c-source
    make clean -C $i*/$j/c-source
    done
done