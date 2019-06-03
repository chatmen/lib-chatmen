package chatmen.udb.model

import java.time.{LocalDate, LocalDateTime}
import ixias.model._
import User._

case class UserPassword(
  id:        Option[User.Id],      // UserID 
  hash:      String,
  updatedAt: LocalDateTime = NOW,  // データ更新日
  createdAt: LocalDateTime = NOW   // データ作成日
)extends EntityModel[User.Id] {

  /** パスワードの検証を行う */
  def verify(password: String) =
    Password.verify(password, hash)
}

object UserPassword{
// --[ エンティティ定義 ]-----------------------------------------------------
  type WithNoId   = Entity.WithNoId   [User.Id, UserPassword]
  type EmbeddedId = Entity.EmbeddedId [User.Id, UserPassword]

  // --[ オブジェクトの生成 ]---------------------------------------------------
  def apply(uid: User.Id, password: String): EmbeddedId =
    Entity.EmbeddedId { new UserPassword(Some(uid), hash(password))}

  // --[ パスワード文字列操作 ]-------------------------------------------------
  /** PBKDF2とランダムSaltを利用してパスワードのハッシュ値を生成する */
  def hash(password: String): String =
    ixias.security.PBKDF2.hash(password)

  /** パスワードの検証を行う */
  def verify(password: String, hash: String): Boolean =
    ixias.security.PBKDF2.compare(password, hash)
}
