package com.example.junit_parallel;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;

@RunWith(ParallelRunners.BlockJUnit4ClassRunner.class)
public class ParallelTest{
	
	@Before
	public void setUp() throws Exception {
		System.out.println("Setup method is called from thread " + Thread.currentThread().getName());
	}
	
	@After
	public void tearDown() throws Exception {
		System.out.println("Teardown method is called from thread " + Thread.currentThread().getName());
	}	
	
	@Rule
	public ExternalResource resource = new ExternalResource() {
		protected void before() throws Throwable {
			System.out.println("Rule is initialized from thread " + Thread.currentThread().getName());
		};
		protected void after() {
			System.out.println("Rule is finalized from thread " + Thread.currentThread().getName());
		};
	};
	
	@Test
	public void testSuccess() throws Exception {
		System.out.println("Test 1 says hello from thread " + Thread.currentThread().getName());
		Thread.sleep(1000);
		assertEquals(1, 1);
		Thread.sleep(1000);
		System.out.println("Test 1 says bye!");
	}
	
	@Ignore("Activate this test to see how failures are handled")
	@Test
	public void testFailure() throws Exception {
		System.out.println("Test 2 says hello from thread " + Thread.currentThread().getName());
		Thread.sleep(1000);
		assertEquals(1, 2);
		System.out.println("Test 2 says bye!");
	}
	
	@Ignore("Activate this test to see how exceptions are handled")
	@Test
	public void testThrow() throws Exception {
		System.out.println("Test 3 says hello from thread " + Thread.currentThread().getName());
		Thread.sleep(1000);
		throw new Exception("Oops!");
	}
}
