package kr.co.hanip.review;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.hanip.common.model.ResultResponse;
import kr.co.hanip.common.util.HttpUtils;
import kr.co.hanip.review.model.*;
import kr.co.hanip.user.etc.UserConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResultResponse<Integer> reviewCreate(HttpServletRequest httpReq, @RequestBody ReviewPostReq req) {
        Integer userId = (Integer) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        if (userId == null) return ResultResponse.fail(401, "로그인 후 이용해주세요");

        int result = reviewService.reviewCreate(req, userId);
        return result == 0 ? ResultResponse.fail(400, "리뷰 등록 실패") : ResultResponse.success(result);
    }

    @GetMapping
    public ResultResponse<ReviewGetRes> reviewGet(@RequestParam int reviewId) {
        ReviewGetRes res = reviewService.reviewGet(reviewId);
        return res == null ? ResultResponse.fail(404, "리뷰 없음") : ResultResponse.success(res);
    }

    @GetMapping("/store/{storeId}")
    public ResultResponse<List<ReviewGetListRes>> reviewList(@PathVariable int storeId) {
        List<ReviewGetListRes> res = reviewService.reviewList(storeId);
        return (res == null || res.isEmpty()) ? ResultResponse.fail(404, "리뷰 없음") : ResultResponse.success(res);
    }

    @PutMapping
    public ResultResponse<Integer> reviewUpdate(HttpServletRequest httpReq, @RequestBody ReviewPutReq req) {
        Integer userId = (Integer) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        if (userId == null) return ResultResponse.fail(401, "로그인 후 이용해주세요");

        int result = reviewService.reviewUpdate(req, userId);
        return result == 0 ? ResultResponse.fail(400, "리뷰 수정 실패") : ResultResponse.success(result);
    }

    @DeleteMapping("/{reviewId}")
    public ResultResponse<Integer> reviewDelete(HttpServletRequest httpReq, @PathVariable int reviewId) {
        Integer userId = (Integer) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        if (userId == null) return ResultResponse.fail(401, "로그인 후 이용해주세요");

        ReviewDeleteReq req = new ReviewDeleteReq(reviewId);
        int result = reviewService.reviewDelete(req);
        return result == 0 ? ResultResponse.fail(400, "리뷰 삭제 실패") : ResultResponse.success(result);
    }
}
