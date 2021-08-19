# Hadoop

Table of contents
* [Introduction](##Introduction)
* [Hadoop Cluster](##HadoopCluster)
* [Hive Project](##Hive Project)
* [Improvements](##Improvements)

## Introduction
The Data Analytics team at LGS Gift Shop wanted to switch to some Big Data Platform to process data, hence
they hired my team, and I to help them transition into a Hadoop ecosystem. Our responsibility 
comprised of Evaluate Core Hadoop components, including MapReduce, HDFS, and YARN, 
provision a Hadoop Cluster using GCP, and solve business problem using Apache Hive and Zeppelin Notebook.


__Technologies:__
> <span style = "color:green"> Hadoop | Apache Hive | Google Cloud Platform | Zeppelin Notebook  </span>

## Hadoop Cluster

![my image](./assets/cluster.png)

Definitions:

|   | Description | 
| ----- | ----- |
| Hadoop Distributed File System (HDFS) | A data storage system that is used by the Hadoop Applications, it makes it possible to scale a single Apache Hadoop cluster to hundreds (and even thousands) of nodes |
| MapReduce | A software framework for processing large data sets in a distributed fashion over several machines |
| Yet Another Resource Negotiator (YARN) | It is responsible for coordinating and managing the underlying resources and scheduling jobs to be run |
| Apache Hive | Hive allows developers to read, write, and manage large amounts of data using SQL |
| Zeppelin Notebook | An open source web-based notebook that allows developers to perform Data Analytics interactively |

Specifications:

- Google Cloud Platform's Dataproc 
- 1 Master Node/2 Worker Nodes
- 13 GB RAM
- Minimum 100 GB Disk Space

## Hive Project

The queries were optimized using a table that is partitioned by year, this would allow us to access data more efficiently as we 
are accessing only the required partition rather than loading all the data into the memory. I also used a columnar
table to decrease the space the data takes.

![my image](./assets/hadoop.png)


## Improvements
- Increase the amount of worker nodes in the cluster so that the system can perform more efficiently with large datasets
- The execution time for Apache Spark is significantly better than Apache Tez
