package pl.xkoem.pricecheckerlib.model;

public class FrontProduct {
    private String id;
    private String name;


    public FrontProduct(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public FrontProduct() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FrontProduct{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
