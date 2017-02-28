package com.ktds.jgu.board.board.vo;

public class IntroduceVO {

	private String name;
	private int age;
	private String home;
	private String hobby;
	private String like;
	
	public String getName() {
		System.out.println("Name Return");
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		System.out.println("Age Return");
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getHome() {
		System.out.println("Home Return");
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public String getHobby() {
		System.out.println("Hobby Return");
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getLike() {
		System.out.println("Like Return");
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	
	
}
