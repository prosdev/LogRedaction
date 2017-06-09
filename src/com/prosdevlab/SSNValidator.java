package com.prosdevlab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//https://www.codeproject.com/Articles/651609/Validating-Social-Security-Numbers-through-Regular
public class SSNValidator {
    private Pattern pattern;
    private Matcher matcher;

    private static final String SSN_REGEX= "SSN=\\\"[0-9]{3}-[0-9]{2}-[0-9]{4}\\\"";

    public SSNValidator() {
        pattern = Pattern.compile(SSN_REGEX);
    }

    /**
     *
     * @param content
     * @return boolean
     */
    public boolean validate(String content) {
        matcher = pattern.matcher(content);
        boolean isValid = false;

        isValid = matcher.find();

        return isValid;
    }
}
