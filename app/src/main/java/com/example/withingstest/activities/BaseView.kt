package com.example.withingstest.activities

interface BaseView<P: BasePresenter> {
    val presenter : P
}