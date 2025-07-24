package kr.co.hanip.review.model;

import lombok.Getter;

@Getter
public class ReviewGetRes {
    private int id;
    private int storeId;
    private String userName;
    private double rating;
    private String menuName;
    private int menuCount;
    private String imagePath;
    private String comment;
    private String ownerComment;
    private String created;
}
