package org.newspeaklanguage;

import java.util.concurrent.ConcurrentLinkedQueue;

public class NSObject
{
	private ConcurrentLinkedQueue<NSMessage> messageQueue;
	
	/* Asynchronous, non-blocking send. */
	public void send(String selector, Object... args) {
		messageQueue.add(new NSMessage(selector, args));
	}
	
	/* Synchronous, blocking send. */
	public void syncSend(String selector, Object... args) throws DoesNotUnderstand {
		
	}
	
	/* Synchronous, blocking send that yields a result. */
	public Object getSyncSend(String selector, Object... args) throws DoesNotUnderstand {
		return null;
	}
}
