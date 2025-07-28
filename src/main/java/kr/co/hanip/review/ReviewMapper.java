package kr.co.hanip.review;

import kr.co.hanip.review.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    int save(ReviewPostReq req);
    List<ReviewGetRes> findAllByStoreIdOrderByIdDesc(int storeId);
    List<ReviewGetRes> findAllByUserIdOrderByIdDesc(int userId);
    ReviewGetRes findByorderId(int orderId);
    Integer findByReviewIdAndStoreId(ReviewPatchDto dto);
    int updateOwnerComment(ReviewPatchReq req);
    int delete(ReviewDeleteDto req);
    int modify(ReviewPutReq req);

}
