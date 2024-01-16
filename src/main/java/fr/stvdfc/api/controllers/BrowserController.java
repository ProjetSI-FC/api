package fr.stvdfc.api.controllers;

import java.util.List;
import java.util.TreeSet;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.stvdfc.api.interfaces.SearchMapData;
import fr.stvdfc.api.services.SearchEngine;

@RestController
@RequestMapping("/browser")
@CrossOrigin(origins = "http://localhost:4200")
public class BrowserController {
    
    @GetMapping("/keywords")
    public List<String> getAllKeyWords() {
        return SearchEngine.getAllKeyWords();
    }

    @GetMapping("/search")
    public SearchMapData search(@RequestParam List<String> keywords) {
        SearchMapData data = SearchEngine.getDataFromKeywords(new TreeSet<String>(keywords));
        return data;
    }
}
