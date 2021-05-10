-- Author: Deepak Elango
-- Date: April 28/2021
-- Project: Linux_SQL
-- Version: Feature_Branch_Version_1
-- Description: This script will be used to answer three queries: Group hosts by hardware info, average memory usage, and detecting host failure

-- Group hosts by hardware info: this query will illustrate a table that comprises of cpu number, host_id, and total memory size in descending order
   SELECT
        id,
        cpu_number,
        totalmem
   FROM host_info
   GROUP BY
        cpu_number,
        id
   ORDER BY
        totalmem DESC;

-- Average memory usage: this query will give data on memory used in 5 min intervals in percent
-- The column will consist of host_id, host_name, timestamp, average memory used in percent
    SELECT
         host_id,
         hostname,
         date_trunc('hour', current_timestamp) + date_part('minute', current_timestamp):: int / 5 * interval '5 min' AS clock,
         AVG(((info.totalmem - usage.memory_free)*100/info.totalmem)) AS avg_used_mem_percentage
    FROM host_usage as usage, host_info as info
    WHERE usage.host_id = info.id
    GROUP BY
         current_timestamp,
         hostname,
         host_id;

-- Detect host failure: This query will detect host failures if the host_usage table inserts less than 3 data points within a 5 min interval
    SELECT
         host_id,
         date_trunc('hour', current_timestamp) + date_part('minute', current_timestamp):: int / 5 * interval '5 min' AS clock
    FROM host_usage
    GROUP BY
        host_id
    HAVING COUNT(*) < 3;