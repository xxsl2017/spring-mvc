package com.test.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

	private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR;

	static {
		int corePoolSize = 20;
		int maximumPoolSize = 200;
		long keepAliveTime = 60;
		ArrayBlockingQueue workQueue = new ArrayBlockingQueue(corePoolSize * 2);// 有界阻塞队列，一旦创建了这样的缓存区，就不能再增加其容量。
		THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(corePoolSize,
				maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);
	}

	private ThreadPool() {
	}

	/**
	 * 提交一个Runnable任务用于执行
	 * 
	 * @param command
	 *            线程任务
	 */
	public static void execute(Runnable command) {
		THREAD_POOL_EXECUTOR.execute(command);
	}

}
