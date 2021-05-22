import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.regex.Pattern;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.log4j.BasicConfigurator;

public class JavaGrepImp implements JavaGrepp {

  final Logger logger = LoggerFactory.getLogger(JavaGrepp.class);

  private String regex;
  private String rootPath;
  private String outFile;

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("Usage: JavaGrep regex rootPath outFile");
    }

    // Use default logger config
    BasicConfigurator.configure();

    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try {
      javaGrepImp.process();
    } catch (Exception ex) {
      javaGrepImp.logger.error(ex.getMessage(), ex);
    }
  }

  @Override
  public void process() throws IOException {
    List<String> lines = new ArrayList<>();
    for (File newFiles : listFiles(getRootPath())) {
      for (String line : readLines(newFiles)) {
        if (containsPattern(line)) {
          lines.add(line);
        }
      }
    }
    writeToFile(lines);
  }

  // Traverse a given directory and return all the files
  // The input directory is rootDir
  @Override
  public List<File> listFiles(String rootDir) {
    List<File> listFile = new ArrayList<>();
    File[] rootDirectory = new File(rootDir).listFiles();

    assert rootDirectory != null;
    for (File file : rootDirectory) {
      if (file.isDirectory()) {
        listFile.addAll(listFiles(file.getAbsolutePath()));
      } else {
        listFile.add(file);
      }
    }
    return listFile;
  }
  // Read a file and return all the lines Explain FileReader, BufferedReader, and character encoding
  @Override
  public List<String> readLines(File inputFile) throws IOException {
    List<String> lineList = new ArrayList<>();
    String linesInFile;
    BufferedReader buffFileReader = new BufferedReader(new FileReader(inputFile));
    linesInFile = buffFileReader.readLine();

    if (linesInFile != null) {
      lineList.add(linesInFile);
    } else {
      buffFileReader.close();
    }
    return lineList;
  }

  // Check if a line contains the regex pattern (passed by the user)
  @Override
  public boolean containsPattern(String line) {
    return (Pattern.matches(getRegex(), line));
  }

  // Write lines to a filE Explore: FileOutputStream, OutputStreamWriter, and BufferedWriter
  @Override
  public void writeToFile(List<String> lines) throws IOException {
    BufferedWriter writeFile = new BufferedWriter(new FileWriter(this.getOutFile()));
    for (String eachLine : lines) {
      writeFile.write(String.valueOf(lines));
    }
    writeFile.flush();
  }

  // Getter and setter methods
  public String getRegex() {
    return regex;
  }

  public void setRegex(String regex) {
    this.regex = regex;
  }

  public String getRootPath() {
    return rootPath;
  }

  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  public String getOutFile() {
    return outFile;
  }

  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }
}
