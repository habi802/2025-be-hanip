package kr.co.hanip.menu;

import kr.co.hanip.menu.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuMapper menuMapper;

    public int memoPosting(MenuPostReq req, int logginedMemberId){
//        MenuPostReq req2 = new MenuPostReq();
//        req2.setStoreId();
        req.setUserId(logginedMemberId);
        return menuMapper.menuPost(req);
    }

    public List<MenuGetListRes> menuGetList(int storeId){
        return menuMapper.menuGetList(storeId);
    }
    public MenuGetRes menuGetOne(int menuId){
        return menuMapper.menuGetOne(menuId);
    }
    public int menuPut(MenuPutReq req, int logginedMemberId){
        req.setUserId(logginedMemberId);
        return menuMapper.menuModify(req);
    }
    public int menuDelete(MenuDeleteReq req){
        return menuMapper.menuDelete(req);
    }

}
