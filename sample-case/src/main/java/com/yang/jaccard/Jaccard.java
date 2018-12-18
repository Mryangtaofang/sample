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
//        props.put("annotators", "tokenize, ssplit, pos, lemma");   //分词、分句、词性标注和次元信息。
//        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        StanfordCoreNLP pipeline = new StanfordCoreNLP("StanfordCoreNLP-chinese.properties");
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
        String text1 = " 这里面涉及到一种情况，如果B已经告诉A自己的缓冲区已满，于是A停止发送数据；等待一段时间后，B的缓冲区出现了富余，于是给A发送报文告诉A我的rwnd大小为400，但是这个报文不幸丢失了，于是就出现A等待B的通知||B等待A发送数据的死锁状态。为了处理这种问题，TCP引入了持续计时器（Persistence timer），当A收到对方的零窗口通知时，就启用该计时器，时间到则发送一个1字节的探测报文，对方会在此时回应自身的接收窗口大小，如果结果仍未0，则重设持续计时器，继续等待。\n" +
                "2. 传递效率\n" +
                "     一个显而易见的问题是：单个发送字节单个确认，和窗口有一个空余即通知发送方发送一个字节，无疑增加了网络中的许多不必要的报文（请想想为了一个字节数据而添加的40字节头部吧！），所以我们的原则是尽可能一次多发送几个字节，或者窗口空余较多的时候通知发送方一次发送多个字节。对于前者我们广泛使用Nagle算法，即：\n" +
                "*1. 若发送应用进程要把发送的数据逐个字节地送到TCP的发送缓存，则发送方就把第一个数据字节先发送出去，把后面的字节先缓存起来；\n" +
                "*2. 当发送方收到第一个字节的确认后（也得到了网络情况和对方的接收窗口大小），再把缓冲区的剩余字节组成合适大小的报文发送出去；\n" +
                "*3. 当到达的数据已达到发送窗口大小的一半或以达到报文段的最大长度时，就立即发送一个报文段；\n" +
                "     对于后者我们往往的做法是让接收方等待一段时间，或者接收方获得足够的空间容纳一个报文段或者等到接受缓存有一半空闲的时候，再通知发送方发送数据";
        String text2 = " 传递效率\n" +
                "     一个显而易见的问题是：单个发送字节单个确认，和窗口有一个空余即通知发送方发送一个字节，无疑增加了网络中的许多不必要的报文（请想想为了一个字节数据而添加的40字节头部吧！），所以我们的原则是尽可能一次多发送几个字节，或者窗口空余较多的时候通知发送方一次发送多个字节。对于前者我们广泛使用Nagle算法，即：\n" +
                "*1. 若发送应用进程要把发送的数据逐个字节地送到TCP的发送缓存，则发送方就把第一个数据字节先发送出去，把后面的字节先缓存起来；\n" +
                "*2. 当发送方收到第一个字节的确认后（也得到了网络情况和对方的接收窗口大小），再把缓冲区的剩余字节组成合适大小的报文发送出去；\n" +
                "*3. 当到达的数据已达到发送窗口大小的一半或以达到报文段的最大长度时，就立即发送一个报文段；";
//        String text1 = "Gridspot can link up idle computers instances across the world to provide large scale efforts with the computing power they require at affordable prices 0103 centsCPU hour These Linux instances run Ubuntu inside a virtual machine You are able to bid on access to these instances and specify the requirements of your tasks or jobs When your bid is fulfilled you can start running the instances using SSH anywhere youd like There are grant options available to defray costs for researchers and nonprofits The Gridspot API allows you to manage instances and identify new ones You can list available instances access them and stop the instances if you so choose Each API call requires an API key that can be generated from your account page";
//        String text2 = "Chapoo is a cloudbased platform for collaboration and project information management The service allows project managers designers engineers and other contributors to improve productivity through better collaboration communication and coordination Chapoo offers a fullfeatured RESTful API for live queries and reports Example functions include getting items contained in a binder getting the contact image of a contact getting a list of forms from a folder and creating new folders Results are returned in JSON format";
        System.out.println(getSimilarity(text1,text2));
        System.out.println(getSimilarity(text1,text1));
    }
}
