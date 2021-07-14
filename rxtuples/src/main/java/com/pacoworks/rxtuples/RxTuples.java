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

import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.functions.Func5;
import rx.functions.Func6;
import rx.functions.Func7;
import rx.functions.Func8;
import org.javatuples.Octet;
import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Quintet;
import org.javatuples.Septet;
import org.javatuples.Sextet;
import org.javatuples.Triplet;

/** RxTuples. **/
public final class RxTuples {
    private RxTuples() {
        // empty constructor
    }

    /**
     * toPair().
     *
     * @param <T> an Observable object
     * @param <U> an Observable object
     * @return Pair
     */
    public static <T, U> rx.functions.Func2<T, U, Pair<T, U>> toPair() {
        return Pair::with;
    }

    /**
     * toTriplet().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @return Triplet
     */
    public static <A, B, C> Func3<A, B, C, Triplet<A, B, C>> toTriplet() {
        return Triplet::with;
    }

    /**
     * toTripletFromSingle().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <T> an Observable object
     * @return Triplet
     */
    public static <A, B, T> rx.functions.Func2<T, Pair<A, B>, Triplet<T, A, B>> toTripletFromSingle() {
        return (t, objects) -> Triplet.with(t, objects.getValue0(), objects.getValue1());
    }

    /**
     * toTripletFromPair().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <T> an Observable object
     * @return Triplet
     */
    public static <A, B, T> Func2<Pair<A, B>, T, Triplet<A, B, T>> toTripletFromPair() {
        return (objects, t) -> Triplet.with(objects.getValue0(), objects.getValue1(), t);
    }

    /**
     * toQuartet().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @return Quartet
     */
    public static <A, B, C, D> Func4<A, B, C, D, Quartet<A, B, C, D>> toQuartet() {
        return Quartet::with;
    }

    /**
     * toQuartetFromSingle().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <T> an Observable object
     * @return Quartet
     */
    public static <A, B, C, T> Func2<T, Triplet<A, B, C>, Quartet<T, A, B, C>> toQuartetFromSingle() {
        return (t, objects) -> Quartet.with(t, objects.getValue0(), objects.getValue1(),
                objects.getValue2());
    }

    /**
     * toQuartetFromPair().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @return Quartet
     */
    public static <A, B, C, D> Func2<Pair<A, B>, Pair<C, D>, Quartet<A, B, C, D>> toQuartetFromPair() {
        return (objects, objects2) -> Quartet.with(objects.getValue0(), objects.getValue1(), objects2.getValue0(),
                objects2.getValue1());
    }

    /**
     * toQuartetFromTriplet().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <T> an Observable object
     * @return Quartet
     */
    public static <A, B, C, T> Func2<Triplet<A, B, C>, T, Quartet<A, B, C, T>> toQuartetFromTriplet() {
        return (objects, t) -> Quartet.with(objects.getValue0(), objects.getValue1(), objects.getValue2(),
                t);
    }

    /**
     * toQuintet().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @return Quintet
     */
    public static <A, B, C, D, E> Func5<A, B, C, D, E, Quintet<A, B, C, D, E>> toQuintet() {
        return Quintet::with;
    }

    /**
     * toQuintetFromSingle().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <T> an Observable object
     * @return Quintet
     */
    public static <A, B, C, D, T> Func2<T, Quartet<A, B, C, D>, Quintet<T, A, B, C, D>> toQuintetFromSingle() {
        return (t, objects) -> Quintet.with(t, objects.getValue0(), objects.getValue1(),
                objects.getValue2(), objects.getValue3());
    }

    /**
     * toQuintetFromPair().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @return Quintet
     */
    public static <A, B, C, D, E> Func2<Pair<A, B>, Triplet<C, D, E>, Quintet<A, B, C, D, E>> toQuintetFromPair() {
        return (objects, objects2) -> Quintet.with(objects.getValue0(), objects.getValue1(), objects2.getValue0(),
                objects2.getValue1(), objects2.getValue2());
    }

