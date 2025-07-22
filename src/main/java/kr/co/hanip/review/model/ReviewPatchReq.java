package kr.co.hanip.review.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
public class ReviewPatchReq {
    private int reviewId;
    private String ownerComment;
}
