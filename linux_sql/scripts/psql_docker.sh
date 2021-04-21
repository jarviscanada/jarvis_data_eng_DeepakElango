#!/bin/bash

# Author: Deepak Elango
# Date: April 21/2021
# Project: Linux_SQL
# Version: Feature_Branch_Version_1
# Description: This script will be used to create, start, and stop an instance of a PSQL Container

# Script usage commands that you will use to test module
#########################################################
## Create a psql docker container with the given username and password.
   # ./scripts/psql_docker.sh create db_username db_password
## Start the stopped psql docker container
   # ./scripts/psql_docker.sh start
## stop the running psql docker container
   # ./scripts/psql_docker.sh stop
#########################################################

# Check the status of Docker. If it is not running, initiate it.
# Pulled memory-efficient postgres image from docker hub
  docker pull postgres:13-alpine
  systemct1 status docker || systemct1 start docker

# Creating a PSQL image
  options=$1
  db_username=$centos # This can be changed to make it more personalized to the user
  db_password=$centos1234 # This can be changed to make it more personalized to the user
    case $options in
        create)
          # Lists all and filters the docker containers to search if container with name: jrvs-psql exists
            if [ "$(docker container ls -a -f name=jrvs-psql | wc -l)" == 2 ]; then
                echo -e "Container is already created!\n"
                echo -e "\e[3musage: ./scripts/psql_docker.sh create db_username db_password\e[0m"
                exit 1
            fi

          # Checks to see if anything was entered for the username and password
          # If nothing was entered it will provide the correct username and password [Remove feature in Master version]
            if [ $db_username == "" || $db_password == "" ]; then
                echo -e "Please enter the correct username and password\n"
                echo -e "\e[3musage:\n username = centos \n password = centos1234\e[0m"
                exit 1
            fi

          # Creates Docker Volume so you can save root image in directory
            docker volume create pgdata

          # Create a PSQL container
            docker run --name jrvs-psql -e POSTGRES_PASSWORD=${db_password} -e POSTGRES_USER=${db_username} -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
            exit $?

          # Checks to see if container was created
            if [ "$(docker container ls -a -f name=jrvs-psql | wc -l)" == 1 ]; then
                echo -e "Container does not exist\n"
                echo -e "Please create new container\n"
                exit 1
            fi
          # End of first pattern: create
          ;;

        start)
          # This starts the PSQL container
            docker start jrvs-psql
            exit $?
          # End of second pattern: start
          ;;

        stop)
          # This stops the PSQL container
            docker stop jrvs-psql
            exit $?
          # End of third pattern: stop
          ;;
        *)
          # Any invalid arguments will return an exit code
            echo -e "Invalid argument\n"
            exit 1
    esac

exit 1











