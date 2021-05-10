package com.example.demotest.model;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class MemberTest {

    private Member member;
    private Member member1;
    private Member member2;

    @BeforeEach
    public void setUp() throws Exception {
        member = new Member("Poxos", 35, Gender.MALE);
        member1 = new Member("Petros", 34, Gender.MALE);
        member2 = new Member("Martiros", 7, Gender.MALE);
    }

    @Test
    void getAllMembers() {

        List<Member> expected = Member.getAllMembers();

        List<Member> actual = new ArrayList<>();
        //    actual.add(new Member("User1",1,Gender.FEMALE));
        actual.add(member);
        actual.add(member1);
        actual.add(member2);

        Assert.assertEquals(expected, actual);
    }

    @Test
    void getAllMembers_NO_NULL() {
        List<Member> expected = Member.getAllMembers();
        Assert.assertNotNull(expected);
    }

    @Test
    void getAllMembers_Male() {
//        Member member = new Member("Poxos", 35, Gender.MALE);
//        //  Member member1 = new Member("Petros", 34, Gender.MALE);
//        //  Member member2 = new Member("Marti", 7, Gender.FEMALE);

        List<Member> expected = Member.getAllMembers(Gender.MALE);
        List<Member> actual = new ArrayList<>();
        actual.add(member);
        Assert.assertEquals(expected, actual);

    }

    @Test
    void getAllMembers_MALE_NO_NULL() {

        List<Member> expected = Member.getAllMembers(Gender.MALE);
        Assert.assertNotNull(expected);
    }

    @Test
    void getAllMembers_Female() {
//        Member member = new Member("Poxos", 35, Gender.MALE);
//        Member member1 = new Member("Petros", 34, Gender.FEMALE);
//        Member member2 = new Member("Marti", 7, Gender.FEMALE);
        List<Member> expected = Member.getAllMembers(Gender.FEMALE);
        List<Member> actual = new ArrayList<>();
        actual.add(member1);
        actual.add(member2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    void getAllMembers_FEMALE_NO_NULL() {

        List<Member> expected = Member.getAllMembers(Gender.FEMALE);
        Assert.assertNotNull(expected);
    }

    @Test
    void getHowManyMembers() {
//        Member member = new Member("Poxos", 35, Gender.MALE);
//        Member member1 = new Member("Petros", 34, Gender.FEMALE);
//        Member member2 = new Member("Marti", 7, Gender.FEMALE);

        int expected = Member.getHowManyMembers();
        int actual = 3;

        Assert.assertEquals(expected, actual);
    }

    @Test
    void getHowManyMembers_MALE() {
//        Member member = new Member("Poxos", 35, Gender.MALE);
//        Member member1 = new Member("Petros", 34, Gender.FEMALE);
//        Member member2 = new Member("Marti", 7, Gender.FEMALE);

        int expected = Member.getHowManyMembers(Gender.MALE);
        int actual = 1;
        Assert.assertEquals(expected, actual);
    }

    @Test
    void getHowManyMembers_FEMALE() {
//        Member member = new Member("Poxos", 35, Gender.MALE);
//        Member member1 = new Member("Petros", 34, Gender.FEMALE);
//        Member member2 = new Member("Marti", 7, Gender.FEMALE);

        int expected = Member.getHowManyMembers(Gender.FEMALE);
        int actual = 2;
        Assert.assertEquals(expected, actual);
    }

    @Test
    void getAllAgeMembers() {
//        Member member = new Member("Poxos", 35, Gender.MALE);
//        Member member1 = new Member("Petros", 34, Gender.FEMALE);
//        Member member2 = new Member("Marti", 7, Gender.FEMALE);

        int expected = Member.getAllAgeMembers();
        int actual = 35 + 34 + 7;
        Assert.assertEquals(expected, actual);
    }

    @Test
    void getAllAgeUsers_MALE() {
//        Member member = new Member("Poxos", 35, Gender.MALE);
//        Member member1 = new Member("Petros", 34, Gender.FEMALE);
//        Member member2 = new Member("Marti", 7, Gender.FEMALE);

        int expected = Member.getAllAgeMembers(Gender.MALE);
        int actual = 35;
        Assert.assertEquals(expected, actual);
    }

    @Test
    void getAllAgeUsers_FEMALE() {
        Member member = new Member("Poxos", 35, Gender.MALE);
        Member member1 = new Member("Petros", 34, Gender.FEMALE);
        Member member2 = new Member("Marti", 7, Gender.FEMALE);

        int expected = Member.getAllAgeMembers(Gender.FEMALE);
        int actual = 34 + 7;
        Assert.assertEquals(expected, actual);
    }


}