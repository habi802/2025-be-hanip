package kr.co.hanip.review.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewPutReq {
    private int id;
    private int userId;
    private int rating;
    private String comment;
    private String imagePath;
}
