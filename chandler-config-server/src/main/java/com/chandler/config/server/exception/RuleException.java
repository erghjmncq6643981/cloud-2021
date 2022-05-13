/*
 *
 * Please contact chandler
 * if you need additional information or have any questions.
 * Please contact chandler Corporation or visit:
 * https://www.jianshu.com/u/117796446366
 *
 * @author 钱丁君-chandler
 * @version 1.0
 */
package com.chandler.config.server.exception;

/**
 *
 * @author 钱丁君-chandler 2021/3/10 2:15 PM
 * @version 1.0.0
 * @since 1.8
 */
public class RuleException extends RuntimeException {

    private int code;

    public RuleException(int code, String message) {
        super(message);
        this.code = code;
    }

    public RuleException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
