package fr.stvdfc.api.controllers;

import java.util.List;
import java.util.TreeSet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.stvdfc.api.interfaces.SearchMapData;
import fr.stvdfc.api.services.SearchEngine;

@RestController
@RequestMapping("/browser")
public class BrowserController {
    
    @GetMapping("/keywords")
    public List<String> getAllKeyWords() {
        return SearchEngine.getAllKeyWords();
    }

    @GetMapping("/search")
    public SearchMapData search(@RequestBody List<String> keywords) {
        SearchMapData data = SearchEngine.getDataFromKeywords(new TreeSet<String>(keywords));
        return data;
    }
}
