package com.vitaly.onlineStore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewsDTO {
    private String reviewTitle;
    private String reviewText;
    private Double reviewRating;
}
