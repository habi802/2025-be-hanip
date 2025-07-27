package kr.co.hanip.review;


import kr.co.hanip.common.util.MyFileUtils;
import kr.co.hanip.review.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper reviewMapper;
    private final MyFileUtils myFileUtils;


    public int save(MultipartFile img, ReviewPostReq req, int loggedInUserId) {
        req.setUserId(loggedInUserId);

        String saveFileName = myFileUtils.makeRandomFileName(img);
        req.setImagePath(saveFileName);
        int result = reviewMapper.save(req);

        String directoryPath = String.format("/review-profile/%d",req.getId());
        myFileUtils.makeFolders(directoryPath);

        String savePathFileName = directoryPath + "/" + saveFileName;
        try {
            myFileUtils.transferTo(img,savePathFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        return 1;
    }

    public List<ReviewGetRes> findAllByStoreId(int storeId) {
        return reviewMapper.findAllByStoreIdOrderByIdDesc(storeId);
    }

    public List<ReviewGetRes> findAllByUserId(int loggedInUserId) {
        return reviewMapper.findAllByUserIdOrderByIdDesc(loggedInUserId);
    }

    public ReviewGetRes reviewGet(int orderId) {
        return reviewMapper.findByorderId(orderId);
    }

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
//      리뷰 수정용
    public int modify(MultipartFile img, ReviewPutReq req, int loggedInUserId) {
        req.setUserId(loggedInUserId);
        String saveFileName = myFileUtils.makeRandomFileName(img);
        req.setImagePath(saveFileName);
        int result = reviewMapper.modify(req);

        String directoryPath = String.format("/review-profile/%d",req.getId());
        myFileUtils.makeFolders(directoryPath);

        String savePathFileName = directoryPath + "/" + saveFileName;
        try {
            myFileUtils.transferTo(img,savePathFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        return 1;
    }
}