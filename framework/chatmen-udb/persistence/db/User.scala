package chatmen-udb.persistence.db

import java.time.LocalDateTime
import slick.jdbc.JdbcProfile
import ixias.persistence.model.Table
import chatmen-udb.model.User


// テーブル定義
//~~~~~~~~~~~~~~
case class UserTable[P <: JdbcProfile]()(implicit val driver: P)
    extends Table[User, P] with SlickColumnTypes[P] {
  import api._

  // --[ DNS定義 ] -------------------------------------------------------------
  lazy val dsn = Map(
    "master" -> DataSourceName("ixias.db.mysql://master/nextbeat_udb"),
    "slave"  -> DataSourceName("ixias.db.mysql://slave/nextbeat_udb")
  )

  // --[ クエリー定義 ] --------------------------------------------------------
  class Query extends BasicQuery(new Table(_)) {}
  lazy val query = new Query

  // --[ テーブル定義 ] --------------------------------------------------------
  class Table(tag: Tag) extends BasicTable(tag, "nextbeat_user") {

    // Columns
    /* @1 */ def uid         = column[User.Id]        ("id",          O.UInt64, O.PrimaryKey, O.AutoInc)
    /* @2 */ def name        = column[String]         ("name",        O.Utf8Char255)
    /* @3 */ def email       = column[Option[String]] ("email",       O.AsciiChar255)
    /* @3 */ def birthday    = column[Option[Int]]    ("country",     O.Int8)
    /* @5 */ def phoneNumber = column[Option[String]] ("phoneNumber", O.AsciiChar16)
    /* @6 */ def updatedAt   = column[LocalDateTime]  ("updated_at",  O.TsCurrent)
    /* @7 */ def createdAt   = column[LocalDateTime]  ("created_at",  O.Ts)

    // Indexes
    def ukey01 = index("ukey01", (email), unique = true)

    // All columns as a tuple
    type TableElementTuple = (
      Option[User.Id], Option[String], Option[String],
      LocalDate, Option[Int], LocalDateTime, LocalDateTime
    )

    // The * projection of the table
    def * = (uid.?, name, email, birthday, phoneNumber, updatedAt, createdAt) <> (
      /** The bidirectional mappings : Tuple(table) => Model */
      (t: TableElementTuple) => {
        val phoneNumver = (t._4, t._5) match {
          case (Some(v1), Some(v2)) => User.PhoneNumber(v1, v2).toOption
          case _                    => None
        }
        User(t._1, t._2, t._3, phone, t._6, t._7)
      },
      /** The bidirectional mappings : Model => Tuple(table) */
      (v: TableElementType)  => User.unapply(v).map { t => (
        t._1, t._2, t._3,
        t._4.map(_.country), t._4.map(_.national), LocalDateTime.now(), t._6
      ) }
    )
  }
}
