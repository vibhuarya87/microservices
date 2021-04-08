package com.nagarro.user.body

import org.springframework.web.bind.annotation.ResponseBody

data class AuthorizedResponse(
    val accessToken: String
)
