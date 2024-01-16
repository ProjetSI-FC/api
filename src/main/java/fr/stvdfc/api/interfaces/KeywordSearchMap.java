package fr.stvdfc.api.interfaces;

import java.util.SortedSet;

public interface KeywordSearchMap extends Iterable<SearchMapData> {
    SearchMapData get(SortedSet<String> keywords);
}
