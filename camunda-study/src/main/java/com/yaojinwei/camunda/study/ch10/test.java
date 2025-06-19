package com.yaojinwei.camunda.study.ch10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yao Jinwei (jinwei.yjw@alibaba-inc.com)
 */
public class test {
    public static void main(String[] args) {
        List<Song> input = new ArrayList<>();

        Song song1 = new Song(2);
        input.add(song1);
        Song song2 = new Song(1);
        Song song3 = new Song(3);
        Song song4 = new Song(4);
        Song song5 = new Song(6);
        Song song6 = new Song(5);
        Song song7 = new Song(8);
        Song song8 = new Song(7);
        input.add(song2);
        input.add(song3);
        input.add(song4);
        input.add(song5);
        input.add(song6);
        input.add(song7);
        input.add(song8);
        List<Integer> ids = new ArrayList<>();
        ids.add(3);
        ids.add(1);
        ids.add(2);
        ids.add(5);
        ids.add(9);
        Collection<Song> rank = rank(input, ids);
        for(Song song :rank){
            System.out.println(song.getId());
        }
    }
    static Collection<Song> rank(List<Song> songs, List<Integer> ids){
        Map<Integer, Song> songMap = new HashMap<>();
        LinkedHashMap<Integer, Song> sorted = new LinkedHashMap<>();

        for(Song song:songs){
            songMap.put(song.getId(), song);
        }
        for(Integer id: ids){
            Song song = songMap.get(id);
            if(song != null){
                sorted.put(song.getId(), song);
            }
        }
        for(Song song:songs){
            if(!sorted.containsKey(song.getId())){
                sorted.put(song.getId(), song);
            }
        }

        return sorted.values();
    }


    static class Song{
        private Integer id;

        public Song(Integer id) {
            this.id = id;
        }

        private Integer getId(){
            return id;
        }
    }
}
