package ca.jrvs.apps.practice;

public interface RegexExc {
    /**
     * Return true if filename extension is jpg or jpeg (case sensitive)
     *
     * @param filename
     * @return
     */
    public boolean matchJpeg(String filename);

    /**
     * Return true if ip is valid [0.0.0.0 to 999.999.999.999]
     *
     * @param ip
     * @return
     */
    public boolean matchIp(String ip);

    /**
     * Return true if line is empty
     *
     * @param line
     * @return
     */
    public boolean isEmptyLine(String line);
}
