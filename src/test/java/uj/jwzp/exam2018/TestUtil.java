package uj.jwzp.exam2018;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestUtil {

    static String jsonContent(String file) {
        InputStream is = TestUtil.class.getResourceAsStream(file + ".json");
        StringBuilder b = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        br.lines().forEach(b::append);
        return b.toString();
    }
}
