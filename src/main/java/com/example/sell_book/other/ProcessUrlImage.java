package com.example.sell_book.other;

import org.springframework.util.StringUtils;

public class ProcessUrlImage {
    public  static String processUrlImage(String urlImage, String name) {
        if(StringUtils.hasText(urlImage)){
            String substring = urlImage.substring(urlImage.lastIndexOf("."));
            String str = name.concat(substring);
            return str;
        }
        return "";
    }
}
