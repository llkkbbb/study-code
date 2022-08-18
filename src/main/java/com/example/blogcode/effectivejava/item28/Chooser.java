package com.example.blogcode.effectivejava.item28;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Chooser<T> {
    private final List<T> choiceArray;

    public Chooser(Collection<T> choices) {
        this.choiceArray = new ArrayList<>(choices);
    }

    public Object choose() {
        Random random = ThreadLocalRandom.current();
        return choiceArray.get(random.nextInt(choiceArray.size()));
    }
}
