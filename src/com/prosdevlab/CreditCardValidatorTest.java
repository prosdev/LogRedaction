package com.prosdevlab;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CreditCardValidatorTest {
    private CreditCardValidator creditCardValidator = new CreditCardValidator();

    @Test
    public void containsCreditCardInfo() {
        String content =  "2016-12-12 00:05:37 Account: 3618 Updated Record: 52571 Fields: Content=\"Payment\", Type=\"Mortgage\", Industry=\"Finance\", FirstName=\"Fred\", LastName=\"Flintstone\", CC=\"5424-9259-6687-3767\"";
        boolean valid = creditCardValidator.validate(content);
        System.out.println("Contains credit card info! " + valid);
        Assert.assertEquals(valid, true);
    }

    @Test
    public void noCreditCardInfo() {
        String content = "2016-12-11 23:30:37 Account: 3618 Deleted record: 50866";
        boolean valid = creditCardValidator.validate(content);
        System.out.println("Contains credit card info! " + valid);
        Assert.assertEquals(valid, false);
    }

}
