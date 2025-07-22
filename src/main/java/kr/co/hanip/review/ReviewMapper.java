package kr.co.hanip.review;

import kr.co.hanip.review.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    int save(ReviewPostReq req);
    List<ReviewGetRes> findAllByStoreIdOrderByIdDesc(int storeId);
    List<ReviewGetRes> findAllByUserIdOrderByIdDesc(int userId);
    ReviewGetRes reviewGet(int reviewId);
    int reviewModify(ReviewPutReq req);
    int reviewDelete(ReviewDeleteReq req);

}
