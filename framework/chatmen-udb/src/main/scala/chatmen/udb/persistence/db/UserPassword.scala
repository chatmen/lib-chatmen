package chatmen.udb.persistence.db

import java.time.LocalDateTime
import slick.jdbc.JdbcProfile
import ixias.persistence.model.Table
import chatmen.udb.model.{User, UserPassword}

// テーブル定義
//~~~~~~~~~~~~~~
case class UserPasswordTable[P <: JdbcProfile]()(implicit val driver: P)
    extends Table[UserPassword, P] with SlickColumnTypes[P] {
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
  class Table(tag: Tag) extends BasicTable(tag, "chatmen_user_password") {
    // Columns
    def uid       = column[User.Id]       ("uid",        O.UInt64, O.PrimaryKey)
    def hash      = column[String]        ("hash",       O.AsciiChar255)
    def updatedAt = column[LocalDateTime] ("updated_at", O.TsCurrent)
    def createdAt = column[LocalDateTime] ("created_at", O.Ts)

    // All columns as a tuple
    type TableElementTuple = (
      User.Id, String, LocalDateTime, LocalDateTime
    )

    // The * projection of the table
    def * = (uid, hash, updatedAt, createdAt) <> (
      //The bidirectional mappings : Tuple(table) => Model
      (t: TableElementTuple) => UserPassword(
        Some(t._1), t._2, t._3, t._4
      ),
      //The bidirectional mappings : Model => Tuple(table)
      (v: TableElementType)  => UserPassword.unapply(v).collect {
        case t if t._1.isDefined => (
          t._1.get, t._2, LocalDateTime.now(), t._4
        )
      }
    )
  }
}
