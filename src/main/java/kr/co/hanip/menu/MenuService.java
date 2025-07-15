package kr.co.hanip.menu;

import kr.co.hanip.menu.model.MenuPostReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuMapper menuMapper;

    public int memoPosting(MenuPostReq req){
//        MenuPostReq req2 = new MenuPostReq();
//        req2.setStoreId();
        return menuMapper.menuPost(req);
    }


}
