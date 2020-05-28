#!/bin/bash

echo -n "" > results_$1

for r in {0..7};
do
	for c in {0..7};
	do
		timeout $1 java KnightsProblem $r $c
		if [ $? -ne 0 ];
		then
			echo $r $c >> results_$1;
		fi
	done
done
