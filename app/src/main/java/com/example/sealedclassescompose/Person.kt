package com.example.sealedclassescompose

class Person(val name: String,val gender: Person.Gender) {
    sealed class Gender{
        object Male: Gender()
        object Female: Gender()
    }
}
