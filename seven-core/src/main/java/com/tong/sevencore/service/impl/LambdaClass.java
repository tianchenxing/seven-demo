package com.tong.sevencore.service.impl;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * @Author: SevenTian
 * @Description:
 * @Create_Date: 13:18 2019/6/25
 * @Modified_Author:
 * @Modified_Date:
 */
public class LambdaClass {
    public static  void  main(String[] args ){

        List<String> abList = getAllList();
        List<Integer> numList = Arrays.asList(1,2,3,4,5,6);
        //遍历
        List<String> collect =
                abList.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(collect);
        List<Integer> collect1 =
                numList.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println(collect1);
        //过滤
        List<Integer> collect2 =
                numList.stream().filter(n -> n + 5 > 10).
                        filter(n -> n < 30).collect(Collectors.toList());
        System.out.println(collect2);
        //对每个元素进行自定义操作
        abList.forEach(n->{
            if(n.equals("j")){
                abList.set(abList.indexOf(n),"coco");
            }
        });
        System.out.println(abList);
        //返回特定的结果集合，
        // limit 返回 Stream 的前面 n 个元素；
        // skip 则是扔掉前 n 个元素
        List<String> collect3 =
                abList.stream().limit(3).skip(1).collect(Collectors.toList());
        System.out.println(collect3);
        List<String> collect4 =
                abList.stream().limit(abList.size()-1).collect(Collectors.toList());
        System.out.println(collect4);
        //sort/min/max/distinct
        List<Integer> collect5 = numList.stream().sorted().collect(Collectors.toList());
        System.out.println(collect5);
        List<Integer> collect6 = numList.stream().sorted((n1, n2) ->
                n2 - n1).collect(Collectors.toList());
        System.out.println(collect6);
        Integer min = numList.stream().min(new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                return n1.compareTo(n2);
            }
        }).get();
        int max = numList.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        }).get();

        System.out.println(min+":"+max);
        long count = abList.stream().count();
        System.out.println(count);
        List<String> collect7 = abList.stream().distinct().collect(Collectors.toList());
        System.out.println(collect7);
//        allMatch：Stream 中全部元素符合传入的 predicate，返回 true
//        anyMatch：Stream 中只要有一个元素符合传入的 predicate，返回 true
//        noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true
        System.out.println(abList.stream().anyMatch(n -> n.contains("a")));
        System.out.println(numList.stream().allMatch(n->n>10));
        System.out.println(numList.stream().noneMatch(n->n<100));
        //21
        Integer reduce = numList.stream().reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer n1, Integer n2) {
                return n1 + n2;
            }
        }).get();
        System.out.println(reduce);
        //121
        Integer h =100;
        Integer reduce1 = numList.stream().reduce(h, new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer n1, Integer n2) {
                return n1 + n2;
            }
        });
        System.out.println(reduce1);
        //hhabcddefc
        String hh = "hh";
        System.out.println(abList.stream().reduce(hh,
                new BiFunction<String, String, String>() {
                    @Override
                    public String apply(String s, String s2) {
                        return s.concat(s2);
                    }
                },
                new BinaryOperator<String>() {
                    @Override
                    public String apply(String s, String s2) {
                        return s.concat(s2);
                    }
                }));
        //hhahhbhhchhdhhdhhehhfhhc
        System.out.println(abList.stream().parallel().reduce(hh,
                new BiFunction<String, String, String>() {
                    @Override
                    public String apply(String s, String s2) {
                        return s.concat(s2);
                    }
                },
                new BinaryOperator<String>() {
                    @Override
                    public String apply(String s, String s2) {
                        return s.concat(s2);
                    }
                }));
    }
     private static  List<String> getAllList(){
         List<String> bList = Arrays.asList("d","e","f","c");
         List<String> aList = Arrays.asList("a","b","c","d");
         List<String> abList = new ArrayList<String>();
         abList.addAll(aList);
         abList.addAll(bList);
         new HashMap<>();
         return abList;
     }
}
