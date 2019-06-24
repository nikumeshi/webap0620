package com.azddi9

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/dba")
class DBAccess : HttpServlet(){
    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val text = req?.getParameter("selectData")
        val dbResult = text?.let { dbRun(text) }
        val tableItems = mutableListOf<String>()
        dbResult?.let {
            for (x in dbResult){
                tableItems.add("""
                    <tr>
                    <td>${x.id}</td>
                    <td>${x.name}</td>
                    <td>${x.pw}</td>
                    </tr>
                    
                """)
            }
        }

        resp?.writer?.println("""
            <html><head>
            <title>resultPage</title>
            <link rel="stylesheet" href="css/style.css">
            </head>
            <body>
            <div class="wrapper">
                <h3>search like "$text"</h3>
                <p></p>
                <table>
                    <tr><th>ID</th><th>NAME</th><th>PW</th></tr>
                    ${tableItems.joinToString()}
                </table>
            </div>
            </body></html>
        """)
    }
}