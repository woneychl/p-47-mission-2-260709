package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.dto.PageDto;
import com.back.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WiseSayingMemRepository implements WiseSayingRepositoryInterface {

    private List<WiseSaying> wiseSayings = new ArrayList<>();
    private int lastId = 0;

    public WiseSaying save(WiseSaying wiseSaying) {
        if(wiseSaying.isNew()) {
            wiseSaying.setId(++lastId);
            wiseSayings.add(wiseSaying);
        }

        return wiseSaying;
    }

    public List<WiseSaying> findListDesc() {
        return wiseSayings.reversed();
    }
    public boolean delete(int id) {
        return wiseSayings.removeIf(wiseSaying -> wiseSaying.getId() == id);

    }
    public  Optional<WiseSaying> findById(int id) {

        for (WiseSaying wiseSaying : wiseSayings) {
            if (wiseSaying.getId() == id) {
                return Optional.of(wiseSaying);
            }
        }

        return Optional.empty();
    }
    public PageDto findByContentContaining(String keyword, int pageSize, int page) {
        List<WiseSaying> result =  wiseSayings
                .reversed()
                .stream()
                .filter(
                        w -> w.getSaying().contains(keyword)
                )
                .toList();

        return pageOf(result, page, pageSize);
    }

    public PageDto findByAuthorContaining(String keyword, int pageSize, int page) {
        List<WiseSaying> result =  wiseSayings
                .reversed()
                .stream()
                .filter(
                        w -> w.getAuthor().contains(keyword)
                )
                .toList();

        return pageOf(result, page, pageSize);
    }
    private PageDto pageOf(List<WiseSaying> filteredContent, int pageNo, int pageSize) {

        List<WiseSaying> content = filteredContent.stream()
                .skip((pageNo-1) * pageSize)
                .limit(pageSize)
                .toList();


        int totalItems = filteredContent.size();
        return new PageDto(pageNo, pageSize, totalItems, content);
    }
}