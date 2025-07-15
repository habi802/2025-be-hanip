package kr.co.hanip.store;

import kr.co.hanip.store.model.StorePostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreMapper {
    int save(StorePostReq req);
}
