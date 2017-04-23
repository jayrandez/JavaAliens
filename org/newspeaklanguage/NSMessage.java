package org.newspeaklanguage;

public class NSMessage
{
	public String selector;
	public Object[] args;
	
	public NSMessage(String selector, Object...args) {
		this.selector = selector;
		this.args = args;
	}
}
