package com.test.cleanarchitecturewithdi.models

import com.google.gson.annotations.SerializedName


data class SignIn (

    @SerializedName("Login") var Login : String,
    @SerializedName("Password") var Password : String

)

data class SignInResponse (

    @SerializedName("id") var id : Int,
    @SerializedName("login") var login : String,
    @SerializedName("name") var name : String,
    @SerializedName("isActive") var isActive : Boolean,
    @SerializedName("hrM_EmployeeId") var hrMEmployeeId : String,
    @SerializedName("isBiometricLogin") var isBiometricLogin : Boolean,
    @SerializedName("isEmailOTPEnabled") var isEmailOTPEnabled : String,
    @SerializedName("isSmslOTPEnabled") var isSmslOTPEnabled : String,
    @SerializedName("refreshToken") var refreshToken : String,
    @SerializedName("userRoles") var userRoles : List<UserRoles>,
    @SerializedName("token") var token : String

)

data class UserRoles (

    @SerializedName("userLogin") var userLogin : String,
    @SerializedName("roleName") var roleName : String

)