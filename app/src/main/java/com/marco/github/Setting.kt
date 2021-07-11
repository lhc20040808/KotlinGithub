package com.marco.github

import com.marco.common.Preference

object Setting {
    var email: String by Preference(AppContext, "email", "")
    var password: String by Preference(AppContext, "password", "")
}