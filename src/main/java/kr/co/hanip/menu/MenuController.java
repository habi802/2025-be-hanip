package kr.co.hanip.menu;

import kr.co.hanip.menu.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/{storeId}")
    public ResponseEntity<?> memoGetting(@PathVariable int storeId) {
        List<MenuGetListRes> result = menuService.menuGetList(storeId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping
    public ResponseEntity<?> memoOneGetting(@RequestParam int menuId) {
        MenuGetRes result = menuService.menuGetOne(menuId);

        return ResponseEntity.ok().body(result);
    }

    @PutMapping
    public ResponseEntity<?> memoPutting(@RequestBody MenuPutReq req){
        int result = menuService.menuPut(req);

        return  ResponseEntity.ok().body(result);
    }
    @DeleteMapping
    public ResponseEntity<?> memoDeleting(@RequestParam int menuId) {
        int result = menuService.menuDelete(menuId);
        return  ResponseEntity.ok().body(result);
    }

}
