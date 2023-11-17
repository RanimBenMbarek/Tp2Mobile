package com.example.myapplication

class Student {
    constructor(nom: String, prenom: String, genre: String,matiere:String) {
        this.nom = nom
        this.prenom = prenom
        this.genre = genre
        this.matiere=matiere
        this.etat=false

    }

    lateinit var nom : String
    lateinit var prenom : String
    lateinit var genre : String
    lateinit var matiere : String
    var etat : Boolean


}