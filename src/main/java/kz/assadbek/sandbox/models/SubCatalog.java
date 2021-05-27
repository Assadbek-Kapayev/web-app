package kz.assadbek.sandbox.models;

public class SubCatalog {
    private int id;
    private int catalogId;
    private String name;

    public SubCatalog() {}

    public SubCatalog(int id, int catalogId, String name) {
        this.id = id;
        this.catalogId = catalogId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
