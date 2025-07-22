package kr.co.hanip.store;

import kr.co.hanip.store.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {
    int save(StorePostDto req); // 가게등록
    List<StoreGetListRes> findAllOrderByStoreId(StoreGetListReq req); // 가게전체조회 & 검색
    StoreGetRes findByStoreId(int storeId); // 가게상세조회
    Integer findStoreIdByUserId(int userId);
    int updateByUserId(StorePutDto dto); // 가게수정
    int updateIsActiveByStoreIdAndUserId(int storeId, int userId); // 가게활성화
    int deleteByStoreIdAndUserId(StoreDeleteDto req); // 가게삭제
}
