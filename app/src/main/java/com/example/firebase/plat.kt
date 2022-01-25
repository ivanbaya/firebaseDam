package com.example.firebase

import com.google.firebase.firestore.ServerTimestamp
import java.util.*


class plat {
    var nom: String? = null
    var descripcio: String? = null
    var preu: Double? = null
    private var mTimestamp: Date? = null

    constructor() {} // Needed for Firebase
    constructor(nom: String?, descripcio: String?, preu: Double?) {
        this.nom = nom
        this.descripcio = descripcio
        this.preu = preu
    }

    @get:ServerTimestamp
    var timestamp: Date?
        get() = mTimestamp
        set(timestamp) {
            mTimestamp = timestamp
        }
}