package com.example.junit_parallel;
 
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadFactory;

import org.junit.runners.model.RunnerScheduler;

public class ParallelRunnerScheduler implements RunnerScheduler{
	
	/**
	 * Custom {@link ThreadFactory} is used in order to give the threads descriptive names.
	 * Alternatively, Guava's ThreadFactoryBuilder 
	 * or just {@link java.itil.concurrent.Executors#defaultThreadFactory} can be used instead.
	 */
	private final ThreadFactory threadFactory = new NamedThreadFactory("junit");
	
	private final ExecutorService threadPool = Executors.newCachedThreadPool(threadFactory);
	
	private final Phaser childCounter = new Phaser();

	@Override
	public void schedule(final Runnable childStatement) {
		threadPool.submit(new Runnable() {			
			@Override
			public void run() {
				childCounter.register();
				childStatement.run();			
				childCounter.arrive();
			}
		});		
	}

	@Override
	public void finished() {
		childCounter.awaitAdvance(0);		
	}

}
