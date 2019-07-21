package chatmen.core.persistence

import chatmen.core.model.Tweet
import scala.concurrent.Future
import slick.jdbc.JdbcProfile
import ixias.persistence.SlickRepository
import chatmen.udb.model.User

// ユーザ・パスワード管理
//~~~~~~~~~~~~~~~~~~~~~~~~
case class TweetRepository[P <: JdbcProfile]()(implicit val driver: P)
  extends SlickRepository[Tweet.Id, Tweet, P]
    with db.SlickColumnTypes[P]
    with db.SlickResourceProvider[P]
{
  import api._

  // --[ Methods ]--------------------------------------------------------------
  //ツイート情報を取得する(Tweetの全項目の情報)
  def get(id: Tweet.Id): Future[Option[EntityEmbeddedId]] =
    RunDBAction(TweetTable, "slave") { _
      .filter(_.id === id)
      .result.headOption
    }
  //UserIdから対象の1件のTweetの全データをとる
  def filterByUserId(uid: User.Id): Future[Seq[EntityEmbeddedId]] =
    RunDBAction(TweetTable, "slave") { _
      .filter(_.uid === uid)
      .result
    }
  //UserIdから対象の全てのTweetの全データが入ったものをリストでとる
  def filterByUserIds(uid: Seq[User.Id]): Future[Seq[EntityEmbeddedId]] =
    RunDBAction(TweetTable, "slave") { _
                                        .filter(_.uid inSet uid)
                                        .result
    }

  // def getAll(): Future[Option[EntityEmbeddedId]] =
  //   RunDBAction(TweetTable, "slave") { _
  //     .result
  //   }

  //ツイート情報を更新する(ツイートをする)
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

  //ツイート情報を削除する
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
