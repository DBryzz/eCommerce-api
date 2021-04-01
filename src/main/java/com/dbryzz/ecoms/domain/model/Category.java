package com.dbryzz.ecoms.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "categories",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "title")
        })
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @NotBlank
    @Column(name = "title")
    @Size(max = 20)
    private String categoryName;

    @NotBlank
    @Column(name = "description")
    @Size(max = 100)
    private String categoryDescription;

    @JsonIgnore
    @Column(name = "products")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productCategory")
    private List<Product> products;


}
