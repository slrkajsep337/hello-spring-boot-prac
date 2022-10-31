package com.springboot.helloprac.parser;

import com.springboot.helloprac.domain.Hospital;

public interface Parser<T> {
    T parse(String str);
}
