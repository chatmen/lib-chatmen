// ///
// package chatmen.udb.persistence

// import scala.concurrent.Future
// import slick.jdbc.JdbcProfile
// import chatmen.udb.model.User
// import chatmen.udb.model.UserEachRelation
// import ixias.persistence.SlickRepository

// // フォロー情報管理
// //~~~~~~~~~~~~~~~~
// case class UserRepository[P <: JdbcProfile]()(implicit val driver: P)
//     extends SlickRepository[User.Id, User, P]
//     with db.SlickColumnTypes[P]
//     with db.SlickResourceProvider[P]
// {
//   import api._

//   /**
//    * フォローをしている人を取得する
//    */
//   def get(fromId: Option(User.Id)): Future[Option[EntityEmbeddedId]] =
//     RunDBAction(UserEachRelationTable, "slave") { _
//       fromId match{
//         Some(isFollow) => {
//           .filter(_.uid   === uid)
//             .result.headOption
//         }
//         None           => result
//       }
//     }

//   /**
//     * ユーザ情報を取得する
//     */
//   def getAll(): Future[Seq[EntityEmbeddedId]] =
//     RunDBAction(UserEachRelationTable, "slave") { _
//                                        .result
//     }

//   /**
//    * IDからユーザ情報を取得する
//    */
//   def filter(cursor: Cursor): Future[Seq[EntityEmbeddedId]] =
//     RunDBAction(UserEachRelationTable, "slave") { _
//       .sortBy(_.uid.desc)
//       .seek(cursor)
//       .result
//     }

//   /**
//    * メールアドレスが既に登録されていないかを確認する (退会ユーザ含む)
//    */
//   def existsEmail(email: String): Future[Boolean] = {
//     RunDBAction(UserEachRelationTable, "slave") { _
//       .filter(_.email === email)
//       .exists.result
//     }
//   }

//   /**
//    * ユーザ情報を追加する
//    */
//   def add(user: EntityWithNoId): Future[Id] =
//     RunDBAction(UserEachRelationTable) { slick =>
//       slick returning slick.map(_.uid) += user.v
//     }

//   /**
//    * ユーザ情報を更新する
//    */
//   def update(user: EntityEmbeddedId): Future[Option[EntityEmbeddedId]] =
//     RunDBAction(UserEachRelationTable) { slick =>
//       val row = slick.filter(_.uid === user.id)
//       for {
//         old <- row.result.headOption
//         _   <- old match {
//           case None    => DBIO.successful(0)
//           case Some(_) => row.update(user.v)
//         }
//       } yield old
//     }

//   def remove(uid: Id): Future[Option[EntityEmbeddedId]] =
//     Future.failed(new UnsupportedOperationException)
// }
