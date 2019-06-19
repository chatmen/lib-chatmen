package chatmen.udb.persistence.db

import java.time.LocalDateTime
import slick.jdbc.JdbcProfile
import ixias.persistence.model.Table
import chatmen.udb.model.User


// テーブル定義
//~~~~~~~~~~~~~~
case class UserTable[P <: JdbcProfile]()(implicit val driver: P)
    extends Table[User, P] with SlickColumnTypes[P] {
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
  class Table(tag: Tag) extends BasicTable(tag, "chatmen_user") {

    // Columns
    /* @1 */ def uid         = column[User.Id]        ("id",          O.UInt64, O.PrimaryKey, O.AutoInc)
    /* @2 */ def name        = column[String]         ("name",        O.Utf8Char255)
    /* @3 */ def email       = column[String]         ("email",       O.AsciiChar255)
    /* @4 */ def phoneNumber = column[Option[Int]]    ("phoneNumber", O.AsciiChar16)
    /* @5 */ def updatedAt   = column[LocalDateTime]  ("updated_at",  O.TsCurrent)
    /* @6 */ def createdAt   = column[LocalDateTime]  ("created_at",  O.Ts)

    // Indexes
    def ukey01 = index("ukey01", (email), unique = true)

    // All columns as a tuple
    type TableElementTuple = (
      Option[User.Id], String, String,
      Option[Int], LocalDateTime, LocalDateTime
    )

    // The * projection of the table
    def * = (uid.?, name, email, phoneNumber, updatedAt, createdAt) <> (
      /** The bidirectional mappings : Tuple(table) => Model */
      (t: TableElementTuple) => {
        User(t._1, t._2, t._3, t._4, t._5, t._6)
      },
      /** The bidirectional mappings : Model => Tuple(table) */
      (v: TableElementType)  => User.unapply(v).map { t => (
        t._1, t._2, t._3,t._4,
        LocalDateTime.now(), t._6
      ) }
    )
  }
}