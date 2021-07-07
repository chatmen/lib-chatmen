package chatmen.udb.persistence.db

import java.time.LocalDateTime
import slick.jdbc.JdbcProfile
import ixias.persistence.model.Table
import chatmen.udb.model.User
import chatmen.udb.model.UserEachRelation

// テーブル定義
//~~~~~~~~~~~~~~
case class UserEachRelationTable[P <: JdbcProfile]()(implicit val driver: P)
    extends Table[UserEachRelation, P] with SlickColumnTypes[P] {
  import api._

  // --[ DNS定義 ] -------------------------------------------------------------
  lazy val dsn = Map(
    "master" -> DataSourceName("ixias.db.mysql://master/chatmen_udb"),
    "slave"  -> DataSourceName("ixias.db.mysql://slave/chatmen_udb")
  )

  // --[ クエリー定義 ] --------------------------------------------------------
  class Query extends BasicQuery(new Table(_)) {}
  lazy val query = new Query

  // --[ テーブル定義 ] --------------------------------------------------------
  class Table(tag: Tag) extends BasicTable(tag, "userEachRelation") {
    // Columns
    def relationid= column[UserEachRelation.Id]  ("relationid", O.UInt64, O.PrimaryKey, O.AutoInc)
    def fromid    = column[User.Id]              ("fromid",     O.UInt64)
    def targetid  = column[User.Id]              ("targetid",   O.UInt64)
    def updatedAt = column[LocalDateTime]        ("updated_at", O.TsCurrent)
    def createdAt = column[LocalDateTime]        ("created_at", O.Ts)

    // All columns as a tuple
    type TableElementTuple = (
     Option[ UserEachRelation.Id], User.Id, User.Id, LocalDateTime, LocalDateTime
    )

    // The * projection of the table
    def * = (relationid.?, fromid, targetid, updatedAt, createdAt) <> (
      (t: TableElementTuple) => {
        UserEachRelation(t._1, t._2, t._3, t._4, t._5)
      },
      (v: TableElementType)  => UserEachRelation.unapply(v).map { t => (
        t._1,t._2, t._3,
        LocalDateTime.now(), t._5
      ) }
    )
  }
}
