package com.azddi9

import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

lateinit var statement: Statement
val connection: Connection by lazy { DriverManager.getConnection("jdbc:mariadb://localhost:3306/test","nikumeshi","toriniku") }
const val TABLE_NAME = "todo_user"

fun dbRun(text: String): MutableSet<DBdataClass>{
    create()
    val item: MutableSet<DBdataClass> = executeSelect(text)
    close()
    return item
}

fun create(){
    Class.forName("org.mariadb.jdbc.Driver")
    statement = connection.createStatement()
}

fun executeSelect(text: String): MutableSet<DBdataClass>{
    val resultSet = statement.executeQuery("SELECT * FROM "+ TABLE_NAME + " WHERE NAME LIKE '$text%';")
    if (!resultSet.first()) return mutableSetOf()

    val returnItem = mutableSetOf<DBdataClass>()
    do {
        returnItem.add(DBdataClass(
            resultSet.getString("ID"),
            resultSet.getString("NAME"),
            resultSet.getString("PASSWORD")))
    }while (resultSet.next())
    resultSet.close()

    return returnItem
}

fun close(){
    statement.close()
    connection.close()
}