package com.example.blogcode.inheritance;

/**
 * packageName    : com.example.blogcode.inheritance
 * fileName       : Character
 * author         : tkdwk567@naver.com
 * date           : 2022/07/09
 */

public class Character implements BasicAttack, Skill {

    private String weapon;
    private int level;
    private long hp;
    private long mp;

    // 스텟 클래스를 따로 만들어 참조하는 식으로 하는게 좋을거 같다!
    private int str; // 스텟
    private int dex; // 스텟
    private int intelligence; // 스텟
    private int lux; // 스텟


    @Override
    public void basicSkill() {
        // 구현...
    }
}
