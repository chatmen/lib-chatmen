package chatmen.core.persistence.db

import slick.jdbc.JdbcProfile
import chatmen.udb.model._
import chatmen.core.model._

trait SlickColumnTypes[P <: JdbcProfile] {
  implicit val driver: P
  import driver.api._

  // -- [ ID定義 ] -------------------------------------------------------------
  implicit val udbIdT01 = MappedColumnType.base[Tweet.Id,                    Long](id => id, Tweet.Id(_))
  implicit val udbIdT02 = MappedColumnType.base[User.Id,                     Long](id => id, User.Id(_))
  //implicit val udbIdT03 = MappedColumnType.base[Favorite.Id,                 Long](id => id, Favorite.Id(_))
  //implicit val udbIdT04 = MappedColumnType.base[ReTweet.Id,                  Long](id => id, ReTweet.Id(_))
}
