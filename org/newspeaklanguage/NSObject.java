package org.newspeaklanguage;

import java.util.concurrent.ConcurrentLinkedQueue;

public class NSObject
{
	private long tag;
	private ConcurrentLinkedQueue<NSMessage> queue;
	
	/* Only Newspeak can provide the queue needed for a registered NSObject. */
	public NSObject(long tag, ConcurrentLinkedQueue<NSMessage> queue) {
		this.tag = tag;
		this.queue = queue;
	}
	
	/* Non-blocking send. */
	public void send(String selector, Object... args) {
		queue.add(new NSMessage(false, tag, selector, args));
	}
	
	/* Blocking send, yields a result. */
	public Object sync(String selector, Object... args)
	{
		NSMessage message = new NSMessage(true, tag, selector, args);
		queue.add(message);
		
		synchronized(message) {
			while(message.pending) {
				try { message.wait(); }
				catch (InterruptedException e) {}
			}
		}
		
		return message.result;
	}

	/* Convenience, if user knows result is an NSOBject. */
	public NSObject syncNS(String selector, Object... args)
	{
		return (NSObject)this.sync(selector, args);
	}
	
	@SuppressWarnings("unused")
	
	private class NSMessage
	{
		public long tag;
		public String selector; 
		public Object[] args;
		
		public boolean pending;
		public Object result;
		
		public NSMessage(boolean pending, long tag, String selector, Object...args) {
			this.tag = tag;
			this.selector = selector;
			this.args = args;
			
			this.pending = pending;
			this.result = null;
		}
	}
}
