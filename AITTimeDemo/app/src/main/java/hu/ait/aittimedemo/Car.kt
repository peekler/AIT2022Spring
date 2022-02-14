package hu.ait.aittimedemo

open class Vehicle(var name: String){

}


class Car(name: String, var type: String) : Vehicle(name) {

    init {

    }


    fun print(): String {
        return type
    }
}