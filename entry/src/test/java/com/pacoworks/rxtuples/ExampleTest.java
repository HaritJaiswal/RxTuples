/*
 * Copyright (c) pakoito 2015
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pacoworks.rxtuples;

import org.junit.Test;
import org.javatuples.Octet;
import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Quintet;
import org.javatuples.Septet;
import org.javatuples.Sextet;
import org.javatuples.Triplet;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

/** ExampleTest **/
public class ExampleTest {
    private final List<String> list1;
    private final List<Integer> list2;
    private final String firstValTest;
    private final String secondValTest;
    private final String thirdValTest;
    private final String fourthValTest;
    private final String fifthValTest;
    private final String sixthValTest;
    private final String seventhValTest;
    private final String eighthValTest;
    
    private int i;

    public ExampleTest(){
        i=0;
        firstValTest = "First value test";
        secondValTest = "Second value test";
        thirdValTest = "Third value test";
        fourthValTest = "Fourth value test";
        fifthValTest = "Fifth value test";
        sixthValTest = "Sixth value test";
        seventhValTest = "Seventh value test";
        eighthValTest = "Eighth value test";
        list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");

        list2 = new ArrayList<>();
        list2.add(4);
        list2.add(5);
        list2.add(6);
    }

    private void checkifEmpty(Observable result){
        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );
    }

    /** Pair **/
    @Test
    public void checkToPair(){
        i=0;
        Observable result = Observable.zip(Observable.from(list1), Observable.range(0, list1.size()),
                RxTuples.<String, Integer>toPair());

       checkifEmpty(result);

        result.subscribe(tmp -> {
            Pair<String,Integer> p = (Pair) tmp;
            assertEquals(firstValTest, list1.get(i), p.getValue0());
            assertEquals(secondValTest, (Integer) i,p.getValue1());
            i++;
        });

    }

    /** Triplet **/
    @Test
    public void checkToTriplet(){
        i=0;
        Observable result = Observable.zip(Observable.from(list1), Observable.range(0, list1.size()),
                Observable.from(list2), RxTuples.<String, Integer,Integer>toTriplet());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Triplet<String,Integer,Integer> trip = (Triplet) tmp;
            assertEquals(firstValTest, list1.get(i),trip.getValue0());
            assertEquals(secondValTest, (Integer) i,trip.getValue1());
            assertEquals(thirdValTest, list2.get(i),trip.getValue2());
            i++;
        });

    }

    @Test
    public void checkToTripletFromSingle(){
        List<Pair<Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Pair<>(1,2));
        Observable result = Observable.zip(Observable.just(0), Observable.from(tmp_list),
                RxTuples.<Integer, Integer,Integer>toTripletFromSingle());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Triplet<Integer,Integer,Integer> trip = (Triplet) tmp;
            assertEquals(firstValTest, (Integer) 0,trip.getValue0());
            assertEquals(secondValTest, tmp_list.get(0).getValue0(),trip.getValue1());
            assertEquals(thirdValTest, tmp_list.get(0).getValue1(),trip.getValue2());
        });

    }

    @Test
    public void checkToTripletFromPair(){
        List<Pair<Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Pair<>(1,2));
        Observable result = Observable.zip(Observable.from(tmp_list), Observable.just(0),
                RxTuples.<Integer, Integer,Integer>toTripletFromPair());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Triplet<Integer,Integer,Integer> trip = (Triplet) tmp;
            assertEquals(firstValTest, tmp_list.get(0).getValue0(),trip.getValue0());
            assertEquals(secondValTest, tmp_list.get(0).getValue1(),trip.getValue1());
            assertEquals(thirdValTest, (Integer)0,trip.getValue2());
            i++;
        });

    }


    /** Quartet **/
    @Test
    public void checkToQuartet(){
        i=0;
        Observable result = Observable.zip(Observable.from(list1), Observable.range(0, list1.size()),
                Observable.from(list2),Observable.range(0,list2.size()),
                RxTuples.<String, Integer, Integer, Integer>toQuartet());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Quartet<String,Integer,Integer,Integer> qt = (Quartet) tmp;
            assertEquals(firstValTest, list1.get(i), qt.getValue0());
            assertEquals(secondValTest, (Integer) i,qt.getValue1());
            assertEquals(thirdValTest,list2.get(i),qt.getValue2());
            assertEquals(fourthValTest,(Integer) i,qt.getValue3());
            i++;
        });

    }

    @Test
    public void checkToQuartetFromSingle(){
        List<Triplet<Integer, Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Triplet<>(1,2,3));
        Observable result = Observable.zip(Observable.just(0),Observable.from(tmp_list),
                RxTuples.<Integer,Integer, Integer,Integer>toQuartetFromSingle());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Quartet<String,Integer, String, String> qt = (Quartet) tmp;
            assertEquals(firstValTest, 0, qt.getValue0());
            assertEquals(secondValTest, tmp_list.get(0).getValue0(),qt.getValue1());
            assertEquals(thirdValTest,tmp_list.get(0).getValue1(),qt.getValue2());
            assertEquals(fourthValTest,tmp_list.get(0).getValue2(),qt.getValue3());
        });

    }

    @Test
    public void checkToQuartetFromPair(){
        List<Pair<Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Pair<>(0,1));

        Observable result = Observable.zip(Observable.from(tmp_list),Observable.just(new Pair<>(1,2)),
                RxTuples.<Integer,Integer, Integer, Integer> toQuartetFromPair());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Quartet<Integer,Integer, Integer, Integer> qt = (Quartet) tmp;
            assertEquals(firstValTest, tmp_list.get(0).getValue0(), qt.getValue0());
            assertEquals(secondValTest, tmp_list.get(0).getValue1(),qt.getValue1());
            assertEquals(thirdValTest,(Integer) 1,qt.getValue2());
            assertEquals(fourthValTest,(Integer) 2,qt.getValue3());
        });
    }

    @Test
    public void checkToQuartetFromTriplet(){
        List<Triplet<String, String,String>> tmp_list = new ArrayList<>();
        tmp_list.add(new Triplet<>("x","y","z"));

        Observable result = Observable.zip(Observable.from(tmp_list),Observable.just("a"),
                RxTuples.<String,String, String, String> toQuartetFromTriplet());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Quartet<String,String, String, String> qt = (Quartet) tmp;
            assertEquals(firstValTest, tmp_list.get(0).getValue0(), qt.getValue0());
            assertEquals(secondValTest, tmp_list.get(0).getValue1(),qt.getValue1());
            assertEquals(thirdValTest,tmp_list.get(0).getValue2(),qt.getValue2());
            assertEquals(fourthValTest,"a",qt.getValue3());
        });
    }

    /** Quintet **/
    @Test
    public void checkToQuintet(){
        i=0;
        Observable result = Observable.zip(Observable.from(list1),Observable.range(0,list1.size()),
                Observable.just("x"),Observable.from(list2),Observable.range(0,list2.size()),
                RxTuples.<String,Integer, String, Integer,Integer> toQuintet());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Quintet<String,Integer, String, Integer,Integer> qt = (Quintet) tmp;
            assertEquals(firstValTest, list1.get(i), qt.getValue0());
            assertEquals(secondValTest, (Integer)i,qt.getValue1());
            assertEquals(thirdValTest,"x",qt.getValue2());
            assertEquals(fourthValTest,list2.get(i),qt.getValue3());
            assertEquals(fifthValTest,(Integer)i,qt.getValue4());
            i++;
        });
    }

    @Test
    public void checkToQuintetFromSingle(){
        List<Quartet<String,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quartet<>("a",2,3,4));
        Observable result = Observable.zip(Observable.just(1),Observable.from(tmp_list),
                RxTuples.<String,Integer,Integer,Integer,Integer> toQuintetFromSingle());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Quintet<String,Integer,Integer,Integer,Integer> qt = (Quintet) tmp;
            assertEquals(firstValTest, 1, qt.getValue0());
            assertEquals(secondValTest, tmp_list.get(0).getValue0(),qt.getValue1());
            assertEquals(thirdValTest,tmp_list.get(0).getValue1(),qt.getValue2());
            assertEquals(fourthValTest,tmp_list.get(0).getValue2(),qt.getValue3());
            assertEquals(fifthValTest,tmp_list.get(0).getValue3(),qt.getValue4());
        });
    }

    @Test
    public void checkToQuintetFromPair(){
        List<Triplet<String, String,String>> tmp_list = new ArrayList<>();
        tmp_list.add(new Triplet<>("x","y","z"));

        Observable result = Observable.zip(Observable.just(new Pair<String,String>("a","b")), Observable.from(tmp_list),
                RxTuples.<String,String,String,String,String> toQuintetFromPair());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Quintet<String,String,String,String,String> qt = (Quintet) tmp;
            assertEquals(firstValTest, "a", qt.getValue0());
            assertEquals(secondValTest, "b",qt.getValue1());
            assertEquals(thirdValTest,tmp_list.get(0).getValue0(),qt.getValue2());
            assertEquals(fourthValTest,tmp_list.get(0).getValue1(),qt.getValue3());
            assertEquals(fifthValTest,tmp_list.get(0).getValue2(),qt.getValue4());
        });

    }

    @Test
    public void checkToQuintetFromTriplet(){
        List<Triplet<String, String,String>> tmp_list = new ArrayList<>();
        tmp_list.add(new Triplet<>("x","y","z"));

        Observable result = Observable.zip(Observable.from(tmp_list), Observable.just(new Pair<String,String>("a","b")),
                RxTuples.<String,String,String,String,String> toQuintetFromTriplet());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Quintet<String,String,String,String,String> qt = (Quintet) tmp;
            assertEquals(firstValTest, tmp_list.get(0).getValue0(), qt.getValue0());
            assertEquals(secondValTest, tmp_list.get(0).getValue1(),qt.getValue1());
            assertEquals(thirdValTest,tmp_list.get(0).getValue2(),qt.getValue2());
            assertEquals(fourthValTest,"a",qt.getValue3());
            assertEquals(fifthValTest,"b",qt.getValue4());
        });
    }

    @Test
    public void checkToQuintetFromQuartet(){
        List<Quartet<String,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quartet<>("a",2,3,4));
        Observable result = Observable.zip(Observable.from(tmp_list), Observable.just(5),
                RxTuples.<String,Integer,Integer,Integer,Integer> toQuintetFromQuartet());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Quintet<String,Integer,Integer,Integer,Integer> qt = (Quintet) tmp;
            assertEquals(firstValTest, tmp_list.get(0).getValue0(),qt.getValue0());
            assertEquals(secondValTest,tmp_list.get(0).getValue1(),qt.getValue1());
            assertEquals(thirdValTest,tmp_list.get(0).getValue2(),qt.getValue2());
            assertEquals(fourthValTest,tmp_list.get(0).getValue3(),qt.getValue3());
            assertEquals(fifthValTest,(Integer)5,qt.getValue4());
        });
    }

    /** Sextet **/
    @Test
    public void checkToSextet(){
        i=0;
        Observable result = Observable.zip(Observable.from(list1),Observable.range(0,list1.size()),Observable.from(list2),
                Observable.range(0,list2.size()),Observable.from(list1),Observable.range(0,list1.size()),
                RxTuples.<String,Integer,Integer,Integer,String,Integer> toSextet());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Sextet<String,Integer,Integer,Integer,String,Integer> st = (Sextet) tmp;
            assertEquals(firstValTest, list1.get(i),st.getValue0());
            assertEquals(secondValTest,(Integer) i,st.getValue1());
            assertEquals(thirdValTest,list2.get(i),st.getValue2());
            assertEquals(fourthValTest,(Integer) i,st.getValue3());
            assertEquals(fifthValTest,list1.get(i),st.getValue4());
            assertEquals(sixthValTest,(Integer)i,st.getValue5());
            i++;
        });
    }

    @Test
    public void checkToSextetFromSingle() {
        List<Quintet<Integer, Integer, Integer, Integer, Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quintet<>(1, 2, 3, 4, 5));

        Observable result = Observable.zip(Observable.just(0), Observable.from(tmp_list),
                RxTuples.<Integer, Integer, Integer, Integer, Integer, Integer>toSextetFromSingle());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Sextet<Integer, Integer, Integer, Integer, Integer, Integer> st = (Sextet) tmp;
            assertEquals(firstValTest, (Integer) 0,st.getValue0());
            assertEquals(secondValTest,tmp_list.get(0).getValue0(),st.getValue1());
            assertEquals(thirdValTest,tmp_list.get(0).getValue1(),st.getValue2());
            assertEquals(fourthValTest,tmp_list.get(0).getValue2(),st.getValue3());
            assertEquals(fifthValTest,tmp_list.get(0).getValue3(),st.getValue4());
            assertEquals(sixthValTest,tmp_list.get(0).getValue4(),st.getValue5());
        });
    }

    @Test
    public void checkToSextetFromPair(){
        List<Quartet<Integer, Integer, Integer, Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quartet<>(1, 2, 3, 4));

        Observable result = Observable.zip(Observable.just(new Pair<String,String>("a","b")), Observable.from(tmp_list),
                RxTuples.<String,String, Integer, Integer, Integer, Integer>toSextetFromPair());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Sextet<String,String, Integer, Integer, Integer, Integer> st = (Sextet) tmp;
            assertEquals(firstValTest, "a",st.getValue0());
            assertEquals(secondValTest,"b",st.getValue1());
            assertEquals(thirdValTest,tmp_list.get(0).getValue0(),st.getValue2());
            assertEquals(fourthValTest,tmp_list.get(0).getValue1(),st.getValue3());
            assertEquals(fifthValTest,tmp_list.get(0).getValue2(),st.getValue4());
            assertEquals(sixthValTest,tmp_list.get(0).getValue3(),st.getValue5());
        });
    }

    @Test
    public void checkToSextetFromTriplet(){
        List<Triplet<Integer, Integer, Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Triplet<>(1, 2, 3));

        Observable result = Observable.zip(Observable.just(new Triplet<String,String,String>("a","b","c")), Observable.from(tmp_list),
                RxTuples.<String,String, String, Integer, Integer, Integer>toSextetFromTriplet());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Sextet<String,String, String, Integer, Integer, Integer> st = (Sextet) tmp;
            assertEquals(firstValTest, "a",st.getValue0());
            assertEquals(secondValTest,"b",st.getValue1());
            assertEquals(thirdValTest,"c",st.getValue2());
            assertEquals(fourthValTest,tmp_list.get(0).getValue0(),st.getValue3());
            assertEquals(fifthValTest,tmp_list.get(0).getValue1(),st.getValue4());
            assertEquals(sixthValTest,tmp_list.get(0).getValue2(),st.getValue5());
        });
    }

    @Test
    public void checkToSextetFromQuartet(){
        List<Quartet<Integer, Integer, Integer, Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quartet<>(1, 2, 3, 4));

        Observable result = Observable.zip(Observable.from(tmp_list),
                Observable.just(new Pair<String,String>("a","b")),
                RxTuples.<Integer, Integer, Integer, Integer,String,String>toSextetFromQuartet());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Sextet<Integer, Integer, Integer, Integer,String,String> st = (Sextet) tmp;
            assertEquals(firstValTest,tmp_list.get(0).getValue0(),st.getValue0());
            assertEquals(secondValTest,tmp_list.get(0).getValue1(),st.getValue1());
            assertEquals(thirdValTest,tmp_list.get(0).getValue2(),st.getValue2());
            assertEquals(fourthValTest,tmp_list.get(0).getValue3(),st.getValue3());
            assertEquals(fifthValTest, "a",st.getValue4());
            assertEquals(sixthValTest,"b",st.getValue5());
        });
    }

    @Test
    public void checkToSextetFromQuintet(){
        List<Quintet<Integer, Integer, Integer, Integer, Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quintet<>(1, 2, 3, 4, 5));

        Observable result = Observable.zip(Observable.from(tmp_list),Observable.just(0),
                RxTuples.<Integer, Integer, Integer, Integer, Integer, Integer>toSextetFromQuintet());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Sextet<Integer, Integer, Integer, Integer, Integer, Integer> st = (Sextet) tmp;
            assertEquals(firstValTest,tmp_list.get(0).getValue0(),st.getValue0());
            assertEquals(secondValTest,tmp_list.get(0).getValue1(),st.getValue1());
            assertEquals(thirdValTest,tmp_list.get(0).getValue2(),st.getValue2());
            assertEquals(fourthValTest,tmp_list.get(0).getValue3(),st.getValue3());
            assertEquals(fifthValTest,tmp_list.get(0).getValue4(),st.getValue4());
            assertEquals(sixthValTest, (Integer) 0,st.getValue5());
        });
    }


    /** Septet **/
    @Test
    public void checkToSeptet(){
        i=0;
        Observable result = Observable.zip(Observable.from(list1),Observable.range(0,list1.size()),Observable.from(list2),
                Observable.range(0,list2.size()),Observable.from(list1),Observable.range(0,list1.size()),
                Observable.from(list2), RxTuples.<String,Integer,Integer,Integer,String,Integer,Integer> toSeptet());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Septet<String,Integer,Integer,Integer,String,Integer,Integer> st = (Septet) tmp;
            assertEquals(firstValTest, list1.get(i),st.getValue0());
            assertEquals(secondValTest,(Integer) i,st.getValue1());
            assertEquals(thirdValTest,list2.get(i),st.getValue2());
            assertEquals(fourthValTest,(Integer) i,st.getValue3());
            assertEquals(fifthValTest,list1.get(i),st.getValue4());
            assertEquals(sixthValTest,(Integer)i,st.getValue5());
            assertEquals(seventhValTest,list2.get(i),st.getValue6());
            i++;
        });
    }

    @Test
    public void checkToSeptetFromSingle(){
        List<Sextet<Integer,Integer,Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Sextet(1,2,3,4,5,6));
        Observable result = Observable.zip(Observable.just(0),Observable.from(tmp_list),
                RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer> toSeptetFromSingle());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Septet<Integer,Integer,Integer,Integer,Integer,Integer,Integer> st = (Septet) tmp;
            assertEquals(firstValTest, (Integer) 0,st.getValue0());
            assertEquals(secondValTest,tmp_list.get(0).getValue0(),st.getValue1());
            assertEquals(thirdValTest,tmp_list.get(0).getValue1(),st.getValue2());
            assertEquals(fourthValTest,tmp_list.get(0).getValue2(),st.getValue3());
            assertEquals(fifthValTest,tmp_list.get(0).getValue3(),st.getValue4());
            assertEquals(sixthValTest,tmp_list.get(0).getValue4(),st.getValue5());
            assertEquals(seventhValTest,tmp_list.get(0).getValue5(),st.getValue6());
        });
    }

    @Test
    public void checkToSeptetFromPair(){
        List<Quintet<Integer,Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quintet<>(2,3,4,5,6));
        Observable result = Observable.zip(Observable.just(new Pair<>(0,1)),Observable.from(tmp_list),
                RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer> toSeptetFromPair());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Septet<Integer,Integer,Integer,Integer,Integer,Integer,Integer> st = (Septet) tmp;
            assertEquals(firstValTest, (Integer) 0,st.getValue0());
            assertEquals(secondValTest,(Integer) 1,st.getValue1());
            assertEquals(thirdValTest,tmp_list.get(0).getValue0(),st.getValue2());
            assertEquals(fourthValTest,tmp_list.get(0).getValue1(),st.getValue3());
            assertEquals(fifthValTest,tmp_list.get(0).getValue2(),st.getValue4());
            assertEquals(sixthValTest,tmp_list.get(0).getValue3(),st.getValue5());
            assertEquals(seventhValTest,tmp_list.get(0).getValue4(),st.getValue6());
        });
    }

    @Test
    public void checkToSeptetFromTriplet(){
        List<Quartet<Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quartet<>(3,4,5,6));
        Observable result = Observable.zip(Observable.just(new Triplet<>(0,1,2)),Observable.from(tmp_list),
                RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer> toSeptetFromTriplet());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Septet<Integer,Integer,Integer,Integer,Integer,Integer,Integer> st = (Septet) tmp;
            assertEquals(firstValTest, (Integer) 0,st.getValue0());
            assertEquals(secondValTest,(Integer) 1,st.getValue1());
            assertEquals(thirdValTest,(Integer) 2,st.getValue2());
            assertEquals(fourthValTest,tmp_list.get(0).getValue0(),st.getValue3());
            assertEquals(fifthValTest,tmp_list.get(0).getValue1(),st.getValue4());
            assertEquals(sixthValTest,tmp_list.get(0).getValue2(),st.getValue5());
            assertEquals(seventhValTest,tmp_list.get(0).getValue3(),st.getValue6());
        });
    }

    @Test
    public void checkToSeptetFromQuartet(){
        List<Quartet<Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quartet<>(3,4,5,6));
        Observable result = Observable.zip(Observable.from(tmp_list),Observable.just(new Triplet<>(0,1,2)),
                RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer> toSeptetFromQuartet());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Septet<Integer,Integer,Integer,Integer,Integer,Integer,Integer> st = (Septet) tmp;
            assertEquals(firstValTest,tmp_list.get(0).getValue0(),st.getValue0());
            assertEquals(secondValTest,tmp_list.get(0).getValue1(),st.getValue1());
            assertEquals(thirdValTest,tmp_list.get(0).getValue2(),st.getValue2());
            assertEquals(fourthValTest,tmp_list.get(0).getValue3(),st.getValue3());
            assertEquals(fifthValTest, (Integer) 0,st.getValue4());
            assertEquals(sixthValTest,(Integer) 1,st.getValue5());
            assertEquals(seventhValTest,(Integer) 2,st.getValue6());
        });
    }

    @Test
    public void checkToSeptetFromQuintet(){
        List<Quintet<Integer,Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quintet<>(2,3,4,5,6));
        Observable result = Observable.zip(Observable.from(tmp_list),Observable.just(new Pair<>(0,1)),
                RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer> toSeptetFromQuintet());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Septet<Integer,Integer,Integer,Integer,Integer,Integer,Integer> st = (Septet) tmp;
            assertEquals(firstValTest,tmp_list.get(0).getValue0(),st.getValue0());
            assertEquals(secondValTest,tmp_list.get(0).getValue1(),st.getValue1());
            assertEquals(thirdValTest,tmp_list.get(0).getValue2(),st.getValue2());
            assertEquals(fourthValTest,tmp_list.get(0).getValue3(),st.getValue3());
            assertEquals(fifthValTest,tmp_list.get(0).getValue4(),st.getValue4());
            assertEquals(sixthValTest, (Integer) 0,st.getValue5());
            assertEquals(seventhValTest,(Integer) 1,st.getValue6());
        });
    }

    @Test
    public void checkToSeptetFromSextet(){
        List<Sextet<Integer,Integer,Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Sextet(1,2,3,4,5,6));
        Observable result = Observable.zip(Observable.from(tmp_list),Observable.just(0),
                RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer> toSeptetFromSextet());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Septet<Integer,Integer,Integer,Integer,Integer,Integer,Integer> st = (Septet) tmp;
            assertEquals(firstValTest,tmp_list.get(0).getValue0(),st.getValue0());
            assertEquals(secondValTest,tmp_list.get(0).getValue1(),st.getValue1());
            assertEquals(thirdValTest,tmp_list.get(0).getValue2(),st.getValue2());
            assertEquals(fourthValTest,tmp_list.get(0).getValue3(),st.getValue3());
            assertEquals(fifthValTest,tmp_list.get(0).getValue4(),st.getValue4());
            assertEquals(sixthValTest,tmp_list.get(0).getValue5(),st.getValue5());
            assertEquals(seventhValTest, (Integer) 0,st.getValue6());
        });
    }

    /** Octet **/

    @Test
    public void checkToOctet(){
        i=0;
        Observable result = Observable.zip(Observable.from(list1),Observable.range(0,list1.size()),Observable.from(list2),
                Observable.range(0,list2.size()),Observable.from(list1),Observable.range(0,list1.size()),
                Observable.from(list2), Observable.range(0,list2.size()), RxTuples.<String,Integer,Integer,Integer,String,Integer,Integer,Integer> toOctet());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Octet<String,Integer,Integer,Integer,String,Integer,Integer,Integer> oct = (Octet) tmp;
            assertEquals(firstValTest, list1.get(i),oct.getValue0());
            assertEquals(secondValTest,(Integer) i,oct.getValue1());
            assertEquals(thirdValTest,list2.get(i),oct.getValue2());
            assertEquals(fourthValTest,(Integer) i,oct.getValue3());
            assertEquals(fifthValTest,list1.get(i),oct.getValue4());
            assertEquals(sixthValTest,(Integer)i,oct.getValue5());
            assertEquals(seventhValTest,list2.get(i),oct.getValue6());
            assertEquals(eighthValTest,(Integer) i,oct.getValue7());
            i++;
        });
    }

    @Test
    public void checkToOctetFromSingle(){
        List<Septet<Integer,Integer,Integer,Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Septet<>(1,2,3,4,5,6,7));

        Observable result = Observable.zip(Observable.just(0),Observable.from(tmp_list)
                , RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer> toOctetFromSingle());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Octet<String,Integer,Integer,Integer,Integer,Integer,Integer,Integer> oct = (Octet) tmp;
            assertEquals(firstValTest, 0,oct.getValue0());
            assertEquals(secondValTest,tmp_list.get(0).getValue0(),oct.getValue1());
            assertEquals(thirdValTest,tmp_list.get(0).getValue1(),oct.getValue2());
            assertEquals(fourthValTest,tmp_list.get(0).getValue2(),oct.getValue3());
            assertEquals(fifthValTest,tmp_list.get(0).getValue3(),oct.getValue4());
            assertEquals(sixthValTest,tmp_list.get(0).getValue4(),oct.getValue5());
            assertEquals(seventhValTest,tmp_list.get(0).getValue5(),oct.getValue6());
            assertEquals(eighthValTest,tmp_list.get(0).getValue6(),oct.getValue7());
        });
    }

    @Test
    public void checkToOctetFromPair(){
        List<Sextet<Integer,Integer,Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Sextet<>(2,3,4,5,6,7));

        Observable result = Observable.zip(Observable.just(new Pair<>(0,1)),Observable.from(tmp_list)
                , RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer> toOctetFromPair());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Octet<String,Integer,Integer,Integer,Integer,Integer,Integer,Integer> oct = (Octet) tmp;
            assertEquals(firstValTest, 0,oct.getValue0());
            assertEquals(secondValTest,(Integer)1, oct.getValue1());
            assertEquals(thirdValTest,tmp_list.get(0).getValue0(),oct.getValue2());
            assertEquals(fourthValTest,tmp_list.get(0).getValue1(),oct.getValue3());
            assertEquals(fifthValTest,tmp_list.get(0).getValue2(),oct.getValue4());
            assertEquals(sixthValTest,tmp_list.get(0).getValue3(),oct.getValue5());
            assertEquals(seventhValTest,tmp_list.get(0).getValue4(),oct.getValue6());
            assertEquals(eighthValTest,tmp_list.get(0).getValue5(),oct.getValue7());
        });
    }

    @Test
    public void checkToOctetFromTriplet(){
        List<Quintet<Integer,Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quintet<>(3,4,5,6,7));

        Observable result = Observable.zip(Observable.just(new Triplet<>(0,1,2)),Observable.from(tmp_list)
                , RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer> toOctetFromTriplet());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Octet<String,Integer,Integer,Integer,Integer,Integer,Integer,Integer> oct = (Octet) tmp;
            assertEquals(firstValTest, 0,oct.getValue0());
            assertEquals(secondValTest,(Integer)1, oct.getValue1());
            assertEquals(thirdValTest,(Integer)2,oct.getValue2());
            assertEquals(fourthValTest,tmp_list.get(0).getValue0(),oct.getValue3());
            assertEquals(fifthValTest,tmp_list.get(0).getValue1(),oct.getValue4());
            assertEquals(sixthValTest,tmp_list.get(0).getValue2(),oct.getValue5());
            assertEquals(seventhValTest,tmp_list.get(0).getValue3(),oct.getValue6());
            assertEquals(eighthValTest,tmp_list.get(0).getValue4(),oct.getValue7());
        });
    }

    @Test
    public void checkToOctetFromQuartet(){
        List<Quartet<Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quartet<>(4,5,6,7));

        Observable result = Observable.zip(Observable.just(new Quartet<>(0,1,2,3)),Observable.from(tmp_list)
                , RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer> toOctetFromQuartet());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Octet<String,Integer,Integer,Integer,Integer,Integer,Integer,Integer> oct = (Octet) tmp;
            assertEquals(firstValTest, 0,oct.getValue0());
            assertEquals(secondValTest,(Integer)1, oct.getValue1());
            assertEquals(thirdValTest,(Integer)2,oct.getValue2());
            assertEquals(fourthValTest,(Integer)3,oct.getValue3());
            assertEquals(fifthValTest,tmp_list.get(0).getValue0(),oct.getValue4());
            assertEquals(sixthValTest,tmp_list.get(0).getValue1(),oct.getValue5());
            assertEquals(seventhValTest,tmp_list.get(0).getValue2(),oct.getValue6());
            assertEquals(eighthValTest,tmp_list.get(0).getValue3(),oct.getValue7());
        });
    }

    @Test
    public void checkToOctetFromQuintet(){
        List<Quintet<Integer,Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quintet<>(3,4,5,6,7));

        Observable result = Observable.zip(Observable.from(tmp_list),Observable.just(new Triplet<>(0,1,2))
                , RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer> toOctetFromQuintet());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Octet<String,Integer,Integer,Integer,Integer,Integer,Integer,Integer> oct = (Octet) tmp;
            assertEquals(firstValTest,tmp_list.get(0).getValue0(),oct.getValue0());
            assertEquals(secondValTest,tmp_list.get(0).getValue1(),oct.getValue1());
            assertEquals(thirdValTest,tmp_list.get(0).getValue2(),oct.getValue2());
            assertEquals(fourthValTest,tmp_list.get(0).getValue3(),oct.getValue3());
            assertEquals(fifthValTest,tmp_list.get(0).getValue4(),oct.getValue4());
            assertEquals(sixthValTest, (Integer)0,oct.getValue5());
            assertEquals(seventhValTest,(Integer)1, oct.getValue6());
            assertEquals(eighthValTest,(Integer)2,oct.getValue7());
        });
    }

    @Test
    public void checkToOctetFromSextet(){
        List<Sextet<Integer,Integer,Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Sextet<>(2,3,4,5,6,7));

        Observable result = Observable.zip(Observable.from(tmp_list),Observable.just(new Pair<>(0,1))
                , RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer> toOctetFromSextet());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Octet<String,Integer,Integer,Integer,Integer,Integer,Integer,Integer> oct = (Octet) tmp;
            assertEquals(firstValTest,tmp_list.get(0).getValue0(),oct.getValue0());
            assertEquals(secondValTest,tmp_list.get(0).getValue1(),oct.getValue1());
            assertEquals(thirdValTest,tmp_list.get(0).getValue2(),oct.getValue2());
            assertEquals(fourthValTest,tmp_list.get(0).getValue3(),oct.getValue3());
            assertEquals(fifthValTest,tmp_list.get(0).getValue4(),oct.getValue4());
            assertEquals(sixthValTest, tmp_list.get(0).getValue5(),oct.getValue5());
            assertEquals(seventhValTest,(Integer)0, oct.getValue6());
            assertEquals(eighthValTest,(Integer)1,oct.getValue7());
        });
    }

    @Test
    public void checkToOctetFromSeptet(){
        List<Septet<Integer,Integer,Integer,Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Septet<>(1,2,3,4,5,6,7));

        Observable result = Observable.zip(Observable.from(tmp_list),Observable.just(0)
                , RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer> toOctetFromSeptet());

        checkifEmpty(result);

        result.subscribe(tmp -> {
            Octet<String,Integer,Integer,Integer,Integer,Integer,Integer,Integer> oct = (Octet) tmp;
            assertEquals(firstValTest,tmp_list.get(0).getValue0(),oct.getValue0());
            assertEquals(secondValTest,tmp_list.get(0).getValue1(),oct.getValue1());
            assertEquals(thirdValTest,tmp_list.get(0).getValue2(),oct.getValue2());
            assertEquals(fourthValTest,tmp_list.get(0).getValue3(),oct.getValue3());
            assertEquals(fifthValTest,tmp_list.get(0).getValue4(),oct.getValue4());
            assertEquals(sixthValTest, tmp_list.get(0).getValue5(),oct.getValue5());
            assertEquals(seventhValTest,tmp_list.get(0).getValue6(), oct.getValue6());
            assertEquals(eighthValTest,(Integer)0,oct.getValue7());
        });
    }
}
