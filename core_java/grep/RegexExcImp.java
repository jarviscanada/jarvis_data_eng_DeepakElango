package ca.jrvs.apps.practice;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexExcImp implements RegexExc {

    public boolean matchJpeg(String filename) {
        return filename.matches("\\.+[j]+[p]+(g|eg)");
    }

    public boolean matchIp(String ip) {
        return ip.matches("([0-9][0-9][0-9]\\.[0-9][0-9][0-9]\\.[0-9][0-9][0-9]\\.[0-9][0-9][0-9])|([0-9][0-9]\\.[0-9][0-9]\\.[0-9][0-9]\\.[0-9][0-9])|([0-9]\\.[0-9]\\.[0-9]\\.[0-9])");
    }

    public boolean isEmptyLine(String line) {
        return line.matches("^\\s*$");
    }
}