package com.example.android_pract15_parcelable

import android.os.Parcel
import android.os.Parcelable

class Animal(_species:String,_yearBorn:Short,_monthBorn:UByte):Parcelable{
    var species:String
    var yearBorn:Short
    var monthBorn:UByte=1U
        get() = field
        set(value){
            if(value <1U || value>12U)
                throw Exception("Wrong month number")
            field=value
        }
    init {
        species=_species
        yearBorn=_yearBorn
        monthBorn=_monthBorn
    }


    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt().toShort(),
        parcel.readByte().toUByte()
    ) {
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(species)
        parcel.writeInt(yearBorn.toInt())
        parcel.writeByte(monthBorn.toByte())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Animal> {
        override fun createFromParcel(parcel: Parcel): Animal {
            return Animal(parcel)
        }

        override fun newArray(size: Int): Array<Animal?> {
            return arrayOfNulls(size)
        }
    }
}