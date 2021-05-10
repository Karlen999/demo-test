package com.example.demotest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Member {

    private int id;
    private String name;
    private int age;
    private Gender gender;
    private static Map<Integer, Member> allMembers = new HashMap<>();
    private static int countId = 0;

    public Member(String name, int age, Gender gender) {
//        if (allMembers == null){
//            allMembers = new HashMap<>();
//        }
        this.name = name;
        this.age = age;
        this.gender = gender;

        if (!hasMember()){
            countId++;
            this.id = countId;
            allMembers.put(id,this);
        }
    }

    private boolean hasMember() {

        for (Member member : allMembers.values()) {
            if (member.equals(this) && member.hashCode() == this.hashCode()){
                return true;
            }
        }
        return false;
    }

    public static List<Member> getAllMembers(){
        return new ArrayList<>(allMembers.values());
    }

    public static List<Member> getAllMembers(Gender gender){
        List<Member> listAllMembers = new ArrayList<>();
        for (Member member : allMembers.values()) {
            if (member.gender == gender){
                listAllMembers.add(member);
            }
        }
        return listAllMembers;
    }

    public static int getHowManyMembers(){
        return allMembers.size();
    }

    public static int getHowManyMembers(Gender gender){
        return getAllMembers(gender).size();
    }

    public static int getAllAgeMembers(){
        int countAge = 0;
        for (Member member:allMembers.values()){
            countAge += member.age;
        }
        return countAge;
    }

    public static int getAllAgeMembers(Gender gender){
        int countAge = 0;
        for (Member member : getAllMembers(gender)) {
            countAge += member.age;
        }
        return countAge;
    }

    public static int getAverageAgeOfAllMembers(){
        int avg = getAllAgeMembers() / getHowManyMembers();
        return avg;
    }

    public static int getAverageAgeOfAllMembers(Gender gender){
        return getAllAgeMembers(gender) / getHowManyMembers(gender);
    }







    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (id != member.id) return false;
        if (age != member.age) return false;
        if (name != null ? !name.equals(member.name) : member.name != null) return false;
        return gender == member.gender;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
