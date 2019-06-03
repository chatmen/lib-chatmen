package chatmen.udb.model

import ixias.model._
import ixias.util.EnumStatus
import java.time.{LocalDate, LocalDateTime}

case class User(
  id:          Option[String],                         // ユーザID 
  name:        Option[String],                         // 名前
  email:       Option[String],
  birthday:    LocalDate,
  phoneNumber: Option[Int],
  updatedAt:   LocalDateTime         = NOW,              // データ更新日
  createdAt:   LocalDateTime         = NOW               // データ作成日
)extends EntityModel[Id]

object User{
    // --[ エンティティ定義 ]-----------------------------------------------------
  val  Id         = the[Identity[Id]]
  type Id         = Long @@ User
  type WithNoId   = Entity.WithNoId   [Id, User]
  type EmbeddedId = Entity.EmbeddedId [Id, User]

    // --[ オブジェクトの生成 ]---------------------------------------------------
  object WithNoId{
    def apply(): WithNoId =
      Entity.WithNoId { User(None) }
  }
}
