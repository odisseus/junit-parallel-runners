package com.example.junit_parallel;

import org.junit.runners.model.InitializationError;


public class ParallelRunners {
	
	public static class BlockJUnit4ClassRunner extends org.junit.runners.BlockJUnit4ClassRunner {
		public BlockJUnit4ClassRunner(Class<?> klass)
				throws InitializationError {
			super(klass);
			this.setScheduler(new ParallelRunnerScheduler());
		}		
	}
	
	private ParallelRunners() {}

}
