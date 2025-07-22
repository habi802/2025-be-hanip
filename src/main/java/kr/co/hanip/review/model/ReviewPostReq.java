package kr.co.hanip.review.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ReviewPostReq {
    private int userId;
    private int orderId;
    private double rating;
    private String comment;
    private String imagePath;
}
