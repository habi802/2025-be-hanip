package kr.co.hanip.favorite;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.hanip.common.model.ResultResponse;
import kr.co.hanip.common.util.HttpUtils;
import kr.co.hanip.favorite.model.FavoriteGetRes;
import kr.co.hanip.favorite.model.FavoritePostReq;
import kr.co.hanip.user.etc.UserConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping
    public ResultResponse<Integer> save(@RequestBody FavoritePostReq req, HttpServletRequest httpReq) {
        Integer userId = (Integer) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        if (userId == null) {
            return ResultResponse.success(null);
        }

        req.setUserId(userId);
        int result = favoriteService.save(req);
        return ResultResponse.success(result);
    }

    @GetMapping
    public ResultResponse<List<FavoriteGetRes>> findAll(HttpServletRequest httpReq) {
        int userId = (int) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        return ResultResponse.success(favoriteService.findAll(userId));
    }

    @GetMapping("/{store_id}")
    public ResultResponse<Integer> find(HttpServletRequest httpReq, @PathVariable("store_id") int storeId) {
        Integer userId = (Integer) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        if (userId == null) {
            return ResultResponse.success(null);
        }

        return ResultResponse.success(favoriteService.find(storeId, userId));
    }

    @DeleteMapping("/{store_id}")
    public ResultResponse<Integer> delete(HttpServletRequest httpReq, @PathVariable("store_id") int storeId) {
        int userId = (int) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        return ResultResponse.success(favoriteService.delete(userId, storeId));
    }
}


