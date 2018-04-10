package ru.ivmiit.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private Long id;
    private String name;
    private Category category;

    public Product(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return getCategory().toString().toLowerCase() + " " + getName();
    }

}
