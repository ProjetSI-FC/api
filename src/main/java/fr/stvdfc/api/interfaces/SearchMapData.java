package fr.stvdfc.api.interfaces;

import java.util.Set;
import java.util.SortedSet;

public interface SearchMapData {
    
    SortedSet<String> getKeywordsSet();
    Set<FileMetadataWithScore> getFilesSet();
}
