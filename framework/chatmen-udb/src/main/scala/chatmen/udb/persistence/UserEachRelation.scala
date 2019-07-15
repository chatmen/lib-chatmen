package chatmen.udb.persistence

import scala.concurrent.Future
import slick.jdbc.JdbcProfile
import chatmen.udb.model.User
import chatmen.udb.model.UserEachRelation
import ixias.persistence.SlickRepository

// フォロー情報管理
//~~~~~~~~~~~~~~~~
case class UserEachRelationRepository[P <: JdbcProfile]()(implicit val driver: P)
    extends SlickRepository[UserEachRelation.Id, UserEachRelation, P]
    with db.SlickColumnTypes[P]
    with db.SlickResourceProvider[P]
{
  import api._

  //フォロー・フォロワーの情報を取得する
  def get(uid: Id): Future[Option[EntityEmbeddedId]] =
    RunDBAction(UserEachRelationTable, "slave") { _
                                               .filter(_.relationid === uid)
                                               .result.headOption
    }

  //ユーザーのフォロー情報を取得
  def getFollowsOfUser(uid: User.Id): Future[Seq[User.Id]] = {
    RunDBAction(UserEachRelationTable,"slave") { _
                                                .filter(_.fromid === uid)
                                                .map(x => x.targetid)
                                                .result
    }
  }

  //ユーザーのフォロワー情報を取得
  def getFollowersOfUser(uid: User.Id): Future[Seq[User.Id]] = {
    RunDBAction(UserEachRelationTable,"slave") { _
                                                .filter(_.targetid === uid)
                                                .map(x => x.fromid)
                                                .result
    }
  }
    //フォロー・フォロワー情報を追加
  def add(user: EntityWithNoId): Future[Id] =
    RunDBAction(UserEachRelationTable) { slick =>
      slick returning slick.map(_.relationid) += user.v
    }

  def update(user: EntityEmbeddedId): Future[Option[EntityEmbeddedId]] =
    RunDBAction(UserEachRelationTable) { slick =>
      val row = slick.filter(_.relationid === user.id)
      for {
        old <- row.result.headOption
        _   <- old match {
          case None    => DBIO.successful(0)
          case Some(_) => row.update(user.v)
        }
      } yield old
    }

  def remove(relationid: Id): Future[Option[EntityEmbeddedId]] =
    Future.failed(new UnsupportedOperationException)
  // /**
  //  * IDからユーザ情報を取得する
  //  */
  // def filter(cursor: Cursor): Future[Seq[EntityEmbeddedId]] =
  //   RunDBAction(UserEachRelationTable, "slave") { _
  //     .sortBy(_.uid.desc)
  //     .seek(cursor)
  //     .result
  //   }

}
