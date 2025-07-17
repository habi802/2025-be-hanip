package kr.co.hanip.menu;

import kr.co.hanip.menu.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    int menuPost(MenuPostReq req);
    int menuModify(MenuPutReq req);
    int menuDelete(MenuDeleteReq req);
    List<MenuGetListRes> menuGetList(int storeId);
    MenuGetRes menuGetOne(int menuId);

}
