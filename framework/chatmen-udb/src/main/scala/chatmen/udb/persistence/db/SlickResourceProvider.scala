package chatmen.udb.persistence.db

import slick.jdbc.JdbcProfile

trait SlickResourceProvider[P <: JdbcProfile] {

  implicit val driver: P

  // --[ テーブル定義 ] --------------------------------------------------------
  object UserTable                  extends UserTable
  object UserPasswordTable          extends UserPasswordTable

  // --[ 全てのテーブル定義 ] --------------------------------------------------
  lazy val AllTables = Seq(
    UserTable,
    UserPasswordTable
  )
}
