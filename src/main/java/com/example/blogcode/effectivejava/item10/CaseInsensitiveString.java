package com.example.blogcode.effectivejava.item10;

/**
 * packageName    : com.example.blogcode.effectivejava.item10
 * fileName       : CaseInsensitiveString
 * author         : tkdwk567@naver.com
 * date           : 2022/07/10
 */
public class CaseInsensitiveString {

    private final String s;

    public CaseInsensitiveString(String s) {
        this.s = s;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CaseInsensitiveString)
            return s.equalsIgnoreCase(((CaseInsensitiveString) obj).s);
        if (obj instanceof String)
            return s.equalsIgnoreCase((String) obj);
        return false;
    }
}
