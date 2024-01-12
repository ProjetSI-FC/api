package fr.stvdfc.api.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;

import org.springframework.boot.json.JsonParserFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


import fr.stvdfc.api.importjson.Import;
import fr.stvdfc.api.interfaces.SearchMapData;
import fr.stvdfc.api.models.KeywordSearchHashMap;
import fr.stvdfc.api.models.SimpleSearchMapData;

public class SearchEngine {

    private static List<String> keywords = null;
    private static KeywordSearchHashMap hashMap = null;

    private SearchEngine() {
        throw new IllegalStateException("Utility class");
    }

    public static List<String> getAllKeyWords() {
        if (keywords == null) {
            try {
                Resource resource = new ClassPathResource("keywords.json");
                String content = new String(resource.getInputStream().readAllBytes());
                JsonParser parser = JsonParserFactory.getJsonParser();
                List<Object> result = parser.parseList(content);
                keywords = new ArrayList<>(result.size());
                for (Object object : result) {
                    keywords.add((String) object);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Collections.unmodifiableList(keywords);
    }

    public static SearchMapData getDataFromKeywords(SortedSet<String> keywords) {
        if (hashMap == null) {
            try {
                hashMap = Import.importHashMapFromJson(new ClassPathResource("hashmap.json").getFile().getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        SearchMapData data = hashMap.get(keywords);

        if(data == null) {
            SimpleSearchMapData emptyData = new SimpleSearchMapData();
            emptyData.setKeywordsSet(keywords);
            return emptyData;
        } else {
            return data;
        }
    }
}
