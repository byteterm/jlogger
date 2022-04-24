package de.byteterm.jlogger.util;

import com.google.gson.Gson;
import de.byteterm.jlogger.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;

/**
 * This class provided all file read and write thinks.
 * If you need to write a file to a specified path, you can use this class.
 * The following file formats a supported:
 * <p></p>
 * <table>
 *     <thead>
 *         <tr>
 *           <th style="padding-right: 15px; font-size: 16px">Type</th>
 *           <th style="padding-right: 15px; font-size: 16px">Write</th>
 *           <th style="font-size: 16px">Read</th>
 *         </tr>
 *     </thead>
 *     <tbody>
 *         <tr>
 *           <td style="padding-right: 15px; font-size: 13px">.json</td>
 *           <td style="padding-right: 15px; font-size: 13px">true</td>
 *           <td style="font-size: 13px">true</td>
 *         </tr>
 *         <tr>
 *           <td style="padding-right: 15px; font-size: 13px">.ini</td>
 *           <td style="padding-right: 15px; font-size: 13px">true</td>
 *           <td style="font-size: 13px">true</td>
 *         </tr>
 *     </tbody>
 * </table>
 * <p></p>
 * @since 1.0
 * @author Daniel Ramke, Niklas Tat
 */
@SuppressWarnings("unused")
public class FileUtils {

    private static final Logger logger = Logger.getLogger();

    /**
     * This method get the current path of the file path.
     * This means if the path ends with filename dot extension.
     * You can use this method to catch the path without the filename.
     * @param path the file path.
     * @return the path without the filename.
     */
    public static String getFileDirectoryFromPath(@NotNull String path) {
        path = fixPath(path);
        String directory = path;

        if(directory.contains(File.separator)) {
            directory = directory.substring(0, path.lastIndexOf(File.separatorChar));
        }

        if(directory.endsWith(File.separator)) {
            return directory;
        }
        return directory + File.separatorChar;
    }

    /**
     * This method catch the filename from a path.
     * @param path the file path.
     * @return the filename witch was found.
     */
    public static String getFileNameFromPath(@NotNull String path) {
        path = fixPath(path);
        return path.substring(path.lastIndexOf(File.separatorChar) + 1);
    }

    /**
     * This method returned only the filename without the extension.
     * @param file the file witch will be lost his extension.
     * @return the result filename.
     */
    public static String getOnlyFileName(String file) {
        String withoutExtension = file;

        int pos = withoutExtension.lastIndexOf(".");
        if(pos != -1) {
            withoutExtension = withoutExtension.substring(0, pos);
        }
        return withoutExtension;
    }

    /**
     * This method returned only the file extension.
     * This is useful for if statements or search for types.
     * @param pathOrFile the given path or file.
     * @return the extension of the path/file
     */
    public static String getFileExtension(String pathOrFile) {
        String fileName = getFileNameFromPath(pathOrFile);
        String converted = getOnlyFileName(fileName);
        return converted.replace(fileName, "");
    }

    /**
     * This method fixed path variables, this means the separator will be change to readable thinks.
     * This is useful if you use multiply operating systems.
     * @param path to fix file path.
     * @return the fixed path.
     */
    public static String fixPath(String path) {
        path = path.replace("\\/", File.separator)
                .replace("\\\\", File.separator)
                .replace("\\", File.separator)
                .replace("/", File.separator);
        return path;
    }

    /**
     * This method create a byte buffer from the given resource.
     * @param resource to convert as byte buffer.
     * @param size the buffer size.
     * @return the finished buffer, buffer is flipped!
     */
    public static ByteBuffer resourceToByteBuffer(String resource, int size) {
        ByteBuffer data = null;
        try {
            InputStream stream = inputStream(resource);
            if (stream == null) {
                logger.error("File [ " + resource + " ] can't be found!");
                return null;
            }
            byte[] bytes = toByteArray(stream, size);
            data = ByteBuffer.allocateDirect(bytes.length).order(ByteOrder.nativeOrder()).put(bytes);
            data.flip();
        } catch (IOException exception) {
            logger.error(exception);
        }
        return data;
    }

    /**
     * This method searched for the running location of the program.
     * The location is the correct path to the .exe.
     * @return the current run path.
     */
    public static File getRunningJarLocation(Class<?> c) {
        File file = null;
        try {
            file = new File(c.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        } catch (URISyntaxException exception) {
            logger.error(exception);
        }
        return file;
    }

    /**
     * @return the parent directory of the running jar file.
     */
    public static File getRunningJarParent(Class<?> c) {
        File file = null;
        try {
            file = new File(getRunningJarLocation(c).getParentFile().getPath());
        } catch (NullPointerException exception) {
            logger.error(exception);
        }
        return file;
    }

    /**
     * This method create an input stream from the given resource.
     * If the resource not found, the method will throw an IOException.
     * @param resource the file resource.
     * @return the resource as stream.
     * @throws IOException if the resource wasn't found.
     */
    private static InputStream inputStream(String resource) throws IOException {
        InputStream stream;
        File file = new File(resource);
        if(file.exists() && file.isFile()) {
            stream = new FileInputStream(file);
        } else {
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
        }
        return stream;
    }

    /**
     * This method create a byte array from the given stream.
     * @param stream to convert as byte array.
     * @param size the array size.
     * @return the generated byte array.
     */
    private static byte[] toByteArray(InputStream stream, int size) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int read;
        byte[] data = new byte[size];

        try {
            while ((read = stream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, read);
            }
            buffer.flush();
        } catch (IOException exception) {
            logger.error(exception);
        }
        return buffer.toByteArray();
    }

    /*
     * File utils for JSON and INI
     */
    public static void writeJson(String path, Map<String, Object> general, JsonStorage... objects) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            if(!(objects == null)) {
                for(JsonStorage storage : objects) {
                    if(general.containsKey(storage.name())) {
                        continue;
                    }
                    general.put(storage.name(), storage.objects());
                }
            }
            Gson gson = new Gson();
            writer.write(gson.toJson(general));
            writer.close();
        } catch (IOException exception) {
            logger.error(exception);
        }
    }

    public static void writeJson(String path, Object toJson) {

    }


}
