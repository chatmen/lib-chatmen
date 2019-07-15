package chatmen.core

package object persistence {

  val default = onMySQL

  object onMySQL {
    implicit lazy val driver = slick.jdbc.MySQLProfile
    object TweetRepository extends TweetRepository
  }
}
