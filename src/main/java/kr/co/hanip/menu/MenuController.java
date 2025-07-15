package kr.co.hanip.menu;

import kr.co.hanip.menu.model.MenuPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/menu")
@RestController
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;


    @PostMapping
    public ResponseEntity<?> memoPosting(@RequestBody MenuPostReq req){
        int result = menuService.memoPosting(req);
        log.info(req.toString());
        return ResponseEntity.ok().body(result);
    }

}
