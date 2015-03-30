package cz.senkadam.gatlingsql.completetest

import cz.senkadam.gatlingsql.actions.{DBWithSettings, ConnectionFactory}
import cz.senkadam.gatlingsql.datasource.DataSourceBuilder
import cz.senkadam.gatlingsql.requests._
import cz.senkadam.gatlingsql.requests.Predef._
import io.gatling.core.Predef._

/**
 * Created by senk on 21.1.15.
 */
class Simulation1 extends Simulation{
  //ConnectionFactory.setConnection(DBWithSettings("X","X","X"))
  val conn = ConnectionFactory.connection
  val sqlWithSources=sql("SQL Queries",DataSourceBuilder.randomizedDataSource(csv("testDataJavaDB.csv")))
  val createTableStudents="""CREATE TABLE students
                            (ID int,
                            NAME varchar(255))"""
  val createTableTeachers="""CREATE TABLE teachers
                            (ID int,
                            NAME varchar(255))"""

  val insertS1="""INSERT INTO students VALUES (1,'JAN')"""
  val insertS2="""INSERT INTO students VALUES (2,'MARIE')"""
  val insertS3="""INSERT INTO students VALUES (3,'JOSEF')"""

  val insertT1="""INSERT INTO students VALUES (4,'NOVAK')"""
  val insertT2="""INSERT INTO students VALUES (5,'CERNY')"""
  val insertT3="""INSERT INTO students VALUES (6,'VOKATA')"""

  SqlUpdateStatement(createTableStudents).executeQuery(conn)
  SqlUpdateStatement(createTableTeachers).executeQuery(conn)

  SqlUpdateStatement(insertS1).executeQuery(conn)
  SqlUpdateStatement(insertS2).executeQuery(conn)
  SqlUpdateStatement(insertS3).executeQuery(conn)

  SqlUpdateStatement(insertT1).executeQuery(conn)
  SqlUpdateStatement(insertT2).executeQuery(conn)
  SqlUpdateStatement(insertT3).executeQuery(conn)
  //the whitespaces are neccessary
  val sqlQuery="SELECT NAME FROM ${table}$ WHERE ID = ${id}$"
  val sqlAllQuery="SELECT * FROM ${table}$ WHERE ID = ${id}$"

  def scn =
    scenario("test").repeat(1){exec(sqlWithSources.preparedQuery("NAME QUERY",sqlQuery)).
      exec(sqlWithSources.preparedQuery("ALL QUERY1",sqlAllQuery)).
      exec(sqlWithSources.preparedQuery("ALL QUERY2",sqlAllQuery)).
      exec(sqlWithSources.preparedQuery("ALL QUERY3",sqlAllQuery))}


  //Gatling EL for ${randomNum}"


  setUp(scn.inject(atOnceUsers(10)))
    .protocols(new SqlProtocol)

}


