package kr.co.hanip.review.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ReviewPostReq {
    private int userId;
    private int storeId;
    private int reviewId;
    private int rating;
    private String imagePath;
    private String comment;
}
