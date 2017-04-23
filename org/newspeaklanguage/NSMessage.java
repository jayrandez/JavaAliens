package org.newspeaklanguage;

public class NSMessage
{
	private String selector;
	private Object[] args;
	
	public NSMessage(String selector, Object...args) {
		this.selector = selector;
		this.args = args;
	}
}
