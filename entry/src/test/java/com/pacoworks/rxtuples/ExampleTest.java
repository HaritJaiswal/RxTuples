/*
 * Copyright (c) pakoito 2015
 * Copyright (C) 2020-21 Application Library Engineering Group
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
    private int i;

    public ExampleTest(){
        i=0;
        list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");

        list2 = new ArrayList<>();
        list2.add(4);
        list2.add(5);
        list2.add(6);
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /** Pair **/
    @Test
    public void check_toPair(){
        i=0;
        Observable result = Observable.zip(Observable.from(list1), Observable.range(0, list1.size()),
                RxTuples.<String, Integer>toPair());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Pair<String,Integer> p = (Pair) tmp;
            assertEquals("First value test", list1.get(i), p.getValue0());
            assertEquals("Second value test", (Integer) i,p.getValue1());
            i++;
        });

    }

    /** Triplet **/
    @Test
    public void check_toTriplet(){
        i=0;
        Observable result = Observable.zip(Observable.from(list1), Observable.range(0, list1.size()),
                Observable.from(list2), RxTuples.<String, Integer,Integer>toTriplet());

        result.isEmpty().subscribe(tmp -> {
            assertFalse((Boolean) tmp);
        });

        result.subscribe(tmp -> {
            Triplet<String,Integer,Integer> trip = (Triplet) tmp;
            assertEquals("First value test", list1.get(i),trip.getValue0());
            assertEquals("Second value test", (Integer) i,trip.getValue1());
            assertEquals("Third value test", list2.get(i),trip.getValue2());
            i++;
        });

    }

    @Test
    public void check_toTripletFromSingle(){
        List<Pair<Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Pair<>(1,2));
        Observable result = Observable.zip(Observable.just(0), Observable.from(tmp_list),
                RxTuples.<Integer, Integer,Integer>toTripletFromSingle());

        result.isEmpty().subscribe(tmp -> {
            assertFalse((Boolean) tmp);
        });

        result.subscribe(tmp -> {
            Triplet<Integer,Integer,Integer> trip = (Triplet) tmp;
            assertEquals("First value test", (Integer) 0,trip.getValue0());
            assertEquals("Second value test", tmp_list.get(0).getValue0(),trip.getValue1());
            assertEquals("Third value test", tmp_list.get(0).getValue1(),trip.getValue2());
        });

    }

    @Test
    public void check_toTripletFromPair(){
        List<Pair<Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Pair<>(1,2));
        Observable result = Observable.zip(Observable.from(tmp_list), Observable.just(0),
                RxTuples.<Integer, Integer,Integer>toTripletFromPair());

        result.isEmpty().subscribe(tmp -> {
            assertFalse((Boolean) tmp);
        });

        result.subscribe(tmp -> {
            Triplet<Integer,Integer,Integer> trip = (Triplet) tmp;
            assertEquals("First value test", tmp_list.get(0).getValue0(),trip.getValue0());
            assertEquals("Second value test", tmp_list.get(0).getValue1(),trip.getValue1());
            assertEquals("Third value test", (Integer)0,trip.getValue2());
            i++;
        });

    }


    /** Quartet **/
    @Test
    public void check_toQuartet(){
        i=0;
        Observable result = Observable.zip(Observable.from(list1), Observable.range(0, list1.size()),
                Observable.from(list2),Observable.range(0,list2.size()),
                RxTuples.<String, Integer, Integer, Integer>toQuartet());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Quartet<String,Integer,Integer,Integer> qt = (Quartet) tmp;
            assertEquals("First value test", list1.get(i), qt.getValue0());
            assertEquals("Second value test", (Integer) i,qt.getValue1());
            assertEquals("Third value test",list2.get(i),qt.getValue2());
            assertEquals("Fourth value test",(Integer) i,qt.getValue3());
            i++;
        });

    }

    @Test
    public void check_toQuartetFromSingle(){
        List<Triplet<Integer, Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Triplet<>(1,2,3));
        Observable result = Observable.zip(Observable.just(0),Observable.from(tmp_list),
                RxTuples.<Integer,Integer, Integer,Integer>toQuartetFromSingle());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Quartet<String,Integer, String, String> qt = (Quartet) tmp;
            assertEquals("First value test", 0, qt.getValue0());
            assertEquals("Second value test", tmp_list.get(0).getValue0(),qt.getValue1());
            assertEquals("Third value test",tmp_list.get(0).getValue1(),qt.getValue2());
            assertEquals("Fourth value test",tmp_list.get(0).getValue2(),qt.getValue3());
        });

    }

    @Test
    public void check_toQuartetFromPair(){
        List<Pair<Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Pair<>(0,1));

        Observable result = Observable.zip(Observable.from(tmp_list),Observable.just(new Pair<>(1,2)),
                RxTuples.<Integer,Integer, Integer, Integer> toQuartetFromPair());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Quartet<Integer,Integer, Integer, Integer> qt = (Quartet) tmp;
            assertEquals("First value test", tmp_list.get(0).getValue0(), qt.getValue0());
            assertEquals("Second value test", tmp_list.get(0).getValue1(),qt.getValue1());
            assertEquals("Third value test",(Integer) 1,qt.getValue2());
            assertEquals("Fourth value test",(Integer) 2,qt.getValue3());
        });
    }

    @Test
    public void check_toQuartetFromTriplet(){
        List<Triplet<String, String,String>> tmp_list = new ArrayList<>();
        tmp_list.add(new Triplet<>("x","y","z"));

        Observable result = Observable.zip(Observable.from(tmp_list),Observable.just("a"),
                RxTuples.<String,String, String, String> toQuartetFromTriplet());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Quartet<String,String, String, String> qt = (Quartet) tmp;
            assertEquals("First value test", tmp_list.get(0).getValue0(), qt.getValue0());
            assertEquals("Second value test", tmp_list.get(0).getValue1(),qt.getValue1());
            assertEquals("Third value test",tmp_list.get(0).getValue2(),qt.getValue2());
            assertEquals("Fourth value test","a",qt.getValue3());
        });
    }

    /** Quintet **/
    @Test
    public void check_toQuintet(){
        i=0;
        Observable result = Observable.zip(Observable.from(list1),Observable.range(0,list1.size()),
                Observable.just("x"),Observable.from(list2),Observable.range(0,list2.size()),
                RxTuples.<String,Integer, String, Integer,Integer> toQuintet());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Quintet<String,Integer, String, Integer,Integer> qt = (Quintet) tmp;
            assertEquals("First value test", list1.get(i), qt.getValue0());
            assertEquals("Second value test", (Integer)i,qt.getValue1());
            assertEquals("Third value test","x",qt.getValue2());
            assertEquals("Fourth value test",list2.get(i),qt.getValue3());
            assertEquals("Fifth value test",(Integer)i,qt.getValue4());
            i++;
        });
    }

    @Test
    public void check_toQuintetFromSingle(){
        List<Quartet<String,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quartet<>("a",2,3,4));
        Observable result = Observable.zip(Observable.just(1),Observable.from(tmp_list),
                RxTuples.<String,Integer,Integer,Integer,Integer> toQuintetFromSingle());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Quintet<String,Integer,Integer,Integer,Integer> qt = (Quintet) tmp;
            assertEquals("First value test", 1, qt.getValue0());
            assertEquals("Second value test", tmp_list.get(0).getValue0(),qt.getValue1());
            assertEquals("Third value test",tmp_list.get(0).getValue1(),qt.getValue2());
            assertEquals("Fourth value test",tmp_list.get(0).getValue2(),qt.getValue3());
            assertEquals("Fifth value test",tmp_list.get(0).getValue3(),qt.getValue4());
        });
    }

    @Test
    public void toQuintetFromPair(){
        List<Triplet<String, String,String>> tmp_list = new ArrayList<>();
        tmp_list.add(new Triplet<>("x","y","z"));

        Observable result = Observable.zip(Observable.just(new Pair<String,String>("a","b")), Observable.from(tmp_list),
                RxTuples.<String,String,String,String,String> toQuintetFromPair());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Quintet<String,String,String,String,String> qt = (Quintet) tmp;
            assertEquals("First value test", "a", qt.getValue0());
            assertEquals("Second value test", "b",qt.getValue1());
            assertEquals("Third value test",tmp_list.get(0).getValue0(),qt.getValue2());
            assertEquals("Fourth value test",tmp_list.get(0).getValue1(),qt.getValue3());
            assertEquals("Fifth value test",tmp_list.get(0).getValue2(),qt.getValue4());
        });

    }

    @Test
    public void check_toQuintetFromTriplet(){
        List<Triplet<String, String,String>> tmp_list = new ArrayList<>();
        tmp_list.add(new Triplet<>("x","y","z"));

        Observable result = Observable.zip(Observable.from(tmp_list), Observable.just(new Pair<String,String>("a","b")),
                RxTuples.<String,String,String,String,String> toQuintetFromTriplet());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Quintet<String,String,String,String,String> qt = (Quintet) tmp;
            assertEquals("First value test", tmp_list.get(0).getValue0(), qt.getValue0());
            assertEquals("Second value test", tmp_list.get(0).getValue1(),qt.getValue1());
            assertEquals("Third value test",tmp_list.get(0).getValue2(),qt.getValue2());
            assertEquals("Fourth value test","a",qt.getValue3());
            assertEquals("Fifth value test","b",qt.getValue4());
        });
    }

    @Test
    public void check_toQuintetFromQuartet(){
        List<Quartet<String,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quartet<>("a",2,3,4));
        Observable result = Observable.zip(Observable.from(tmp_list), Observable.just(5),
                RxTuples.<String,Integer,Integer,Integer,Integer> toQuintetFromQuartet());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Quintet<String,Integer,Integer,Integer,Integer> qt = (Quintet) tmp;
            assertEquals("First value test", tmp_list.get(0).getValue0(),qt.getValue0());
            assertEquals("Second value test",tmp_list.get(0).getValue1(),qt.getValue1());
            assertEquals("Third value test",tmp_list.get(0).getValue2(),qt.getValue2());
            assertEquals("Fourth value test",tmp_list.get(0).getValue3(),qt.getValue3());
            assertEquals("Fifth value test",(Integer)5,qt.getValue4());
        });
    }

    /** Sextet **/
    @Test
    public void check_toSextet(){
        i=0;
        Observable result = Observable.zip(Observable.from(list1),Observable.range(0,list1.size()),Observable.from(list2),
                Observable.range(0,list2.size()),Observable.from(list1),Observable.range(0,list1.size()),
                RxTuples.<String,Integer,Integer,Integer,String,Integer> toSextet());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Sextet<String,Integer,Integer,Integer,String,Integer> st = (Sextet) tmp;
            assertEquals("First value test", list1.get(i),st.getValue0());
            assertEquals("Second value test",(Integer) i,st.getValue1());
            assertEquals("Third value test",list2.get(i),st.getValue2());
            assertEquals("Fourth value test",(Integer) i,st.getValue3());
            assertEquals("Fifth value test",list1.get(i),st.getValue4());
            assertEquals("Sixth value test",(Integer)i,st.getValue5());
            i++;
        });
    }

    @Test
    public void check_toSextetFromSingle() {
        List<Quintet<Integer, Integer, Integer, Integer, Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quintet<>(1, 2, 3, 4, 5));

        Observable result = Observable.zip(Observable.just(0), Observable.from(tmp_list),
                RxTuples.<Integer, Integer, Integer, Integer, Integer, Integer>toSextetFromSingle());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Sextet<Integer, Integer, Integer, Integer, Integer, Integer> st = (Sextet) tmp;
            assertEquals("First value test", (Integer) 0,st.getValue0());
            assertEquals("Second value test",tmp_list.get(0).getValue0(),st.getValue1());
            assertEquals("Third value test",tmp_list.get(0).getValue1(),st.getValue2());
            assertEquals("Fourth value test",tmp_list.get(0).getValue2(),st.getValue3());
            assertEquals("Fifth value test",tmp_list.get(0).getValue3(),st.getValue4());
            assertEquals("Sixth value test",tmp_list.get(0).getValue4(),st.getValue5());
        });
    }

    @Test
    public void check_toSextetFromPair(){
        List<Quartet<Integer, Integer, Integer, Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quartet<>(1, 2, 3, 4));

        Observable result = Observable.zip(Observable.just(new Pair<String,String>("a","b")), Observable.from(tmp_list),
                RxTuples.<String,String, Integer, Integer, Integer, Integer>toSextetFromPair());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Sextet<String,String, Integer, Integer, Integer, Integer> st = (Sextet) tmp;
            assertEquals("First value test", "a",st.getValue0());
            assertEquals("Second value test","b",st.getValue1());
            assertEquals("Third value test",tmp_list.get(0).getValue0(),st.getValue2());
            assertEquals("Fourth value test",tmp_list.get(0).getValue1(),st.getValue3());
            assertEquals("Fifth value test",tmp_list.get(0).getValue2(),st.getValue4());
            assertEquals("Sixth value test",tmp_list.get(0).getValue3(),st.getValue5());
        });
    }

    @Test
    public void check_toSextetFromTriplet(){
        List<Triplet<Integer, Integer, Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Triplet<>(1, 2, 3));

        Observable result = Observable.zip(Observable.just(new Triplet<String,String,String>("a","b","c")), Observable.from(tmp_list),
                RxTuples.<String,String, String, Integer, Integer, Integer>toSextetFromTriplet());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Sextet<String,String, String, Integer, Integer, Integer> st = (Sextet) tmp;
            assertEquals("First value test", "a",st.getValue0());
            assertEquals("Second value test","b",st.getValue1());
            assertEquals("Third value test","c",st.getValue2());
            assertEquals("Fourth value test",tmp_list.get(0).getValue0(),st.getValue3());
            assertEquals("Fifth value test",tmp_list.get(0).getValue1(),st.getValue4());
            assertEquals("Sixth value test",tmp_list.get(0).getValue2(),st.getValue5());
        });
    }

    @Test
    public void check_toSextetFromQuartet(){
        List<Quartet<Integer, Integer, Integer, Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quartet<>(1, 2, 3, 4));

        Observable result = Observable.zip(Observable.from(tmp_list),
                Observable.just(new Pair<String,String>("a","b")),
                RxTuples.<Integer, Integer, Integer, Integer,String,String>toSextetFromQuartet());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Sextet<Integer, Integer, Integer, Integer,String,String> st = (Sextet) tmp;
            assertEquals("First value test",tmp_list.get(0).getValue0(),st.getValue0());
            assertEquals("Second value test",tmp_list.get(0).getValue1(),st.getValue1());
            assertEquals("Third value test",tmp_list.get(0).getValue2(),st.getValue2());
            assertEquals("Fourth value test",tmp_list.get(0).getValue3(),st.getValue3());
            assertEquals("Fifth value test", "a",st.getValue4());
            assertEquals("Sixth value test","b",st.getValue5());
        });
    }

    @Test
    public void check_toSextetFromQuintet(){
        List<Quintet<Integer, Integer, Integer, Integer, Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quintet<>(1, 2, 3, 4, 5));

        Observable result = Observable.zip(Observable.from(tmp_list),Observable.just(0),
                RxTuples.<Integer, Integer, Integer, Integer, Integer, Integer>toSextetFromQuintet());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Sextet<Integer, Integer, Integer, Integer, Integer, Integer> st = (Sextet) tmp;
            assertEquals("First value test",tmp_list.get(0).getValue0(),st.getValue0());
            assertEquals("Second value test",tmp_list.get(0).getValue1(),st.getValue1());
            assertEquals("Third value test",tmp_list.get(0).getValue2(),st.getValue2());
            assertEquals("Fourth value test",tmp_list.get(0).getValue3(),st.getValue3());
            assertEquals("Fifth value test",tmp_list.get(0).getValue4(),st.getValue4());
            assertEquals("Sixth value test", (Integer) 0,st.getValue5());
        });
    }


    /** Septet **/
    @Test
    public void check_toSeptet(){
        i=0;
        Observable result = Observable.zip(Observable.from(list1),Observable.range(0,list1.size()),Observable.from(list2),
                Observable.range(0,list2.size()),Observable.from(list1),Observable.range(0,list1.size()),
                Observable.from(list2), RxTuples.<String,Integer,Integer,Integer,String,Integer,Integer> toSeptet());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Septet<String,Integer,Integer,Integer,String,Integer,Integer> st = (Septet) tmp;
            assertEquals("First value test", list1.get(i),st.getValue0());
            assertEquals("Second value test",(Integer) i,st.getValue1());
            assertEquals("Third value test",list2.get(i),st.getValue2());
            assertEquals("Fourth value test",(Integer) i,st.getValue3());
            assertEquals("Fifth value test",list1.get(i),st.getValue4());
            assertEquals("Sixth value test",(Integer)i,st.getValue5());
            assertEquals("Seventh value test",list2.get(i),st.getValue6());
            i++;
        });
    }

    @Test
    public void check_toSeptetFromSingle(){
        List<Sextet<Integer,Integer,Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Sextet(1,2,3,4,5,6));
        Observable result = Observable.zip(Observable.just(0),Observable.from(tmp_list),
                RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer> toSeptetFromSingle());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Septet<Integer,Integer,Integer,Integer,Integer,Integer,Integer> st = (Septet) tmp;
            assertEquals("First value test", (Integer) 0,st.getValue0());
            assertEquals("Second value test",tmp_list.get(0).getValue0(),st.getValue1());
            assertEquals("Third value test",tmp_list.get(0).getValue1(),st.getValue2());
            assertEquals("Fourth value test",tmp_list.get(0).getValue2(),st.getValue3());
            assertEquals("Fifth value test",tmp_list.get(0).getValue3(),st.getValue4());
            assertEquals("Sixth value test",tmp_list.get(0).getValue4(),st.getValue5());
            assertEquals("Seventh value test",tmp_list.get(0).getValue5(),st.getValue6());
        });
    }

    @Test
    public void check_toSeptetFromPair(){
        List<Quintet<Integer,Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quintet<>(2,3,4,5,6));
        Observable result = Observable.zip(Observable.just(new Pair<>(0,1)),Observable.from(tmp_list),
                RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer> toSeptetFromPair());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Septet<Integer,Integer,Integer,Integer,Integer,Integer,Integer> st = (Septet) tmp;
            assertEquals("First value test", (Integer) 0,st.getValue0());
            assertEquals("Second value test",(Integer) 1,st.getValue1());
            assertEquals("Third value test",tmp_list.get(0).getValue0(),st.getValue2());
            assertEquals("Fourth value test",tmp_list.get(0).getValue1(),st.getValue3());
            assertEquals("Fifth value test",tmp_list.get(0).getValue2(),st.getValue4());
            assertEquals("Sixth value test",tmp_list.get(0).getValue3(),st.getValue5());
            assertEquals("Seventh value test",tmp_list.get(0).getValue4(),st.getValue6());
        });
    }

    @Test
    public void check_toSeptetFromTriplet(){
        List<Quartet<Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quartet<>(3,4,5,6));
        Observable result = Observable.zip(Observable.just(new Triplet<>(0,1,2)),Observable.from(tmp_list),
                RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer> toSeptetFromTriplet());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Septet<Integer,Integer,Integer,Integer,Integer,Integer,Integer> st = (Septet) tmp;
            assertEquals("First value test", (Integer) 0,st.getValue0());
            assertEquals("Second value test",(Integer) 1,st.getValue1());
            assertEquals("Third value test",(Integer) 2,st.getValue2());
            assertEquals("Fourth value test",tmp_list.get(0).getValue0(),st.getValue3());
            assertEquals("Fifth value test",tmp_list.get(0).getValue1(),st.getValue4());
            assertEquals("Sixth value test",tmp_list.get(0).getValue2(),st.getValue5());
            assertEquals("Seventh value test",tmp_list.get(0).getValue3(),st.getValue6());
        });
    }

    @Test
    public void check_toSeptetFromQuartet(){
        List<Quartet<Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quartet<>(3,4,5,6));
        Observable result = Observable.zip(Observable.from(tmp_list),Observable.just(new Triplet<>(0,1,2)),
                RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer> toSeptetFromQuartet());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Septet<Integer,Integer,Integer,Integer,Integer,Integer,Integer> st = (Septet) tmp;
            assertEquals("First value test",tmp_list.get(0).getValue0(),st.getValue0());
            assertEquals("Second value test",tmp_list.get(0).getValue1(),st.getValue1());
            assertEquals("Third value test",tmp_list.get(0).getValue2(),st.getValue2());
            assertEquals("Fourth value test",tmp_list.get(0).getValue3(),st.getValue3());
            assertEquals("Fifth value test", (Integer) 0,st.getValue4());
            assertEquals("Sixth value test",(Integer) 1,st.getValue5());
            assertEquals("Seventh value test",(Integer) 2,st.getValue6());
        });
    }

    @Test
    public void check_toSeptetFromQuintet(){
        List<Quintet<Integer,Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quintet<>(2,3,4,5,6));
        Observable result = Observable.zip(Observable.from(tmp_list),Observable.just(new Pair<>(0,1)),
                RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer> toSeptetFromQuintet());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Septet<Integer,Integer,Integer,Integer,Integer,Integer,Integer> st = (Septet) tmp;
            assertEquals("First value test",tmp_list.get(0).getValue0(),st.getValue0());
            assertEquals("Second value test",tmp_list.get(0).getValue1(),st.getValue1());
            assertEquals("Third value test",tmp_list.get(0).getValue2(),st.getValue2());
            assertEquals("Fourth value test",tmp_list.get(0).getValue3(),st.getValue3());
            assertEquals("Fifth value test",tmp_list.get(0).getValue4(),st.getValue4());
            assertEquals("Sixth value test", (Integer) 0,st.getValue5());
            assertEquals("Seventh value test",(Integer) 1,st.getValue6());
        });
    }

    @Test
    public void check_toSeptetFromSextet(){
        List<Sextet<Integer,Integer,Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Sextet(1,2,3,4,5,6));
        Observable result = Observable.zip(Observable.from(tmp_list),Observable.just(0),
                RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer> toSeptetFromSextet());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Septet<Integer,Integer,Integer,Integer,Integer,Integer,Integer> st = (Septet) tmp;
            assertEquals("First value test",tmp_list.get(0).getValue0(),st.getValue0());
            assertEquals("Second value test",tmp_list.get(0).getValue1(),st.getValue1());
            assertEquals("Third value test",tmp_list.get(0).getValue2(),st.getValue2());
            assertEquals("Fourth value test",tmp_list.get(0).getValue3(),st.getValue3());
            assertEquals("Fifth value test",tmp_list.get(0).getValue4(),st.getValue4());
            assertEquals("Sixth value test",tmp_list.get(0).getValue5(),st.getValue5());
            assertEquals("Seventh value test", (Integer) 0,st.getValue6());
        });
    }

    /** Octet **/

    @Test
    public void check_toOctet(){
        i=0;
        Observable result = Observable.zip(Observable.from(list1),Observable.range(0,list1.size()),Observable.from(list2),
                Observable.range(0,list2.size()),Observable.from(list1),Observable.range(0,list1.size()),
                Observable.from(list2), Observable.range(0,list2.size()), RxTuples.<String,Integer,Integer,Integer,String,Integer,Integer,Integer> toOctet());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Octet<String,Integer,Integer,Integer,String,Integer,Integer,Integer> oct = (Octet) tmp;
            assertEquals("First value test", list1.get(i),oct.getValue0());
            assertEquals("Second value test",(Integer) i,oct.getValue1());
            assertEquals("Third value test",list2.get(i),oct.getValue2());
            assertEquals("Fourth value test",(Integer) i,oct.getValue3());
            assertEquals("Fifth value test",list1.get(i),oct.getValue4());
            assertEquals("Sixth value test",(Integer)i,oct.getValue5());
            assertEquals("Seventh value test",list2.get(i),oct.getValue6());
            assertEquals("Eighth value test",(Integer) i,oct.getValue7());
            i++;
        });
    }

    @Test
    public void check_toOctetFromSingle(){
        List<Septet<Integer,Integer,Integer,Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Septet<>(1,2,3,4,5,6,7));

        Observable result = Observable.zip(Observable.just(0),Observable.from(tmp_list)
                , RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer> toOctetFromSingle());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Octet<String,Integer,Integer,Integer,Integer,Integer,Integer,Integer> oct = (Octet) tmp;
            assertEquals("First value test", 0,oct.getValue0());
            assertEquals("Second value test",tmp_list.get(0).getValue0(),oct.getValue1());
            assertEquals("Third value test",tmp_list.get(0).getValue1(),oct.getValue2());
            assertEquals("Fourth value test",tmp_list.get(0).getValue2(),oct.getValue3());
            assertEquals("Fifth value test",tmp_list.get(0).getValue3(),oct.getValue4());
            assertEquals("Sixth value test",tmp_list.get(0).getValue4(),oct.getValue5());
            assertEquals("Seventh value test",tmp_list.get(0).getValue5(),oct.getValue6());
            assertEquals("Eighth value test",tmp_list.get(0).getValue6(),oct.getValue7());
        });
    }

    @Test
    public void check_toOctetFromPair(){
        List<Sextet<Integer,Integer,Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Sextet<>(2,3,4,5,6,7));

        Observable result = Observable.zip(Observable.just(new Pair<>(0,1)),Observable.from(tmp_list)
                , RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer> toOctetFromPair());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Octet<String,Integer,Integer,Integer,Integer,Integer,Integer,Integer> oct = (Octet) tmp;
            assertEquals("First value test", 0,oct.getValue0());
            assertEquals("Second value test",(Integer)1, oct.getValue1());
            assertEquals("Third value test",tmp_list.get(0).getValue0(),oct.getValue2());
            assertEquals("Fourth value test",tmp_list.get(0).getValue1(),oct.getValue3());
            assertEquals("Fifth value test",tmp_list.get(0).getValue2(),oct.getValue4());
            assertEquals("Sixth value test",tmp_list.get(0).getValue3(),oct.getValue5());
            assertEquals("Seventh value test",tmp_list.get(0).getValue4(),oct.getValue6());
            assertEquals("Eighth value test",tmp_list.get(0).getValue5(),oct.getValue7());
        });
    }

    @Test
    public void check_toOctetFromTriplet(){
        List<Quintet<Integer,Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quintet<>(3,4,5,6,7));

        Observable result = Observable.zip(Observable.just(new Triplet<>(0,1,2)),Observable.from(tmp_list)
                , RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer> toOctetFromTriplet());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Octet<String,Integer,Integer,Integer,Integer,Integer,Integer,Integer> oct = (Octet) tmp;
            assertEquals("First value test", 0,oct.getValue0());
            assertEquals("Second value test",(Integer)1, oct.getValue1());
            assertEquals("Third value test",(Integer)2,oct.getValue2());
            assertEquals("Fourth value test",tmp_list.get(0).getValue0(),oct.getValue3());
            assertEquals("Fifth value test",tmp_list.get(0).getValue1(),oct.getValue4());
            assertEquals("Sixth value test",tmp_list.get(0).getValue2(),oct.getValue5());
            assertEquals("Seventh value test",tmp_list.get(0).getValue3(),oct.getValue6());
            assertEquals("Eighth value test",tmp_list.get(0).getValue4(),oct.getValue7());
        });
    }

    @Test
    public void check_toOctetFromQuartet(){
        List<Quartet<Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quartet<>(4,5,6,7));

        Observable result = Observable.zip(Observable.just(new Quartet<>(0,1,2,3)),Observable.from(tmp_list)
                , RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer> toOctetFromQuartet());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Octet<String,Integer,Integer,Integer,Integer,Integer,Integer,Integer> oct = (Octet) tmp;
            assertEquals("First value test", 0,oct.getValue0());
            assertEquals("Second value test",(Integer)1, oct.getValue1());
            assertEquals("Third value test",(Integer)2,oct.getValue2());
            assertEquals("Fourth value test",(Integer)3,oct.getValue3());
            assertEquals("Fifth value test",tmp_list.get(0).getValue0(),oct.getValue4());
            assertEquals("Sixth value test",tmp_list.get(0).getValue1(),oct.getValue5());
            assertEquals("Seventh value test",tmp_list.get(0).getValue2(),oct.getValue6());
            assertEquals("Eighth value test",tmp_list.get(0).getValue3(),oct.getValue7());
        });
    }

    @Test
    public void check_toOctetFromQuintet(){
        List<Quintet<Integer,Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Quintet<>(3,4,5,6,7));

        Observable result = Observable.zip(Observable.from(tmp_list),Observable.just(new Triplet<>(0,1,2))
                , RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer> toOctetFromQuintet());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Octet<String,Integer,Integer,Integer,Integer,Integer,Integer,Integer> oct = (Octet) tmp;
            assertEquals("First value test",tmp_list.get(0).getValue0(),oct.getValue0());
            assertEquals("Second value test",tmp_list.get(0).getValue1(),oct.getValue1());
            assertEquals("Third value test",tmp_list.get(0).getValue2(),oct.getValue2());
            assertEquals("Fourth value test",tmp_list.get(0).getValue3(),oct.getValue3());
            assertEquals("Fifth value test",tmp_list.get(0).getValue4(),oct.getValue4());
            assertEquals("Sixth value test", (Integer)0,oct.getValue5());
            assertEquals("Seventh value test",(Integer)1, oct.getValue6());
            assertEquals("Eighth value test",(Integer)2,oct.getValue7());
        });
    }

    @Test
    public void check_toOctetFromSextet(){
        List<Sextet<Integer,Integer,Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Sextet<>(2,3,4,5,6,7));

        Observable result = Observable.zip(Observable.from(tmp_list),Observable.just(new Pair<>(0,1))
                , RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer> toOctetFromSextet());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Octet<String,Integer,Integer,Integer,Integer,Integer,Integer,Integer> oct = (Octet) tmp;
            assertEquals("First value test",tmp_list.get(0).getValue0(),oct.getValue0());
            assertEquals("Second value test",tmp_list.get(0).getValue1(),oct.getValue1());
            assertEquals("Third value test",tmp_list.get(0).getValue2(),oct.getValue2());
            assertEquals("Fourth value test",tmp_list.get(0).getValue3(),oct.getValue3());
            assertEquals("Fifth value test",tmp_list.get(0).getValue4(),oct.getValue4());
            assertEquals("Sixth value test", tmp_list.get(0).getValue5(),oct.getValue5());
            assertEquals("Seventh value test",(Integer)0, oct.getValue6());
            assertEquals("Eighth value test",(Integer)1,oct.getValue7());
        });
    }

    @Test
    public void check_toOctetFromSeptet(){
        List<Septet<Integer,Integer,Integer,Integer,Integer,Integer,Integer>> tmp_list = new ArrayList<>();
        tmp_list.add(new Septet<>(1,2,3,4,5,6,7));

        Observable result = Observable.zip(Observable.from(tmp_list),Observable.just(0)
                , RxTuples.<Integer,Integer,Integer,Integer,Integer,Integer,Integer,Integer> toOctetFromSeptet());

        result.isEmpty().subscribe(tmp -> {
                    assertFalse((Boolean) tmp);
                }
        );

        result.subscribe(tmp -> {
            Octet<String,Integer,Integer,Integer,Integer,Integer,Integer,Integer> oct = (Octet) tmp;
            assertEquals("First value test",tmp_list.get(0).getValue0(),oct.getValue0());
            assertEquals("Second value test",tmp_list.get(0).getValue1(),oct.getValue1());
            assertEquals("Third value test",tmp_list.get(0).getValue2(),oct.getValue2());
            assertEquals("Fourth value test",tmp_list.get(0).getValue3(),oct.getValue3());
            assertEquals("Fifth value test",tmp_list.get(0).getValue4(),oct.getValue4());
            assertEquals("Sixth value test", tmp_list.get(0).getValue5(),oct.getValue5());
            assertEquals("Seventh value test",tmp_list.get(0).getValue6(), oct.getValue6());
            assertEquals("Eighth value test",(Integer)0,oct.getValue7());
        });
    }
}
