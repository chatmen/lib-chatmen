package chatmen.core.persistence

import chatmen.core.model.Tweet
import scala.concurrent.Future
import slick.jdbc.JdbcProfile
import ixias.persistence.SlickRepository

// ユーザ・パスワード管理
//~~~~~~~~~~~~~~~~~~~~~~~~
case class TweetRepository[P <: JdbcProfile]()(implicit val driver: P)
  extends SlickRepository[Tweet.Id, Tweet, P]
    with db.SlickColumnTypes[P]
    with db.SlickResourceProvider[P]
{
  import api._

  // --[ Methods ]--------------------------------------------------------------
  //ツイート情報を取得する
  def get(id: Id): Future[Option[EntityEmbeddedId]] =
    RunDBAction(TweetTable, "slave") { _
      .filter(_.id === id)
      .result.headOption
    }

  //ツイート情報を更新する
  def add(data: EntityWithNoId): Future[Id] =
    RunDBAction(TweetTable) { slick =>
      slick returning slick.map(_.id) += data.v
    }

  // --[ Methods ]--------------------------------------------------------------
  //ツイート情報を更新する
  def update(data: EntityEmbeddedId): Future[Option[EntityEmbeddedId]] =
    RunDBAction(TweetTable) { slick =>
      val row = slick.filter(_.id === data.id)
      for {
        old <- row.result.headOption
        _   <- old match {
          case None    => slick += data.v
          case Some(_) => row.update(data.v)
        }
      } yield old
    }

  //パスワード情報を削除する
  def remove(id: Id): Future[Option[EntityEmbeddedId]] =
    RunDBAction(TweetTable) { slick =>
      val row = slick.filter(_.id === id)
      for {
        old <- row.result.headOption
        _   <- old match {
          case None    => DBIO.successful(0)
          case Some(_) => row.delete
        }
      } yield old
    }
}
