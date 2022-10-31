package com.springboot.helloprac.parser;

import com.springboot.helloprac.domain.Hospital;

public interface Parser<Parser> {
    Parser parse(String str);
}
