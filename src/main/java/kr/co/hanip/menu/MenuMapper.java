package kr.co.hanip.menu;

import kr.co.hanip.menu.model.MenuPostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper {
    int menuPost(MenuPostReq req);

}
