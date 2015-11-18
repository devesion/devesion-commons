package com.devesion.commons.utils.crypto;

import org.testng.annotations.Test;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.fest.assertions.Assertions.assertThat;

public class CryptoIdGeneratorTest {

	private static final int NUMBER_OF_LOOPS_IN_THREAD = 1000;
	private static final int NUMBER_OF_THREADS = 10;

	@Test
	public final void constructorShouldGenerateUniqueId() throws Exception {
		// given
		Set<String> aggregateIds = new ConcurrentSkipListSet<>();
		CyclicBarrier barrier = new CyclicBarrier(NUMBER_OF_THREADS);

		// when
		ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
		for (int i = 0; i < NUMBER_OF_THREADS; i++) {
			executorService.submit(new IdGeneratorExecutor(barrier, aggregateIds));
		}

		executorService.shutdown();
		executorService.awaitTermination(1, TimeUnit.DAYS);

		// then
		assertThat(aggregateIds).hasSize(NUMBER_OF_THREADS * NUMBER_OF_LOOPS_IN_THREAD);
	}

	private class IdGeneratorExecutor implements Runnable {

		private final CyclicBarrier barrier;
		private final Set<String> aggregateIds;

		public IdGeneratorExecutor(CyclicBarrier latch, Set<String> aggregateIds) {
			this.barrier = latch;
			this.aggregateIds = aggregateIds;
		}

		@Override
		public void run() {
			waitForOthers();
			buildAggregateKeysInLoop(aggregateIds, NUMBER_OF_LOOPS_IN_THREAD);
		}

		private void waitForOthers() {
			try {
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				throw new IllegalStateException(e);
			}
		}

		private void buildAggregateKeysInLoop(Collection<String> aggregateIds, int numberOfLoops) {
			for (int i = 0; i < numberOfLoops; i++) {
				String aggregateId = CryptoIdGenerator.generate();
				aggregateIds.add(aggregateId);
			}
		}
	}
}