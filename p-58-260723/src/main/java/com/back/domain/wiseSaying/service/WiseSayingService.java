package com.back.domain.wiseSaying.service;

import com.back.domain.wiseSaying.dto.PageDto;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepositoryInterface;
import com.back.global.AppContext;

import java.util.Optional;

public class WiseSayingService {

    private WiseSayingRepositoryInterface wiseSayingRepository;

    public WiseSayingService() {
        this.wiseSayingRepository = AppContext.wiseSayingMemRepository;
    }

    public WiseSaying write(String saying, String author) {
        WiseSaying wiseSaying = new WiseSaying(saying, author);
        wiseSayingRepository.save(wiseSaying);

        return wiseSaying;
    }

    public PageDto findListDesc(String keywordType, String keyword, int pageSize, int page) {

        if(keywordType.equals("content")) {
            return wiseSayingRepository.findByContentContaining(keyword, pageSize, page);

        } else {
            return wiseSayingRepository.findByAuthorContaining(keyword, pageSize, page);
        }

    }
    public boolean delete(int id) {
        return wiseSayingRepository.delete(id);
    }

    public Optional<WiseSaying> findById(int id) {

        return wiseSayingRepository.findById(id);

    }

    public void modify(WiseSaying wiseSaying, String newSaying, String newAuthor) {
        wiseSaying.setSaying(newSaying);
        wiseSaying.setAuthor(newAuthor);
        wiseSayingRepository.save(wiseSaying);
    }
}
