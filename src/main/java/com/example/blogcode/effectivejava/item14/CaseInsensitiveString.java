package com.example.blogcode.effectivejava.item14;

/**
 * packageName    : com.example.blogcode.effectivejava.item14
 * fileName       : CaseInsensitiveString
 * author         : tkdwk567@naver.com
 * date           : 2022/07/19
 */
public class CaseInsensitiveString implements Comparable<CaseInsensitiveString> {

    private String s;

    @Override
    public int compareTo(CaseInsensitiveString cis) {
        return String.CASE_INSENSITIVE_ORDER.compare(s, cis.s);
    }
}
