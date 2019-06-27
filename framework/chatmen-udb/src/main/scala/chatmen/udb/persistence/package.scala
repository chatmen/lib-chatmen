
package chatmen.udb

package object persistence {

  val default = onMySQL

  object onMySQL {
    implicit lazy val driver = slick.jdbc.MySQLProfile
    object UserRepository extends UserRepository
  }
}