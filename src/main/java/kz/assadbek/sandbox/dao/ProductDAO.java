package kz.assadbek.sandbox.dao;

import kz.assadbek.sandbox.models.Catalog;
import kz.assadbek.sandbox.models.Product;
import kz.assadbek.sandbox.models.SubCatalog;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgre098A";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Catalog findCatalog(int id) {
        Catalog catalog = null;
        try {
            PreparedStatement ps =
                    connection.prepareStatement("SELECT * FROM pg_catalog WHERE id=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            rs.next();
            catalog = new Catalog();
            catalog.setId(rs.getInt("id"));
            catalog.setName(rs.getString("name"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return catalog;
    }

    public List<SubCatalog> showSubCatalog(int id) {
        List<SubCatalog> subCatalogs = new ArrayList<>();
        try {
            PreparedStatement ps =
                    connection.prepareStatement("SELECT * FROM subcatalog WHERE catalogid=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SubCatalog subCatalog = new SubCatalog();
                subCatalog.setId(rs.getInt("id"));
                subCatalog.setCatalogId(rs.getInt("catalogid"));
                subCatalog.setName(rs.getString("name"));
                subCatalogs.add(subCatalog);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return subCatalogs;
    }

    public List<Catalog> catalogs() {
        List<Catalog> catalogs = new ArrayList<>();

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM pg_catalog");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Catalog catalog = new Catalog();

                catalog.setId(rs.getInt("id"));
                catalog.setName(rs.getString("name"));

                catalogs.add(catalog);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return catalogs;
    }

    public List<SubCatalog> subCatalogs() {
        List<SubCatalog> subCatalogs = new ArrayList<>();

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM subcatalog");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                SubCatalog subCatalog = new SubCatalog();

                subCatalog.setId(rs.getInt("id"));
                subCatalog.setCatalogId(rs.getInt("catalogid"));
                subCatalog.setName(rs.getString("name"));

                subCatalogs.add(subCatalog);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return subCatalogs;
    }

    public List<Product> showProducts(int id) {
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM product WHERE subcatalogid=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = new Product();

                product.setId(rs.getInt("id"));
                product.setSubCatalogId(rs.getInt("subcatalogid"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));

                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    public void save(Catalog catalog) {
        try (PreparedStatement ps =
                     connection.prepareStatement("INSERT INTO pg_catalog(name) VALUES(?)")){
            ps.setString(1, catalog.getName());

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateCatalog(int id, Catalog catalog) {
        try {
            PreparedStatement ps =
                    connection.prepareStatement("UPDATE pg_catalog SET name=? WHERE id=?");
            ps.setString(1, catalog.getName());
            ps.setInt(2, id);

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteCatalog(int id) {
        try {
            PreparedStatement ps =
                    connection.prepareStatement("DELETE FROM pg_catalog WHERE id=?");
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
