package fr.stvdfc.api.importjson;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.SortedSet;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.stvdfc.api.interfaces.FileMetadataWithScore;
import fr.stvdfc.api.models.KeywordSearchHashMap;
import fr.stvdfc.api.models.SimpleFileMetadataWithScore;



public class Import {

    public static KeywordSearchHashMap importHashMapFromJson(String path){

        JSONArray array = null;
        try (InputStream fileReader = new FileInputStream(path)) {
            JSONTokener tokener = new JSONTokener(fileReader);
            JSONObject obj = new JSONObject(tokener);
            array = obj.getJSONArray("hashObject");
        } catch (IOException e) {
            e.printStackTrace();
  
        }
        KeywordSearchHashMap hashMap = new KeywordSearchHashMap();
        for (int i=0; i < array.length(); i++){
            JSONObject obj = array.getJSONObject(i);
            JSONArray keywordsArray = obj.getJSONArray("keywords");
            SortedSet<String> keywords = getKeywordsFromJSON(keywordsArray);
            JSONArray resultArray = obj.getJSONArray("results");
            for(int j=0 ; j < resultArray.length(); j++){
                FileMetadataWithScore fileMetadata = getFileMetadataFromJSON(resultArray.getJSONObject(j));
                hashMap.add(keywords,fileMetadata);
            }
        }
        return hashMap;
    }
    private static SortedSet<String> getKeywordsFromJSON(JSONArray array){
        SortedSet<String> keywords = new TreeSet<>();
        for(int i = 0; i < array.length(); i++){
            String keyword = array.getString(i);
            keywords.add(keyword);
        }
        return keywords;
    }
    private static FileMetadataWithScore getFileMetadataFromJSON(JSONObject obj){
        SimpleFileMetadataWithScore file = new SimpleFileMetadataWithScore();
        file.setScore(obj.getInt("score"));
        JSONObject metadata = obj.getJSONObject("metadata");
        file.addMetadata(metadata.getString("hashkey"), metadata.getString("value"));
        return file;
    }
}
