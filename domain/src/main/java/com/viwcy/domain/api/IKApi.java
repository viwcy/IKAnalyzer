package com.viwcy.domain.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.dic.Dictionary;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;
import java.util.HashSet;

/**
 * TODO  Copyright (c) yun lu 2021 Fau (viwcy4611@gmail.com), ltd
 */
@RestController
@RequestMapping("/ik")
public class IKApi {

    public static void main(String[] args) {
        StringReader sr = new StringReader("IK Analyzer是一个结合词典分词和文法分词的中文分词开源工具包。它使用了全新的正向迭代最细粒度切分算法。1懂系统");
        IKSegmenter ik = new IKSegmenter(sr, true);
        Lexeme lex = null;
        while (true) {
            try {
                if (!((lex = ik.next()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(lex.getLexemeText());
        }
    }

    @PostMapping("/analysis")
    public Collection<String> analysis(@RequestParam("content") String content, @RequestParam("isMaxWordLength") Boolean isMaxWordLength) {
        StringReader reader = new StringReader(content);
        IKSegmenter ik = new IKSegmenter(reader, isMaxWordLength);
        Lexeme lex = null;
        HashSet<String> set = new HashSet<>();
        while (true) {
            try {
                if (!((lex = ik.next()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            set.add(lex.getLexemeText());
        }
        return set;
    }

    @PostMapping("/add")
    public String add(@RequestParam("keyword") String keyword) {
        Dictionary dictionary = Dictionary.getSingleton();
        dictionary.addWords(new HashSet<String>() {{
            add(keyword);
        }});
        return "OK";
    }
}
