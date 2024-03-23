package com.example.demo;

import java.sql.Time;

public record Movie(Integer id,
                    String title,
                    Integer year,
                    Integer director,
                    Time length,
                    Integer rating) {
}

/*
* 1. id - целочисленный уникальный идентификатор записи

2. title - название кинофильма, строка до 100 символов

3. year - год выпуска, целое число от 1900 до 2100;

4. director - id режиссера;

5. length - продолжительность фильма, тип - время;

6. rating - рейтинг фильма (целое число от 0 до 10).
* */