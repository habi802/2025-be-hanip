package kr.co.hanip.review;


import kr.co.hanip.review.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

//    public ReviewGetRes reviewGet(int reviewId) {
//        return reviewMapper.reviewGet(reviewId);
//    }

    public Integer updateOwnerComment(ReviewPatchReq req, int storeId) {
        ReviewPatchDto dto = ReviewPatchDto.builder()
                .reviewId(req.getReviewId())
                .storeId(storeId)
                .build();
        Integer checkReviewId = reviewMapper.findByReviewIdAndStoreId(dto);

        if (checkReviewId == null) {
            return null;
        }

        return reviewMapper.updateOwnerComment(req);
    }

    public int delete(int reviewId, int loggedInUserId) {
        ReviewDeleteDto dto = ReviewDeleteDto.builder()
                .reviewId(reviewId)
                .userId(loggedInUserId)
                .build();

        return reviewMapper.delete(dto);
    }
}