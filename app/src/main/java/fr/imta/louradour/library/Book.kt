package fr.imta.louradour.library

import android.os.Parcel
import android.os.Parcelable

data class Book(val isbn: String, val title: String, val price: String, val cover: String, val synopsis: List<String>){
    fun getFormattedPrice(): String{
        return "$price €"
    }

    fun getFormattedSynopsys(): String{
        return synopsis.joinToString("\n\n")
    }
}