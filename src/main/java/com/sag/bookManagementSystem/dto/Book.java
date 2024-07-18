package com.sag.bookManagementSystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sag.bookManagementSystem.constants.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    /**
     * Unique book number
     */
    @Id
    @JsonProperty("bookId")
    private Long id;

    /**
     * title of the book
     */
    @NotNull
    private String title;

    /**
     * author of the book
     */
    private String author;

    /**
     * category of the book
     */
    private Category category;

    /**
     * price of the book
     */
    @Min(value = 0, message = "Price should be positive value.")
    private float price;

    /**
     * Amount of book available
     */
    @JsonProperty("available_books")
    @Min(value = 0, message = "Total Count should be positive value.")
    private int totalCount;
}

