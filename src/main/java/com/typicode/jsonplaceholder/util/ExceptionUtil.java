package com.typicode.jsonplaceholder.util;

import org.apache.commons.lang3.StringUtils;

public class ExceptionUtil extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private static String mDashes = "\n" + StringUtils.repeat("-",20) +"\n";

    public ExceptionUtil() {
        // do nothing
    }

    public ExceptionUtil(String sMessage) {
        super(mDashes +sMessage +mDashes);
    }

    public ExceptionUtil(String sMessage, Throwable throwable) {
        super(sMessage, throwable);
    }

    public ExceptionUtil(Throwable throwable) {
        super(throwable);
    }


}
