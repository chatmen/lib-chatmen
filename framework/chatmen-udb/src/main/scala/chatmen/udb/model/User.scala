package chatmen.udb.model

import ixias.model._
//import ixias.util.EnumStatus
import java.time.LocalDateTime

import User._
case class User(
  id:          Option[Id],                             // ユーザID
  name:        String,                                 // 名前
  email:       String,                                 // email
  phoneNumber: Option[Int]           = None,           // 携帯番号
  updatedAt:   LocalDateTime         = NOW,            // データ更新日
  createdAt:   LocalDateTime         = NOW             // データ作成日
) extends EntityModel[Id]

object User {
    // --[ エンティティ定義 ]-----------------------------------------------------
  val  Id         = the[Identity[Id]]
  type Id         = Long @@ User
  type WithNoId   = Entity.WithNoId   [Id, User]
  type EmbeddedId = Entity.EmbeddedId [Id, User]

    // --[ オブジェクトの生成 ]---------------------------------------------------
  object WithNoId{
    def apply(name: String, email : String): WithNoId =
      Entity.WithNoId {  User(None, name, email) }
  }
}