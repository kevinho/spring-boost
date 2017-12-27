package io.kvh.springkit;

import org.springframework.stereotype.Service;

/**
 *
 * @author changbinhe
 * @date 2017/5/3
 */
@Service
public class StringCensorService {

    public String censor(String input) {
        return censor(input, 50);
    }

    public String censor(String input, int percent) {
        //emtpy or length = 1
        if (StringUtils.isEmpty(input) || input.length() == 1) {
            return input;
        }

        int subLen = input.length() * percent / 100;

        int start = (input.length() - subLen) / 2;
        int end = (input.length() + subLen) / 2;

        if (start < 0) {
            start = 0;
        }

        if (end > input.length()) {
            end = input.length() - 1;
        }

        StringBuilder stringBuilder = new StringBuilder(input);

        for (int i = start; i <= end; i++) {
            stringBuilder.setCharAt(i, '*');
        }

        return stringBuilder.toString();
    }

}
