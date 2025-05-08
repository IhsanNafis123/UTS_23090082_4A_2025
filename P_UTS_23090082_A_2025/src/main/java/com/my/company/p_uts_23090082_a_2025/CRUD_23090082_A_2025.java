/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.my.company.p_uts_23090082_a_2025;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.util.Arrays;

/**
 *
 * @author Ihsan
 */
public class CRUD_23090082_A_2025 {
    private MongoCollection<Document> collection;
    private MongoClient mongoClient;

    // Konstruktor: Inisialisasi klien MongoDB dan mengakses database serta koleksi
    public CRUD_23090082_A_2025() {
        try {
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            MongoDatabase database = mongoClient.getDatabase("uts_23090082_A_2025");
            collection = database.getCollection("coll_23090082_A_2025");
        } catch (Exception e) {
            System.err.println("Error saat menghubungkan ke MongoDB: " + e.getMessage());
        }
    }

    // CREATE - Menambahkan beberapa dokumen dengan struktur berbeda
    public void createData() {
        try {
            // Dokumen pertama: nama, usia, jurusan
            Document doc1 = new Document("nama", "Fahri").append("usia", 20).append("jurusan", "TI");

            // Dokumen kedua: nama, hobi, nilai
            Document doc2 = new Document("nama", "Akmal").append("hobi", "Mancing").append("nilai", 85);

            // Dokumen ketiga: nama, alamat, aktif
            Document doc3 = new Document("nama", "Utbah").append("alamat", "Dukuhturi");

            // Menambahkan dokumen ke dalam koleksi
            collection.insertMany(Arrays.asList(doc1, doc2, doc3));

            System.out.println("Data berhasil ditambahkan.");
        } catch (Exception e) {
            System.err.println("Error saat menambahkan data: " + e.getMessage());
        }
    }

    // READ - Menampilkan seluruh dokumen dari koleksi
    public void readData() {
        try {
            FindIterable<Document> docs = collection.find();
            docs.forEach((doc) -> System.out.println(doc.toJson()));
        } catch (Exception e) {
            System.err.println("Error saat membaca data: " + e.getMessage());
        }
    }

    // UPDATE - Mengubah field tertentu dalam dokumen
    public void updateData() {
        try {
            collection.updateOne(Filters.eq("nama", "Fahri"), new Document("$set", new Document("usia", 21)));
            System.out.println("Data berhasil diubah.");
        } catch (Exception e) {
            System.err.println("Error saat mengubah data: " + e.getMessage());
        }
    }

    // DELETE - Menghapus dokumen dari koleksi
    public void deleteData() {
        try {
            collection.deleteOne(Filters.eq("nama", "Akmal"));
            System.out.println("Data berhasil dihapus.");
        } catch (Exception e) {
            System.err.println("Error saat menghapus data: " + e.getMessage());
        }
    }

    // SEARCH - Mencari dan menampilkan dokumen yang mengandung key tertentu
    public void searchData(String key) {
        try {
            FindIterable<Document> docs = collection.find(Filters.exists(key));
            docs.forEach((doc) -> System.out.println("Ditemukan: " + doc.toJson()));
        } catch (Exception e) {
            System.err.println("Error saat mencari data: " + e.getMessage());
        }
    }

    // Menutup koneksi MongoDB setelah selesai
    public void closeConnection() {
        try {
            if (mongoClient != null) {
                mongoClient.close();
            }
        } catch (Exception e) {
            System.err.println("Error saat menutup koneksi MongoDB: " + e.getMessage());
        }
    }

    // Metode main sebagai titik masuk program
    public static void main(String[] args) {
        CRUD_23090082_A_2025 app = new CRUD_23090082_A_2025();
        app.createData();
        app.readData();
        app.updateData();
        app.readData();
        app.deleteData();
        app.readData();
        app.searchData("alamat");
        app.closeConnection();
    }
}