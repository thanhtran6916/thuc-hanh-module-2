package view;

import controller.FileManagement;
import controller.ProductManagement;
import model.Product;
import model.ProductComparatorCostIncrease;
import model.ProductComparatorCostReduce;

import java.util.InputMismatchException;

import static controller.Const.*;

public class ProductMenu {

    public static ProductManagement products = new ProductManagement();

    private static FileManagement fileManagement = new FileManagement(FILE_PRODUCT_DATA, products);

    public static void run() {
        int choose = -1;
        do {
            System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ SẢN PHẨM ----");
            menu();
            int chooseFirst = 1;
            int chooseLast = 9;
            choose = getChoose(choose, chooseFirst, chooseLast);
            switch (choose) {
                case 1:
                    show5Product();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    editProduct();
                    break;
                case 4:
                    removeProduct();
                    break;
                case 5:
                    sort();
                    break;
                case 6:
                    searchCostMaxProduct();
                    break;
                case 7:
                    readFile();
                    break;
                case 8:
                    saveFile();
                    break;
            }
        } while (choose != 9);
    }

    private static int getChoose(int choose, int chooseFirst, int chooseLast) {
        try {
            choose = scanner.nextInt();
            if (choose < chooseFirst || choose > chooseLast) {
                throw new Exception();
            }
        } catch (InputMismatchException e) {
            System.err.println("Nhập sai định dạng của trường dữ liệu");
            choose = -1;
        } catch (Exception e) {
            System.err.println("Không có lựa chọn này");
        } finally {
            scanner.nextLine();
        }
        return choose;
    }

    private static void sort() {
        int choose = -1;
        do {
            System.out.println("MENU SẮP XẾP");
            menuSort();
            int chooseFirst = 1;
            int chooseLast = 3;
            choose = getChoose(choose, chooseFirst, chooseLast);
            switch (choose) {
                case 1:
                    sortProductIncrease();
                    break;
                case 2:
                    sortProductReduce();
                    break;
            }
        } while (choose != 3);

    }

    private static void sortProductReduce() {
        ProductComparatorCostReduce costReduce = new ProductComparatorCostReduce();
        products.sort(costReduce);
        show5Product();
    }

    private static void sortProductIncrease() {
        ProductComparatorCostIncrease costIncrease = new ProductComparatorCostIncrease();
        products.sort(costIncrease);
        show5Product();
    }

    private static void menuSort() {
        System.out.println("1. Sắp xếp sản phẩm theo giá tăng dần");
        System.out.println("2. Sắp xếp sản phẩm theo giá giảm dần");
        System.out.println("3. Quay lại");
        System.out.println("Nhập lựa chọn");
    }

    private static void readFile() {
        fileManagement.readFile();
    }

    private static void saveFile() {
        fileManagement.saveFile();
    }

    private static void searchCostMaxProduct() {
        double costMax = 0;
        int index = INDEX_OUT_OF_ARRAY;
        for (int i = 0; i < products.getProducts().size(); i++) {
            Product product = products.getProducts().get(i);
            if (product.getCost() > costMax) {
                costMax = product.getCost();
                index = i;
            }
        }
        Product productCostMax = products.getProducts().get(index);
        System.out.println(productCostMax);
    }


    private static void removeProduct() {
        System.out.println("Nhập vào ID sản phẩm cần xóa");
        String id = scanner.nextLine();
        int index = products.searchIndexById(id);
        if (index == INDEX_OUT_OF_ARRAY) {
            System.out.println("Không tìm được sản phẩm với mã sản phẩm trên.");
            System.out.println("Ấn phím bất kỳ để nhập lại / Ấn phím Enter để thoát ra");
            String choose = scanner.nextLine();
            if (choose.equals("")) {
                return;
            } else {
                removeProduct();
            }
        } else {
            System.out.println("Nhập y để xác nhận xóa");
            String choose = scanner.nextLine();
            if (choose.equals("y")) {
                products.remove(index);
                System.out.println("Đã xóa");
            } else {
                return;
            }
        }
    }

    private static void editProduct() {
        System.out.println("Nhập mã sản phẩm cần sửa");
        String id = scanner.nextLine();
        int index = products.searchIndexById(id);
        if (index == INDEX_OUT_OF_ARRAY) {
            System.out.println("Không tìm được sản phẩm với mã sản phẩm trên.");
            System.out.println("Ấn phím bất kỳ để nhập lại / Ấn phím Enter để thoát ra");
            String choose = scanner.nextLine();
            if (choose.equals("")) {
                return;
            } else {
                editProduct();
            }
        } else {
            Product product = null;
            try {
                product = inputProduct();
            } catch (InputMismatchException e) {
                System.err.println("Nhập sai định dạng của trường dữ liệu");
                System.out.println("Nhập lại");
                scanner.nextLine();
                editProduct();
            }
            products.edit(index, product);
            System.out.println("Đã thay thế");
        }
    }


    private static void addProduct() {
        try {
            Product product = inputProduct();
            boolean check = products.add(product);
            if (check) {
                System.out.println("Đã thêm sản phẩm mới");
            } else {
                System.out.println("Không thể thêm được sản phẩm mới");
            }
        } catch (InputMismatchException e) {
            System.err.println("Nhập sai định dạng của trường dữ liệu");
            System.out.println("Nhập lại");
            scanner.nextLine();
            addProduct();
        }
    }

    private static void show5Product() {
        int count = 0;
        for (Product product : products.getProducts()) {
            if (count >= 5) {
                break;
            }
            System.out.println("Nhấn phím Enter để tiếp tục.");
            scanner.nextLine();
            System.out.println(product);
            count++;
        }
    }

    private static Product inputProduct() throws InputMismatchException {
        System.out.println("Mã sản phẩm");
        String id = scanner.nextLine();
        System.out.println("Tên");
        String name = scanner.nextLine();
        System.out.println("Giá");
        double cost = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Số lượng");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Mô tả");
        String description = scanner.nextLine();
        return new Product(id, name, cost, quantity, description);
    }

    private static void menu() {
        System.out.println("1. Xem danh sách");
        System.out.println("2. Thêm mới");
        System.out.println("3. Cập nhập");
        System.out.println("4. Xóa");
        System.out.println("5. Sắp xếp");
        System.out.println("6. Tìm sản phẩm có giá trị đắt nhất");
        System.out.println("7. Đọc từ File");
        System.out.println("8. Ghi vào file");
        System.out.println("9. Thoát");
        System.out.println("Chọn chức năng: ");

    }
}
