package com.ism.repositories.list;

import java.util.ArrayList;

public class Table<T> {
    private ArrayList<T> tables = new ArrayList<>();

    public int insert(T data) {
        tables.add(data);
        return 1;
    }

    public int update(T data) {
        int pos = indexOf(data.getId());
        if (pos != -1) {
            tables.set(pos, data);
            return 1;
        }
        return 0;
    }

    public ArrayList<T> findAll() {
        return tables;
    }

    public T findByID(int id) {
        int pos = indexOf(id);
        if (pos != -1) {
            return tables.get(pos);
        }
        return null;
    }

    public int delete(int id) {
        int pos = indexOf(id);
        if (pos != -1) {
            tables.remove(pos);
            return 1;
        }
        return 0;
    }

    public int indexOf(int id) {
        int pos = 0;
        for (T item : tables) {
            // Assurez-vous que la classe T a une méthode getId()
            if (item.getId() == id) {
                return pos;
            }
            pos++;
        }
        return -1;
    }
}
