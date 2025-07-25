package kr.co.hanip.store;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.hanip.common.util.MyFileUtils;
import kr.co.hanip.store.model.*;
import kr.co.hanip.user.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreMapper storeMapper;
    private final UserMapper userMapper;
    private final MyFileUtils myFileUtils;

//    public int saveStore(StorePostReq req) {
//    }

    // 가게 리스트 조회 (비로그인)
    public List<StoreGetListRes> findAllStore(StoreGetListReq req) {
        return storeMapper.findAllStore(req);
    }

    // 가게 상세 조회
    public StoreGetRes findStore(int storeId) {
        return storeMapper.findByStoreId(storeId);
    }

    // 사장 가게 상세 조회
    public StoreGetDto findByUserId(int userId) {
        return storeMapper.findByUserId(userId);
    }

    // 가게 수정
    @Transactional
    public int modifyStore(MultipartFile img, StorePutReq req, int userId) {
        String encodePw = userMapper.findPasswordByUserId(userId);
        if (!BCrypt.checkpw(req.getPassword(), encodePw)) {
            return 0;
        } else {
            String savedFileName = null;
            if(img != null && !img.isEmpty()) {
                savedFileName = myFileUtils.makeRandomFileName(img);
                String directoryPath = String.format("store-profile/%d", req.getStoreId());
                myFileUtils.makeFolders(directoryPath);

                String savedPathFileName = directoryPath + "/" + savedFileName;
                try {
                    myFileUtils.transferTo(img, savedPathFileName);
                } catch (IOException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
            StorePutDto storePutDto = StorePutDto.builder()
                    .userId(userId)
                    .storeId(req.getStoreId())
                    .category(req.getCategory())
                    .name(req.getName())
                    .comment(req.getComment())
                    .businessNumber(req.getBusinessNumber())
                    .licensePath(req.getLicensePath())
                    .postcode(req.getPostcode())
                    .address(req.getAddress())
                    .addressDetail(req.getAddressDetail())
                    .tel(req.getTel())
                    .ownerName(req.getOwnerName())
                    .imagePath(savedFileName)
                    .password(req.getPassword())
                    .phone(req.getPhone())
                    .email(req.getEmail())
                    .build();

                int result = storeMapper.updateByUserId(storePutDto);
                return result;
            }
    }

    // 가게 활성화
    public int modifyStoreActive(int storeId, int userId) {
        return storeMapper.updateIsActiveByStoreIdAndUserId(storeId, userId);
    }

    // 가게 지우기
    public int removeStore(StoreDeleteReq req, int userId) {
        String encodePw = userMapper.findPasswordByUserId(userId);
        StoreDeleteDto storeDeleteDto = new StoreDeleteDto(req.getStoreId(), userId, req.getPassword());
        if (!BCrypt.checkpw(req.getPassword(), encodePw)) {
            return 0;
        } else {
            return storeMapper.deleteByStoreIdAndUserId(storeDeleteDto);
        }
    }

}
