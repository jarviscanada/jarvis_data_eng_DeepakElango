# Java Grep Application

## <ins>Introduction

Linux uses the `grep` command to search for a string of characters in a given directory. The `Java Grep Application` is
an implementation of the Linux `grep` feature, the application was created using two methods: <ins>loops</ins> and <ins>
Lambda/Steam API's</ins>. The project was built with `Apache Maven`
and deployed using `docker`.

__Targeted User:__ The product can be used by anyone who wishes to search through a large data file for a specific text
pattern and return the output to another file.

__Technologies:__
> Git | Docker | Java SE 8 [*Lambda/Steam Functions*] | Apache Maven

## <ins>Design

### __Script Descriptions__

- `JavaGrepp.java`; This is a public interface file with all the methods required to traverse a directory, read a file,
  check to see if a line contains the regex_pattern, and return it to another file.

- `JavaGrepImp.java`; This is the loop implementation of the Java Grep Application.

- `JavaGrepLambdaImp.java`; This is the Lambda/Steam implementation of the Java Grep Application.

### __Pseudocode__

Loop Implementation:

```
matchedLines = []
for file in listFilesRecursively(rootDir)
for line in readLines(file)
if containsPattern(line)
matchedLines.add(line)
writeToFile(matchedLines)
```

Lambda/Steam Implementation:

```
lines = new ArrayList[]
lines = lines.getRootPath()
Lines = lines.filter(containsPattern())
writeToFile(lines)
```

## <ins>Product Usage

The `Java Grep Application` takes three arguments:
> {regex_pattern} ${src_dir} ${outfile}

- **regex_pattern:** the text pattern you wish to find
- **src_dir:** root directory path
- **outfile:** output file name

__Approach 1__:
Linux Grep command

```
regex_pattern=".*Romeo.*Juliet.*"
src_dir="./data"
egrep -r ${regex_pattern} ${src_dir}
```

__Approach 2__:
Java Grep Application

```
java -jar grep-demo.jar ${regex_pattern} ${src_dir} ${outfile}
```

__Approach 3__:
Dockerized Java Grep Application

```
docker pull deelango/core_java/grep
docker run --rm -v `pwd`/data:/data -v `pwd`/out:/out deelango/core_java/grep ${regex_pattern} ${src_dir} ${outfile}
```

## <ins> Deployment

The `Java Grep Application` was converted into an image using `docker build` and uploaded to DockerHub
using `docker push` for easier distribution to users.

## <ins>Limitations and Improvements

The two biggest limitations have to do with memory and lack of functionality:

- This application can only search through a file and return a matching string, but what if I want to search through a
  file and return all the words that start with a capital A.

- The size of the input file is limited by the heap size of the machine running the program. If we want to process a
  file larger than the memory available, then the program would crash as it would need to load all the data from the
  file before processing it.
    - A simple solution would be to use the Lambda/Steam implementation, this would allow only the data that is being
      operated on to be held in memory.
