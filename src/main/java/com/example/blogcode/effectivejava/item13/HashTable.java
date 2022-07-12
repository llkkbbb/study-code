package com.example.blogcode.effectivejava.item13;

/**
 * packageName    : com.example.blogcode.effectivejava.item13
 * fileName       : HashTable
 * author         : tkdwk567@naver.com
 * date           : 2022/07/12
 */
public class HashTable implements Cloneable {

    private Entry[] buckets;

    public HashTable() {
        this.buckets = new Entry[5];
    }

    public Entry[] getBuckets() {
        return buckets;
    }

    private static class Entry {
        final Object key;
        Object value;
        Entry next;

        public Entry(Object key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        //        Entry deepCopy() {
//            return new Entry(key, value, next == null ? null : next.deepCopy());
//        }

        Entry deepCopy() {
            Entry result = new Entry(key, value, next);
            for (Entry p = result; p.next != null; p = p.next) {
                p.next = new Entry(p.next.key, p.next.value, p.next.next);
            }
            return result;
        }
    }


    //    @Override
//    public HashTable clone() {
//        try {
//            HashTable result = (HashTable) super.clone();
//            result.buckets = new Entry[buckets.length];
//            for (int i = 0; i < buckets.length; i++)
//                if (buckets[i] != null)
//                    result.buckets[i] = buckets[i].deepCopy();
//            return result;
//        } catch (CloneNotSupportedException e) {
//            throw new AssertionError();
//        }
//    }


//    @Override
//    public HashTable clone() {
//        try {
//            HashTable result = (HashTable) super.clone();
//            result.buckets = buckets.clone();
//            return result;
//        } catch (CloneNotSupportedException e) {
//            throw new AssertionError();
//        }
//    }


//    @Override
//    protected final Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }
}
