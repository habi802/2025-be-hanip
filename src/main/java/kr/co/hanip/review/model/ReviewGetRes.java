package kr.co.hanip.review.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class ReviewGetRes {
    private int id;
    private int userId;
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
