package com.dao;

public class bookDao {
	String bookNum;
    String bookName;
    String author;
    String type;
	int remainNum;
	
    
    public bookDao(){
    	super();
    }
    public  bookDao(String bookNum ,String bookName,String author){
    	this.bookNum=bookNum;
    	this.bookName=bookName;
    	this.author=author;
    }
    
    public String getBookNum() {
		return bookNum;
	}
	public void setBookNum(String bookNum) {
		this.bookNum = bookNum;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getType() {
		return type;
	}
	
	public int getRemainNum() {
		return remainNum;
	}
	public void setRemainNum(int remainnum) {
		this.remainNum = remainnum;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
