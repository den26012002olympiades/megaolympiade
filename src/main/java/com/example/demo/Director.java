package com.example.demo;

public record Director(Integer id, String fio) {
}

/*
* id - целочисленный уникальный идентификатор записи
* fio - ФИО режиссера, строка до 100 символов
* */