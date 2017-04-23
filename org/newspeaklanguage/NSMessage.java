package org.newspeaklanguage;

public class NSMessage
{
	public long tag;
	public String selector;
	public Object[] args;
	
	public NSMessage(long tag, String selector, Object...args) {
		this.tag = tag;
		this.selector = selector;
		this.args = args;
	}
}
