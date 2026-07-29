package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.dto.PageDto;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.global.AppConfig;
import com.back.global.Util;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WiseSayingFileRepository implements WiseSayingRepositoryInterface {
    private static final String DB_PATH = "/db/wiseSaying";

    public static void clear() {
        Util.file.delete(getDbPath());
    }

    private static String getDbPath(){
        return AppConfig.getMode() + DB_PATH;
    }
    private String getFilePath(int id) {

        return getDbPath() + "/%d.json".formatted(id);
    }

    private String getLastIdPath() {
        return getDbPath() + "/lastId.txt";
    }


    public Optional<WiseSaying> findById(int id) {
        String jsonStr = Util.file.get(getFilePath(id), "");

        if(jsonStr.isEmpty()) return  Optional.empty();

        Map<String, Object> wiseSayingMap = Util.json.toMap(jsonStr);
        WiseSaying wiseSaying = new WiseSaying();
        WiseSaying wiseSaying1 = wiseSaying.fromMap(wiseSayingMap);

        return Optional.of(wiseSaying1);
    }

    public PageDto findByContentContaining(String keyword, int pageSize, int page) {
        return null;
    }

    public PageDto findByAuthorContaining(String keyword, int pageSize, int page) {
        return null;
    }

    private void incrementLastId() {
        Util.file.set(getLastIdPath(), String.valueOf(getLastId() + 1));
    }

    public boolean delete(WiseSaying wiseSaying) {
        return Util.file.delete(getFilePath(wiseSaying.getId()));
    }
    public boolean delete(int id) {
        return false;
    }
    public PageDto findByContentContainingIdDesc(String kw, int pageSize, int pageNo) {

        List<WiseSaying> filteredWiseSayings = findAll().stream()
                .filter(wiseSaying -> wiseSaying.getSaying().contains(kw))
                .sorted(Comparator.comparing(WiseSaying::getId).reversed())
                .toList();

        return pageOf(filteredWiseSayings, pageNo, pageSize);
    }

    private PageDto pageOf(List<WiseSaying> filteredContent, int pageNo, int pageSize) {

        List<WiseSaying> content = filteredContent.stream()
                .skip((pageNo-1) * pageSize)
                .limit(pageSize)
                .toList();

        int totalItems = filteredContent.size();
        return new PageDto(pageNo, pageSize, totalItems, content);
    }
    private int getLastId() {
        return Util.file.getAsInt(getLastIdPath(), 0);
    }

    public WiseSaying save(WiseSaying wiseSaying) {

        if(wiseSaying.isNew()) {

            incrementLastId();
            int lastId = getLastId();
            wiseSaying.setId(lastId);
        }

        // wiseSaying -> map
        Map<String, Object> wiseSayingMap = wiseSaying.toMap();

        // map -> json
        String jsonStr = Util.json.toString(wiseSayingMap);

        // 파일로 생성/저장
        Util.file.set(getFilePath(wiseSaying.getId()), jsonStr);


        return wiseSaying;
    }



    public PageDto findByAuthorContainingIdDesc(String kw, int pageSize, int pageNo) {
        List<WiseSaying> filteredWiseSayings = findAll().stream()
                .filter(wiseSaying -> wiseSaying.getAuthor().contains(kw))
                .sorted(Comparator.comparing(WiseSaying::getId).reversed())
                .toList();

        return pageOf(filteredWiseSayings, pageNo, pageSize);
    }
    public List<WiseSaying> findAll() {
        return Util.file.walkRegularFiles(getDbPath(), "^\\d+\\.json$")
                .map(path -> Util.file.get(path.toString(), ""))
                .map(Util.json::toMap)
                .map(WiseSaying::fromMap)
                .toList();
    }
    public List<WiseSaying> findListDesc() {
        return findAll().reversed();
    }

    public PageDto findByContentContainingOrAuthorContainingIdDesc(String kw, int pageSize, int pageNo) {
        List<WiseSaying> filteredWiseSayings = findAll().stream()
                .filter(wiseSaying -> wiseSaying.getAuthor().contains(kw) || wiseSaying.getSaying().contains(kw))
                .sorted(Comparator.comparing(WiseSaying::getId).reversed())
                .toList();

        return pageOf(filteredWiseSayings, pageNo, pageSize);
    }


}