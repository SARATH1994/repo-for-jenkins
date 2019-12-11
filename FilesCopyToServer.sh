#!/bin/bash

set -x 

##################### make in Working_dir ####################################################################

Working_dir='/c/CodeCheckout/COB_Nexus/Bancs/restful'

cd $Working_dir

##################### Assigning IP value Choosen env ##########################################################

case deploy_env in
   
   "DEV")
      env=""
      ;;
   "SIT01")
     env=""
      ;;
   "SIT02")
      env=""
      ;;
   
esac


echo " Make sure all the files in Current directory is Unique in names "

sleep 10 

################# Finding Config.txt file #########################################################################

CFG=`find $PWD -type f -name config.txt `

if [ -n "$CFG" ] 
then 

echo " Config file at $CFG"

else 

echo " Config file is not found "

exit 1;

fi

################# Reading values from Config.txt into different Arrys and Cleaning There after #####################

tail -n +3 $CFG | awk -F'[#]' '{print $2}' > $Working_dir/Type.txt

tail -n +3 $CFG | awk -F'[#]' '{print $3}' > $Working_dir/Source.txt

tail -n +3 $CFG | awk -F'[#]' '{print $4}' > $Working_dir/Dest.txt

mapfile -t Ty < $Working_dir/Type.txt

mapfile -t Sr < $Working_dir/Source.txt

mapfile -t Dt < $Working_dir/Dest.txt

rm -rf $Working_dir/Type.txt $Working_dir/Source.txt $Working_dir/Dest.txt $Working_dir/server.info


#################################### Killing / Stoping the Application in Chosen env ############

HOME=`ssh $usr@$env echo '$BANCS_HOME'` 

PID=`ssh root@$env netstat -plten | grep 8010 |  awk '{print $9}' | cut -d "/" -f 1`

ssh root@$env "kill -9 $PID"


################################### Uploading Files to Destination #################################


for ((i=0;i < ${#Sr[@]};i++))

do
	
if [ ${Ty[i]} = 'f' ]
		
		then
		echo " Checking the Absolute Path of a file "
		IFS=',' read -ra ADDR <<< ${Sr[i]}
		
			for j in "${ADDR[@]}" 
			do
  
				E=`echo "$j" | xargs`
				Path=`find $PWD -type f -name "$E"` 
				echo " Checking the access to the element "
				ls -lrt $Path
  
				if [ $? == 0 ]
				then
				echo " Success : Path is accessable "
				echo " Copying files from $Path to SIT01:${Dt[i]}"
				scp root@$env:${Dt[i]} $Working_dir/Payments_Backup_BSPAY_*
				sleep 10
				scp -r $HOME/$Path root@$env:${Dt[i]}
				sleep 10
				else
				echo " Source is not existed in Curr dir "
				exit 1
				fi

			done

   
  

elif [ "${Ty[i]}" = "d" ]
		
		then
		echo " Checking the Absolute Path of a dir  " 
		IFS=',' read -ra ADDR <<< ${Sr[i]}
		for j in "${ADDR[@]}" 
		do
		E=`echo "$j" | xargs`
		dir=`find $PWD -type d -name "$E"` 
		echo " Checking the access to the dir "
		ls -lrt $dir
  
		if [ $? == 0 ]
		then
		echo " Success : dir is accesssible "
		echo " Copying dir from $dir to $env:${Dt[i]}"
		scp root@$env:${Dt[i]} $Working_dir/Payments_Backup_BSPAY_*/
		scp -r $dir root@$env:${Dt[i]}
		sleep 100
		else
		echo " Source is not existed in Curr dir "
		exit 1
		fi
		done
 
else 
		echo "Source type is needed "
		exit 2
fi 

done

################################## Starting the APPLICATION & deleting logs ##############################

ssh root@$env /var/opt/data/jboss/BANCS_JB7/Restful/Startup.sh; rm -rf /var/opt/data/jboss/BANCS_JB7/Restful/logs/*


  
