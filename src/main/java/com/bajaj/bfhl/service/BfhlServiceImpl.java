package com.bajaj.bfhl.service;

import com.bajaj.bfhl.dto.BfhlRequest;
import com.bajaj.bfhl.dto.BfhlResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Value("${bfhl.user-id:diwanshu_baskota_16082003}")
    private String userId;

    @Value("${bfhl.email:diwanshu1618.be23@chitkarauniversity.edu.in}")
    private String email;

    @Value("${bfhl.roll-number:2311981618}")
    private String rollNumber;

    @Override
    public BfhlResponse processData(BfhlRequest request) {
        List<String> evenNumbers = new ArrayList<>();
        List<String> oddNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        BigInteger sumVal = BigInteger.ZERO;
        List<String> originalAlphabets = new ArrayList<>();

        if (request != null && request.getData() != null) {
            for (String item : request.getData()) {
                if (item == null) {
                    continue;
                }
                if (isNumeric(item)) {
                    BigInteger val = new BigInteger(item);
                    sumVal = sumVal.add(val);
                    if (val.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
                        evenNumbers.add(item);
                    } else {
                        oddNumbers.add(item);
                    }
                } else if (isAlphabetic(item)) {
                    alphabets.add(item.toUpperCase());
                    originalAlphabets.add(item);
                } else {
                    specialCharacters.add(item);
                }
            }
        }

        String concatString = buildConcatString(originalAlphabets);

        return new BfhlResponse(
                true,
                userId,
                email,
                rollNumber,
                evenNumbers,
                oddNumbers,
                alphabets,
                specialCharacters,
                sumVal.toString(),
                concatString
        );
    }

    private boolean isNumeric(String str) {
        if (str.isEmpty()) {
            return false;
        }
        return str.matches("^-?\\d+$");
    }

    private boolean isAlphabetic(String str) {
        if (str.isEmpty()) {
            return false;
        }
        return str.matches("^[a-zA-Z]+$");
    }

    private String buildConcatString(List<String> originalAlphabets) {
        if (originalAlphabets.isEmpty()) {
            return "";
        }
        StringBuilder joined = new StringBuilder();
        for (String s : originalAlphabets) {
            joined.append(s);
        }

        String joinedStr = joined.reverse().toString();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < joinedStr.length(); i++) {
            char c = joinedStr.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
        }
        return result.toString();
    }
}
