package chatmen.udb.persistence

import scala.concurrent.Future
import slick.jdbc.JdbcProfile

import nextbeat.udb.model.{ User, UserBlock }
import ixias.persistence.SlickRepository

// ユーザ・パスワード管理
//~~~~~~~~~~~~~~~~~~~~~~~~
case class UserBlockRepository[P <: JdbcProfile]()(implicit val driver: P)
    extends SlickRepository[User.Id, UserPassword, P]
    with db.SlickColumnTypes[P]
    with db.SlickResourceProvider[P]
{
  import api._

  // --[ Methods ]--------------------------------------------------------------
  /**
   * ブロック情報を取得する
   */
  def get(uid: Id): Future[Option[EntityEmbeddedId]] =
    RunDBAction(UserBlockTable, "slave") { _
      .filter(_.uid === uid)
      .result.headOption
    }

  // --[ Methods ]--------------------------------------------------------------
  /**
   * ブロック状態を更新する
   */
  def update(user: EntityEmbeddedId): Future[Option[EntityEmbeddedId]] =
    RunDBAction(UserBlockTable) { slick =>
      val row = slick.filter(_.uid === user.id)
      for {
        old <- row.result.headOption
        _   <- old match {
          case None    => slick += passwd.v
          case Some(_) => row.update(user.v)
        }
      } yield old
    }

  /**
   * ブロック情報を削除する
   */
  def remove(uid: Id): Future[Option[EntityEmbeddedId]] =
    RunDBAction(UserBlockTable) { slick =>
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
