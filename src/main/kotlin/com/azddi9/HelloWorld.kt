package com.azddi9

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/hello")
class HelloWorld : HttpServlet(){
    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val hello = "Hello ${req?.getParameter("hogeText")}"
        resp?.writer?.println(hello)
    }
}