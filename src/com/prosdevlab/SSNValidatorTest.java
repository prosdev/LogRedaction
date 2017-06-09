package com.prosdevlab;

import org.junit.Assert;
import org.junit.Test;

public class SSNValidatorTest {
    private SSNValidator ssnValidator = new SSNValidator();

    @Test
    public void containsValidSSN() {
        String content =  "2016-12-12 01:16:19 Account: 3618 Updated Record: 85714 Fields: Content=\"Quote\", Type=\"Auto\", Industry=\"Insurance\", FirstName=\"Fred\", LastName=\"Flintstone\", SSN=\"620-07-3092\"";
        boolean valid = ssnValidator.validate(content);
        System.out.println("Contains SSN info! " + valid);
        Assert.assertEquals(valid, true);
    }

    @Test
    public void noValidSSN() {
        String content =  "2016-12-12 00:05:37 Account: 3618 Updated Record: 52571 Fields: Content=\"Payment\", Type=\"Mortgage\", Industry=\"Finance\", FirstName=\"Fred\", LastName=\"Flintstone\", CC=\"5424-9259-6687-3767\"";
        boolean valid = ssnValidator.validate(content);
        System.out.println("Contains SSN info! " + valid);
        Assert.assertEquals(valid, false);
    }
}
