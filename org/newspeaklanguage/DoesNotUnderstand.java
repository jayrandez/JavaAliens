package org.newspeaklanguage;

public class DoesNotUnderstand extends Exception
{
	NSObject receiver;
	String selector;
	
	public String toString() {
		String description = "[Receiver] does not understand selector " + selector;
		return description;
	}
}
