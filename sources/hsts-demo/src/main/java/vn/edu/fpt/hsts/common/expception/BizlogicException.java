/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.common.expception;

public class BizlogicException extends Exception {

    /**
     * The constructor
     * @param message
     * @param cause
     * @param args
     */
    public BizlogicException(final String message, final Throwable cause, final Object... args) {
        super(message, cause);
    }

    /**
     * The constructor
     * @param message
     */
    public BizlogicException(final String message) {
        this(message, null);
    }

    /**
     * The constructor
     * @param cause
     */
    public BizlogicException(final Throwable cause) {
        this(null, cause);
    }
}
