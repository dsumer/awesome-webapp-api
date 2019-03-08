package com.awesome.webapp.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

const val GREETING_NAME_PARAM = "greetingName"

@RestController
class GreetingController {

    @GetMapping("/greeting")
    fun greeting(req: HttpServletRequest): String {
        var name = "World"
        var sessionName = req.getSession()?.getAttribute(GREETING_NAME_PARAM)
        if (sessionName != null && sessionName is String) {
            name = sessionName
        }

        return "Hello, $name"
    }

    @PostMapping("/greeting")
    fun saveGreeting(@RequestBody name: String, req: HttpServletRequest) {
        req.getSession().setAttribute(GREETING_NAME_PARAM, name)
    }
}