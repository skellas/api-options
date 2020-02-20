package com.teachingtechleads.data.object;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Entity
@Value
@AllArgsConstructor
@Builder(toBuilder = true)
public class Book {
    // Core details
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @Column(length = 56, nullable = false)
    @NonNull
    private String author;
    @Column(length = 128, nullable = false)
    @NonNull
    private String title;
    @Column(length = 255)
    private String description;

    // Extra details
    @Column(length = 13)
    private String isbn;
    @Column(length = 255)
    private String synopsis;
    @Column(length = 255)
    private String coverImageUrl;
}
