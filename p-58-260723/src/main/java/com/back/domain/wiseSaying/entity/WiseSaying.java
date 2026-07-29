package com.back.domain.wiseSaying.entity;

import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class WiseSaying {
    private int id;
    private String saying;
    private String author;

    public WiseSaying(String saying, String author) {
        this.saying = saying;
        this.author = author;
    }
    public boolean isNew() {
        return id == 0;
    }

    public Map<String,Object> toMap(){
        Map<String , Object> map = new LinkedHashMap<>();
        map.put("id", id);
        map.put("content", saying);
        map.put("author", author);

        return map;
    }
    public static WiseSaying fromMap(Map<String, Object> map){
        int id = (int)map.get("id");
        String content = (String)map.get("content");
        String author = (String)map.get("author");
        return new WiseSaying(id, content, author);
    }
}
