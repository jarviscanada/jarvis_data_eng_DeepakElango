
-- Author: Deepak Elango
-- Date: April 23/2021
-- Project: Linux_SQL
-- Version: Feature_Branch_Version_1
-- Description: This script will be used to create two tables that will host all hardware specifications and resource usage data
--              for data analytics purposes
-- Script usage commands that you will use to test module
-- #########################################################
-- Insert new rows in the <host_info> table by implementing the following statements
-- ...INSERT INTO host_info (id, hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, L2_cache, total_mem, timestamp)
-- Insert new rows in the <host_usage> table by implementing the following statements
-- ...INSERT INTO host_usage ("timestamp", host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available)
-- #########################################################

-- This will start at the beginning of each iteration to give the user an understanding of what the host_info and the host_usage tables accept as input arguments
    colourStart="\e[36m"
    italicsStart= "\e[3m "
    colourEnd="\e[0m"

    echo -e " -------------------------------------------------------------------\n"
    echo -e "${colourStart} Insert new rows in the <host_info> table by: \n${colourEnd}"
    echo -e "${italicsStart} ...INSERT INTO host_info (id, hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, L2_cache, total_mem, timestamp) \n${colourEnd}"
    echo -e "${colourStart} Insert new rows in the <host_usage> table by: \n${colourEnd}"
    echo -e "${italicsStart} ...INSERT INTO host_usage ("timestamp", host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available) \n${colourEnd}"
    echo -e " -------------------------------------------------------------------\n"

-- This table will be used to store all the hardware specifications
    CREATE TABLE PUBLIC.host_info
    (
        id                  SERIAL NOT NULL PRIMARY KEY,
        hostname            VARCHAR NOT NULL UNIQUE,
        cpu_number          INT NOT NULL,
        cpu_architecture    VARCHAR NOT NULL,
        cpu_model           VARCHAR NOT NULL,
        cpu_mhz             INT NOT NULL,
        L2_cache            INT NOT NULL,           -- in kB
        L2_cacheTotal_mem   INT NOT NULL,           -- in kB
        timestamp           INT NOT NULL,           -- UTC timezone
    )

-- This table will be used to store all the resource usage data
    CREATE TABLE PUBLIC.host_usage
    (
        "timestamp"        TIMESTAMP NOT NULL,      -- UTC timezone
        host_id            SERIAL NOT NULL,
        memory_free        INT NOT NULL,            -- in MB
        cpu_idle           INT NOT NULL,            -- in percentage
        cpu_kernel         INT NOT NULL,            -- in percentage
        disk_io            INT NOT NULL,            -- the number of disks in Input/Output
        disk_available     INT NOT NULL,
        FOREIGN KEY (host_id) REFERENCES host_info (id),
    )