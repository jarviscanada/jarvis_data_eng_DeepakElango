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
        totalmem [DESC];

-- Average memory usage: this query will give data on memory used in 5 min intervals in percent
-- The column will consist of host_id, host_name, timestamp, avgerate memory used in percent
    SELECT
         usage.host_id,
         info.hostname,
         date_trunc('hour', usage."timestamp") + date_part('minute', usage."timestamp"):: int / 5 * interval '5 min' AS clock,
         AVG((info.totalmem - usage.memory_free/info.totalmem)*100) AS avg_used_mem_percentage

    FROM host_usage usage, host_info info;
