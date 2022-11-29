package com.example.pasport.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class User {
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null

    var name:String? = null
    var surname:String? = null
    var number:String? = null
    var image:String? = null


    constructor()

    constructor(id: Int?, name: String?, surname: String?, number: String?, image: String?) {
        this.id = id
        this.name = name
        this.surname = surname
        this.number = number
        this.image = image }

    constructor(name: String?, surname: String?, number: String?, image: String?) {
        this.name = name
        this.surname = surname
        this.number = number
        this.image = image
    }


}