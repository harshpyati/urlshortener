package com.harsh.urlshortener.service.impl;

import com.harsh.urlshortener.service.IdEncoderService;

import javax.xml.stream.events.Characters;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IdEncoderServiceImpl implements IdEncoderService {
    public static final Map<Integer, Character> encoder = new HashMap<>();

    static {
        int i = 1;
        for (char c = 'a'; c <= 'z'; c++, i++) {
            encoder.put(i, c);
        }

        for (char c = 'A'; c <= 'Z'; c++, i++) {
            encoder.put(i, c);
        }

        for (int num = 0; num < 10; num++, i++) {
            encoder.put(i, (char) (num + '0'));
        }
    }

    @Override
    public String encodeId(Integer id) {
        StringBuilder sb = new StringBuilder();
        String binStringOfId = Integer.toBinaryString(id);
        List<String> binSubStrings = getBinSubStrings(binStringOfId);
        for (String binSubString : binSubStrings) {
            sb.append(binEncoder(binSubString));
        }
        return sb.toString();
    }

    public List<String> getBinSubStrings(String binString) {
        List<String> binSubStrings = new ArrayList<>();
        for (int i = 0; i < binString.length(); i += 6) {
            binSubStrings.add(binString.substring(i, Math.min(binString.length(), i + 6)));
        }
        return binSubStrings;
    }

    public char binEncoder(String binString) {
        Integer decValue = Integer.parseInt(binString, 2);
        return encoder.get(decValue);
    }
}
