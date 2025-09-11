/*
package com.shoppingcart.cartify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fileName;
    private String fileType;

    @Lob                            // Large Object
    private Blob Image;
    private  String downloadUrl;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}
*/
