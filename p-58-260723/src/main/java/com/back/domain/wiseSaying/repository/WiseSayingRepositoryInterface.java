package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.dto.PageDto;
import com.back.domain.wiseSaying.entity.WiseSaying;

import java.util.List;
import java.util.Optional;

public interface WiseSayingRepositoryInterface {

    public WiseSaying save(WiseSaying wiseSaying);

    public List<WiseSaying> findListDesc();

    public boolean delete(int id);

    public Optional<WiseSaying> findById(int id);

    public PageDto findByContentContaining(String keyword, int pageSize, int page);

    public PageDto findByAuthorContaining(String keyword, int pageSize, int page);

}
