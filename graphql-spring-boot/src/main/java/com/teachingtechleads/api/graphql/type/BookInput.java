package com.teachingtechleads.api.graphql.type;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class BookInput {
    @NonNull
    private String author;
    @NonNull
    private String title;
    private String description;

}
