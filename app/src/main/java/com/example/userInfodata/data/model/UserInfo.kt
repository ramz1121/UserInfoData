package com.corona.java.userinfo.model

import androidx.databinding.BaseObservable

/*Model Class*/

 class UserInfo(
    var id: Int? = null,
    var name: String? = null,
    var email: String? = null,
    var phone: String? = null): BaseObservable(){
}