    /**
     * toQuintetFromTriplet().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @return Quintet
     */
    public static <A, B, C, D, E> Func2<Triplet<A, B, C>, Pair<D, E>, Quintet<A, B, C, D, E>>
        toQuintetFromTriplet() {
        return (objects, objects2) -> Quintet.with(objects.getValue0(), objects.getValue1(), objects.getValue2(),
                objects2.getValue0(), objects2.getValue1());
    }

    /**
     * toQuintetFromQuartet().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <T> an Observable object
     * @return Quintet
     */
    public static <A, B, C, D, T> Func2<Quartet<A, B, C, D>, T, Quintet<A, B, C, D, T>> toQuintetFromQuartet() {
        return (objects, t) -> Quintet.with(objects.getValue0(), objects.getValue1(), objects.getValue2(),
                objects.getValue3(), t);
    }

    /**
     * toSextet().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @param <F> an Observable object
     * @return Sextet
     */
    public static <A, B, C, D, E, F> Func6<A, B, C, D, E, F, Sextet<A, B, C, D, E, F>> toSextet() {
        return Sextet::with;
    }

    /**
     * toSextetFromSingle().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @param <T> an Observable object
     * @return Sextet
     */
    public static <A, B, C, D, E, T> Func2<T, Quintet<A, B, C, D, E>, Sextet<T, A, B, C, D, E>>
        toSextetFromSingle() {
        return (t, objects) -> Sextet.with(t, objects.getValue0(), objects.getValue1(),
                objects.getValue2(), objects.getValue3(), objects.getValue4());
    }

    /**
     * toSextetFromPair().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @param <F> an Observable object
     * @return Sextet
     */
    public static <A, B, C, D, E, F> Func2<Pair<A, B>, Quartet<C, D, E, F>, Sextet<A, B, C, D, E, F>>
        toSextetFromPair() {
        return (objects, objects2) -> Sextet.with(objects.getValue0(), objects.getValue1(), objects2.getValue0(),
                objects2.getValue1(), objects2.getValue2(), objects2.getValue3());
    }

    /**
     * toSextetFromTriplet().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @param <F> an Observable object
     * @return Sextet
     */
    public static <A, B, C, D, E, F> Func2<Triplet<A, B, C>, Triplet<D, E, F>, Sextet<A, B, C, D, E, F>>
        toSextetFromTriplet() {
        return (objects, objects2) -> Sextet.with(objects.getValue0(), objects.getValue1(), objects.getValue2(),
                objects2.getValue0(), objects2.getValue1(), objects2.getValue2());
    }

    /**
     * toSextetFromQuartet().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @param <F> an Observable object
     * @return Sextet
     */
    public static <A, B, C, D, E, F> Func2<Quartet<A, B, C, D>, Pair<E, F>, Sextet<A, B, C, D, E, F>>
        toSextetFromQuartet() {
        return (objects, objects2) -> Sextet.with(objects.getValue0(), objects.getValue1(), objects.getValue2(),
                objects.getValue3(), objects2.getValue0(), objects2.getValue1());
    }

    /**
     * toSextetFromQuintet().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @param <T> an Observable object
     * @return Sextet
     */
    public static <A, B, C, D, E, T> Func2<Quintet<A, B, C, D, E>, T, Sextet<A, B, C, D, E, T>>
        toSextetFromQuintet() {
        return (objects, t) -> Sextet.with(objects.getValue0(), objects.getValue1(), objects.getValue2(),
                objects.getValue3(), objects.getValue4(), t);
    }

    /**
     * toSeptet().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @param <F> an Observable object
     * @param <G> an Observable object
     * @return Septet
     */
    public static <A, B, C, D, E, F, G> Func7<A, B, C, D, E, F, G, Septet<A, B, C, D, E, F, G>>
        toSeptet() {
        return Septet::with;
    }

