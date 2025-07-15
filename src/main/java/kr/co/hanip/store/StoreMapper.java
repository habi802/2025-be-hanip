package kr.co.hanip.store;

import kr.co.hanip.store.model.StoreGetRes;
import kr.co.hanip.store.model.StoreListReq;
import kr.co.hanip.store.model.StoreListRes;
import kr.co.hanip.store.model.StorePostDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {
    int save(StorePostDto req);
    List<StoreListRes> findAllStore(StoreListReq req);
    StoreGetRes findStoreById(int storeId);
}
