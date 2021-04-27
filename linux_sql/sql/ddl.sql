-- Author: Deepak Elango
-- Date: April 23/2021
-- Project: Linux_SQL
-- Version: Feature_Branch_Version_1
-- Description: This script will be used to create two tables that will host all hardware specifications and resource usage data
--              for data analytics purposes

-- This table will be used to store all the hardware specifications
    CREATE TABLE PUBLIC.host_info
    (
        id                  SERIAL NOT NULL PRIMARY KEY,
        hostname            VARCHAR NOT NULL UNIQUE,
        cpu_number          INT NOT NULL,
        cpu_architecture    VARCHAR NOT NULL,
        cpu_model           VARCHAR NOT NULL,
        cpu_mhz             REAL NOT NULL,
        l2_cache            VARCHAR NOT NULL,       -- in kB
        totalMem            VARCHAR NOT NULL,       -- in kB
        timestamp           VARCHAR NOT NULL        -- UTC timezone
    );

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
        FOREIGN KEY (host_id) REFERENCES host_info (id)
    );