package kr.co.hanip.review;

import kr.co.hanip.review.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    int reviewCreate(ReviewPostReq req);
    ReviewGetRes reviewGet(int reviewId);
    List<ReviewGetListRes> reviewGetList(int storeId);
    int reviewModify(ReviewPutReq req);
    int reviewDelete(ReviewDeleteReq req);

}
