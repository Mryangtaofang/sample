package com.yang.jaccard;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class Jaccard {

    public static double getSimilarity(String document1, String document2) {
        //获取词干对应的文本，封装成集合
        List<String> wordslist1 = getlema(document1);
        List<String> wordslist2 = getlema(document2);
        Set<String> words2Set = new HashSet<>();
        words2Set.addAll(wordslist2);
        //求交集
        Set<String> intersectionSet = new ConcurrentSkipListSet<>();
        wordslist1.parallelStream().forEach(word -> {
            if (words2Set.contains(word)) {
                intersectionSet.add(word);
            }
        });
        //交集的大小
        int intersectionSize = intersectionSet.size();
        //求并集
        Set<String> unionSet = new HashSet<>();
        wordslist1.forEach(word -> unionSet.add(word));
        wordslist2.forEach(word -> unionSet.add(word));
        //并集的大小
        int unionSize = unionSet.size();
        //相似度分值
        double score = intersectionSize / (double) unionSize;
        return score;
    }
    
    public static List<String> getlema(String text){
        //词干对应的单词集合
        List<String> wordslist = new ArrayList<>();;
        //StanfordCoreNLP获取词干
        Properties props = new Properties();  // set up pipeline properties
        props.put("annotators", "tokenize, ssplit, pos, lemma");   //分词、分句、词性标注和次元信息。
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation document = new Annotation(text);
        pipeline.annotate(document);
        List<CoreMap> words = document.get(CoreAnnotations.SentencesAnnotation.class);
        for(CoreMap word_temp: words) {
            for (CoreLabel token: word_temp.get(CoreAnnotations.TokensAnnotation.class)) {
                String lema = token.get(CoreAnnotations.LemmaAnnotation.class);  // 获取对应上面word的词元信息，即我所需要的词形还原后的单词
                wordslist.add(lema);
//                System.out.println(lema);
            }
        }
        return wordslist;
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
//      BufferedReader reader = new BufferedReader( new InputStreamReader( new FileInputStream( new File("")),"gbk"));

        String text1 = "Gridspot can link up idle computers instances across the world to provide large scale efforts with the computing power they require at affordable prices 0103 centsCPU hour These Linux instances run Ubuntu inside a virtual machine You are able to bid on access to these instances and specify the requirements of your tasks or jobs When your bid is fulfilled you can start running the instances using SSH anywhere youd like There are grant options available to defray costs for researchers and nonprofits The Gridspot API allows you to manage instances and identify new ones You can list available instances access them and stop the instances if you so choose Each API call requires an API key that can be generated from your account page";
        String text2 = "Chapoo is a cloudbased platform for collaboration and project information management The service allows project managers designers engineers and other contributors to improve productivity through better collaboration communication and coordination Chapoo offers a fullfeatured RESTful API for live queries and reports Example functions include getting items contained in a binder getting the contact image of a contact getting a list of forms from a folder and creating new folders Results are returned in JSON format";
        System.out.println(getSimilarity(text1,text2));
        System.out.println(getSimilarity(text1,text1));
    }
}
