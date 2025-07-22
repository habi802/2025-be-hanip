package kr.co.hanip.review;


import kr.co.hanip.review.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper reviewMapper;

    public int save(ReviewPostReq req, int loggedInUserId) {
        req.setUserId(loggedInUserId);
        return reviewMapper.save(req);
    }

    public List<ReviewGetRes> findAllByStoreId(int storeId) {
        return reviewMapper.findAllByStoreIdOrderByIdDesc(storeId);
    }

    public List<ReviewGetRes> findAllByUserId(int loggedInUserId) {
        return reviewMapper.findAllByUserIdOrderByIdDesc(loggedInUserId);
    }

    public ReviewGetRes reviewGet(int reviewId) {
        return reviewMapper.reviewGet(reviewId);
    }

    public int reviewUpdate(ReviewPutReq req, int userId) {
        req.setUserId(userId);
        return reviewMapper.reviewModify(req);
    }

    public int reviewDelete(ReviewDeleteReq req) {
        return reviewMapper.reviewDelete(req);
    }
}