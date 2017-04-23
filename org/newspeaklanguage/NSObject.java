package org.newspeaklanguage;

import java.util.concurrent.ConcurrentLinkedQueue;

public class NSObject
{
	public long tag;
	public ConcurrentLinkedQueue<NSMessage> queue;
	
	/* Non-blocking send. */
	public void send(String selector, Object... args) {
		queue.add(new NSMessage(tag, selector, args));
	}
	
	/* Blocking send. */
	public void sync(String selector, Object... args) throws DoesNotUnderstand
	{
		
	}
	
	/* Blocking send that yields a result. */
	public Object get(String selector, Object... args) throws DoesNotUnderstand
	{
		return null;
	}
	
	/* Blocking send that yields an NSObject registered with the Java Process. */
	public NSObject getNSObj(String selector, Object... args) throws DoesNotUnderstand
	{
		return null;
	}
}


