package com.example.recondition_car_importer.utility;

import java.io.*;
import java.util.ArrayList;

public class BinaryFileUtil {

    // Save a single object (overwrites the file)
    public static <T extends Serializable> void saveObject(String fileName, T object) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Append a single object to an existing file
    public static <T extends Serializable> void appendObject(String fileName, T object) {
        File file = new File(fileName);

        try (ObjectOutputStream out =
                     file.exists() && file.length() > 0
                             ? new AppendableObjectOutputStream(new FileOutputStream(file, true))
                             : new ObjectOutputStream(new FileOutputStream(file, true))) {

            out.writeObject(object);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read all objects stored one after another
    public static <T> ArrayList<T> readObjects(String fileName) {
        ArrayList<T> list = new ArrayList<>();

        File file = new File(fileName);

        if (!file.exists() || file.length() == 0)
            return list;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {

            while (true) {
                try {
                    @SuppressWarnings("unchecked")
                    T obj = (T) in.readObject();
                    list.add(obj);
                } catch (EOFException e) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Save an entire ArrayList
    public static <T extends Serializable> void saveList(String fileName, ArrayList<T> list) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read an entire ArrayList
    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> readList(String fileName) {

        File file = new File(fileName);

        if (!file.exists() || file.length() == 0)
            return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    // Custom ObjectOutputStream to avoid writing header while appending
    private static class AppendableObjectOutputStream extends ObjectOutputStream {

        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset();
        }
    }
}