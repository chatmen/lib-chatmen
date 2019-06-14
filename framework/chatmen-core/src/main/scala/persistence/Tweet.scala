package chatmen.core.persistence

import chatmen.core.model.Tweet

import scala.concurrent.Future
import slick.jdbc.JdbcProfile
import chatmen.udb.model.User
import ixias.persistence.SlickRepository

// ユーザ・パスワード管理
//~~~~~~~~~~~~~~~~~~~~~~~~
case class UserPasswordRepository[P <: JdbcProfile]()(implicit val driver: P)
  extends SlickRepository[User.Id, Tweet, P]
    with db.SlickColumnTypes[P]
    with db.SlickResourceProvider[P]
{
  import api._

  // --[ Methods ]--------------------------------------------------------------
  /**
    * パスワード情報を取得する
    */
  def get(uid: Id): Future[Option[EntityEmbeddedId]] =
    RunDBAction(UserPasswordTable, "slave") { _
      .filter(_.uid === uid)
      .result.headOption
    }

  // --[ Methods ]--------------------------------------------------------------
  /**
    * パスワード情報を更新する
    */
  def update(passwd: EntityEmbeddedId): Future[Option[EntityEmbeddedId]] =
    RunDBAction(UserPasswordTable) { slick =>
      val row = slick.filter(_.uid === passwd.id)
      for {
        old <- row.result.headOption
        _   <- old match {
          case None    => slick += passwd.v
          case Some(_) => row.update(passwd.v)
        }
      } yield old
    }

  /**
    * パスワード情報を削除する
    */
  def remove(uid: Id): Future[Option[EntityEmbeddedId]] =
    RunDBAction(UserPasswordTable) { slick =>
      val row = slick.filter(_.uid === uid)
      for {
        old <- row.result.headOption
        _   <- old match {
          case None    => DBIO.successful(0)
          case Some(_) => row.delete
        }
      } yield old
    }

  // --[ Methods ]--------------------------------------------------------------
  @deprecated("use update method", "2.0")
  def add(passwd: EntityWithNoId): Future[Id] =
    Future.failed(new UnsupportedOperationException)
}
