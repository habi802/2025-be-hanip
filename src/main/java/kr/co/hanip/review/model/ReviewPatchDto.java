package kr.co.hanip.review.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewPatchDto {
    private int reviewId;
    private int storeId;
}