    /**
     * toSeptetFromSingle().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @param <F> an Observable object
     * @param <T> an Observable object
     * @return Septet
     */
    public static <A, B, C, D, E, F, T> Func2<T, Sextet<A, B, C, D, E, F>, Septet<T, A, B, C, D, E, F>>
        toSeptetFromSingle() {
        return (t, objects) -> Septet.with(t, objects.getValue0(), objects.getValue1(),
                objects.getValue2(), objects.getValue3(), objects.getValue4(),
                objects.getValue5());
    }

    /**
     * toSeptetFromPair().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @param <F> an Observable object
     * @param <G> an Observable object
     * @return Septet
     */
    public static <A, B, C, D, E, F, G> Func2<Pair<A, B>, Quintet<C, D, E, F, G>, Septet<A, B, C, D, E, F, G>>
        toSeptetFromPair() {
        return (objects, objects2) -> Septet.with(objects.getValue0(), objects.getValue1(), objects2.getValue0(),
                objects2.getValue1(), objects2.getValue2(), objects2.getValue3(),
                objects2.getValue4());
    }

    /**
     * toSeptetFromTriplet().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @param <F> an Observable object
     * @param <G> an Observable object
     * @return Septet
     */
    public static <A, B, C, D, E, F, G> Func2<Triplet<A, B, C>, Quartet<D, E, F, G>, Septet<A, B, C, D, E, F, G>>
        toSeptetFromTriplet() {
        return (objects, objects2) -> Septet.with(objects.getValue0(), objects.getValue1(), objects.getValue2(),
                objects2.getValue0(), objects2.getValue1(), objects2.getValue2(),
                objects2.getValue3());
    }

    /**
     * toSeptetFromQuartet().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @param <F> an Observable object
     * @param <G> an Observable object
     * @return Septet
     */
    public static <A, B, C, D, E, F, G> Func2<Quartet<A, B, C, D>, Triplet<E, F, G>, Septet<A, B, C, D, E, F, G>>
        toSeptetFromQuartet() {
        return (objects, objects2) -> Septet.with(objects.getValue0(), objects.getValue1(), objects.getValue2(),
                objects.getValue3(), objects2.getValue0(), objects2.getValue1(),
                objects2.getValue2());
    }

    /**
     * toSeptetFromQuintet().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @param <F> an Observable object
     * @param <G> an Observable object
     * @return Septet
     */
    public static <A, B, C, D, E, F, G> Func2<Quintet<A, B, C, D, E>, Pair<F, G>, Septet<A, B, C, D, E, F, G>>
        toSeptetFromQuintet() {
        return (objects, objects2) -> Septet.with(objects.getValue0(), objects.getValue1(), objects.getValue2(),
                objects.getValue3(), objects.getValue4(), objects2.getValue0(),
                objects2.getValue1());
    }

    /**
     * toSeptetFromSextet().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @param <F> an Observable object
     * @param <T> an Observable object
     * @return Septet
     */
    public static <A, B, C, D, E, F, T> Func2<Sextet<A, B, C, D, E, F>, T, Septet<A, B, C, D, E, F, T>>
        toSeptetFromSextet() {
        return (objects, t) -> Septet.with(objects.getValue0(), objects.getValue1(), objects.getValue2(),
                objects.getValue3(), objects.getValue4(), objects.getValue5(), t);
    }

    /**
     * toOctet().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @param <F> an Observable object
     * @param <G> an Observable object
     * @param <H> an Observable object
     * @return Octet
     */
    public static <A, B, C, D, E, F, G, H> Func8<A, B, C, D, E, F, G, H, Octet<A, B, C, D, E, F, G, H>>
        toOctet() {
        return Octet::with;
    }

    /**
     * toOctetFromSingle().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @param <F> an Observable object
     * @param <G> an Observable object
     * @param <T> an Observable object
     * @return Octet
     */
    public static <A, B, C, D, E, F, G, T> Func2<T, Septet<A, B, C, D, E, F, G>, Octet<T, A, B, C, D, E, F, G>>
        toOctetFromSingle() {
        return (t, objects) -> Octet.with(t, objects.getValue0(), objects.getValue1(), objects.getValue2(),
                objects.getValue3(), objects.getValue4(), objects.getValue5(),
                objects.getValue6());
    }

