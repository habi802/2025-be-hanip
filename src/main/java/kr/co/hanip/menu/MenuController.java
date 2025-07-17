package kr.co.hanip.menu;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.hanip.common.model.ResultResponse;
import kr.co.hanip.common.util.HttpUtils;
import kr.co.hanip.menu.model.*;
import kr.co.hanip.user.etc.UserConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RequestMapping("/api/menu")
@RestController
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;


    @PostMapping
    public ResultResponse<Integer> menuPosting(HttpServletRequest httpReq,@RequestBody MenuPostReq req){
        int logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);

        int result = menuService.memoPosting(req,logginedMemberId);
        log.info(req.toString());
        if(result == 0){
            return ResultResponse.fail(400,"메뉴 등록 실패");
        }
        return ResultResponse.success(result);
    }

    @GetMapping("/{storeId}")
    public ResultResponse<List<MenuGetListRes>> menuGetting(@PathVariable int storeId) {
        List<MenuGetListRes> result = menuService.menuGetList(storeId);
        if(result == null || result.size() == 0){
            return ResultResponse.fail(400,"메뉴 리스트 조회 실패");
        }


        return ResultResponse.success(result);


    }

    @GetMapping
    public ResultResponse<MenuGetRes> menuOneGetting(@RequestParam int menuId) {
        MenuGetRes result = menuService.menuGetOne(menuId);

        if(result == null){
            return ResultResponse.fail(400,"메뉴 조회 실패");
        }

        return ResultResponse.success(result);
    }

    @PutMapping
    public ResultResponse<Integer> memoPutting(HttpServletRequest httpReq,@RequestBody MenuPutReq req){
        Integer logginedMemberId = (Integer) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        int result = menuService.menuPut(req,logginedMemberId);
        if(result == 0|| logginedMemberId == null){
            return ResultResponse.fail(400,"메뉴 수정 실패");
        }

        return  ResultResponse.success(result);
    }
    @DeleteMapping("/{menuId}")
    public ResultResponse<Integer> memoDeleting(HttpServletRequest httpReq,@PathVariable int menuId) {
        int logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, UserConstants.LOGGED_IN_USER_ID);
        MenuDeleteReq req = new MenuDeleteReq(menuId,logginedMemberId);
        int result = menuService.menuDelete(req);
        if(result == 0 || logginedMemberId == 0 ){
            return ResultResponse.fail(400,"메뉴 삭제 실패");
        }

        return  ResultResponse.success(result);
    }

}
