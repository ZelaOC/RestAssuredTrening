package POJOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    private int id;
    private String name;
    private String slug;
    private String descripion;

    public Product(int id, String name, String slug, String descripion) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.descripion = descripion;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getDescripion() {
        return descripion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setDescripion(String descripion) {
        this.descripion = descripion;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", descripion='" + descripion + '\'' +
                '}';
    }
}
