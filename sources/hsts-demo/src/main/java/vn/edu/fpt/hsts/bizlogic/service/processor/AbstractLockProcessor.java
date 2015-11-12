/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 11/13/2015.
 */
package vn.edu.fpt.hsts.bizlogic.service.processor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * The wrapper of {@link ReentrantReadWriteLock}
 */
public class AbstractLockProcessor {
    /**
     * The locker.
     */
    private final ReentrantReadWriteLock locker = new ReentrantReadWriteLock(true);
    /**
     * <p>
     * Prevent other threads read and write.
     * </p>
     */
    protected final void lockWrite() {
        locker.writeLock().lock();
    }
    /**
     * <p>
     * Un-lock for other threads accessing.
     * </p>
     */
    protected final void unlockWrite() {
        locker.writeLock().unlock();
    }
    /**
     * <p>
     * Prevent other threads writes.
     * </p>
     */
    protected final void lockRead() {
        locker.readLock().lock();
    }
    /**
     * <p>
     * Un-lock for other threads writing.
     * </p>
     */
    protected final void unlockRead() {
        locker.readLock().unlock();
    }
}
