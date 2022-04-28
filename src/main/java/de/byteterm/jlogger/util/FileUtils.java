package de.byteterm.jlogger.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import de.byteterm.jlogger.Logger;
import org.ini4j.Wini;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
     * This method create a byte buffer from the given resource.
     * Warning the size is caught by the resource.
     * @param resource to convert as byte buffer.
     * @return the finished buffer, buffer is flipped!
     */
    public static ByteBuffer resourceToByteBuffer(String resource) {
        return resourceToByteBuffer(resource, capacity(resource));
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
    public static InputStream inputStream(String resource) throws IOException {
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
    public static byte[] toByteArray(InputStream stream, int size) {
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

    /**
     * This method returned the correct resource length as byte[].
     * @param path the resource witch give us the size.
     * @return the length value witch is stored.
     */
    public static int capacity(String path) {
        int capacity = 0;
        try {
            InputStream stream = new FileInputStream(path);
            capacity = stream.available();
            stream.close();
        } catch (IOException exception) {
            logger.error(exception);
        }
        return capacity;
    }

    /*
     * File utils for JSON files
     */

    /**
     * This method write a json object to a file. You can create maps arrays and one liner.
     * @param path the file output path.
     * @param general the general map witch can store name, id or something.
     * @param objects the objects witch represent arrays or maps like address or attributes.
     */
    public static void writeJson(@NotNull String path, @NotNull Map<String, Object> general, @NotNull FileStorage... objects) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            if(!(objects == null)) {
                for(FileStorage storage : objects) {
                    if(general.containsKey(storage.name())) {
                        continue;
                    }
                    general.put(storage.name(), storage.objects());
                }
            }
            Gson gson = new Gson();
            writer.write(gson.toJson(general));
        } catch (IOException exception) {
            logger.error(exception);
        }
    }

    /**
     * This method write a json object from the given class object.
     * @param path the output path.
     * @param toJson the object witch compare to json.
     * @see ObjectMapper
     */
    public static void writeJson(@NotNull String path, @NotNull Object toJson) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(path), toJson);
        } catch (IOException exception) {
            logger.error(exception);
        }
    }

    /**
     * This method returned a raw string of the given json file.
     * @param path the json file.
     * @return the raw string.
     */
    public static String readJson(@NotNull String path) {
        String out = null;
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();
            out = parser.toString();
        } catch (IOException exception) {
            logger.error(exception);
        }
        return out;
    }

    /**
     * This method stored all the json content in maps.
     * These maps can store objects witch needed a cast to the provided type.
     * @param path the json file.
     * @param stored the map to store the stuff.
     */
    public static void readJson(@NotNull String path, @NotNull Map<String, Object> stored) {
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();

            for(String key : parser.keySet()) {
                JsonElement element = parser.get(key);
                String elementAsString = element.toString();

                //If the element a map.
                if(checkMap(key, element, stored)) {
                    continue;
                }

                if(checkList(key, element, stored)) {
                    continue;
                }

                if(elementAsString.startsWith("\"")) {
                    elementAsString = elementAsString.substring(1, (elementAsString.length() - 1) );
                    stored.put(key, elementAsString);
                    continue;
                }
                stored.put(key, element);
            }
        } catch (IOException exception) {
            logger.error(exception);
        }
    }

    /**
     * This private method check the given json content for maps.
     * @param key the over key on top.
     * @param element the element witch is a map.
     * @param stored the map witch stored the content.
     * @return the state of the element.
     */
    private static boolean checkMap(String key, JsonElement element, Map<String, Object> stored) {
        boolean state = false;

        String text = element.toString();
        if(text.startsWith("{")) {
            Map<String, Object> objectMap = new HashMap<>();
            JsonObject jsonObject = JsonParser.parseString(text).getAsJsonObject();
            for(String objectKeys : jsonObject.keySet()) {
                JsonElement objectElement = jsonObject.get(objectKeys);
                String value = objectElement.toString();
                if(checkMap(objectKeys, objectElement, objectMap)) {
                    continue;
                }

                if(checkList(key, element, stored)) {
                    continue;
                }

                if(value.startsWith("\"")) {
                    value = value.substring(1, (value.length() - 1) );
                    objectMap.put(objectKeys, value);
                }
                objectMap.put(objectKeys, objectElement);
            }
            stored.put(key, objectMap);
            state = true;
        }

        return state;
    }

    /**
     * This private method check the given json content for arrays.
     * @param key the over key on top.
     * @param element the element witch is an array.
     * @param stored the map witch stored the content.
     * @return the state of the element.
     */
    private static boolean checkList(String key, JsonElement element, Map<String, Object> stored) {
        boolean state = false;

        String text = element.toString();
        if(text.startsWith("[")) {
            List<Object> list = new ArrayList<>();
            JsonArray object = JsonParser.parseString(text).getAsJsonArray();
            for(int i = 0; i < object.size(); i++) {
                JsonElement arrayElement = object.get(i);
                String value = arrayElement.toString();

                if(value.startsWith("\"")) {
                    value = value.substring(1, (value.length() - 1) );
                    list.add(value);
                    continue;
                }
                list.add(object.get(i));
            }
            stored.put(key, list);
            state = true;
        }

        return state;
    }

    /*
     * FileUtils for INI files
     */

    /**
     * This method create a new ini file with the given options.
     * @param path the path for the new file.
     * @param objects the options witch will present.
     */
    public static void writeIni(@NotNull String path, @NotNull FileStorage... objects) {
        try {
            Wini ini = new Wini(new File(path));
            for(FileStorage storage : objects) {
                String head = storage.name();
                for(String key : storage.objects().keySet()) {
                    ini.put(head, key, storage.objects().get(key));
                }
            }
            ini.store();
        } catch (IOException exception) {
            logger.error(exception);
        }
    }

    /**
     * This method read ini file.
     * @param path the path to the ini file.
     * @param stored the stored list for the options.
     */
    public static void readIni(@NotNull String path, @NotNull List<FileStorage> stored) {
        try {
            Wini ini = new Wini(new File(path));
            for(String section : ini.keySet()) {
                Map<String, Object> objects = new HashMap<>();
                for(String key : ini.get(section).keySet()) {
                    objects.put(key, ini.get(section, key));
                }
                FileStorage storage = new FileStorage(section, objects);
                stored.add(storage);
            }
        } catch (IOException exception) {
            logger.error(exception);
        }
    }

}
