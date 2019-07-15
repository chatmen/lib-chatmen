/* package chatmen.core.persistence

import scala.concurrent.Future
import slick.jdbc.JdbcProfile
import chatmen.core.model.Favorite
import ixias.persistence.SlickRepository

// ユーザ・パスワード管理
//~~~~~~~~~~~~~~~~~~~~~~~~
case class FavoriteRepository[P <: JdbcProfile]()(implicit val driver: P)
  extends SlickRepository[Tweet.Id, Favorite, P] //TweetではなくReTweet.Id?
    with db.SlickColumnTypes[P]
    with db.SlickResourceProvider[P]
{
  import api._

  // --[ Methods ]--------------------------------------------------------------
  // パスワード情報を取得する
  def get(id: Id): Future[Option[EntityEmbeddedId]] =
    RunDBAction(TweetTable, "slave") { _
      .filter(_.id === id)
      .result.headOption
    }

  // --[ Methods ]--------------------------------------------------------------
  // お気に入りの数を更新する
  def update(favorite: EntityEmbeddedId): Future[Option[EntityEmbeddedId]] =
    RunDBAction(FavoriteTable) { slick =>
      val row = slick.filter(_.id === retweet.id)
      for {
        old <- row.result.headOption
        _   <- old match {
          case None    => slick += retweet.v
          case Some(_) => row.update(retweet.v)
        }
      } yield old
    }

  // パスワード情報を削除する
  def remove(id: Id): Future[Option[EntityEmbeddedId]] =
    RunDBAction(ReTweetTable) { slick =>
      val row = slick.filter(_.id === id)
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
  def add(retweet: EntityWithNoId): Future[Id] =
    Future.failed(new UnsupportedOperationException)
}


 */