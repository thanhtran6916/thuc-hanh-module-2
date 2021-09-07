package controller;

import model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static controller.Const.INDEX_OUT_OF_ARRAY;

public class ProductManagement implements GeneralManagement<Product>{
    private List<Product> products = new ArrayList<>();

    @Override
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int searchIndexById(String id) {
        int index = INDEX_OUT_OF_ARRAY;
        for (int i = 0; i < products.size(); i++) {
            if (id.equals(products.get(i).getId())) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public boolean add(Product product) {
        boolean check = products.add(product);
        if (check) {
            return true;
        }
        return false;
    }

    @Override
    public void remove(int index) {
        products.remove(index);
    }

    @Override
    public void edit(int index, Product product) {
        products.set(index, product);
    }

    @Override
    public void sort(Comparator<Product> comparator) {
        Collections.sort(products, comparator);
    }

}
