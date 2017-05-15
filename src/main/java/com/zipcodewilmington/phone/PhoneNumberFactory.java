package com.zipcodewilmington.phone;

import com.zipcodewilmington.exceptions.InvalidPhoneNumberFormatException;
import com.zipcodewilmington.tools.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by leon on 5/1/17.
 */
public final class PhoneNumberFactory {
    private static final Logger logger = LoggerFactory.getLogger(PhoneNumberFactory.class);
    private int areaCode;
    private int centralOffice;
    private int phoneLine;

    private PhoneNumberFactory() {
        /** This constructor is private
         *  This class is uninstantiable */
    }

    /**
     * @param phoneNumberCount - number of PhoneNumber objects to instantiate
     * @return array of randomly generated PhoneNumber objects
     */ //TODO - Implement logic
    public static PhoneNumber[] createRandomPhoneNumberArray(int phoneNumberCount) {
        PhoneNumber[] numbers = new PhoneNumber[phoneNumberCount];
        int numberOfRandoms = 0;
        while (numberOfRandoms < phoneNumberCount) {
            PhoneNumber number = createRandomPhoneNumber();
            if (number != null) {
                numbers[numberOfRandoms] = number;
                numberOfRandoms++;
            }
        }

        return numbers;
    }

    /**
     * @return an instance of PhoneNumber with randomly generated phone number value
     */ //TODO - Implement logic
    private static PhoneNumber createRandomPhoneNumber() {
        return createPhoneNumberSafely(RandomNumberFactory.createInteger(100, 999),
                RandomNumberFactory.createInteger(100, 999), RandomNumberFactory.createInteger(1000, 9999));
    }


    /**
     * @param areaCode          - 3 digit code
     * @param centralOfficeCode - 3 digit code
     * @param phoneLineCode     - 4 digit code
     * @return a new phone number object
     */ //TODO - if input is valid, return respective PhoneNumber object, else return null
    public static PhoneNumber createPhoneNumberSafely(int areaCode, int centralOfficeCode, int phoneLineCode) {
        String numberAsString = formatPhoneNumberToString(areaCode, centralOfficeCode, phoneLineCode);
        try {
            logger.info("Attempting to create a new PhoneNumber object with a value of " + numberAsString);
            return createPhoneNumber(numberAsString);
        } catch(InvalidPhoneNumberFormatException e) {
            logger.warn(numberAsString.toString() + "is not a valid phone number");
            return null;
        }
    }

    private static String formatPhoneNumberToString(int areaCode, int centralOfficeCode, int phoneLineCode) {
        return String.format("(%d)-%d-%d", areaCode, centralOfficeCode, phoneLineCode);
    }

    /**
     * @param phoneNumberString - some String corresponding to a phone number whose format is `(###)-###-####`
     * @return a new phone number object
     * @throws InvalidPhoneNumberFormatException - thrown if phoneNumberString does not match acceptable format
     */ // TODO - Add throws statement to method signature
    public static PhoneNumber createPhoneNumber(String phoneNumberString) throws InvalidPhoneNumberFormatException {
        return new PhoneNumber(phoneNumberString);
    }
}