package com.in28minules.springboot.learnspringboot;

public class Coures {
	private long id;
	private String name;
	private String author;
	
	public Coures(long id, String name, String author) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}
	
	@Override
	public String toString() {
		return "Coures [id=" + id + ", name=" + name + ", author=" + author + "]";
	}
	
}
