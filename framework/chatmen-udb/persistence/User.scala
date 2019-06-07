package chatmen.udb.persistence

import scala.concurrent.Future
import slick.jdbc.JdbcProfile
import chatmen.udb.model.User
import ixias.persistence.SlickRepository

// ユーザ情報管理
//~~~~~~~~~~~~~~~~
case class UserRepository[P <: JdbcProfile]()(implicit val driver: P)
    extends SlickRepository[User.Id, User, P]
    with db.SlickColumnTypes[P]
    with db.SlickResourceProvider[P]
{
  import api._

  // --[ Methods ]--------------------------------------------------------------
  /**
   * ユーザ情報を取得する
   */
  def get(uid: Id): Future[Option[EntityEmbeddedId]] =
    RunDBAction(UserTable, "slave") { _
      .filter(_.uid   === uid)
      .filter(_.state === (User.Status.IS_ACTIVE: User.Status))
      .result.headOption
    }

  /**
   * メールアドレスからユーザ情報を取得する
   */
  def findByEmail(email: String): Future[Option[EntityEmbeddedId]] =
    RunDBAction(UserTable, "slave") { _
      .filter(_.email === email)
      .filter(_.state === (User.Status.IS_ACTIVE: User.Status))
      .result.headOption
    }

  /**
   * 電話番号からユーザ情報を取得する
   */
  def findByPhoneNumber(phone: User.PhoneNumber): Future[Option[EntityEmbeddedId]] =
    RunDBAction(UserTable, "slave") { _
      .filter(_.country === phone.country)
      .filter(_.phone   === phone.national)
      .filter(_.state   === (User.Status.IS_ACTIVE: User.Status))
      .result.headOption
    }

  // --[ Methods ]--------------------------------------------------------------
  /**
   * IDからユーザ情報を取得する
   */
  def filter(cursor: Cursor): Future[Seq[EntityEmbeddedId]] =
    RunDBAction(UserTable, "slave") { _
      .filter(_.state === (User.Status.IS_ACTIVE: User.Status))
      .sortBy(_.uid.desc)
      .seek(cursor)
      .result
    }

  /**
   * IDからユーザ情報を取得する
   */
  def filterByIds(uids: Seq[Id]): Future[Seq[EntityEmbeddedId]] =
    RunDBAction(UserTable, "slave") { _
      .filter(_.uid inSet uids)
      .filter(_.state === (User.Status.IS_ACTIVE: User.Status))
      .result
    }

  // --[ Methods ]--------------------------------------------------------------
  /**
   * ユーザ数を取得する
   */
  def count: Future[Int] =
    RunDBAction(UserTable, "slave") { _
      .filter(_.state === (User.Status.IS_ACTIVE: User.Status))
      .length.result
    }

  /**
   * メールアドレスが既に登録されていないかを確認する (退会ユーザ含む)
   */
  def existsEmail(email: String): Future[Boolean] = {
    RunDBAction(UserTable, "slave") { _
      .filter(_.email === email)
      .exists.result
    }
  }

  /**
   * ユーザ情報を保存する
   */
  def add(user: EntityWithNoId): Future[Id] =
    RunDBAction(UserTable) { slick =>
      slick returning slick.map(_.uid) += user.v
    }

  /**
   * ユーザ情報を更新する
   */
  def update(user: EntityEmbeddedId): Future[Option[EntityEmbeddedId]] =
    RunDBAction(UserTable) { slick =>
      val row = slick.filter(_.uid === user.id)
      for {
        old <- row.result.headOption
        _   <- old match {
          case None    => DBIO.successful(0)
          case Some(_) => row.update(user.v)
        }
      } yield old
    }

  /**
   * メールアドレスを保存する
   */
  def updateEmail(uid: Id, email: Option[String]): Future[Option[EntityEmbeddedId]] =
    RunDBAction(UserTable) { slick =>
      val row = slick.filter(_.uid === uid)
      for {
        old <- row.result.headOption
        _   <- old match {
          case None    => DBIO.successful(0)
          case Some(_) => row.map(_.email).update(email)
        }
      } yield old
    }

  /**
   * 電話番号を保存する
   */
  def updatePhoneNumber(uid: Id, phoneNumber: Option[User.PhoneNumber]): Future[Option[EntityEmbeddedId]] =
    RunDBAction(UserTable) { slick =>
      val row = slick.filter(_.uid === uid)
      for {
        old <- row.result.headOption
        _   <- old match {
          case None       => DBIO.successful(0)
          case Some(user) => row
              .map(c => (c.country, c.phone))
              .update(phoneNumber match {
                case None    => (None, None)
                case Some(v) => (Some(v.country), Some(v.national))
              })
        }
      } yield old
    }

  /**
   * ユーザのステータスを停止にする
   */
  def deactivate(uid: Id, state: User.Status): Future[Option[EntityEmbeddedId]] =
    Seq(
      User.Status.IS_INACTIVE,
      User.Status.BLOCKING,
      User.Status.WITHDRAWING
    ).contains(state) match {
      case false => Future.failed(new IllegalArgumentException)
      case true  => RunDBAction(UserTable) { slick =>
        val row = slick.filter(_.uid === uid)
        for {
          old <- row.result.headOption
          _   <- old match {
            case None    => DBIO.successful(0)
            case Some(_) => row.map(_.state).update(state)
          }
        } yield old
      }
    }

  // --[ Methods ]--------------------------------------------------------------
  @deprecated("unsupported operation", "2.0")
  def remove(uid: Id): Future[Option[EntityEmbeddedId]] =
    Future.failed(new UnsupportedOperationException)
}