    /**
     * toOctetFromPair().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @param <F> an Observable object
     * @param <G> an Observable object
     * @param <H> an Observable object
     * @return Octet
     */
    public static <A, B, C, D, E, F, G, H> Func2<Pair<A, B>, Sextet<C, D, E, F, G, H>, Octet<A, B, C, D, E, F, G, H>>
        toOctetFromPair() {
        return (objects, objects2) -> Octet.with(objects.getValue0(), objects.getValue1(), objects2.getValue0(),
                objects2.getValue1(), objects2.getValue2(), objects2.getValue3(),
                objects2.getValue4(), objects2.getValue5());
    }

    /**
     * toOctetFromTriplet().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @param <F> an Observable object
     * @param <G> an Observable object
     * @param <H> an Observable object
     * @return Octet
     */
    public static <A, B, C, D, E, F, G, H> Func2<Triplet<A, B, C>, Quintet<D, E, F, G, H>,
            Octet<A, B, C, D, E, F, G, H>> toOctetFromTriplet() {
        return (objects, objects2) -> Octet.with(objects.getValue0(), objects.getValue1(), objects.getValue2(),
                objects2.getValue0(), objects2.getValue1(), objects2.getValue2(),
                objects2.getValue3(), objects2.getValue4());
    }

    /**
     * toOctetFromQuartet().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @param <F> an Observable object
     * @param <G> an Observable object
     * @param <H> an Observable object
     * @return Octet
     */
    public static <A, B, C, D, E, F, G, H> Func2<Quartet<A, B, C, D>, Quartet<E, F, G, H>,
            Octet<A, B, C, D, E, F, G, H>> toOctetFromQuartet() {
        return (objects, objects2) -> Octet.with(objects.getValue0(), objects.getValue1(), objects.getValue2(),
                objects.getValue3(), objects2.getValue0(), objects2.getValue1(),
                objects2.getValue2(), objects2.getValue3());
    }

    /**
     * toOctetFromQuintet().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @param <F> an Observable object
     * @param <G> an Observable object
     * @param <H> an Observable object
     * @return Octet
     */
    public static <A, B, C, D, E, F, G, H> Func2<Quintet<A, B, C, D, E>, Triplet<F, G, H>,
            Octet<A, B, C, D, E, F, G, H>> toOctetFromQuintet() {
        return (objects, objects2) -> Octet.with(objects.getValue0(), objects.getValue1(), objects.getValue2(),
                objects.getValue3(), objects.getValue4(), objects2.getValue0(),
                objects2.getValue1(), objects2.getValue2());
    }

    /**
     * toOctetFromSextet().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @param <F> an Observable object
     * @param <G> an Observable object
     * @param <H> an Observable object
     * @return Octet
     */
    public static <A, B, C, D, E, F, G, H> Func2<Sextet<A, B, C, D, E, F>, Pair<G, H>,
            Octet<A, B, C, D, E, F, G, H>> toOctetFromSextet() {
        return (objects, objects2) -> Octet.with(objects.getValue0(), objects.getValue1(), objects.getValue2(),
                objects.getValue3(), objects.getValue4(), objects.getValue5(),
                objects2.getValue0(), objects2.getValue1());
    }

    /**
     * toOctetFromSeptet().
     *
     * @param <A> an Observable object
     * @param <B> an Observable object
     * @param <C> an Observable object
     * @param <D> an Observable object
     * @param <E> an Observable object
     * @param <F> an Observable object
     * @param <G> an Observable object
     * @param <T> an Observable object
     * @return Octet
     */
    public static <A, B, C, D, E, F, G, T> Func2<Septet<A, B, C, D, E, F, G>, T, Octet<A, B, C, D, E, F, G, T>>
        toOctetFromSeptet() {
        return (objects, t) -> Octet.with(objects.getValue0(), objects.getValue1(), objects.getValue2(),
                objects.getValue3(), objects.getValue4(), objects.getValue5(),
                objects.getValue6(), t);
    }
}

