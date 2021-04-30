#!/bin/bash

# Author: Deepak Elango
# Date: April 27/2021
# Project: Linux_SQL
# Version: Feature_Branch_Version_2
# Description: This script will be used collect computer usage data and then insert the data into the psql instance

      psql_host=$1
      psql_port=$2
      db_name=$3
      psql_user=$4

      date=$(date +%Y-%m-%d)
      time=$(date +" %H:%M:%S")
      timestamp=$(echo $date $time)
      memory_free=$(vmstat | awk 'NR==3  {print $4 }' | xargs)
      cpu_idle=$(vmstat | awk 'NR==3  {print $15 }' | xargs)
      cpu_kernel=$(vmstat | awk 'NR==3  {print $14 }' | xargs)
      disk_io=$(vmstat | awk 'NR==3  {print $16}' | xargs)
      disk_available=$(df -BM | awk 'FNR == 6 {print $4}')

      # This will create the data that will be added to the Host_info table
      host_usage_query="INSERT INTO host_usage (timestamp, host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available)
                             VALUES ('$timestamp', (SELECT id FROM host_info), '$memory_free', '$cpu_idle', '$cpu_kernel', '$disk_io', '$disk_available');"

      # Executing the insert command through PSQL CLI
      psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$host_usage_query"
      exit 0
      #bash host_usage.sh "localhost" 5432 "host_agent" "postgres" "mypassword"