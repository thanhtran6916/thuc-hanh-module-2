package controller;

import java.util.Comparator;
import java.util.List;

public interface GeneralManagement<T> {
    boolean add(T t);
    void remove(int index);
    void edit(int index, T t);
    void sort(Comparator<T> comparator);
    List<T> getProducts();
}
