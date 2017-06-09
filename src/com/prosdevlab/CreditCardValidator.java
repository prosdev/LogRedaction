package com.prosdevlab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditCardValidator {
    private Pattern pattern;
    private Matcher matcher;

    //This will find any sequence of 13 to 16 digits, allowing dashes in between.
    private static final String CREDIT_CARD_PATTERN= "\\b(?:\\d[ -]*?){13,16}\\b";

    public CreditCardValidator() {
        pattern = Pattern.compile(CREDIT_CARD_PATTERN);
    }

    /**
     * Will return true if, and only if, a subsequence of the passed in content
     * matches given pattern.
     *
     * @param content
     * @return boolean
     */
    public boolean validate(final String content) {
        matcher = pattern.matcher(content);
        boolean isValid = matcher.find();
        return isValid;
    }
}
