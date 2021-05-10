#!/bin/bash

# Author: Deepak Elango
# Date: April 26/2021
# Project: Linux_SQL
# Version: Feature_Branch_Version_2
# Description: This script will be used collect hardware specification data and then insert the data into the psql instance
#              Since the hardware specifications are static, the script will be executed only once

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
lscpu_out=$(lscpu)
hostname='hostname'

cpu_number=$(echo "$lscpu_out" | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out" | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out" | egrep "^Model:" | awk '{print $2}' | xargs)
cpu_mhz=$(echo "$lscpu_out" | egrep "^CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out" | egrep "^L2 cache:" | awk '{print $3}' | xargs)
totalMem=$(free | awk 'NR==2  {print $2 }' | xargs)
date=$(date +%Y-%m-%d)
time=$(date +" %H:%M:%S")
timestamp=$(echo $date $time)

# This will create the data that will be added to the Host_info table
host_info_query="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, totalMem, timestamp)
                 VALUES ('$hostname', '$cpu_number', '$cpu_architecture', '$cpu_model', '$cpu_mhz', '$l2_cache', '$totalMem', '$timestamp');"

# Executing the insert command through PSQL CLI
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$host_info_query"
exit $?
#bash host_info.sh "localhost" 5432 "host_agent" "postgres" "mypassword"
