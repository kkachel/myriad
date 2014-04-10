import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Myriad Java Programming Problem.
 *
 * Program that takes as input from the command line an absolute path, and prints out a list of
 * subdirectories and files contained within that path; sorting by size from largest to smallest.
 * The size of a subdirectory is the size of all of that subdirectories contents.
 * Program is displaying the size of the files, and subdirectories in kilobytes.
 *
 * @author Krzysztof Kachel
 */
public class DU {

    /**
     * Main application method which takes as a parameter absolute path to directory.
     *
     * @param args list of command line parameters with absolute path
     */
    public static void main(String[] args) {
        String dirPath = getDirPathParam(args);
        File dir = FileUtils.createFile(dirPath);

        List<FileSize> files = FileUtils.getDirContentSize(dir);
        Collections.sort(files);

        for(FileSize fileSize: files) {
            System.out.println(fileSize);
        }
    }

    /**
     * Gets absolute path parameter.
     *
     * @param args list of parameters from command line
     * @return absolute path to directory
     */
    public static String getDirPathParam(String[] args) {
        if(args == null || args.length == 0) {
            throw new IllegalStateException("Absolute path parameter is not set!");
        }
        return args[0];
    }
}

/**
 * General file manipulation utilities.
 *
 * @author Krzysztof Kachel
 */
class FileUtils {

    /**
     * Creates list of {@link FileSize} objects which represents
     * content of directory together with sizes.
     * The only files explicitly listed in the result
     * should be those in the top level of the absolute path.
     *
     * @param dir directory of which content information will be generated
     * @return list of directory content together with sizes
     */
    public static List<FileSize> getDirContentSize(File dir) {
        List<FileSize> files = new ArrayList<FileSize>();
        for (File file: dir.listFiles()) {
            files.add(FileSizeFactory.getFileSize(file));
        }
        return files;
    }

    /**
     * Creates {@link File} object based on absolute file path.
     *
     * @param absolutePath full path to file
     * @return {@link File}
     */
    public static File createFile(String absolutePath) {
        File file = new File(absolutePath);
        if(!file.exists()) {
            throw new IllegalStateException("Location " + absolutePath + " dose not exist!");
        }
        return file;
    }
}

/**
 * General bytes manipulation utilities.
 *
 * @author Krzysztof Kachel
 */
class ByteUtils {

    private static final long KILOBYTE = 1024;

    private static final String KILOBYTE_SYMBOL = "KB";

    /**
     * Converts bytes to kilobytes.
     *
     * @param bytes
     * @return kilobytes
     */
    public static long toKilobytes(long bytes) {
        return bytes/KILOBYTE;
    }

    /**
     * Converts bytes to kilobytes string.
     *
     * @param bytes
     * @return kilobytes string
     */
    public static String toKilobytesString(long bytes) {
        return toKilobytes(bytes) + KILOBYTE_SYMBOL;
    }
}

/**
 * Represents file and directory size.
 *
 * @author Krzysztof Kachel
 */
class FileSize implements Comparable<FileSize> {

    private String absolutePath;

    private Long size;

    private FileType type;

    /**
     * Initialize new instance.
     *
     * @param absolutePath full path to file/directory
     * @param type file type (file/directory)
     * @param size file/directory size
     */
    public FileSize(String absolutePath, FileType type, long size) {
        this.absolutePath = absolutePath;
        this.type = type;
        this.size = size;
    }

    @Override
    public int compareTo(FileSize o) {
        return (o.size).compareTo(this.size);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.type);
        builder.append(" ");
        builder.append(this.absolutePath.toUpperCase());
        builder.append(" ");
        builder.append(ByteUtils.toKilobytesString(this.size));
        return builder.toString();
    }
}

/**
 * Creates instances of {@link FileSize}.
 *
 * @author Krzysztof Kachel
 */
class FileSizeFactory {

    /**
     * Creates {@link FileSize} object based on {@link File}.
     *
     * @param file decides about creation of {@link FileSize}
     * @return {@link FileSize}
     */
    public static FileSize getFileSize(File file) {
        if (file.isFile()) {
            return new FileSize(file.getAbsolutePath(), FileType.FILE, file.length());
        } else {
            return new FileSize(file.getAbsolutePath(), FileType.DIR, getDirSize(file));
        }
    }

    /**
     * Gets size of directory which accumulates sizes of files and subdirectories in this directory.
     *
     * @param dir directory
     * @return size of directory
     */
    public static long getDirSize(File dir) {
        long size = 0;
        for (File file: dir.listFiles()) {
            if (file.isFile()) {
                size += file.length();
            } else {
                size += getDirSize(file);
            }
        }
        return size;
    }
}

/**
 * Represents file type.
 *
 * @author Krzysztof Kachel
 */
enum FileType {
    FILE,
    DIR
}
