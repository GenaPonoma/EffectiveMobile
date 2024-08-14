package com.example.homework

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class User(
    var lastName: String?,
    var firstName: String?,
    var patronymic: String?
) : Parcelable {
}