package chatmen.udb.persistence.db

import slick.jdbc.JdbcProfile
import chatmen.udb.model._

trait SlickColumnTypes[P <: JdbcProfile] {
  implicit val driver: P
  import driver.api._

  // -- [ ID定義 ] -------------------------------------------------------------
  implicit val udbIdT01 = MappedColumnType.base[User.Id,                    Long](id => id, User.Id(_))
}