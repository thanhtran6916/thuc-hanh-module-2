package controller;

import model.Product;

import java.io.*;

public class FileManagement {
    private GeneralManagement management;
    private File file;

    public FileManagement(File file, GeneralManagement management) {
        this.management = management;
        this.file = file;
    }

    public void readFile() {
        try {
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                String id = data[0];
                String name = data[1];
                double cost = Double.parseDouble(data[2]);
                int quantity = Integer.parseInt(data[3]);
                String description = data[4];
                Product product = new Product(id, name, cost, quantity, description);
                management.add(product);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("File data không tồn tại");
        } catch (IOException e) {
            System.err.println("Lỗi đọc file");
        }
    }

    public void saveFile() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Object product : management.getProducts()) {
                bufferedWriter.write(product.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Lỗi lưu file");;
        }
    }
}
