package kr.co.hanip.store;

import kr.co.hanip.store.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {
    int save(StorePostDto req); // 가게등록
    List<StoreGetListRes> findAllStore(StoreGetListReq req); // 가게전체조회 & 검색
    StoreGetRes findStoreByStoreId(int storeId); // 가게상세조회
    int modifyStoreByUserId(StorePutDto dto); // 가게수정
    int deleteStoreByStoreIdAndUserId(StoreDeleteDto req); // 가게삭제
}
