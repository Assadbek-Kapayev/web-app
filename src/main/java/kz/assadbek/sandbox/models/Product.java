package kz.assadbek.sandbox.models;

public class Product {
    private int id;
    private int subCatalogId;
    private String name;
    private String description;

    public Product() {}

    public Product(int id, int subCatalogId, String name, String description) {
        this.id = id;
        this.subCatalogId = subCatalogId;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubCatalogId() {
        return subCatalogId;
    }

    public void setSubCatalogId(int subCatalogId) {
        this.subCatalogId = subCatalogId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
