package kr.co.hanip.review;


import kr.co.hanip.review.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper reviewMapper;


    public int reviewCreate(ReviewPostReq req, int userId) {
        req.setUserId(userId);
        return reviewMapper.reviewCreate(req);
    }

    public ReviewGetRes reviewGet(int reviewId) {
        return reviewMapper.reviewGet(reviewId);
    }

    public List<ReviewGetListRes> reviewList(int storeId) {
        return reviewMapper.reviewGetList(storeId);
    }

    public int reviewUpdate(ReviewPutReq req, int userId) {
        req.setUserId(userId);
        return reviewMapper.reviewModify(req);
    }

    public int reviewDelete(ReviewDeleteReq req) {
        return reviewMapper.reviewDelete(req);
    }
}