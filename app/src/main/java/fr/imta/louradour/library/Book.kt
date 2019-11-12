package fr.imta.louradour.library

data class Book(val isbn: String, val title: String, val price: String, val cover: String){
    fun getFormattedPrice(): String{
        return "$price €"
    }
}