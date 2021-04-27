#!/bin/bash

# Author: Deepak Elango
# Date: April 21/2021
# Project: Linux_SQL
# Version: Feature_Branch_Version_1
# Description: This script will be used to create, start, and stop an instance of a PSQL Container

  docker pull postgres:latest
  #docker pull postgres:13-alpine
  sudo systemctl status docker || systemctl start docker
  colourStart="\e[32m"
  colourEnd="\e[0m"
  options=$1
  dockerContainer=$(docker container ls -a -f name=jrvs-psql | wc -l)

    # General reminders that will occur at every instance
    echo -e " -------------------------------------------------------------------\n"
    echo -e "\e[36m Usage: \n${colourEnd}"
    echo -e "\e[36m Please enter one of the following option:\n create_username_password\n start\n stop\n${colourEnd}"
    echo -e " -------------------------------------------------------------------\n"

    case $options in
        create_centos_centos1234)
          # Lists all and filters the docker containers to search if container with name: jrvs-psql exists
            if [ $dockerContainer == 2 ]; then
                echo -e "${colourStart} Container is already created!\n${colourEnd}"
                exit 1
            fi

          # Creates Docker Volume so you can save root image in directory
            docker volume create pgdata

          # Create a PSQL container
            docker run --name jrvs-psql -e POSTGRES_PASSWORD=${db_password} -e POSTGRES_USER=${db_username} -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
            exit $?

          # Checks to see if container was created
            if [ $dockerContainer == 1 ]; then
                echo -e "${colourStart} \nContainer does not exist\n${colourEnd}"
                echo -e "${colourStart} Please create new container\n${colourEnd}"
                exit 1
            fi
          ;;

        start)
          # This starts the PSQL container
            docker start jrvs-psql
            echo -e "${colourStart} \nContainer has been started\n${colourEnd}"
            exit $?
          ;;

        stop)
          # This stops the PSQL container
            docker stop jrvs-psql
            echo -e "${colourStart} \nContainer has stopped\n${colourEnd}"
            exit $?
          ;;
        *)
        echo -e "\e[3m Note that you must know your username and password to create a PSQL container\n${colourEnd}"
        exit 1
    esac

exit 0