#!/bin/bash

function search(){
	num=0
	local path=$1
	one=1
	subdir "/" $path
	if [ $num -eq 0 ]; then
		echo "There is no such directory"
	elif [ $num -eq 1 ]; then
		echo ${dirs[0]}
		while :
		do
			echo "Do you want change directory?[y/n]"
			read ismove
			if [ $ismove = "y" ]; then
				echo "Directory is changed > "${dirs[0]}
				cd ${dirs[0]}
				break
			elif [ $ismove = "n" ]; then
				break
			fi
		done
	else
		for((i=1;i<=$num;i++)); do
			echo "["$i"]""  "${dirs[$i-1]}
		done
		echo "Which directory do you want?"
		read scan
		echo ${dirs[$scan-1]}
		while :
		do
			echo "Do you want change directory?[y/n]"
			read ismove
			if [ $ismove = "y" ]; then
				echo "Directory is changed > "${dirs[$scan-1]}
				cd ${dirs[$scan-1]}
				break
			elif [ $ismove = "n" ]; then
				break
			fi
		done
	fi
}

function subdir(){
	local path=$1
	local keypath=$2
	for subdirs in `ls -l $path | grep "^d" | awk '{print $9}'`; do
		if [ $path = "/" ]; then
			local abpath=/$subdirs
		else
			local abpath=$path/$subdirs
		fi
		if [ $subdirs = $keypath ]; then
			dirs[num]=$abpath
			let num=$num+1
		fi
		subdir $abpath $keypath
	done
}

while :
do
	echo "Enter the name of directory : "
	read input
	if [ $input = "." ]; then
		break
	else
		search $input
	fi
done
echo "종료"