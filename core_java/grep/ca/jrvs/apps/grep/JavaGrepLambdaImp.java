import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.log4j.BasicConfigurator;

public class JavaGrepLambdaImp extends JavaGrepImp {
  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("Usage: JavaGrep regex rootPath outFile");
    }
    JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
    javaGrepLambdaImp.setRegex(args[0]);
    javaGrepLambdaImp.setRootPath(args[1]);
    javaGrepLambdaImp.setOutFile(args[2]);
    try {
      javaGrepLambdaImp.process();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  
  @Override
    public void process() throws IOException {
        List<String> lines = new ArrayList<>();
        lines = listFiles(getRootPath()).stream().filter(x -> containsPattern(x)).collect(Collectors.toList())
        writeToFile(lines);
    }
  
  // The following will be implemented using Lambda and Steam API's
  /**
   * Traverse a given directory and return all files
   *
   * @param rootDir input directory
   * @return files under the rootDir
   */
  public List<File> listFiles(String rootDir) throws IOException {
    return Files.list(Paths.get(rootDir))
        .filter(Files::isRegularFile)
        .map(Path::toFile)
        .collect(Collectors.toList());
  }

  /**
   * Read a file and return all the lines Explain FileReader, BufferedReader, and character encoding
   *
   * @param inputFile file to be read
   * @return lines
   * @throws java.lang.IllegalArgumentException if a given input file is not a file
   */
  public List<String> readLines(File inputFile) throws IOException {
    return Files.lines(Paths.get(inputFile.getAbsolutePath()))
        .map(String::trim)
        .filter(s -> !s.isEmpty())
        .collect(Collectors.toList());
  }
}
