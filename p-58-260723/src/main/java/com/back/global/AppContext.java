package com.back.global;

import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.controller.WiseSayingController;
import com.back.domain.wiseSaying.repository.WiseSayingFileRepository;
import com.back.domain.wiseSaying.repository.WiseSayingMemRepository;
import com.back.domain.wiseSaying.repository.WiseSayingRepositoryInterface;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.Scanner;

public class AppContext {

    public static Scanner sc;
    public static SystemController systemController;
    public static WiseSayingController wiseSayingController;
    public static WiseSayingService wiseSayingService;
    public static WiseSayingRepositoryInterface wiseSayingRepositoryInterface;
    public static WiseSayingMemRepository wiseSayingMemRepository;
    public static WiseSayingFileRepository wiseSayingFileRepository;

    public static void init(Scanner _sc, boolean isFileMode) { //테스트용 스캐너
        AppContext.sc = _sc;
        AppContext.wiseSayingFileRepository = new WiseSayingFileRepository();
        AppContext.wiseSayingMemRepository = new WiseSayingMemRepository();
        AppContext.wiseSayingRepositoryInterface = isFileMode ?
                new WiseSayingFileRepository() : new WiseSayingMemRepository();
        AppContext.wiseSayingService = new WiseSayingService();
        AppContext.wiseSayingController = new WiseSayingController();
        AppContext.systemController = new SystemController();
    }
//앱 스캐너
    public static void init(boolean isFileMode) {
        //실제 앱에 사용될 스캐너
        init(new Scanner(System.in), isFileMode);
    }


}
